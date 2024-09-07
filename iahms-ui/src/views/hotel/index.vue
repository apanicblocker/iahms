<script lang="ts" setup>

import PageHeader from '@/views/hotel/components/page-header.vue'

</script>

<template>
  <div class="main-container">
    <PageHeader />
    <div class="content">
      <router-view />
    </div>
  </div>
</template>

<style scoped>
.main-container {
  height: 100%;
  background-color: var(--base-c-bg-2);
  color: var(--base-c-text-1);

  .content {
    height: calc(100% - 56px);
    overflow: hidden;
  }
}
</style>
