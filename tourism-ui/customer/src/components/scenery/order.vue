<template>
  <div>

    <!--    搜索行-->
    <el-card style="height: auto;padding:0;" class="head">
      <div id="op" style="margin-bottom: 5px">
        <el-row type="flex" justify="center">
          <el-col :span="2" style="margin-left: 30px">
            <el-button style="margin: 5px 10px 5px" type="success" @click="dialogFormVisible = true" size="mini">请确认您的信息
            </el-button>
          </el-col>

        </el-row>
      </div>
    </el-card>

    <!-- 新增、修改时弹出的对话框  -->
    <el-dialog title="订单信息" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="torderForm" :model="form" label-width="auto"
               label-suffix=":">
        <el-form-item label="用户名" prop="person_name">
          <el-input v-model="form.person_name"></el-input>
        </el-form-item>

        <el-form-item label="手机号码" prop="person_tel">
          <el-input v-model="form.person_tel"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="person_email">
          <el-input v-model="form.person_email"></el-input>
        </el-form-item>
        <el-form-item label="说明" prop="torder_content">
          <el-input v-model="form.torder_content"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="saveUserInfo">取 消</el-button>
        <el-button type="primary" @click="onSubmit('torderForm')">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  export default {
    name: "order",
    data() {
      return {
        tableData: [],
        search: '',
        form: {
          torder_id: '',
          person_name: '',

          person_tel: '',
          person_email: '',
          torder_content:'',

        },
        dialogTableVisible: false,
        dialogFormVisible: false,
        formLabelWidth: '120px',
        rules: {
          person_name: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
          ],

           person_tel: [
            {required: true, message: '请输入手机号码', trigger: 'blur'},
            {
              pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
              message: '手机号码不正确',
              trigger: 'blur'
            }
          ],
         person_email: [
            {pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, message: '邮箱格式错误', trigger: 'blur'}
          ]
        },
        total: 0,
        pageSize: 6,
        pageNow: 1,
        searchName: '',
        multipleSelection: []
      };
    },
    methods: {
      //用来处理分页的相关方法
      findPage(page) {
        this.pageNow = page
        if (this.search == '' || this.search == null) {
          this.findAll(page, this.pageSize)
        } else {
          this.SearchByName(this.search, page, this.pageSize)
        }
      },
      findSize(size) {
        this.pageSize = size
        if (this.search == '' || this.search == null) {
          this.findAll(this.pageNow, size)
        } else {
          this.SearchByName(this.search, page, this.pageSize)
        }
      },
      saveUserInfo() {
        this.dialogFormVisible = false
      },
      handleEdit(index, row) {
        console.log(index, row);
        this.dialogFormVisible = true//展示编辑表单
        this.form = row
      }
      ,

      onSubmit(torderForm) {
        this.$refs[torderForm].validate((valid) => {
          if (valid) {
            //发送一个ajax请求
            this.$axios.post("/HorderController/saveOrUpdate", this.form).then(res => {
              console.log(res);
              if (res.data.status) {
                this.$message({
                  message: res.data.msg,
                  type: 'success'
                });
              } else {
                this.$message.error(res.data.msg);
              }
              //清空表单信息
              this.form = {};
              //刷新data
              this.findAll()
            });
          } else {
            this.$message.error("当前输入的数据不合法");
            return false;
          }
        });
        this.dialogFormVisible = false
      }
      ,

      handleSelectionChange(val) {
        this.multipleSelection = val;
        console.log(val);
      },

    },
    created() {
      this.findAll()
    },

  }
</script>

<style>
  .el-card {
    margin: 10px 10px 5px 10px;
  }

  .el-card__body {
    padding: 0;
  }

  .el-card .el-table {
    margin-top: 10px;
  }

  #op {
    margin-top: 10px;
  }

  .head .el-input--mini .el-input__inner {
    width: 200px;
    height: 40px;
    line-height: 40px;
  }

  .el-input--mini {
    width: auto;
  }

  label {
    width: auto;
  }
</style>
