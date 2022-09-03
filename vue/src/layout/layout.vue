<template>
  <div>
    <!-- 头部-->
    <Header /><!-- components文件夹中的组件，用的时候就导入然后用标签方式使用-->

    <!-- 主体-->
    <div style="display: flex">
      <!--侧边栏-->
      <Aside/>
      <!-- 内容区域-->
      <router-view style="flex: 1" />
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
  name: "layout",
  components:{
    Header,/* Header是组件名，写了export default后，选中组件名按Alt+Enter生成 import Header from "@/components/Header";*/
    Aside
  }
}
</script>

