<script>
export default {
  name: 'ReportLineChart',
  props: {
    inputData: {
      type: Array,
      default: () => [
        { name: '入住', data: [120, 132, 101, 134, 90, 230, 210] },
        { name: '退房', data: [220, 182, 191, 234, 290, 330, 310] },
        { name: '在住', data: [150, 232, 201, 154, 190, 330, 410] },
      ]
    },
    xAxisData: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      chart: null,
      radioLabel: ref(''),
      series: {name: '', data: []},
    }
  },
  watch: {
    radioLabel: {
      handler(newVal) {
        for (let i = 0; i < this.inputData.length; i++) {
          if (this.inputData[i].name === newVal) {
            this.series = Object.assign(this.inputData[i])
            if (this.chart) this.applyChartOption()
            break
          }
        }
      },
      deep: true
    }
  },
  mounted() {
    window.addEventListener('resize', this.handleResize)
    this.$nextTick(() => {
      this.initChart()
      // 初始化单选框更新线图
      this.radioLabel = this.inputData[0]?.name
    })
  },
  methods: {
    initChart() {
      this.chart = markRaw(this.$echarts.init(this.$refs.chart))
      this.applyChartOption()
    },
    applyChartOption() {
      let seriesName = this.series.name
      let seriesData = this.series.data
      let xAxisData = []
      if (this.xAxisData.length === 0) {
        xAxisData = new Array(this.series.data.length).fill('Invalid Date').slice()
      } else {
        xAxisData = this.xAxisData.slice()
      }
      let chartOption = {
        grid: {
          left: '3%',
          right: '3%',
          bottom: '4%',
          top: '4%',
          containLabel: true
        },
        xAxis: {
          boundaryGap: false,
          data: xAxisData,
        },
        yAxis: {
          offset: 16,
          type: 'value',
          min: 0,
          splitNumber: 5,
          position: 'left',
        },
        tooltip: {
          trigger: 'axis',
          formatter: function(params) {
            // 搞不懂为什么是 params[0] 而不是直接使用 params
            return params[0].name + '<br />' + params[0].seriesName +
            '<span style="' +
            ' color: var(--base-c-accent-2);' +
            ' margin-left: 8px;' +
            '">' + '¥' + params[0].value + '</span>'
          },
        },
        series: [
          {
            name: seriesName,
            type: 'line',
            data: seriesData,
            smooth: true,
            lineStyle: {
              width: 5,
              shadowColor: 'rgba(0,0,0,0.2)',
              shadowBlur: 16,
              shadowOffsetY: 16
            },
            areaStyle: {
              color: 'rgba(255,255,255,0.2)',
              shadowColor: 'rgba(0,0,0,0.2)',
              shadowBlur: 16,
            }
          },
        ]
      }
      this.chart.setOption(chartOption)
    },

    handleResize() {
      this.chart.resize()
    },
  }
}
</script>

<template>
  <div class="report-line-chart">
    <el-radio-group
      class="radio-group"
      v-model="radioLabel"
      text-color="var(--base-c-bg-1)"
      fill="var(--base-c-accent-1)"
      style="
        --el-fill-color-blank: transparent;
        --el-color-primary: var(--base-c-accent-1);
        --el-text-color-regular: var(--base-c-text-1);
      "
    >
      <el-radio-button
        v-for="(item, index) in inputData"
        :key="index"
        :label="item.name"
        :value="item.name"
      ></el-radio-button>
    </el-radio-group>
    <div ref="chart" id="chart"></div>
  </div>
</template>

<style scoped>
.report-line-chart {
  box-sizing: content-box;
  padding: 24px;
}

.radio-group {
  
}

#chart {
  min-height: 300px;
  margin-top: 40px;
}
</style>
