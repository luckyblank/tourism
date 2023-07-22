<template>
  <div>
    <el-row>
      <div style="display: flex;">
        <i class="el-icon-arrow-left" @click="backCar"> </i>
        <span style="font-weight: bold; font-size: 25px;margin-left: 8px; "
          >车次:</span
        >
        <span
          style="font-weight: bold; font-size: 25px; color: cornflowerblue;text-indent: 4px;"
        >
          {{ carList.trainNumber }}
        </span>
        <span style="font-weight: bold; font-size: 25px;text-indent: 12px;">
          票价:</span
        >

        <span
          style="font-weight: bold; font-size: 25px; color: cornflowerblue;text-indent: 4px;"
        >
          ￥{{ carList.price }}
        </span>
        <el-button
          size="medium"
          type="warning"
          @click="toSpotOrder()"
          style="margin-left: 3px;"
          >预订</el-button
        >
      </div>
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
                >订票信息</span
              >
            </div>
            <div>
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >出发地-目的地</span
              >
              <span style="float: left; margin-left: 20px;">{{
                carList.title
              }}</span>
              <br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >出发日期</span
              >
              <span style="float: left; margin-left: 10px;">{{
                carList.startDate
              }}</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >时刻</span
              >
              <span style="float: left; margin-left: 10px;"
                >{{ carList.timeRange }}-{{ carList.timeEnd }} </span
              ><br /><br />'

              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >换乘站</span
              >
              <span style="float: left; margin-left: 10px;"
                >{{ carList.interchangeStation }} </span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >换乘时间</span
              >
              <span style="float: left; margin-left: 10px;"
                >{{ carList.intermediateTime }} </span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >经停站</span
              >
              <span style="float: left; margin-left: 10px;">{{
                carList.intermediateStop
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
                >火车票预定事项</span
              >
            </div>
            <div>
              <span class="textHead">【免费政策】</span>
              <div class="textBody">
                儿童：身高1.2米（不含）以下；
                老人：65周岁（含）以上凭老年证或身份证； 军人：凭有效证件；
                残疾人：凭有效证件。
              </div>
              <span class="textHead">【优惠政策】</span>
              <div class="textBody">
                儿童：身高1.2米（含）-1.4米（含）之间，儿童一定要在成年人的陪同下购票；
                老人：年龄60周岁（含）-65周岁（不含）之间凭有效证件，按生日计算年龄；
                学生：全日制大学本科及以下学历的本人有效学生证。
                以上信息仅供参考，具体以购票当日实际公示信息为准。
              </div>
              <span class="textHead">温馨提示</span>

              <div class="textBody" style="padding-bottom: 20px;">
                为购票顺利，预订时请务必填写真实姓名、手机号码等信息。
              </div>
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
      carList: {},
    };
  },
  created() {
    this.getHotelData();
  },
  methods: {
    toSpotOrder() {
      this.$router.push({
        path: "/carOrder",
        query: {
          car_id: this.$route.query.car_id,
          price: this.carList.price,
          setoffTime: this.carList.startDate,
        },
      });
    },
    backCar() {
      this.$router.push({
        path: "/car",
      });
    },

    getHotelData() {
      let that = this;
      newAxios
        .get("/car/findCarById", {
          params: {
            id: this.$route.query.car_id,
          },
        })
        .then((res) => res.data)
        .then((res) => {
          if (res.code === 200) {
            this.carList = res.data;
          }
        })
        .catch((err) => {
          this.$message("正在加载ing.......");
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
