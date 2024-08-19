<script>
import PageHeader from '@/views/hotel/components/page-header.vue'

export default {
  name: 'HotelIndex',
  components: {
    PageHeader
  },
  data() {
    return {}
  }
}
</script>

<template>
  <div class="hotel-index">
    <PageHeader />
    <router-view />
  </div>
</template>

<style scoped>
</style>
