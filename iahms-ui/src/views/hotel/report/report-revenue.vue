<script>
import ReportContainer from './components/report-container.vue'
import ReportPieChart from './components/report-pie-chart.vue';

export default {
  name: 'ReportRevenue',
  components: {
    ReportContainer,
    ReportPieChart,
  },
  data() {
    return {
      value: '',
      defaultTime: [Date.now(), Date.now()]
    }
  }
}
</script>

<template>
  <div class="report-revenue">
    <div class="date-picker-content">
      <el-date-picker
        v-model="value"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :default-time="defaultTime"
      />
    </div>
    <ReportContainer class="container" title="营业额概况">
      <template #sub-title>
        <span>统计说明 ?</span>
      </template>
      <template #content>
        <ReportPieChart/>
      </template>
    </ReportContainer>

    <div class="pie-chart-list">
      <ReportContainer class="container" title="总收款">
        <template #content>
          <ReportPieChart/>
        </template>
      </ReportContainer>
      <ReportContainer class="container" title="总退款">
        <template #content>
          <ReportPieChart/>
        </template>
      </ReportContainer>
    </div>
  </div>
</template>

<style scoped>
.report-revenue {
  text-align: left;
  /* background-color: aqua; */
}

.container {
  margin-top: 16px;
}

.pie-chart-list {
  display: flex;
  justify-content: space-between;

  .container {
    width: calc(50% - 8px);
  }
}
</style>
