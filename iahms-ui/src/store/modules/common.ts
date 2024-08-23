

const state = () => ({
  theme: 'dark',
})

const getters = {
  theme: (state: any) => state.theme,
}

const mutations = {
  SET_THEME(state: any, theme: string) {
    state.theme = theme
    // 把主题存到localStorage
    localStorage.setItem('theme', theme)
    // 修改#app data-theme属性更改主题
    document.getElementById('app')?.setAttribute('data-theme', theme)
  }
}

const actions = {
  SET_THEME({ commit }: any, theme: string) {
    commit('SET_THEME', theme)
  }
}

export default {
  state,
  getters,
  mutations,
  actions,
}