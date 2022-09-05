<template>
<div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden">
  <div style="width: 400px;margin: 150px auto">
    <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎注册</div>
    <el-form :model="form" size="normal" :rules="rules" ref="form">
      <el-form-item prop="username">
        <el-input v-model="form.username" :prefix-icon="Avatar" />
      </el-form-item>
      <el-form-item prop="password">
        <el-input  v-model="form.password"  show-password  :prefix-icon="Lock"/>
      </el-form-item>
      <el-form-item prop="confirm">
        <el-input  v-model="form.confirm"  show-password  :prefix-icon="Lock"/>
      </el-form-item>
      <el-form-item >
       <el-button style="width: 100%;" type="primary" @click="register">注 册</el-button>
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
  name: "Register",
  data(){
    return{
      form:{},
      rules:{
        username:[
          {required:true,message:"请输入用户名",trigger:"blur"},
        ],
        password:[
          {required:true,message:"请输入密码",trigger:"blur"},
        ],
        confirm:[
          {required:true,message:"请确认密码",trigger:"blur"},
        ]
      }
    }
  },
  setup(){
    return {
      Avatar,
      Lock
    }
  },
  methods:{
    register(){
      if (this.form.password!==this.form.confirm){
        this.$message({type:"error",message:"两次密码输入不一致"})
        return
      }

      this.$refs['form'].validate((valid) => {//表格标签的ref属性的验证合法(就是密码和账号同时输入)
        if (valid) {
      request.post("/user/register",this.form).then(res=>{
        if (res.code === '0') {//res.code是放回结果的一些性质,0就是成功
          this.$message({type:"success",message:"注册成功"})//this.$message是
          this.$router.push("/Login")//登录成功后进行页面跳转
        }
        else {
          this.$message({type:"error",message:res.msg})
        }
      })
        }})
    }
  }

}
</script>

