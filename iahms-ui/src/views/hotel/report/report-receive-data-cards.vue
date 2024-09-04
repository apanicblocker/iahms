<script lang="ts" setup>

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

</script>

<template>
  <div class="report-data-cards" v-if="props.dataList.length !== 0">
    <div class="card-item"
      v-for="(item, index) in dataList"
      :key="index"
      :cardData="item"
    >
      <el-divider class="divider" direction="vertical" v-if="index !== 0" />
      <ReportDataCard :cardData="item" />
    </div>
  </div>
  <el-empty v-else :image-size="20" />
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
