import type { Meta, StoryObj } from '@storybook/vue3';
import { vueRouter } from 'storybook-vue3-router'
import ThemeSwitch from '@/views/hotel/components/theme-switch.vue';

const meta = {
  title: 'Hotel/Components/ThemeSwitch',
  component: ThemeSwitch,
  tags: ['autodocs'],
} satisfies Meta<typeof ThemeSwitch>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {

}
Default.decorators = [vueRouter()]