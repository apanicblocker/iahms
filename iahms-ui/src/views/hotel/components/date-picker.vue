<script lang="ts" setup>

const emit = defineEmits(['change'])

const selectedDate = ref('')
const defaultTime = ref<[Date, Date]>([new Date(), new Date()])
const shortcuts = [
  {
    text: '昨天',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 1)
      return [start, end]
    }
  },
  {
    text: '今天',
    value: [new Date(), new Date()]
  },
  {
    text: '上周',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 14)
      end.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    }
  },
  {
    text: '本周',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    }
  },
  {
    text: '上月',
    value: () => {
      const now = new Date()
      const year = now.getFullYear()
      const month = now.getMonth()
      const end = new Date(year, month, 0)
      const start = new Date(year, month - 1, 1)
      return [start, end]
    }
  },
  {
    text: '本月',
    value: () => {
      const now = new Date()
      const year = now.getFullYear()
      const month = now.getMonth()
      const end = new Date()
      const start = new Date(year, month, 1)
      return [start, end]
    }
  },
]
</script>

<template>
  <div class="date-picker">
    <el-date-picker
      popper-class="date-picker-popper"
      v-model="selectedDate"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      :default-time="defaultTime"
      :shortcuts="shortcuts"
      :clearable="false"
      @change="emit('change', selectedDate)"
      style="
        --el-text-color-primary: var(--base-c-text-2);
        --el-input-bg-color: var(--base-c-bg-1);
        --el-input-border-color: var(--base-c-bg-2);
        --el-input-hover-border-color: var(--base-c-bg-3);
      "
    />
  </div>
</template>

<style scoped>
.date-picker {
  .el-icon {
    color: var(--base-c-text-1);
  }
}
</style>
