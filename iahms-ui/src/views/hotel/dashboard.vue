<script>
import DataCard from './components/data-card.vue';

export default {
  name: 'DashboardPage',
  components: {
    DataCard
  },
  data() {
    return {
      cardDataList: [
        {
          title: '标题',
          value: '0',
          param: 'order',
        },
        {
          title: '标题1',
          value: '1',
          param: 'order',
        },
        {
          title: '标题2',
          value: '2',
          param: 'order',
        },
        {
          title: '标题3',
          value: '3',
          param: '参数3',
        },
      ]
    }
  },
  methods: {
    routeTo(param) {
      console.log('route to: ' + param)
      // 到时候可能是个复杂的Json数据，需要解析
      // this.$router.push({path: '/hotel/' + param})
    }
  }
}
</script>

<template>
  <div class="dashboard-content">
    <main>
      <div class="data-card-content">
        <DataCard :cardDataList="cardDataList" @clickCard="routeTo" />
      </div>
    </main>
    <aside>
      <div class="memo-content">
        <h1>备忘录</h1>
      </div>
    </aside>
  </div>
</template>

<style scoped>
.dashboard-content {
  display: flex;;
  margin: 16px;

  main {
    width: 75%;
  }

  aside {
    flex-grow: 1;
    margin-left: 16px;
  }
}



</style>