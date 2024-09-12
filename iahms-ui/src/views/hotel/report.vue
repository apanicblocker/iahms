<script lang="ts" setup>

import ContentLayout from './components/content-layout.vue'
import PageSidebar from './components/page-sidebar_v2.vue'

</script>

<template>
  <ContentLayout>
    <template #aside>
      <PageSidebar :pageName="'report'" />
    </template>
    <template #section>
      <router-view />
    </template>
  </ContentLayout>
</template>

<style scoped>
</style>
