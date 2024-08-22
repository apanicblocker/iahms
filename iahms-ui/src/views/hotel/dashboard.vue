<script>
import DataCards from './components/data-cards.vue';
import MemoNote from './components/memo-note.vue';
import HomeLineChart from './components/home-line-chart.vue';

export default {
  name: 'DashboardPage',
  components: {
    DataCards,
    MemoNote,
    HomeLineChart,
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
      ],
      // 近7天入住率数据
      chartData: {
        title: '近7天入住率',
        xAxisData: ['2024-08-01', '2024-08-02', '2024-08-03', '2024-08-04', '2024-08-05', '2024-08-06', '2024-08-07'],
        seriesData: [20, 35, 21, 21, 25, 49, 71],
        formatter: '{value}%',
      }
    }
  },
  methods: {
    routeTo(param) {
      console.log('route to: ' + param)
      // 到时候可能是个复杂的Json数据，需要解析
      // this.$router.push({path: '/hotel/' + param})
    },
    saveNote(content) {
      console.log('save note: ', content)
    },
  }
}
</script>

<template>
  <div class="dashboard-content">
    <main>
      <div class="data-card-content">
        <DataCards :cardDataList="cardDataList" @clickCard="routeTo" />
      </div>
      <div class="line-chart-content">
        <HomeLineChart v-bind="chartData"/>
      </div>
    </main>
    <aside>
      <div class="memo-content">
        <MemoNote @updateNote="saveNote"/>
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

.line-chart-content {
  margin-top: 16px;
  height: 325px;
}

</style>