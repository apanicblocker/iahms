import type { Meta, StoryObj } from '@storybook/vue3';
import PageHeaderTitle from '@/views/hotel/components/page-header-title.vue'
import { vueRouter } from 'storybook-vue3-router'

const meta = {
  title: 'Public/PageHeader/PageHeaderTitle',
  component: PageHeaderTitle,
  tags: ['autodocs'],
  argTypes: {
    imgPath: {
      control: 'text',
    },
    titleText: {
      control: 'text',
    },
    pageRoutePath: {
      control: 'text',
    },
  },
  args: {
    imgPath: '/src/assets/vue.svg',
    titleText: '标题',
    pageRoutePath: '/'
  },
} satisfies Meta<typeof PageHeaderTitle>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
  // args: {
  //   imgPath: '/src/assets/vue.svg',
  //   titleText: '标题',
  // },
}

// 导入路由（以至于不会跳转报错）
Default.decorators = [vueRouter()]