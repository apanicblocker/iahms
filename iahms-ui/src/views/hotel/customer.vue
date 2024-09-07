<script lang="ts" setup>

import ContentLayout from '@/views/hotel/components/content-layout.vue'
import PageSidebar from './components/page-sidebar_v2.vue'

</script>

<template>
  <ContentLayout>
    <template #aside>
      <PageSidebar :pageName="'customer'" />
    </template>
    <template #section>
      <router-view />
    </template>
  </ContentLayout>
</template>

<style scoped>

</style>
