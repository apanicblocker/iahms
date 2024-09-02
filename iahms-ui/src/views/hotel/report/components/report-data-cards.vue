<script>
export default {
  name: 'ReportDataCards',
  props: {
    useHead: {
      type: Boolean,
      default: true,
    },
    dataList: {
      type: Array,
      default: () => [
        {
          title: '住宿总营业额',
          value: 1200.00,
          seriesData: [120, 132, 101, 134, 90, 230, 210],
          tips: 10,
        },
        {
          icon: '',
          title: '客房收入',
          value: 1200.00,
          tips: 10,
        },
        {
          icon: '',
          title: '餐饮收入',
          value: 1200.00,
          tips: 10,
        },
        {
          icon: '',
          title: '其他收入',
          value: 1200.00,
          tips: 10,
        }
      ]
    },
  },
  data() {
    return {
      chart: null,
      headData: {},
      bodyData: [],
    }
  },
  mounted() {
    window.addEventListener('resize', this.handleResize)
    this.$nextTick(() => {
      this.initChart()
    })
  },
  methods: {
    initChart() {
      // 判断是否启用头部数据（就是第一个数据是特殊渲染的）
      if (this.useHead) {
        this.bodyData = this.dataList.slice(1)
        this.headData = this.dataList[0]
        this.chart = markRaw(this.$echarts.init(this.$refs.chart))
        this.applyChartOption()
      } else {
        this.bodyData = this.dataList
      }
    },
    applyChartOption() {
      let chartOption = {
        grid: {
          left: '0',
          right: '0',
          top: '0',
          bottom: '0',
        },
        xAxis: {
          show: true,
          data: new Array(this.headData.seriesData.length).fill(''),
          boundaryGap: false,
          axisLine: {
            lineStyle: {
              color: 'transparent' // 设置X轴线样式为透明
            }
          }
        },
        yAxis: {
          show: false,
        },
        series: [
          {
            type: 'line',
            data: this.headData.seriesData,
            showSymbol: false, // 隐藏所有数据点
            smooth: true,
            lineStyle: {
              color: 'red',
              width: 2,
              shadowColor: 'rgba(255,255,255,0.1)',
              shadowBlur: 4,
              shadowOffsetX: 2,
              shadowOffsetY: 4,
            },
          }
        ]
      }
      if (this.chart) {
        this.chart.setOption(chartOption)
      }
    },
    handleResize() {
      this.chart.resize()
    }
  }
}
</script>

<template>
  <div class="report-data-cards">
    <div class="header-card" v-if="headData.length !== 0">
      <div class="title">{{ headData.title }}</div>
      <div class="content">
        <div class="value">￥{{ headData.value }}</div>
        <div class="chart" ref="chart"></div>
      </div>
      <div class="tips">
        <span>日环比</span>
        <el-icon><TopRight /></el-icon>
        <span>{{ headData.tips }}%</span>
      </div>
    </div>
    <div
      class="card-item"
      v-for="(item, index) in bodyData"
      :key="index"
    >
      <div class="main">
        <div class="icon">
          <el-icon><PictureFilled style="color: red;"/></el-icon>
        </div>
        <div class="content">
          <div class="title">{{ item.title }}</div>
          <div class="value">￥{{ item.value }}</div>
        </div>
      </div>
      <div class="tips">占比 {{ item.tips }}%</div>
    </div>
  </div>
</template>

<style scoped>
.report-data-cards {
  display: flex;
  padding: 24px;

  .title {
    font-size: 14px;
    filter: brightness(0.8);
  }
  .tips {
    margin-top: 8px;
    font-size: 12px;
    color: var(--base-c-text-3);
  }
  > *:not(:last-child) {
    margin-right: 24px;
  }
}

.header-card {
  flex-grow: 1;

  .content {
    display: flex;
    align-items: center;
    margin-top: 8px;
    border-right: 1px solid var(--base-c-bg-3);

    .value {
      font-size: 32px;
      font-weight: bold;
    }
    .chart {
      width: 120px;
      height: 30px;
      margin-left: 24px;
    }
  }
}

.card-item {
  flex-grow: 1;

  &:not(:last-child) {
    .value {
      border-right: 1px solid var(--base-c-bg-3);
    }
  }

  .value {
    margin-top: 8px;
    font-size: 26px;
    font-weight: 700;
  }
  .tips {
    margin: 4px 0 0 72px;
  }
}

.main {
  display: flex;
  align-items: center;

  .icon {
    text-align: center;
    line-height: 48px;
    width: 48px;
    height: 48px;
  }
  .content {
    flex-grow: 1;
    margin-left: 24px;
  }
}

</style>
