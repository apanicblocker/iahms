import type { Meta, StoryObj } from '@Storybook/react'

import pageHeaderTitle from './page-header-title.vue'

const meta = {
  title: 'Public/PageHeader/PageHeaderTitle',
  component: pageHeaderTitle,
  tags: ['autodocs'],
} satisfies Meta<typeof pageHeaderTitle>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
  args: {
    imgPath: '/src/assets/vue.svg',
    titleText: '标题',
  },
}