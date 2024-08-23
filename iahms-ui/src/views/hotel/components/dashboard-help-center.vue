<script>
export default {
  name: 'DashboardHelpCenter',
  data() {
    return {
      dataList: [
        {
          title: '营业类报表',
          subTitle: '如何按渠道查看订单数据？',
          routePath: '/hotel/helpCenter/question/...',
        },
        {
          title: '订单操作',
          subTitle: '如何操作订单住宿录入？',
          routePath: '/hotel/helpCenter/question/...',
        },
        {
          title: '房态',
          subTitle: '如何筛选日历房态显示的房型？',
          routePath: '/hotel/helpCenter/question/...',
        },
        {
          title: '房态',
          subTitle: '如何查看房情表？',
          routePath: '/hotel/helpCenter/question/...',
        },
      ]
    }
  }
}
</script>

<template>
  <div class="dashboard-help-center">
    <div
      class="help-center-item"
      v-for="(item, index) in dataList"
      :key="index"
      @click="$router.push(item.routePath)"
    >
      <div class="title">{{ item.title }}</div>
      <div class="sub-title">{{ item.subTitle }}</div>
    </div>
  </div>
</template>

<style scoped>
.dashboard-help-center {
  display: flex;
}

.help-center-item {
  cursor: pointer;
  flex: 0.25;
  padding: 8px 0 8px 16px;
  background-color: var(--base-c-bg-2);
  text-align: left;

  & + & {
    margin-left: 16px;
  }
  .title {
    font-size: 14px;
    font-weight: 400;
  }
  .sub-title {
    font-size: 12px;
    opacity: 0.6;
  }
  &:hover {
    filter: brightness(1.1);
    background-color: var(--base-c-bg-3);
  }
}

</style>
