<template>
  <div>
    <el-row
      style="width: 100%; display: block; float: left; margin-top: 30px;margin-bottom: 30px;"
    >
    </el-row>

    <el-row>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >个人资料</span
              >
            </div>
            <div>
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >账号：</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                myInfo.username
              }}</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >姓名</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                myInfo.name
              }}</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >手机号码</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                myInfo.linkTel
              }}</span
              ><br /><br />
              <el-button size="medium" type="warning" @click="toOrderDetail()"
                >返回</el-button
              >
              <el-button
                size="medium"
                type="warning"
                @click="uodateInfo(myInfo.id)"
                >修改</el-button
              >
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import newAxios from "../../utils/axios";
export default {
  data() {
    return {
      myInfo: {},
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    toOrderDetail() {
      this.$router.back();
    },
    uodateInfo(id) {
      //  this.$router.replace("/updateMaterial");
      this.$router.push({
        path: "/updateMaterial",
        query: {
          userId: id,
        },
      });
    },
    getUser() {
      newAxios
        .get("/admin/findUserById")
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            console.log(res.data);
            this.myInfo = res.data;
          }
        })
        .catch((err) => {
          this.$message = "正在加载ing...有事请留言";
        });
    },
  },
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
