<script>
export default {
  name: 'DashboardComponentContainer',
  props: {
    title: {
      type: String,
      default: '请输入标题'
    },
    optionText: {
      type: String,
      default: ''
    },
  },
  emits: ['clickOption'],
  data() {
    return {}
  }
}
</script>

<template>
  <div class="container">
    <div class="header">
      <div class="title">{{ title }}</div>
      <div
        class="option"
        v-if="optionText"
        @click="$emit('clickOption')"
      >
        {{ optionText }}
        <el-icon><ArrowRight /></el-icon>
      </div>
    </div>
    <div class="body">
      <slot name="component"></slot>
    </div>
  </div>
</template>

<style scoped>
.container {
  padding: 16px;
  background-color: var(--base-c-bg-1);
}

.header {
  display: flex;
  justify-content: space-between;

  .title {
    font-weight: 700;
    color: var(--base-c-text-1);
  }

  .option {
    cursor: pointer;;
    display: flex;
    align-items: center;
    font-size: 14px;
    color: var(--base-c-text-2);
  }
}

.body {
  margin-top: 16px;
  color: var(--base-c-text-1);
}
</style>
