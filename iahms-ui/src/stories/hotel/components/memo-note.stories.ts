import type { Meta, StoryObj } from '@storybook/vue3';
import { vueRouter } from 'storybook-vue3-router'
import MemoNote from '@/views/hotel/components/memo-note.vue';

const meta = {
  title: 'Hotel/Components/MemoNote',
  component: MemoNote,
  tags: ['autodocs'],
} satisfies Meta<typeof MemoNote>

export default meta
type Story = StoryObj<typeof meta>

export const Default: Story = {

}
Default.decorators = [vueRouter()]