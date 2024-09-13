<script lang="ts" setup>

const props = defineProps({
  title: String,
  titleWidth: String,
})
</script>

<template>
  <div class="container">
    <div class="title" v-if="title" :style="{ width: titleWidth }">{{ title }}</div>
    <div class="content">
      <slot name="content"></slot>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  align-items: center;
  .title {
    margin-right: 8px;
    text-align: right;
    font-size: 14px;
  }
  .content {
    flex: 1;
  }
}
</style>
