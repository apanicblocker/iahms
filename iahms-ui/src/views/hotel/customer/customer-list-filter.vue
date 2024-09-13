<script lang="ts" setup>

import { formatDate } from '@/utils/dataFormat'

import FilterItemContainer from '@/views/hotel/components/filter-item-container.vue'

interface CustomerFilterParam {
  keyword: string,
  consumeTimeStart: string,
  consumeTimeEnd: string,
  createTimeStart: string,
  createTimeEnd: string,
  amountConsumeStart: number | string,
  amountConsumeEnd: number | string,
  amountOrderTimesStart: number | string,
  amountOrderTimesEnd: number | string,
  birthdayStart: string,
  birthdayEnd: string,
  tagIds: number[],
  inBlacklist: boolean | string,
  channelId: number | string,
}

const filterTitleWidth = '84px'           // 筛选项的标题固定宽度
const isPanelClosed = ref<boolean>(false) // 面板关闭状态
const defaultTime: [Date, Date] = [       // 日期时间选择器的默认时间
  new Date(2000, 1, 1, 0, 0, 0),
  new Date(2000, 2, 1, 23, 59, 59),
] // '00:00:00', '23:59:59'
const consumeTimeRange = ref(['', '',])
const createTimeRange = ref(['', ''])
const birthdayRange = ref(['', ''])
const filterData = reactive<CustomerFilterParam>({
  keyword: '',
  consumeTimeStart: '',
  consumeTimeEnd: '',
  createTimeStart: '',
  createTimeEnd: '',
  amountConsumeStart: '',
  amountConsumeEnd: '',
  amountOrderTimesStart: '',
  amountOrderTimesEnd: '',
  birthdayStart: '',
  birthdayEnd: '',
  tagIds: [],
  inBlacklist: '',
  channelId: '',
})

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
  filterData.consumeTimeStart = formatDate(consumeTimeRange.value[0])
  filterData.consumeTimeEnd = formatDate(consumeTimeRange.value[1])
  filterData.createTimeStart = formatDate(createTimeRange.value[0])
  filterData.createTimeEnd = formatDate(createTimeRange.value[1])
  filterData.birthdayStart = formatDate(birthdayRange.value[0])
  filterData.birthdayEnd = formatDate(birthdayRange.value[1])
  console.log('TODO：提交filterData: ', filterData)
}
</script>

<template>
  <div class="customer-list-filter" :class="{ isClosed: isPanelClosed }">
    <div class="filter-list">
      <FilterItemContainer class="filter-item large">
        <template #content>
          <el-input class="searchbar"
            v-model="filterData.keyword"
            placeholder="搜索姓名、手机号"
            @change="submitForm"
          >
            <template #suffix>
              <el-icon style="color: var(--base-c-accent-2);"><Search /></el-icon>
            </template>
          </el-input>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="上次下单时间" :titleWidth="filterTitleWidth">
        <template #content>
          <el-date-picker
            v-model="consumeTimeRange"
            type="datetimerange"
            popper-class="popper"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            :default-time="defaultTime"
            style="width: 100%;"
            @change="submitForm"
          />
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="成为客户时间" :titleWidth="filterTitleWidth">
        <template #content>
          <el-date-picker
            v-model="createTimeRange"
            type="datetimerange"
            popper-class="popper"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            :default-time="defaultTime"
            style="width: 100%;"
            @change="submitForm"
          />
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="累计消费金额" :titleWidth="filterTitleWidth">
        <template #content>
          <div class="amount-consume">
            <el-input
              v-model="filterData.amountConsumeStart"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
              @change="submitForm"
            />
            <span style="width: 8px; margin: 0 16px;">-</span>
            <el-input
              v-model="filterData.amountConsumeEnd"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
              @change="submitForm"
            />
          </div>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="累计下单数" :titleWidth="filterTitleWidth">
        <template #content>
          <div class="amount-consume">
            <el-input
              v-model="filterData.amountOrderTimesStart"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
              @change="submitForm"
            />
            <span style="width: 8px; margin: 0 16px;">-</span>
            <el-input
              v-model="filterData.amountOrderTimesEnd"
              placeholder="请输入"
              :formatter="numberFormat"
              style="width: calc(50% - 20px);"
              @change="submitForm"
            />
          </div>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item large" title="生日" :titleWidth="filterTitleWidth">
        <template #content>
          <el-date-picker
            v-model="birthdayRange"
            type="daterange"
            popper-class="popper"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            style="width: 100%;"
            @change="submitForm"
          />
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="标签" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.tagIds"
            popper-class="popper"
            placeholder="全部"
            @change="submitForm"
          >

          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="黑名单" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.inBlacklist"
            popper-class="popper"
            placeholder="全部"
            @change="submitForm"
          >
            <el-option label="全部" value="" />
            <el-option label="已加入" :value="true" />
            <el-option label="未加入" :value="false" />
          </el-select>
        </template>
      </FilterItemContainer>
      <FilterItemContainer class="filter-item small" title="来源渠道" :titleWidth="filterTitleWidth">
        <template #content>
          <el-select
            v-model="filterData.channelId"
            popper-class="popper"
            placeholder="全部"
          >
          <!-- 从数据库导入(总之不应该是写死的) -->
          </el-select>
        </template>
      </FilterItemContainer>
      <!-- <div class="filter-item large"></div> -->
      <!-- <div class="filter-item large"></div> -->
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

  .searchbar {
    --el-input-bg-color: var(--base-c-primary-1);
    opacity: 0.8;
  }
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
