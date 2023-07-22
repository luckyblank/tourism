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
                >保险详情</span
              >
            </div>
            <div>
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >投保人：</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{ userName }}</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >投保时间</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{ addTiem }}</span
              ><br /><br />

              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >保险公司：</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">
                <span v-if="insuranceCompany == 0">人寿保险</span>
                <span v-else-if="insuranceCompany == 1">健康平安</span>
                <span v-else-if="insuranceCompany == 2">天天飘保险</span>
                <span v-else>一生无忧</span> </span
              ><br /><br />

              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >保险类型：</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">
                <span v-if="type == 0">全身险</span>
                <span v-else-if="type == 1">酒店保险</span>
                <span v-else-if="type == 2">景区保险</span>
                <span v-else-if="type == 3">出行保险</span>
                <span v-else>路线保险 </span> </span
              ><br /><br />
            </div>
            <el-button size="medium" type="warning" @click="toOrderDetail()"
              >返回上一级</el-button
            >
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
      type: "",
      insuranceCompany: "",
      userName: "",
      addTiem: "",
    };
  },
  created() {
    this.getHotelData();
  },
  methods: {
    toOrderDetail() {
      this.$router.push({
        path: "/myInsurance",
      });
    },

    getHotelData() {
      console.log("11111");
      console.log(this.$route.query.orderId);
      newAxios
        .get("insuranceUserInfo/getUserInfoById", {
          params: {
            id: this.$route.query.myInsuranceId,
          },
        })
        .then((res) => {
          console.log(res.data.data);
          this.addTiem = res.data.data.addTiem;
          this.userName = res.data.data.userName;
          this.type = res.data.data.type;
          this.insuranceCompany = res.data.data.insuranceCompany;
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
