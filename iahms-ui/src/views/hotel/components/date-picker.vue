<script>
export default {
  name: 'DatePicker',
  emits: ['change'],
  data() {
    return {
      selectedDate: this.selectedDate = [Date.now(), Date.now()]
    }
  },
  watch: {
    selectedDate(newVal) {
      this.$emit('change', newVal)
    },
  },
}
</script>

<template>
  <div class="date-picker">
    <el-date-picker
      v-model="selectedDate"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      popper-class="date-picker-popper"
      :clearable="false"
      style="
        --el-text-color-primary: var(--base-c-text-1);
        --el-input-bg-color: var(--base-c-bg-1);
        --el-border-color: var(--base-c-bg-3);
      "
    />
  </div>
</template>

<style scoped>
  .el-icon {
    color: var(--base-c-text-1);
  }
</style>
<style>
.date-picker-popper {
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
