import type { Meta, StoryObj } from '@Storybook/react'
import PageHeader from './page-header.vue'

const meta = {
  title: 'Public/PageHeader/PageHeader',
  component: PageHeader,
  tags: ['autodocs'],
} satisfies Meta<typeof PageHeader>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
}