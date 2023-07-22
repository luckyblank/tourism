<template>
  <div>
    <!--    搜索行-->
    <el-card style="height: auto;padding:0;" class="head">
      <div id="op" style="margin-bottom: 5px">
        <el-row type="flex" justify="center">
          <el-col :span="2" style="margin-left: 30px">
            <div style="display: flex;">
              <el-button
                style="margin: 5px 10px 5px"
                type="success"
                @click="dialogFormVisible = true"
                size="mini"
                >请确认预定的路线信息
              </el-button>

              <el-button
                style="margin: 5px 10px 5px"
                type="info"
                size="mini"
                @click="back"
                >返回
              </el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>

    <!-- 新增、修改时弹出的对话框  -->
    <el-dialog
      title="订单信息"
      :visible.sync="dialogFormVisible"
      style="width: 1000px;margin: 0 auto; "
    >
      <el-form
        ref="torderForm"
        :model="form"
        label-width="auto"
        label-suffix=":"
      >
        <el-form-item label="总价格"> {{ this.price }}￥</el-form-item>

        <el-form-item label="预定路线时间">
          <el-date-picker
            v-model="form.setoffTime"
            type="date"
            placeholder="输入预定路线时间"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item label="用户名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="form.linkTel"></el-input>
        </el-form-item>
        <el-form-item label="人数">
          <el-input v-model="form.peopleCount"></el-input>
        </el-form-item>
        <el-form-item label="说明">
          <el-input v-model="form.requirement"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="saveUserInfo">取 消</el-button>
        <el-button type="primary" @click="onSubmit('torderForm')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import newAxios from "../../utils/axios";
export default {
  name: "hotelOrder",
  data() {
    return {
      tableData: [],
      search: "",
      form: {
        linkTel: "",
        name: "",
        peopleCount: 1,
        requirement: "",
      },
      price: 0,
      // id: 0,
      setoffTime: "",
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: "120px",

      total: 0,
      pageSize: 6,
      pageNow: 1,

      multipleSelection: [],
      sumprice: 0,
    };
  },
  created() {
    this.price = parseInt(this.$route.query.price);
    //this.id = parseInt(this.$route.query.routeId);
  },
  methods: {
    saveUserInfo() {
      this.dialogFormVisible = false;
    },

    back() {
      this.$router.back();
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.dialogFormVisible = true; //展示编辑表单
      this.form = row;
    },
    onSubmit(torderForm) {
      newAxios
        .post("/order/userOrder", {
          productId: parseInt(this.$route.query.routeId),
          orderType: 3,
          peopleCount: this.form.peopleCount,
          price: this.price * this.form.peopleCount,
          requirement: this.form.requirement,
          setoffTime: this.form.setoffTime,
          linkTel: this.form.linkTel,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.dialogFormVisible = false;
            this.$message({
              type: "success",
              message: "预定成功!",
            });
            this.$router.push({
              path: "/routeDetail",
              query: {
                routeId: parseInt(this.$route.query.routeId),
              },
            });
          } else {
            this.$message({
              type: "info",
              message: "预定失败！请留言联系客服",
            });
          }
        });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(val);
    },
  },
};
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
