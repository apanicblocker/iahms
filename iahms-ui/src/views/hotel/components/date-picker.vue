<script>
export default {
  name: 'DatePicker',
  data() {
    return {
      value: ''
    }
  }
}
</script>

<template>
  <div class="date-picker">
    <el-date-picker
      v-model="value"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      popper-class="date-picker-popper"
      style="
        --el-text-color-primary: var(--base-c-text-1);
        --el-input-bg-color: var(--base-c-bg-1);
        --el-border-color: var(--base-c-bg-3);
      "
    />
  </div>
</template>

<style>
.date-picker-popper {

  .el-icon {
    color: var(--base-c-text-1);
  }
  .el-picker-panel {
    color: var(--base-c-text-1);
    background-color: var(--base-c-bg-1);
  }
  .el-date-table {
    td.start-date .el-date-table-cell__text {
      background-color: var(--base-c-accent-1);
    }
    td.end-date .el-date-table-cell__text {
      background-color: var(--base-c-accent-1);
    }
    td.in-range .el-date-table-cell {
      background-color: var(--base-c-bg-3);
    }
  }
}

</style>
