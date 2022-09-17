import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/layout.vue'//路由的路径的写法一

const routes = [
  {//框架路由
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/User",//重定向(当访问"/"时直接跳到"/home")
    children:[//children是嵌套路由
      {
        path:'/User',
        name:'User',
        component:()=>import("@/views/User")//路由的路径的写法二
      },
      {
        path: '/Person',
        name: 'Person',
        component: ()=>import("@/views/Person")
      },
      {
        path: '/Book',
        name: 'Book',
        component: ()=>import("@/views/Book")
      },
      {
        path: '/News',
        name: 'News',
        component: ()=>import("@/views/News")
      },
    ]
  },
  {
    path: '/Login',
    name: 'Login',
    component: ()=>import("@/views/Login")
  },
  {
    path: '/Register',
    name: 'Register',
    component: ()=>import("@/views/Register")
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
