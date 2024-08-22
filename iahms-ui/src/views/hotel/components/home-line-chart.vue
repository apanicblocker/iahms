<script>
import { markRaw } from 'vue'

export default {
  name: 'HomeLineChart',
  props: {
    title: {
      type: String,
      default: '请输入标题'
    },
    xAxisData: {
      type: Array,
      required: true
    },
    seriesData: {
      type: Array,
      required: true
    },
    formatter: {
      type: String,
      default: '{value}'
    }
  },
  data() {
    return {
      chart: null,
      // 图表配置
      option: {
        notMerge: true,
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '3%',
          bottom: '4%',
          top: '4%',
          containLabel: true
        },
        xAxis: {
          boundaryGap: false,
          data: this.xAxisData
        },
        yAxis: {
          offset: 16,
          type: 'value',
          min: 0,
          splitNumber: 5,
          position: 'left',
          axisLabel: {
            formatter: this.formatter
          }
        },
        series: [
          {
            name: '入住率',
            type: 'line',
            data: this.seriesData,
            smooth: true,
            lineStyle: {
              width: 5,
              shadowColor: 'rgba(0,0,0,0.3)',
              shadowBlur: 16,
              shadowOffsetY: 16
            },
            areaStyle: {
              color: 'rgba(255,255,255,0.25)',
            }
          },
        ]
      }
    }
  },
  mounted() {
    window.addEventListener('resize', this.handleResize);
    this.$nextTick(() => {
      this.initChart()
    })
  },
  unmounted() {
    window.removeEventListener('resize', this.handleResize);
  },
  methods: {
    initChart() {
      this.chart = markRaw(this.$echarts.init(this.$el.querySelector('#chart')))
      if (this.option && typeof this.option === 'object') {
        this.chart.setOption(this.option)
      }
    },
    handleResize() {
      this.chart.resize()
    }
  }
}
</script>

<template>
  <div class="home-line-chart">
    <div class="title">{{ title }}</div>
    <div id="chart"></div>
  </div>
</template>

<style scoped>
.home-line-chart {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: var(--base-c-bg-1);

  .title {
    padding: 16px;
    border-bottom: 2px solid var(--base-c-bg-2);
    text-align: left;
    font-size: 16px;
    font-weight: 700;
    color: var(--base-c-text-1);
  }

  #chart {
    flex-grow: 1;
    padding: 16px;
  }
}
</style>
