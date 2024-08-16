<script>
import ThemeToggler from '../components/theme-toggler.vue';

export default {
  name: 'TestPage',
  components: {
    ThemeToggler
  },
  data() {
    return {
    }
  }
}
</script>

<template>
  <div class="test-page">
    <ThemeToggler />
  </div>

</template>

<style scoped>
.test-page {
  height: 100vh;
  background-color: aqua;

  * {
    background-color: burlywood;
    border-bottom: 1px solid black;
  }
}
</style>