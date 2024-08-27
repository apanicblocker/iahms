<script>

export default {
  name: 'ReportPieChart',
  props: {
    chartData: {
      type: Array,
      default: () => [
        { value: 1048, name: 'Search Engine' },
        { value: 735, name: 'Direct' },
        { value: 580, name: 'Email' },
        { value: 484, name: 'Union Ads' },
        { value: 300, name: 'Video Ads' }
      ]
    }
  },
  data() {
    return {
      chart: null,
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
      let chartData = this.chartData
      let chartOption = {
        title: {
          text: '总收入\n\n¥120.00',
          textAlign: 'center',
          left: '120px',
          top: 'center',
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
              ' ' + '￥'+ params.value.toFixed(2) +
              ' ' + params.percent + '%'
          }
        },
        legend: {
          show: true,
          orient: 'vertical',
          top: 'center',
          left: '290px',
          formatter: function(name) {
            let total = chartData.reduce((sum, item) => sum + item.value, 0)
            console.log(total);
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
  /* width: 250px; */
  height: 250px;
  padding: 40px;
}
</style>
