<script lang="ts" setup>

import PageSidebar from './components/page-sidebar_v2.vue'

</script>

<template>
  <div class="section-customer">
    <PageSidebar class="sidebar" :pageName="'customer'" />
    <div class="content">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
.section-customer {
  display: flex;
  height: 100%;

  .content {
    width: 100%;
    padding: 16px;
    overflow-x: hidden;
    overflow-y: auto;
  }
}
</style>
