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
    legendPosition: {
      type: String,
      default: 'right'
    }
  },
  data() {
    return {
      chart: null,
      legend: {},
      rightLegend: {
        orient: 'vertical',
        left: '280px',
        bottom: 'center',
      },
      downLegend: {
        orient: 'horizontal',
        left: '0',
        bottom: '0',
      }
    }
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
    this.$nextTick(() => {
      this.initChart()
    })
  },
  methods: {
    initChart() {
      if (this.legendPosition === 'right') {
        this.legend = this.rightLegend
      } else {
        this.legend = this.downLegend
      }
      let chartData = this.chartData
      let total = chartData.reduce((sum, item) => sum + item.value, 0)
      let chartOption = {
        title: {
          text: this.titleText + '\n\n¥' + total,
          textAlign: 'center',
          left: '120px',
          top: 'center',
        },
        grip: {
          bottom: '20px',
          containLabel: true
        },
        tooltip: {
          trigger: 'item',
          // valueFormatter: (value) => '¥' + value.toFixed(2),
          formatter: function(params) {
            return params.name +
              '<br/>' +
              '<div style="' +
              '   display:inline-block;' +
              '   width:10px;' +
              '   height:10px;' +
              '   margin-right: 5px;' +
              '   border-radius:50%;' +
              '   background-color:'+params.color + ';">' +
              '</div>' +
              ' | ' + '¥' + params.value.toFixed(2) +
              ' | ' + params.percent + '%'
          }
        },
        legend: {
          show: true,
          orient: this.legend.orient,
          left: this.legend.left,
          bottom: this.legend.bottom,
          formatter: function(name) {
            let value = chartData.find(item => item.name === name).value;
            let percentAge = ((value / total) * 100).toFixed(2) + '%'
            return name + '\t\t\t\t\t\t\t\t' + percentAge
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['56%', '80%'],
            center: ['125px', 'center'],
            label: { show: false },
            data: chartData,
          }
        ],
      }
      this.chart = markRaw(this.$echarts.init(this.$el))
      this.chart.setOption(chartOption)
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
