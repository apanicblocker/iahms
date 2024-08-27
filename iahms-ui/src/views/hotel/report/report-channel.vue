<script>
import ReportContainer from './components/report-container.vue'
import ReportPieChart from './components/report-pie-chart.vue'

export default {
  name: 'ReportChannel',
  components: {
    ReportContainer,
    ReportPieChart,
  },
  data() {
    return {}
  }
}
</script>

<template>
  <div class="report-channel">
    <div class="pie-chart-list">
      <ReportContainer class="container" title="订单总金额">
        <template #content>
          <ReportPieChart titleText="总订单金额"/>
        </template>
      </ReportContainer>
      <ReportContainer class="container" title="间夜数">
        <template #content>
          <ReportPieChart titleText="总间夜数"/>
        </template>
      </ReportContainer>
    </div>
  </div>
</template>

<style scoped>
.pie-chart-list {
  display: flex;
  justify-content: space-between;
  margin-top: 40px;

  .container {
    width: calc(50% - 8px);

    ReportPieChart {
      min-width: 465px;
    }
  }
}
</style>
