import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/layout.vue'//路由的路径的写法一

const routes = [
  {//框架路由
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/home",//重定向(当访问"/"时直接跳到"/home")
    children:[//children是嵌套路由
      {
        path:'home',
        name:'Home',
        component:()=>import("@/views/HomeView")//路由的路径的写法二
      }
    ]
  },
  {
    path: '/Login',
    name: 'Login',
    component: ()=>import("@/views/Login")
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
