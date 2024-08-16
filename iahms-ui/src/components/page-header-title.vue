<script>
export default {
  name: 'PageHeaderTitle',
  props: {
    imgPath: {
      type: String,
      default: ''
    },
    titleText: {
      type: String,
      default: '请输入标题'
    },
    pageRoutePath: {
      type: String,
      default: '/'
    }
  },
  data() {
    return {}
  }
}
</script>

<template>
  <div class="page-header-title">
    <img
      class="logo-icon"
      style="cursor: pointer"
      :src="imgPath" alt="logo图片"
      @click="$router.push(pageRoutePath)"
    />
    <div class="title-text">
      {{ titleText }}
      <svg class="icon" aria-hidden="true">
        <use xlink:href="#icon-angle-up-l" />
      </svg>
    </div>
  </div>
</template>

<style scoped>
.page-header-title {
  display: flex;
  align-items: center;

  .logo-icon {
    cursor: pointer;
    width: 32px;
    height: 32px;
    margin: 0 10px;
  }

  .title-text {
    cursor: pointer;
    color: var(--base-c-text-1);

    .icon {
      width: 14px;
      height: 14px;
      margin-left: 6px;
    }
  }
}

</style>
