<template>
  <div>
    <el-row>
      <el-col :span="17">
        <div class="block">
          <el-carousel trigger="click" height="325px">
            <el-carousel-item v-for="item in 1" :key="item">
              <el-image
                style="width:100%;height: 100%; "
                :src="require('@/assets/images/' + imgUrl)"
              >
              </el-image>
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-col>

      <el-col :span="6" style="text-align: left; margin-left: 20px;">
        <h1 style="font-weight: bold; font-size: 25px; color: cornflowerblue;">
          景区名：{{ spotName }}
        </h1>
        <h1 style="font-weight: bold; font-size: 25px; color: cornflowerblue;">
          星级：{{ spotStar }}级
        </h1>
        <p>
          <span style="font-weight: bold;  color: orange;">地址：</span
          >{{ spotAddress }}
        </p>
        <p>
          <span style="font-weight: bold;  color: orange;">景区简介：</span
          >{{ spotIntro }}
        </p>
        <br />
        <el-button size="medium" type="warning" @click="toSpotOrder()"
          >景区预订</el-button
        >
      </el-col>
    </el-row>

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
                >景区简介</span
              >
            </div>
            <div>
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >入离时间</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{ openTime }}</span
              ><br /><br />

              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >景区介绍</span
              ><br /><br />
              <span style="float: left; margin-left: 10px;">{{
                spotIntro
              }}</span
              ><br /><br />
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >景区预定事项</span
              >
            </div>
            <div>
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
              >
                【免费政策】</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">
                儿童：身高1.2米（不含）以下；
                老人：65周岁（含）以上凭老年证或身份证； 军人：凭有效证件；
                残疾人：凭有效证件。 </span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >【优惠政策】</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">
                儿童：身高1.2米（含）-1.4米（含）之间，儿童一定要在成年人的陪同下方可入园；
                老人：年龄60周岁（含）-65周岁（不含）之间凭有效证件，按生日计算年龄；
                学生：全日制大学本科及以下学历的本人有效学生证。
                以上信息仅供参考，具体以景区当日实际公示信息为准。 </span
              ><br /><br />

              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >温馨提示</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">
                为保证入景区顺利，预订时请务必填写真实姓名、手机号码等信息。 </span
              ><br /><br />
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple-light">
          <el-card class="box-card" style="min-height: 700px;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 20px;"
                >周边酒店</span
              >
            </div>
            <div v-for="o in 4" :key="o" class="text item">
              {{ "列表内容 " + o }}
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
      spotName: "",
      imgUrl: "",
      spotAddress: "",
      ticketsMessage: 0.0,
      linkPhone: "",
      spotIntro: "",
      spotStar: 3,
      setoffTime: 0,
      openTime: "",
    };
  },
  created() {
    this.getHotelData();
  },
  methods: {
    toSpotOrder() {
      this.$router.push({
        path: "/scenicOrder",
        query: {
          spotId: this.$route.query.spotId,
          price: this.ticketsMessage,
        },
      });
    },

    getHotelData() {
      console.log("11111");
      console.log(this.$route.query.spotId);
      newAxios
        .get("/scenic/findById", {
          params: {
            id: this.$route.query.spotId,
          },
        })
        .then((res) => {
          console.log(res.data.data);
          this.spotName = res.data.data.spotName;
          this.imgUrl = res.data.data.imgUrl;
          this.spotAddress = res.data.data.spotAddress;
          this.ticketsMessage = res.data.data.ticketsMessage;
          this.linkPhone = res.data.data.linkPhone;
          this.spotIntro = res.data.data.spotIntro;
          this.spotStar = res.data.data.spotStar;
          this.openTime = res.data.data.openTime;
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
