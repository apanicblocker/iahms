<script>
export default {
  name: 'ReportContainer',
  props: {
    title: {
      type: String,
      default: '请输入标题'
    }
  },
  data() {
    return {}
  }
}
</script>

<template>
  <div class="report-container">
    <div class="header">
      <div class="title">{{ title }}</div>
      <div class="sub-title">
        <slot name="sub-title"></slot>
      </div>
      <div class="option">
        <slot name="option"></slot>
      </div>
    </div>
    <div class="body">
      <slot name="content"></slot>
    </div>
  </div>
</template>

<style scoped>
.report-container {
  /* background-color: aqua; */
}

.header {
  display: flex;
  align-items: flex-end;
  line-height: 1.2;

  .title {
    font-size: 16px;
    font-weight: bold;
  }
  .sub-title {
    margin-left: 8px;
    font-size: 12px;
    color: #999;
  }
  .option{
    flex: 1;
    text-align: right;
  }
}

.body {
  margin-top: 16px;
  background-color: var(--base-c-bg-1);
}
</style>
