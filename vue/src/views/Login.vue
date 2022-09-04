<template>
<div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden">
  <div style="width: 400px;margin: 150px auto">
    <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎登录</div>
    <el-form :model="form" size="normal">
      <el-form-item >
        <el-input v-model="form.username" :prefix-icon="Avatar" />
      </el-form-item>
      <el-form-item prop="password">
        <el-input  v-model="form.password"  show-password  :prefix-icon="Lock"/>
      </el-form-item>
      <el-form-item >
       <el-button style="width: 100%;" type="primary" @click="login">登 录</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import { Avatar,Lock} from '@element-plus/icons-vue'
import request from "@/utils/request";
// 统一导入el-icon图标
export default {
  name: "Login",
  data(){
    return{
      form:{}
    }
  },
  setup(){
    return {
      Avatar,
      Lock
    }
  },
  methods:{
    login(){
      request.post("/user/login",this.form).then(res=>{
        if (res.code === '0') {//res.code是放回结果的一些性质,0就是成功
          this.$message({type:"success",message:"登录成功"})//this.$message是
          this.$router.push("/")//登录成功后进行页面跳转
        }
        else {
          this.$message({type:"error",message:res.msg})
        }
      })
    }
  }

}
</script>

