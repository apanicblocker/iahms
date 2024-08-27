<script>
import PageSidebar from './components/page-sidebar.vue';

export default {
  name: 'ReportPage',
  components: {
    PageSidebar,
  },
  data() {
    return {
      pageName: 'report',
    }
  },
}
</script>

<template>
  <div class="report-page">
    <div class="sidebar">
      <PageSidebar :pageName="this.pageName" />
    </div>
    <main>
      <router-view />
    </main>
  </div>
</template>

<style scoped>
.report-page {
  display: flex;
  height: 100%;

  main {
    width: 100%;
    padding: 16px;
    overflow-x: hidden;
    overflow-y: auto;
  }
}
</style>
