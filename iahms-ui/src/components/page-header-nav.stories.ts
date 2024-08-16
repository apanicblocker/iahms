import type { Meta, StoryObj } from '@Storybook/react'

import pageHeaderNav from './page-header-nav.vue'

const meta = {
  title: 'Public/PageHeader/PageHeaderNav',
  component: pageHeaderNav,
  tags: ['autodocs'],
} satisfies Meta<typeof pageHeaderNav>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
  args: {
    navList: [
      {
        name: '首页',
        path: '/',
      },
    ]
  },
}