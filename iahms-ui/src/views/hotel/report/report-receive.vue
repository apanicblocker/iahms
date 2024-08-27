<script>
import ReportContainer from './components/report-container.vue'
import ReportPieChart from './components/report-pie-chart.vue'

export default {
  name: 'ReportReceive',
  components: {
    ReportContainer,
    ReportPieChart
  },
  data() {
    return {}
  }
}
</script>

<template>
  <div class="report-receive">
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
  </div>
</template>

<style scoped>
.report-receive {

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
