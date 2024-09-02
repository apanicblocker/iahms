<script>

export default {
  name: 'ReportPieChartV1',
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
      total: ref(0)
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
      this.total = chartData.reduce((sum, item) => sum + item.value, 0)
      let total = this.total
      let chartOption = {
        // title: {
        //   text: this.titleText + '\n\n¥' + total,
        //   left: 'center',
        //   top: '110px',
        // },
        tooltip: {
          trigger: 'item',
          textStyle: { color: 'var(--base-c-text-1)' },
          backgroundColor: 'var(--base-c-bg-1)',
          // valueFormatter: (value) => '¥' + value.toFixed(2),
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
              ' | ' + '¥' + params.value.toFixed(2) +
              ' | ' + params.percent + '%'
          }
        },
        legend: {
          left: 'center',
          bottom: '0',
          textStyle: { color: 'var(--base-c-text-1)' },
          formatter: function(name) {
            let value = chartData.find(item => item.name === name).value;
            let percentAge = ((value / total) * 100).toFixed(2) + '%'
            return name + '（ '+ percentAge +' ）'
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['90px', '125px'],
            center: ['center', '130px'],
            label: { show: false },
            data: chartData,
          }
        ],
      }
      this.chart = markRaw(this.$echarts.init(this.$refs.chart))
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
    <div class="title" style="text-align: center;">
      <div class="text">{{ titleText }}</div>
      <br/>
      <div class="number">{{ '¥' + total }}</div>
    </div>
    <div class="chart" ref="chart"></div>
  </div>
</template>

<style scoped>
.report-pie-chart {
  position: relative;

  .title {
    position: absolute;
    top: 145px;
    left: 50%;
    transform: translate(-50%, 0);
    .number { font-weight: bold; }
  }

  .chart {
    box-sizing: content-box;
    min-width: 465px;
    height: 342px;
    padding: 40px;
  }
}
</style>
