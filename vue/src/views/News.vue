、<template>
  <div style="padding: 10px;">
    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">{{CQS}}</el-button><!--@click="add"表示点击这个按钮就触发add的函数-->
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <!--    查找区域-->
    <div>
      <el-input v-model="search" placeholder="Please input" style="width: 30%" clearable/>
      <el-button type="primary" style="margin-left: 5px " @click="load">查询</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-table :data="tableData" border stripe style="width: 100%">
        <el-table-column prop="id" label="ID" width="180" sortable />
        <el-table-column prop="title" label="标题" width="180" />
        <el-table-column prop="author" label="作者" width="180" />
        <el-table-column prop="time" label="时间" width="180" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button size="small" @click="detail(scope.row)"
            >详情</el-button
            >
            <el-button size="small" @click="handleEdit(scope.row)"
            >编辑</el-button
            >
            <el-popconfirm title="是否确定删除?" @confirm="handleDelete(scope.row.id)"><!--气泡绑定按钮的事件用@confirm-->
              <template #reference>
                <el-button
                    size="small"
                    type="danger"
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
            width="50%"
        >
          <el-form :model="form" label-width="20%">
            <el-form-item label="标题">
              <el-input v-model="form.title" style="width: 80%" /><!--:model="form"是绑定的对象变量；username是from的属性-->
            </el-form-item>
            <div id="div1"></div>
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

        <el-dialog
            v-model="vis"
            title="详情"
            width="50%"
        >
          <el-card>
            <div v-html="details.content" style="min-height: 100px"></div>
          </el-card>
        </el-dialog>

      </div>

    </div>

  </div>
</template>

<script>

import request from "@/utils/request";
import E from "wangeditor";
let editor;

export default {
  name: 'News',
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
      CQS:"新增",
      tableData:[

      ],
      details:{},
      vis:false
    }
  },
  created() {
    this.load()
  },
  mounted() {//只有页面元素都执行后才会执行mounted的代码
  },
  methods:{
    detail(row){
      this.details=row
      this.vis=true
    },
    handleEdit(row){//编辑数据
      this.form=JSON.parse(JSON.stringify(row))//对数据进行深拷贝与其他form对象隔开
      this.dialogVisible=true//打开弹窗
      this.$nextTick(()=>{//是否有异步元素的判断
        // 关联弹窗里面的div，new一个 editor对象
        editor =new E('#div1')
        editor.create()
        editor.txt.html(row.content)//存入row.content信息到editor
      })
    },
    handleSizeChange(pageSize){//改变当前每页的个数触发
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){//改变当前页码触发
      this.currentPage=pageNum
      this.load()
    },
    add(){
      this.dialogVisible=true;
      this.form={}//清空之前的记录
      this.$nextTick(()=>{//是否有异步元素的判断
        // 关联弹窗里面的div，new一个 editor对象
        editor =new E('#div1')
        // 配置 server 接口地址
        editor.config.uploadImgServer = 'http://localhost:9090/files/editor/upload'
        editor.config.uploadFileName = "file"  // 设置上传参数名称
        editor.create();
      })

    },//this.dialogVisible=true是add函数触发时，改变变量
    save(){
      this.form.content=editor.txt.html()//键文本编辑信息给content
      if (this.form.id){//更新
        request.put("/news/updata",this.form).then(res => {console.log(res)
          if (res.code==='0') {//res.code是放回结果的一些性质,0就是成功
            this.$message({type:"success",message:"更新成功"})//this.$message是自带的弹窗
          }
          else {
            this.$message({type:"error",message:res.msg})
          }
        })
      }
      else {//新增
        let userStr=sessionStorage.getItem("user")||"{}"
        let user=JSON.parse(userStr)
        this.form.author=user.nickName
        request.post("/news/save",this.form).then(res => {console.log(res)
          if (res.code === '0') {//res.code是放回结果的一些性质,0就是成功
            this.$message({type:"success",message:"新增成功"})//this.$message是自带的弹窗
          }
          else {
            this.$message({type:"error",message:res.msg})
            console.log(res)
          }
        })

        //request是需要import引入的(写完时用Alt+Shift+Enter快捷引入)
        //request.post("接口的路径",传的参数)   //then是多余的。用来给后台看返回信息的
      }
      this.load()
    },
    load(){
      request.get("/news/findPage",{params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
          search:this.search
        }}).then(res => {
        console.log(res)
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },
    handleDelete(id){
      console.log(id)
      request.delete("/news/delNews/"+id).then(res=>{
        if (res.code === '0') {//res.code是放回结果的一些性质,0就是成功
          this.$message({type:"success",message:"删除成功"})//this.$message是自带的弹窗
        }
        else {
          this.$message({type:"error",message:res.msg})
        }
      })
      this.load()
    },
    filesUploadSuccess(res) {
      console.log(res)
      this.form.cover = res.data
    },

  }

}
</script>
