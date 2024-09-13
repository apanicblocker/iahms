<script lang="ts" setup>

import OrderListFilter from './order-list-filter.vue';

</script>

<template>
  <div class="order-list">
    <OrderListFilter />
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
</style>
