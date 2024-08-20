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
  <div class="hotel-index">
    <PageHeader />
    <div class="section">
      <router-view />
    </div>
  </div>
</template>

<style scoped>
.hotel-index {
  height: 100%;
  background-color: var(--base-c-bg-2);
}
.section {
  /* 当内容溢出时，该标签会自动展示滚动条 */
  overflow: auto;
  height: calc(100% - 56px);
}
</style>
