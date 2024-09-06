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
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
.page-content {
  height: 100%;
  background-color: var(--base-c-bg-2);
  color: var(--base-c-text-1);
}
main {
  /* 当内容溢出时，该标签会自动展示滚动条 */
  overflow: hidden;
  height: calc(100% - 56px);
}
</style>
