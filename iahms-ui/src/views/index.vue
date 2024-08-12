<script>
import PageHeader from '@/components/PageHeader/index.vue'

export default {
  name: 'MyIndex',
  data() {
    return {}
  }
}
</script>

<template>
  <PageHeader titleText="小鸟公寓" imgPath="/src/assets/vue.svg"/>
  <router-view />
</template>

<style scoped>
</style>
