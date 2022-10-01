<template>
<div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden">
  <div style="width: 400px;margin: 150px auto">
    <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎登录</div>
    <el-form :model="form" size="normal" :rules="rules" ref="form" >
      <el-form-item prop="username">
        <el-input v-model="form.username" :prefix-icon="Avatar" />
      </el-form-item>
      <el-form-item prop="password">
        <el-input  v-model="form.password"  show-password  :prefix-icon="Lock"/>
      </el-form-item>
      <el-form-item>
        <div style="display: flex">
          <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 50%;" placeholder="请输入验证码"></el-input>
          <ValidCode @input="createValidCode" />
        </div>
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
import ValidCode from "@/components/ValidCode";
import Echart from "@/components/Echart";
// 统一导入el-icon图标
export default {
  name: "Login",
  components: {
    ValidCode
  },
  data(){
    return{
      form:{},
      rules:{
        username:[
          {required:true,message:"请输入用户名",trigger:"blur"},
        ],
        password:[
          {required:true,message:"请输入密码",trigger:"blur"},
        ]
      },
      validCode:''
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
      this.$refs['form'].validate((valid) => {//表格标签的ref属性的验证合法(就是密码和账号同时输入)
        if (valid) {
          if (!this.form.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }
          request.post("/user/login",this.form).then(res=>{
            if (res.code === '0') {//res.code是放回结果的一些性质,0就是成功
              this.$message({type:"success",message:"登录成功"})//this.$message是
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
              this.$router.push("/")//登录成功后进行页面跳转
            }
            else {
              this.$message({type:"error",message:res.msg})
            }
          })
        }
      })


    },
    createValidCode(data){//连接验证码组件的
    this.validCode=data
    }
  }

}
</script>

