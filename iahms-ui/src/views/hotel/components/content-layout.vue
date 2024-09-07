<script lang="ts" setup>

</script>

<template>
  <div class="content-container">
    <div class="aside">
      <slot name="aside"></slot>
    </div>
    <div class="section">
      <slot name="section"></slot>
    </div>
  </div>
</template>

<style scoped>
.content-container {
  display: flex;
  height: 100%;

  .section {
    width: 100%;
    padding: 16px;
    overflow-x: hidden;
    overflow-y: auto;
  }
}
</style>
