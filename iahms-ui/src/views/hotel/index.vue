<script>
import PageHeader from '@/views/hotel/components/page-header.vue'

export default {
  name: 'HotelIndex',
  components: {
    PageHeader
  }
}
</script>

<template>
  <div class="page-content">
    <PageHeader />
    <main>
      <router-view />
    </main>
  </div>
</template>

<style scoped>
.page-content {
  height: 100%;
  background-color: var(--base-c-bg-2);
}
main {
  /* 当内容溢出时，该标签会自动展示滚动条 */
  overflow: auto;
  height: calc(100% - 56px);
}
</style>
