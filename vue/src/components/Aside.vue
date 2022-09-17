<template>
  <div>
    <el-menu
      style="width: 200px;min-height: calc(100hv - 50px)"
      :default-active="path"
      router
      class="el-menu-vertical"><!-- default-active="path"表示默认高光index为user的并激活此对象-->
      <el-sub-menu index="1" v-if="user.role === 1">
        <template #title>系统管理</template>
        <el-menu-item index="/user" >用户管理</el-menu-item>
      </el-sub-menu>
      <el-menu-item index="/book">书籍管理</el-menu-item>
      <el-menu-item index="/news">新闻管理</el-menu-item>
    </el-menu>
  </div>
</template>
<script>
export default {
  name:"Aside",
  data(){
    path:this.$route.path
    user:{}
  },
  created() {
    let userStr=sessionStorage.getItem("user")||"{}"
    this.user=JSON.parse(userStr)
  }

}
</script>
<style scoped>

</style>