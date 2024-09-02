<script>
import ReportContainer from './components/report-container.vue'

import DatePicker from '@/views/hotel/components/date-picker.vue'
import ReportDataCards from './components/report-data-cards.vue';
import ReportPieChartV1 from './components/report-pie-chart_v1.vue';
import ReportLineChart from './components/report-line-chart.vue';
import BaseTable from '@/views/hotel/components/base-table.vue';

export default {
  name: 'report-revenue',
  components: {
    ReportContainer,
    DatePicker,
    ReportDataCards,
    ReportPieChartV1,
    ReportLineChart,
    BaseTable
  },
  data() {
    return {
      value: '',
      spanData: [2, -2],
      theadData: [
        {
          prop: 'type',
          label: '项目'
        },
        {
          prop: 'detail',
          label: '明细'
        },
        {
          prop: 'total',
          label: '合计',
        },
        {
          prop: '08/08',
          label: '08/08',
        },
      ],
      tbodyData: [
        {
          type: '房费',
          detail: '全日房费',
          '08/08': 80.80
        },
        {
          type: '房费',
          detail: '佣金',
          '08/08': 920.08
        },
        {
          type: '总计'
        }
      ]
    }
  },
  methods: {}
}

</script>

<template>
  <div class="report-revenue">
    <div class="date-picker-content">
      <DatePicker />
    </div>
    <ReportContainer class="data-card-content" title="营业额概况">
      <template #sub-title>
        <span>统计说明 ?</span>
      </template>
      <template #content>
        <ReportDataCards />
      </template>
    </ReportContainer>
    <div class="charts-content">
      <ReportContainer title="营业汇总统计">
        <template #content>
          <ReportPieChartV1 titleText="住宿总营业额" />
        </template>
      </ReportContainer>
      <ReportContainer class="line-chart-content" title="营业额趋势" >
        <template #content>
          <ReportLineChart/>
        </template>
      </ReportContainer>
    </div>
    <ReportContainer class="table-content" title="营业额明细">
      <template #content>
        <BaseTable
          :theadData="theadData"
          :tbodyData="tbodyData"
          :spanData="spanData"
        />
      </template>
    </ReportContainer>
  </div>
</template>

<style scoped>
.report-revenue {
  text-align: left;
}

.data-card-content {
  margin-top: 16px;
}

.charts-content {
  display: flex;
  width: 100%;
  margin-top: 40px;

  .line-chart-content {
    flex-grow: 1;
    margin-left: 16px;
  }
}

.table-content {
  margin-top: 40px;
}
</style>
