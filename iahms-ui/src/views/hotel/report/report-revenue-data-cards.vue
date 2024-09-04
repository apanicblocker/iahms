<script lang="ts" setup>

import echarts from '@/lib/echarts'
import ReportDataCard from './components/report-data-card.vue'

interface CardItem {
  icon?: string,
  title: string,
  value: number | string,
  tips?: number | string,
}

const props = defineProps({
  useHead: {
    type: Boolean,
    default: false,
  },
  dataList: {
    type: Array as PropType<CardItem[]>,
    default: () => [],
  },
  otherParams: {
    type: Object,
    default: () => {}
  }
})

const chart: echarts = ref(null)
const chartRef = ref(null)
const headData: CardItem = reactive({title: '', value: ''})
const bodyData: CardItem[] = reactive([])
const seriesData: number[] = reactive([])

onMounted(() => {
  if (props.dataList.length !== 0) {
    // 判断是否启用头部数据（就是第一个数据是特殊渲染的）
    if (props.useHead) {
      bodyData.push(...props.dataList.slice(1))
      Object.assign(headData, props.dataList[0])
    } else {
      bodyData.push(...props.dataList)
    }

    nextTick(() => {
      initChart()
    })
  }
})
const initChart = () => {
  const chartData = props.otherParams.seriesData ? props.otherParams.seriesData : undefined
  if (chartData) {
    chart.value = markRaw(echarts.init(chartRef.value))
    seriesData.values = chartData.slice()
    applyChartOption()
  }

}
const applyChartOption = () => {
  let chartOption = {
    grid: {
      left: '0',
      right: '0',
      top: '0',
      bottom: '0',
    },
    xAxis: {
      show: true,
      data: new Array(seriesData.values.length).fill(''),
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
        data: seriesData.values,
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
  if (chart.value) {
    chart.value.setOption(chartOption)
    window.addEventListener('resize', handleResize)
  }
}
const handleResize = () => {
  chart.value.resize()
}
</script>

<template>
  <div class="report-data-cards" v-if="props.dataList.length !== 0">
    <ReportDataCard class="header-card" :cardData="headData" v-if="props.useHead">
      <template #content>
        <div class="title">{{ headData.title }}</div>
        <div class="value">
          ￥{{ headData.value }}
          <div class="chart" ref="chartRef"></div>
        </div>
      </template>
      <template #tips>
        <span>日环比</span>
        <el-icon style="color: red;"><TopRight /></el-icon>
        <span style="color: red;">{{ headData.tips }}%</span>
      </template>
    </ReportDataCard>

    <div class="card-item"
      v-for="(item, index) in bodyData"
      :key="index"
      :cardData="item"
    >
      <el-divider class="divider" direction="vertical" />
      <ReportDataCard :cardData="item"/>
    </div>
  </div>
  <el-empty v-else :image-size="20">
  </el-empty>
</template>

<style scoped>
.report-data-cards {
  box-sizing: content-box;
  display: flex;
  height: 102px;
  padding: 24px;
  .divider {
    height: 48px;
    margin-right: 24px;
  }
  .card-item {
    flex-grow: 1;
    display: flex;
    align-items: center;
  }
}

.header-card {
  flex-grow: 1;
  .title {
    font-size: 14px;
  }
  .value {
    display: flex;
    align-items: center;
    margin-top: 8px;
    height: 48px;
    font-size: 32px;
    font-weight: bold;
    line-height: 39px;
    .chart {
      width: 120px;
      height: 30px;
      margin-left: 24px;
    }
  }
}
</style>
