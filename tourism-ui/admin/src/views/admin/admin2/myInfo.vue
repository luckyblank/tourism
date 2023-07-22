<template>
  <div>
    <el-row
      style="
        width: 100%;
        display: block;
        float: left;
        margin-top: 30px;
        margin-bottom: 30px;
      "
    >
    </el-row>

    <el-row>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px"
                >个人资料</span
              >
            </div>
            <div style="display: flex">
              <el-image
                :src="require('@/assets/images/' + myInfo.icon)"
                style="width: 100px; height: 100px"
              ></el-image>
              <div>
                <span
                  style="
                    float: left;
                    font-weight: bold;
                    color: orange;
                    margin-left: 20px;
                  "
                  >账号</span
                >
                <span style="float: left; margin-left: 20px">{{
                  myInfo.username
                }}</span>
                <span
                  style="
                    float: left;
                    font-weight: bold;
                    color: orange;
                    margin-left: 20px;
                  "
                  >姓名</span
                >
                <span style="float: left; margin-left: 20px">{{
                  myInfo.name
                }}</span>
                <br /><br />
                <span
                  style="
                    float: left;
                    font-weight: bold;
                    color: orange;
                    margin-left: 20px;
                  "
                  >手机号码</span
                >
                <span style="float: left; margin-left: 20px">{{
                  myInfo.linkTel
                }}</span>
              </div>
              <div>
                <el-button
                  style="margin-left: 6px; margin-top: 144px"
                  size="medium"
                  type="warning"
                  @click="toUpdateInfo()"
                  >修改资料</el-button
                >
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ApiService } from "@/services";
export default {
  name: "MyInfo",
  data() {
    return {
      myInfo: {},
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    toUpdateInfo() {
      this.$router.push({
        path: "updateMaterial",
        query: { id: this.myInfo.id },
      });
    },

    getUser() {
      ApiService.get("/admin/findAdmin")
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
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
