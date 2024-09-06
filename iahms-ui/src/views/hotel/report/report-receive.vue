<script lang="ts">
import ReportContainer from './components/report-container.vue'

import DatePicker from '@/views/hotel/components/date-picker.vue'
import PieChart from './components/report-pie-chart.vue'
import DataCards from './report-receive-data-cards.vue'
import PayDetailTable from './report-receive-pay-detail-table.vue'
import BaseTable from '../components/base-table.vue'

export default {
  name: 'ReportReceive',
  components: {
    ReportContainer,
    DatePicker,
    PieChart,
    DataCards,
    PayDetailTable,
    BaseTable,
  },
  data() {
    return {
      activeName: 'summary',
      // 卡片列表
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
      ],
      // 饼图数据
      pieChartData: {
        receive: [
          { value: 1048, name: 'Test Search' },
          { value: 735, name: 'Test Direct' },
          { value: 580, name: 'Test Email' },
          { value: 484, name: 'Test Union Ads' },
          { value: 300, name: 'Test Video Ads' }
        ],
        pay: [
          { value: 1048, name: 'Test Search' },
          { value: 735, name: 'Test Direct' },
          { value: 580, name: 'Test Email' },
          { value: 484, name: 'Test Union Ads' },
          { value: 300, name: 'Test Video Ads' }
        ],
      },
      // 支付方式明细
      payMethodTableData: {
        thead: [
          { label: '支付方式', prop: 'payMethod' },
          { label: '合计', prop: 'summary' },
          { label: '08/01', prop: '08/01' },
        ],
        tbody: [
          {
            'payMethod': '现金',
            'summary': 120000,
            '08/01': 20,
          },
          {
            'payMethod': '现金',
            'summary': 120000,
            '08/01': 20,
          },
          {
            'payMethod': '现金',
            'summary': 120000,
            '08/01': 20,
          },
          {
            'payMethod': '现金',
            'summary': 120000,
            '08/01': 20,
          },
        ],
      },
      // 筛选栏
      filterOptionList: {
        checkType: [
          { label: '收款', value: 1 },
          { label: '收押金', value: 2 },
          { label: '退款', value: 3 },
          { label: '退押金', value: 4 },
        ],
        checkMethods: [
          { label: '微信', value: 11 },
          { label: '支付宝', value: 12 },
          { label: '现金', value: 13 }
        ],
      },
      checkMethodValue: [],
      checkTypeValue: [],
      // 收款记录表格
      theadData: [
        { label: '项目', prop: 'project' },
        { label: '收款时间', prop: 'receiveTime' },
        { label: '支付方式', prop: 'payMethod' },
        { label: '收款金额', prop: 'receiveNumber' },
        { label: '入住时间', prop: 'checkinTime' },
        { label: '操作人', prop: 'updater' },
        { label: '操作时间', prop: 'updateTime' },
        { label: '关联订单', prop: 'orderId', width: 210 },
        { label: '联系人', prop: 'contact' },
        { label: '联系电话', prop: 'contactNumber' },
      ],
      tbodyData: [
        { project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},{ project: 2},
      ],
      currentPage: undefined,
      pageSize: undefined,
    }
  },
  methods: {
    handleClick(tab, event) {
      // console.log(tab, event)
    },
    handleSizeChange() {

    },
    handleCurrentChange() {

    },
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
            <DataCards :useHead="true" :dataList="cardDataList"/>
          </template>
        </ReportContainer>
        <div class="pie-chart-list">
          <ReportContainer class="container" title="总收款">
            <template #content>
              <PieChart titleText="总收款" :chartData="pieChartData.receive"/>
            </template>
          </ReportContainer>
          <ReportContainer class="container" title="总退款">
            <template #content>
              <PieChart titleText="总退款" :chartData="pieChartData.pay"/>
            </template>
          </ReportContainer>
        </div>
        <ReportContainer class="pay-detail-content" title="支付方式明细" style="--content-bg-color: transparent;">
          <template #sub-title>
            <div class="sub-title">（ 2024-08-01 至 2024-10-31 ）</div>
          </template>
          <template #content>
            <PayDetailTable :theadData="payMethodTableData.thead" :tbodyData="payMethodTableData.tbody"/>
          </template>
        </ReportContainer>
      </el-tab-pane>

      <el-tab-pane class="receipt-record-tab" label="收款记录" name="record">
        <div class="filter-bar">
          <el-select
            v-model="checkTypeValue"
            clearable
            placeholder="请选择"
            style="
              width: 240px;
            "
          >
            <el-option
              v-for="item in filterOptionList.checkType"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          <el-select
            v-model="checkMethodValue"
            clearable
            multiple
            collapse-tags
            placeholder="请选择"
            style="width: 240px"
          >
            <el-option
              v-for="item in filterOptionList.checkMethods"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          <DatePicker />
          <div class="reset-btn">重置筛选</div>
          <div class="export-btn-content">
            <button>导出报表</button>
          </div>
        </div>
        <div class="receipt-table-content">
          <BaseTable :tableHeight="785" :theadData="theadData" :tbodyData="tbodyData">
            <template #footer>
              <div class="tableFooter">
                <div class="table-info">
                  <span>共计5条记录，</span>
                  <span>净收款：</span><span style="color: royalblue">￥12,124,00</span>
                  <el-divider direction="vertical"/>
                  <span>总收款：</span><span style="color: orange">￥12,124,00</span>
                  <el-divider direction="vertical"/>
                  <span>总退款：</span><span style="color: red">￥12,124,00</span>
                </div>
                <el-pagination
                  class="pagination"
                  v-model:current-page="currentPage"
                  v-model:page-size="pageSize"
                  :page-sizes="[100, 200, 300, 400]"
                  layout="total, sizes, prev, pager, next"
                  :total="400"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                />
              </div>
            </template>
          </BaseTable>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.report-receive {
  /* 有空记得把这两团狗屎处理一下 */
  --el-fill-color-blank: var(--base-c-bg-1);
  --el-border-color-light: var(--base-c-bg-3);
  --el-text-color-primary: var(--base-c-text-2);
  --el-color-primary: var(--base-c-accent-1);

  text-align: left;

  .tabs {
    height: 100%;
  }
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

.filter-bar {
  display: flex;
  align-items: center;

  > * {
    & + & {
      margin-left: 8px;
    }
  }
  .reset-btn {
    cursor: pointer;
    color: var(--base-c-accent-1);
    &:hover {
      filter: brightness(1.1);
    }
  }
  .export-btn-content {
    flex-grow: 1;
    text-align: right;
    button {
      height: 32px;
      padding: 8px 12px;
      background-color: var(--base-c-accent-1);
      border-radius: 4px;
      line-height: 16px;
      font-size: 14px;
      color: var(--base-c-bg-1);
      &:hover {
        border: 1px solid var(--base-c-accent-2);
      }
    }
  }
}
.receipt-table-content {
  margin-top: 24px;
}
.tableFooter {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
  padding: 12px;
  background-color: var(--base-c-bg-1);
  border: 1px solid var(--base-c-bg-3);
  border-top: none;
  .table-info {
    display: flex;
    align-items: center;
  }
  .pagination {
  }
}
.pay-detail-content {
  margin-top: 40px;
}
</style>
