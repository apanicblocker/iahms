<script lang="ts" setup>

import OrderListFilter from './order-list-filter.vue';

interface Order {
  orderNum: string,
  originName: string,
  contactPersonName: string,
  contactPhone: string,
  checkinType: number,
}

const orderList = ref<Order[]>([
  {
    orderNum: '123125125',
    originName: '自来客',
    contactPersonName: '老王',
    contactPhone: '',
    checkinType: 1,
  },
])

</script>

<template>
  <div class="order-list">
    <OrderListFilter />
    <div class="table-content">
      <el-table
        :data="orderList"
        :border="true"
      >
        <el-table-column fixed label="订单号/渠道订单号" min-width="235">
          <template #default="scope">
            <span style="color: royalblue;">{{ scope.row.orderNum }}</span>
          </template>
        </el-table-column>
        <el-table-column label="渠道" prop="originName" min-width="141" />
        <el-table-column label="联系人" prop="contactPersonName" min-width="94"/>
        <el-table-column label="手机号" prop="contactPhone" min-width="141" />
        <el-table-column label="入住类型" prop="checkinType" min-width="141" />
        <el-table-column label="房型" prop="roomType" min-width="235" />
        <el-table-column label="房间" prop="roomName" min-width="235" />
        <el-table-column label="入住时间" prop="checkinTime" min-width="188" />
        <el-table-column label="离店时间" prop="checkoutTime" min-width="188" />
        <el-table-column label="入住状态" prop="checkinStatus" min-width="141" />
        <el-table-column label="房费" prop="roomAmount" min-width="141" />
        <el-table-column label="订单总金额" prop="orderAmount" min-width="141" />
        <el-table-column label="结帐状态" prop="payStatus" min-width="141" />
        <el-table-column label="创建时间" prop="createTime" min-width="188" />
      </el-table>
    </div>
  </div>
</template>

<style scoped>
.order-list {
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
}

.table-content {
  margin-top: 16px;
}
</style>
