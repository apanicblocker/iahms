<script lang="ts">
import ReportContainer from './components/report-container.vue'

import DatePicker from '@/views/hotel/components/date-picker.vue'
import ReportDataCards from './report-revenue-data-cards.vue';
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
      // 营业额概况
      otherParams: {
        seriesData: [120, 132, 101, 134, 90, 230, 210]
      },
      cardDataList: [
        {
          title: '住宿总营业额',
          value: 1200.00,
          tips: 10,
        },
        {
          icon: 'HomeFilled',
          title: '客房收入',
          value: 1200.00,
          tips: 10,
        },
        {
          icon: 'HomeFilled',
          title: '餐饮收入',
          value: 1200.00,
          tips: 10,
        },
        {
          icon: 'HomeFilled',
          title: '其他收入',
          value: 1200.00,
          tips: 10,
        }
      ],
      // 表格数据
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
        <ReportDataCards :useHead="true" :dataList="cardDataList" :otherParams="otherParams"/>
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
