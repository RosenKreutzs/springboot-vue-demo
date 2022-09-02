import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'/*引入element-plus的架构*/
import zhCn from 'element-plus/es/locale/lang/zh-cn'/* 将element-plus的默认文字描述变为中文*/
import 'element-plus/dist/index.css'/*引入element-plus的样式*/

import "@/assets/css/global.css"/* 全局引入global样式 */


createApp(App).use(store).use(router).use(ElementPlus ,{locale: zhCn,size:'small'}).mount('#app')
