<script lang="ts" setup>
import PageSidebar from './components/page-sidebar_v2.vue';

</script>

<template>
  <div class="order-page">
    <div class="sidebar">
      <PageSidebar :pageName="'order'"/>
    </div>
    <main>
      <router-view />
    </main>
  </div>
</template>

<style scoped>
.order-page {
  display: flex;
  height: 100%;

  main {
    padding: 16px;
  }
}

</style>
