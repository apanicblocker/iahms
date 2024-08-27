<script>
import TestChart from './report/components/test-chart.vue';


export default {
  name: 'TestPage',
  components: {
    TestChart
  },
  data() {
    return {
      text1: 'test1',
    }
  },
  methods: {
    test() {
      console.log('test');
    }
  }
}
</script>

<template>
  <div class="test-page">
    <TestChart />
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