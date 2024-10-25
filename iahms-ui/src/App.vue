<script lang="ts" setup>

import { useRoute } from 'vue-router'

const route = useRoute()

</script>

<template>
  <router-view :key="route.fullPath"/>
</template>

<style scoped>
</style>
