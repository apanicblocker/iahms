<script>
import ButtonToIndex from '@/components/button-to-index.vue'

export default {
  components: {
    ButtonToIndex,
  },
  name: '404',
}
</script>

<template>
  <div class="page-404">
    <h1>404</h1>
    <p>页面不存在</p>
    <ButtonToIndex />
  </div>
</template>

<style scoped>
.page-404 {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  font-size: 36px;

  h1 {
    margin: 0;
  }
  p {
    margin-bottom: 128px;
  }
}


</style>
