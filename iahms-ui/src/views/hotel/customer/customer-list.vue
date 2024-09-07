<script lang="ts" setup>

import { ElTable } from 'element-plus'
import { formatDate } from '@/utils/dataFormat'

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
    name: 'aaa',
    phoneNumber: 123456,
    tags: '-',
    amountConsume: 512.789,
    amountConsumeTimes: 23,
    lastConsumeTime: new Date()
  },
  { name: 'aaa' },
  { name: 'aaa' },
  { name: 'aaa' },
  { name: 'aaa' },
])
const customerTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<Customer[]>([])

const handleSelectionChange = (val: Customer[]) => {
  multipleSelection.value = val
}
const moneyFormatter = (row: any, column: any, cellValue: any, index: number) => {
  return cellValue ? '￥'+cellValue.toFixed(2) : '-'
}
const timeFormatter = (row: any, column: any, cellValue: any, index: number) => {
  return cellValue ? formatDate(cellValue) : '-'
}
</script>

<template>
  <div class="customer-list">
    <el-table
      ref="customerTableRef"
      :data="customerList"
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
          sss
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>
.customer-list {
  background-color: aqua;
}
</style>
