<script lang="ts" setup>

import PageHeader from './components/page-header.vue'
import PageSidebar from './components/page-sidebar.vue'

</script>

<template>
  <PageHeader />
  <div class="main">
    <PageSidebar />
    <div class="section">
      <router-view />
    </div>
  </div>
</template>

<style scoped>
.main {
  display: flex;
  height: calc(100% - 56px);
  overflow: hidden;
}

.section {
  width: 100%;
  padding: 16px;
  overflow-x: hidden;
  overflow-y: auto;
}
</style>
