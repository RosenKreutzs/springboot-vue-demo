<template>
  <div style="padding: 10px;">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">{{CQS}}</el-button><!--@click="add"表示点击这个按钮就触发add的函数-->
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    查找区域-->
    <div>
      <el-input v-model="search" placeholder="Please input" style="width: 30%"/>
      <el-button type="primary" style="margin-left: 5px">查询</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-table :data="tableData" border stripe style="width: 100%"><!-- tableData是外来变量-->
        <el-table-column prop="id" label="ID" width="180" sortable /><!--sortable是按data排序-->
        <el-table-column prop="username" label="用户名" width="180" /><!--prop是tableData对象的一个属性名；label是显示给页面的属性名 -->
        <el-table-column prop="nickName" label="昵称" /><!--后台的数据到前台来下划线都会变成驼峰-->
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="address" label="地址" />
        <el-table-column label="Operations">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
            >
            <el-popconfirm title="是否确定删除?">
              <template #reference>
                <el-button
                    size="small"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button
                >
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <!--分页-->
      <div style="margin: 10px 0">
        <el-pagination
            v-model:currentPage="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="[5, 10, 15, 20]"
            :small="small"
            :disabled="disabled"
            :background="background"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
        /><!--page-size一页多少条数据，page-sizes="[5, 10, 15, 20]"数据的间距，total="total"总页数-->
        <!--@size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            两个函数
            -->
        <el-dialog
            v-model="dialogVisible"
            title="提示"
            width="30%"
        >
          <el-form :model="form" label-width="30%">
            <el-form-item label="用户名">
              <el-input v-model="form.username" style="width: 80%" /><!--:model="form"是绑定的对象变量；username是from的属性-->
            </el-form-item>
            <el-form-item label="昵称">
              <el-input v-model="form.nickName" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="年龄">
              <el-input v-model="form.age" style="width: 80%"/>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio label="男" v-model="form.sex" size="large">男</el-radio>
              <el-radio label="女" v-model="form.sex" size="large">女</el-radio>
              <el-radio label="未知" v-model="form.sex" size="large">未知</el-radio>
            </el-form-item>
            <el-form-item label="地址">
              <el-input type="textarea" v-model="form.address" style="width: 80%"/>
            </el-form-item>
          </el-form>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save"
        >确定</el-button
        >
      </span>
          </template>
        </el-dialog>

      </div>

    </div>

  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {

  },
  data(){
    return{
      form:{},
      dialogVisible:false,
      search:'',
      currentPage:1,
      total:0,
      pageSize:10,
      CQS:'新增',
      tableData:[

      ]
    }
  },
  created() {
this.load()
  },
  methods:{
    handleEdit(){

    },
    handleSizeChange(){

    },
    handleCurrentChange(){

    },
    add(){
      this.dialogVisible=true;
      this.form={}//清空之前的记录
    },//this.dialogVisible=true是add函数触发时，改变变量
    save(){
      request.post("/user/save",this.form).then(res => {console.log(res)})

      //request是需要import引入的(写完时用Alt+Shift+Enter快捷引入)
      //request.post("接口的路径",传的参数)   //then是多余的。用来给后台看返回信息的
    },
    load(){
      request.get("/user/findPage",{params: {
          currentPage: 1,
          pageSize: 10,
          search:this.search
        }}).then(res => {
        console.log(res)
        this.tableData=res.data.records
      })
    }
  }

}
</script>
