<script>
import ReportContainer from './components/report-container.vue'

import DatePicker from '@/views/hotel/components/date-picker.vue'
import ReportPieChart from './components/report-pie-chart.vue'
import ReportDataCards from './report-receive-data-cards.vue'

export default {
  name: 'ReportReceive',
  components: {
    ReportContainer,
    DatePicker,
    ReportDataCards,
    ReportPieChart,
  },
  data() {
    return {
      activeName: 'summary',
      cardDataList: [
      {
          icon: 'HomeFilled',
          title: '净收入',
          value: 1100.00,
          tips: 10,
        },
        {
          icon: 'HomeFilled',
          title: '总收入',
          value: 1200.00,
          tips: 10,
        },
        {
          icon: 'HomeFilled',
          title: '总退款',
          value: 1200.00,
          tips: 10,
        }
      ]
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<template>
  <div class="report-receive">
    <el-tabs v-model="activeName" class="tabs" @tab-click="handleClick">
      <el-tab-pane label="收款汇总" name="summary">
        <div class="date-picker-content">
          <DatePicker />
        </div>
        <ReportContainer class="overview" title="收款概况">
          <template #sub-title>
            <div class="sub-title">统计说明 ?</div>
          </template>
          <template #content>
            <ReportDataCards :useHead="true" :dataList="cardDataList"/>
          </template>
        </ReportContainer>
        <div class="pie-chart-list">
          <ReportContainer class="container" title="总收款">
            <template #content>
              <ReportPieChart titleText="总收款"/>
            </template>
          </ReportContainer>
          <ReportContainer class="container" title="总退款">
            <template #content>
              <ReportPieChart titleText="总退款"/>
            </template>
          </ReportContainer>
        </div>
      </el-tab-pane>
      <el-tab-pane label="收款记录" name="record">
        
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.report-receive {
  text-align: left;
}

.overview {
  margin-top: 24px;
}

.pie-chart-list {
  display: flex;
  justify-content: space-between;
  margin-top: 40px;

  .container {
    width: calc(50% - 8px);
  }
}
</style>
