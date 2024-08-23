import type { Meta, StoryObj } from '@storybook/vue3';
import PageHeaderNav from '@/views/hotel/components/page-header-nav.vue'
import { vueRouter } from 'storybook-vue3-router'

const meta = {
  title: 'Public/PageHeader/PageHeaderNav',
  component: PageHeaderNav,
  tags: ['autodocs'],
} satisfies Meta<typeof PageHeaderNav>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
  args: {
    navList: [
      {
        name: '首页',
        path: '/hotel-index'
      },
      {
        name: '房态',
        path: '/hotel-state'
      },
      {
        name: '渠道',
        path: '/hotel-channel'
      },
      {
        name: '关于',
        path: '/hotel-about'
      },
      {
        name: '组件测试',
        path: '/component-test'
      }
    ]
  },
}
Default.decorators = [vueRouter()]