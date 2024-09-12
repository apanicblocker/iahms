<script lang="ts" setup>

import { formatDate } from '@/utils/dataFormat'

import CustomerListFilter from './customer-list-filter.vue'
import CustomerListOptions from './customer-list-options.vue'

interface Customer {
  name: string,
  phoneNumber: number,
  tags: string,
  amountConsume: number,
  amountConsumeTimes: number,
  lastConsumeTime: Date,
}

const customerList = ref<Customer[]>([
  {
    name: 'aaa1',
    phoneNumber: 123456,
    tags: '-',
    amountConsume: 512.789,
    amountConsumeTimes: 23,
    lastConsumeTime: new Date()
  },
  {
    name: 'aaa2',
    phoneNumber: 123456,
    tags: '-',
    amountConsume: 512.789,
    amountConsumeTimes: 23,
    lastConsumeTime: new Date()
  },
  {
    name: 'aaa3',
    phoneNumber: 123456,
    tags: '-',
    amountConsume: 512.789,
    amountConsumeTimes: 23,
    lastConsumeTime: new Date()
  },
  {
    name: 'aaa4',
    phoneNumber: 123456,
    tags: '-',
    amountConsume: 512.789,
    amountConsumeTimes: 23,
    lastConsumeTime: new Date()
  },
  {
    name: 'aaa5',
    phoneNumber: 123456,
    tags: '-',
    amountConsume: 512.789,
    amountConsumeTimes: 23,
    lastConsumeTime: new Date()
  },
])
const customerTableRef = ref()
const multipleSelection = ref<Customer[]>([])

const handleSelectionChange = (val: Customer[]) => {
  multipleSelection.value = val
  // 同步状态至底部全选框
  let length = val.length
  if (length !== 0 && length < currentPageData.value.length) {
    selectAll.value = false
    isIndeterminate.value = true
  } else if(length === currentPageData.value.length) {
    selectAll.value = true
    isIndeterminate.value = false
  } else {
    selectAll.value = false
    isIndeterminate.value = false
  }
}
const moneyFormatter = (row: any, column: any, cellValue: any, index: number) => {
  return cellValue ? '￥'+cellValue.toFixed(2) : '-'
}
const timeFormatter = (row: any, column: any, cellValue: any, index: number) => {
  return cellValue ? formatDate(cellValue) : '-'
}

// 表格底部
// 批量操作栏
const selectAll = ref<boolean>(false)
const isIndeterminate = ref<boolean>(false)
// 分页栏
const total = ref<number>(customerList.value.length)
const currentPage = ref<number>(1)
const pageSizes = ref<number[]>([10, 25, 30])
const pageSize = ref<number>(pageSizes.value[0])
const currentPageData: Ref<Customer[]> = computed(() => {
  // 截取当前页的数据
  const start = (currentPage.value - 1) * pageSize.value;
  const end = currentPage.value * pageSize.value;
  return customerList.value.slice(start, end);
})

const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}
</script>

<template>
  <div class="customer-list">
    <CustomerListFilter />
    <div class="table-content">
      <el-table class="el-table"
        ref="customerTableRef"
        :data="currentPageData"
        :border="true"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection"/>
        <el-table-column label="姓名" prop="name"/>
        <el-table-column label="手机号" prop="phoneNumber"/>
        <el-table-column label="标签" prop="tags" width="260"/>
        <el-table-column label="累计消费金额" prop="amountConsume" sortable :formatter="moneyFormatter" align="right"/>
        <el-table-column label="累计下单数" prop="amountConsumeTimes" sortable  align="right"/>
        <el-table-column label="上次下单时间" prop="lastConsumeTime" width="260" :formatter="timeFormatter" />
        <el-table-column label="操作" width="380">
          <template #default>
            <CustomerListOptions />
          </template>
        </el-table-column>
      </el-table>
      <div class="tfoot">
        <div class="batch-operation">
          <el-checkbox
            v-model="selectAll"
            :indeterminate="isIndeterminate"
            label="全选"
            @click="() => customerTableRef.toggleAllSelection()"
            style="color: var(--base-c-text-1);"
          />
          <div class="total-number">
            <span>已选&nbsp;&nbsp;{{ multipleSelection.length }}个&nbsp;&nbsp;客户</span>
          </div>
          <div class="btn-container">
            <el-select class="add-tags" popper-class="popper" placeholder="加标签">
              <el-option value="1" @click="">对选中的人加标签</el-option>
              <el-option value="2" @click="">对筛选出来的{{ total }}人加标签</el-option>
            </el-select>
            <el-select class="set-blacklist" popper-class="popper" placeholder="设置黑名单">
              <el-option value="1" @click="">对选中的人加入黑名单</el-option>
              <el-option value="2" @click="">对筛选出来的{{ total }}人加入黑名单</el-option>
              <el-option value="3" @click="">对选中的人移出黑名单</el-option>
              <el-option value="4" @click="">对筛选出来的{{ total }}人移出黑名单</el-option>
            </el-select>
          </div>
        </div>
        <el-pagination
          popper-class="popper"
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="pageSizes"
          layout="total, sizes, prev, pager, next"
          :total="total"
          size="small"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.customer-list {
  --tfoot-c-bg: var(--base-c-bg-1);
  --tfoot-c-border: var(--base-c-bg-2);
  --tfoot-c-font: var(--base-c-text-1);

  --el-border-color: var(--base-c-bg-3);
  --el-fill-color-blank: var(--base-c-bg-1);
  --el-text-color-placeholder: var(--base-c-text-1);
  --el-text-color-regular: var(--base-c-text-1);

  .el-table {
    --el-table-header-bg-color: var(--ia-c-table-header-bg);
    --el-table-header-text-color: var(--base-c-text-1);
    --el-table-border-color: var(--base-c-bg-2);
    --el-table-text-color: var(--base-c-text-1);
    --el-table-tr-bg-color: var(--base-c-bg-1);
    --el-table-row-hover-bg-color: var(--ia-c-table-row-hover-bg);
  }

  .table-content {
    margin-top: 16px;
  }
}

.tfoot {
  display: flex;
  justify-content: space-between;
  padding: 12px;
  background-color: var(--tfoot-c-bg);
  border: 1px solid var(--tfoot-c-border);
  border-top: none;
}

.batch-operation {
  display: flex;
  align-items: center;
  font-size: 12px;

  .total-number {
    margin-left: 8px;
  }
  .btn-container {
    display: flex;
    margin-left: 16px;

    .add-tags {
      width: 90px;
    }
    .set-blacklist {
      width: 120px;
      margin-left: 12px;
    }
  }
}
</style>
