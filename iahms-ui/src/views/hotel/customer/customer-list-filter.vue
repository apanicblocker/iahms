<script lang="ts" setup>

import FilterItemContainer from '@/views/hotel/components/filter-item-container.vue'

interface CustomerFilterParam {
  consumeTimeStart: string,
  consumeTimeEnd: string,
  createTimeStart: string,
  createTimeEnd: string,
  amountConsumeStart: number | undefined,
  amountConsumeEnd: number | undefined,
  amountOrderTimesStart: number | undefined,
  amountOrderTimesEnd: number | undefined,
  birthdayStart: string,
  birthdayEnd: string,
  tagIds: number[],
  inBlacklist: boolean | undefined,
  channelId: number | undefined,
}
const isPanelClosed = ref<boolean>(false)
const filterData = reactive<CustomerFilterParam>({
  consumeTimeStart: '',
  consumeTimeEnd: '',
  createTimeStart: '',
  createTimeEnd: '',
  amountConsumeStart: undefined,
  amountConsumeEnd: undefined,
  amountOrderTimesStart: undefined,
  amountOrderTimesEnd: undefined,
  birthdayStart: '',
  birthdayEnd: '',
  tagIds: [],
  inBlacklist: undefined,
  channelId: undefined,
})
const consumeTimeRange = ref([
  filterData.consumeTimeStart,
  filterData.consumeTimeEnd,
])
const createTimeRange = ref([
  filterData.createTimeStart,
  filterData.createTimeEnd,
])
const birthdayRange = ref([
  filterData.birthdayStart,
  filterData.birthdayEnd,
])

const numberFormat = (value: number) => {
  if (value > 10000000) value = 10000000
  return value.toString()
        .replace(/[^0-9\.]/, '')         // 限制输入字符为数字和小数点
        .replace(/(\..*?)\.*/, '$1')     // 去除多余小数点
        .replace(/(\.\d{2}).*/, '$1')    // 截取小数点后两位为止
  // return value.toString()
  //       .replace(/[^0-9\.]/, '')
  //       .replace(/(?<=\..*?)\.*/, '')
  //       .replace(/(?<=\.\d{2}).*/, '')
}
const submitForm = () => {
  console.log('TODO：提交filterData: ', filterData)
}
</script>

<template>
  <div class="customer-list-filter" :class="{ isClosed: isPanelClosed }">
    <div class="filter-list">
      <FilterItemContainer class="filter-item large">
        <template #content>
          <div class="searchbar">搜索栏</div>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="上次下单时间">
        <template #content>
          <el-date-picker
            v-model="consumeTimeRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            style="width: 100%;"
            @blur="submitForm"
          />
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="成为客户时间">
        <template #content>
          <el-date-picker
            v-model="createTimeRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            style="width: 100%;"
          />
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="累计消费金额">
        <template #content>
          <div class="amount-consume">
            <el-input
              v-model="filterData.amountConsumeStart"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
            />
            <span style="width: 8px; margin: 0 16px;">-</span>
            <el-input
              v-model="filterData.amountConsumeEnd"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
            />
          </div>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="累计下单数">
        <template #content>
          <div class="amount-consume">
            <el-input
              v-model="filterData.amountOrderTimesStart"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
            />
            <span style="width: 8px; margin: 0 16px;">-</span>
            <el-input
              v-model="filterData.amountOrderTimesEnd"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
            />
          </div>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="生日">
        <template #content>
          <el-date-picker
            v-model="birthdayRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            style="width: 100%;"
          />
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="标签">
        <template #content>
          <el-select
            v-model="filterData.tagIds"
            popper-class="popper"
            placeholder="全部"
          >

          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="黑名单">
        <template #content>
          <el-select
            v-model="filterData.inBlacklist"
            popper-class="popper"
            placeholder="全部"
          >
            <el-option label="全部" value="" />
            <el-option label="已加入" :value="true" />
            <el-option label="未加入" :value="false" />
          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="来源渠道">
        <template #content>
          <el-select
            v-model="filterData.channelId"
            popper-class="popper"
            placeholder="全部"
          >
          <!-- 从数据库(总之不应该是写死的)导入 -->
          </el-select>
        </template>
      </FilterItemContainer>
      <div class="filter-item large"></div>
      <div class="filter-item large"></div>
      <div id="control-btns">
        <button class="close-btn" @click="() => isPanelClosed = !isPanelClosed">
          收起筛选 <el-icon class="el-icon"><ArrowUp /></el-icon>
        </button>
        <el-divider direction="vertical" style="height: 24px; margin: 0 8px;"/>
        <button class="import-customer">导入客户</button>
        <button class="export-detail">导出明细</button>
        <button class="add-customer">添加客户</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.customer-list-filter {
  position: relative;
  height: 256px;  /* 原本是auto自适应的，但是不写死没有动画过渡效果 */
  padding: 16px 16px 0 0;
  background-color: var(--base-c-bg-1);
  overflow: hidden;
  transition: height 0.5s ease;

  &.isClosed {
    height: 64px;
    .close-btn .el-icon{
      transform: rotate(180deg);
    }
  }
}

.filter-list {
  display: flex;
  flex-wrap: wrap;

  .filter-item {
    height: 32px;
    padding-left: 16px;
    margin-bottom: 16px;
  }
  .large { width: 50%; }
  .small { width: 25%; }
}

#control-btns {
  position: absolute;
  right: 0;
  bottom: 0;
  width: 50%;
  padding: 16px;
  background-color: var(--base-c-bg-1);
  text-align: right;

  button + button {
    margin-left: 12px;
  }
  .close-btn {
    padding: 0;
    border: none;
    color: var(--base-c-accent-1);

    &:hover {
      background-color: inherit;
      filter: brightness(1.1);
    }
    .el-icon {
      display: inline-block;
      vertical-align: middle;
      font-size: 14px;
      height: 21px;
      line-height: 24px;
    }
  }
  .export-detail {
    border: 1px solid var(--base-c-accent-1);
    color: var(--base-c-accent-1);

    &:hover {
      filter: brightness(1.1)
    }
  }
  .add-customer {
    background-color: var(--base-c-accent-1);
    border: 1px solid var(--base-c-accent-1);
    color: var(--base-c-bg-1);

    &:hover {
      filter: brightness(1.1);
    }
  }
}
</style>
