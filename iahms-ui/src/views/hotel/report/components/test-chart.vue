<script>
export default {
  name: 'TestChart',
  data() {
    return {
      chart: null,
      chartOption: {
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          show: true,
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    }
  },
  mounted() {
    this.chart = markRaw(this.$echarts.init(this.$el))
    this.chart.setOption(this.chartOption)
  }
}
</script>

<template>
  <div class="test-chart"></div>
</template>

<style scoped>
.test-chart {
  
  height: 500px;
}
</style>
