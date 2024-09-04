<script>

export default {
  name: 'ReportPieChart',
  props: {
    titleText: {
      type: String,
      default: '请输入标题'
    },
    chartData: {
      type: Array,
      default: () => [
        { value: 1048, name: 'Test Search' },
        { value: 735, name: 'Test Direct' },
        { value: 580, name: 'Test Email' },
        { value: 484, name: 'Test Union Ads' },
        { value: 300, name: 'Test Video Ads' }
      ]
    },
  },
  data() {
    return {
      chart: null,
      chartTitleColor: '',
    }
  },
  watch: {
    '$store.state.common.theme': {
      handler(newVal) {
        this.adjustChartTitleColor('--base-c-text-1')
        this.setChartOption()
      },
      deep: true,
    }
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
    this.$nextTick(() => {
      this.initChart()
      this.setChartOption()
    })
  },
  methods: {
    initChart() {
      this.chart = markRaw(this.$echarts.init(this.$el))
    },
    setChartOption() {
      // 饼图中间标题的 颜色（为了自适应主题，只能这样麻烦了）
      this.adjustChartTitleColor('--base-c-text-1')
      let chartData = this.chartData
      // 饼图中间标题的 总数
      let total = chartData.reduce((sum, item) => sum + item.value, 0)
      let chartOption = {
        title: {
          text: this.titleText + '\n\n¥' + total,
          textAlign: 'center',
          left: '120px',
          top: '100px',
          textStyle: {
            color: this.chartTitleColor,
          }
        },
        tooltip: {
          trigger: 'item',
          // valueFormatter: (value) => '¥' + value.toFixed(2),
          textStyle: { color: 'var(--base-c-text-1)' },
          backgroundColor: 'var(--base-c-bg-1)',
          formatter: function(params) {
            return params.name +
              '<br/>' +
              '<div style="' +
              '   display:inline-block;' +
              '   width:10px;' +
              '   height:10px;' +
              '   margin-right:5px;' +
              '   border-radius:50%;' +
              '   background-color:'+params.color + ';">' +
              '</div>' +
              ' ¥' + params.value.toFixed(2) +
              ' | ' + params.percent + '%'
          }
        },
        legend: {
          orient: 'vertical',
          left: '280px',
          bottom: 'center',
          textStyle: {
            color: 'var(--base-c-text-1)'
          },
          formatter: function(name) {
            let value = chartData.find(item => item.name === name).value;
            let percentAge = ((value / total) * 100).toFixed(2) + '%'
            return name + '\t\t\t\t\t\t\t\t' + percentAge
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['66%', '90%'],
            center: ['125px', 'center'],
            label: { show: false },
            data: chartData,
          }
        ],
      }
      this.chart.setOption(chartOption)
    },
    adjustChartTitleColor(cssName) {
      // 获取根元素的计算样式
      let element = document.getElementById('app')
      let styles = window.getComputedStyle(element);
      // 获取 CSS 变量值
      this.chartTitleColor = styles.getPropertyValue(cssName);
    },
    handleResize() {
      this.chart.resize()
    }
  },
}
</script>

<template>
  <div class="report-pie-chart">
  </div>
</template>

<style scoped>
.report-pie-chart {
  box-sizing: content-box;
  min-width: 465px;
  min-height: 250px;
  padding: 40px;
}
</style>
