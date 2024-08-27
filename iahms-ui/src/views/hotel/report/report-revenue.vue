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
      defaultTime: ref<[Date, Date]>([
        new Date(2000, 1, 1, 0, 0, 0),
        new Date(2000, 2, 1, 23, 59, 59),
      ])
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
        <!-- TODO -->
      </template>
    </ReportContainer>
    <ReportContainer class="container" title="营业汇总统计">
      <template #content>
        <ReportPieChart :legendPosition="'down'"/>
      </template>
    </ReportContainer>
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
</style>
