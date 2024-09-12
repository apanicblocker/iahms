<script lang="ts" setup>

const props = defineProps({
  title: String,
  width: String,
})
</script>

<template>
  <div class="container">
    <div class="title" v-if="title">{{ title }}</div>
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
    width: 84px;
    margin-right: 8px;
    text-align: right;
    font-size: 14px;
  }
  .content {
    flex: 1;
  }
}
</style>
