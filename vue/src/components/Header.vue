<template>
  <!--
  Header.vue文件是页面的一个组件(页面组件都放在components文件夹中)
  --><div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color:blue ;">后台管理</div>
    <div style="flex:1"></div>
    <div style="width: 100px">
      {{nickName}}
      <el-dropdown>
    <span class="el-dropdown-link" style="padding-top:15px">
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/Login')">退出系统</el-dropdown-item>
            <!-- @click="$router.push('/Login')" 就是点击这个按钮就跳转页面-->
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
let userStr=sessionStorage.getItem("user")||"{}"
let user=JSON.parse(userStr)
export default {
  name: "Header",
  data() {
    return {
      nickName:""
    }
  },
  created() {
    console.log(user)
    this.nickName=user.nickName
  }
}
</script>

<style scoped>

</style>