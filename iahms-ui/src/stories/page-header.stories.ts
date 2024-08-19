import type { Meta, StoryObj } from '@storybook/vue3';
import PageHeader from '@/views/hotel/components/page-header.vue'
import { vueRouter } from 'storybook-vue3-router'

const meta = {
  title: 'Public/PageHeader/PageHeader',
  component: PageHeader,
  render: (args: any) => ({
    components: { PageHeader },
    setup() {
      return { args };
    },
    template: `<PageHeader :someArgs="args" />`,
  }),
  // Story的参数
  parameters: {
    layout: 'fullscreen',
  },
  // 组件的参数
  args: {

  },
  tags: ['autodocs'],
} satisfies Meta<typeof PageHeader>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {
}

Default.decorators = [vueRouter()]