<script>
import DashboardComponentContainer from './components/dashboard-component-container.vue';

import MemoNote from './components/memo-note.vue';
import DashboardDataCards from './components/dashboard-data-cards.vue';
import DashboardLineChart from './components/dashboard-line-chart.vue';
import DashboardCommonFunction from './components/dashboard-common-function.vue'
import DashboardHelpCenter from './components/dashboard-help-center.vue'

export default {
  name: 'DashboardPage',
  components: {
    DashboardComponentContainer,

    MemoNote,
    DashboardDataCards,
    DashboardLineChart,
    DashboardCommonFunction,
    DashboardHelpCenter,
  },
  data() {
    return {
      cardDataList: [
        {
          title: '今日预抵',
          value: '2',
          param: '/order/jinriyudi',
        },
        {
          title: '今日预离',
          value: '15',
          param: '/order/jinriyuli',
        },
        {
          title: '今日新办',
          value: '2',
          param: '/order/jinrixinban',
        },
        {
          title: '未排房',
          value: '42',
          param: '/order/weipaifang',
        },
        {
          title: '待处理',
          value: '11',
          param: '/order/daichuli',
        },
      ],
      // 近7天入住率数据
      chartData: {
        title: '近7天入住率',
        xAxisData: ['2024-08-01', '2024-08-02', '2024-08-03', '2024-08-04', '2024-08-05', '2024-08-06', '2024-08-07'],
        seriesData: [20, 35, 21, 21, 25, 49, 71],
        formatter: '{value}%',
        valueFormatter: (value) => `${value}%`,
      },
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
  <div class="page-content">
    <main>
      <div class="data-card-content">
        <DashboardDataCards :cardDataList="cardDataList" @clickCard="routeTo" />
      </div>
      <div class="line-chart-content">
        <DashboardLineChart v-bind="chartData"/>
      </div>
      <DashboardComponentContainer
        class="common-function-content"
        :title="'常用功能'"
        :optionText="'配置'"
        @clickOption="$refs.commonFunction.showDialog()"
      >
        <template #component>
          <DashboardCommonFunction ref="commonFunction"/>
        </template>
      </DashboardComponentContainer>
      <DashboardComponentContainer
        class="help-center-content"
        :title="'帮助中心'"
        :optionText="'更多'"
        @clickOption="$router.push('/hotel/helpCenter')"
      >
        <template #component>
          <DashboardHelpCenter />
        </template>
      </DashboardComponentContainer>
    </main>
    <aside>
      <div class="memo-content">
        <MemoNote @updateNote="saveNote"/>
      </div>
    </aside>
  </div>
</template>

<style scoped>
.page-content {
  display: flex;
  margin: 16px;
  overflow: auto;

  main {
    width: 75%;

    /* 选择main的下一级全部子标签（除了第1个） */
    > * {
      & + & {
        margin-top: 16px;
      }
    }
  }
  aside {
    flex-grow: 1;
    margin-left: 16px;
  }
}

.line-chart-content {
  height: 325px;
}

</style>