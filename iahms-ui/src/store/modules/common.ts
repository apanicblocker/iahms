import store from "../index";

const state = () => ({
  // 主题
  theme: 'light',
  // 侧边栏是否折叠
  sidebarCollapsed: false,
  // 侧边栏默认展开的菜单index列表
  defaultOpeneds: {},
  // 数据字典
  dataDict: {}
})

const getters = {
  theme: (state: any) => state.theme,

  sidebarCollapsed: (state: any) => state.sidebarCollapsed,

  defaultOpeneds: (state: any) => (pageName: string) => {
    // 没有初始化的则初始化为一个空数组
    if (state.defaultOpeneds[pageName] === undefined) {
      store.commit('SET_DEFAULT_OPENEDS', {
        defaultOpeneds: [],
        pageName: pageName,
      })
      console.log(pageName + ' state.defaultOpeneds: ', state.defaultOpeneds);
    }
    return state.defaultOpeneds[pageName]
  },
}

const mutations = {
  // 设置主题
  SET_THEME(state: any, theme: string) {
    state.theme = theme
    // 把主题存到localStorage
    localStorage.setItem('theme', theme)
    // 修改#app data-theme属性更改主题
    document.getElementsByTagName('html')[0]?.setAttribute('data-theme', theme)
  },

  // 设置侧边栏状态
  SET_SIDEBAR_COLLAPSED(state: any, collapsed: boolean) {
    state.sidebarCollapsed = collapsed
  },
  
  // 设置默认展开的菜单
  SET_DEFAULT_OPENEDS(state: any, data: { pageName: string, defaultOpeneds: string[] }) {
    state.defaultOpeneds[data.pageName] = data.defaultOpeneds
  }
}

const actions = {
  // SET_THEME({ commit }: any, theme: string) {
  //   commit('SET_THEME', theme)
  // },
}

export default {
  state,
  getters,
  mutations,
  actions,
}