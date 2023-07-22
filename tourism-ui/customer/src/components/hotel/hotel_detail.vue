<template>
  <div>
    <span style="color: cadetblue; margin-right: 1000px;" @click="back"
      >返回</span
    >
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

      <el-col
        :span="7"
        style="text-align: left; padding:20px;background: #fcfcfc;"
      >
        <h1 style="font-weight: bold;color: rgb(60, 66, 66); font-size: 18px;">
          酒店名：<span style="color: black;font-size: 23px;">
            {{ hotelName }}</span
          >
        </h1>

        <h1 style="font-weight: bold;color: rgb(60, 66, 66); font-size: 18px;">
          地址：<span style="color: black;font-size: 23px;">
            {{ address }}</span
          >
        </h1>

        <div style="margin-top: 102px">
          <div style="display:flex">
            <span style="font-weight: bold; color: rgb(23, 24, 26);"
              >星级:</span
            >
            <el-rate
              v-model="score"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{value}"
            >
            </el-rate>
          </div>
        </div>

        <br />

        <el-button size="medium" type="warning" @click="toHotelOrder(hotelId1)"
          >酒店预订</el-button
        >
        <span style="margin-left: 10px;">
          <i class="el-icon-star-on" v-if="isCollect" @click="collected"></i>
          <i class="el-icon-star-off" v-else @click="collected"></i>
        </span>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="13" style="margin-top: 20px;">
        <div class="grid-content bg-purple">
          <div class="block">
            <el-date-picker
              v-model="value55"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              @change="gethoteldate"
            >
            </el-date-picker>
            <span class="demonstration">共{{ datacalc }}晚{{ price }}元</span>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row
      style="width: 100%; display: block; float: left; margin-top: 30px;margin-bottom: 30px;"
    >
    </el-row>

    <el-row>
      <el-col :span="13">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >酒店简介</span
              >
            </div>
            <div>
              <span class="textHead">入离时间</span><br />
              <div class="textBody">
                <span>入住时间:14:00以后 离店时间:14:00以前</span>
              </div>
              <br />
              <span class="textHead">酒店介绍</span><br />
              <div class="textBody" style="padding-bottom: 20px;">
                {{ hotelIntro }}
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="13">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >设施与服务</span
              >
            </div>

            <div>
              <span class="textHead">预定须知</span>
              <br />
              <div class="textBody">
                {{ bookingInstructions }}
              </div>
              <br />
              <span class="textHead">房型信息</span>
              <div class="textBody">
                {{ facilityInformation }}
              </div>
              <span class="textHead">基本设施</span>
              <div class="textBody" style="padding-bottom: 20px;">
                {{ facilityInformation }}
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="11">
        <div class="grid-content bg-purple-light">
          <el-card class="box-card" style="min-height: 700px;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 20px;"
                >周边景点</span
              >
            </div>

            <!-- 酒店介绍表格-->
            <el-table :data="tableData" style="width: 100%">
              <el-table-column
                header-align="center"
                align="center"
                label="图片"
              >
                <template slot-scope="scope">
                  <el-image
                    :src="require('@/assets/images/' + scope.row.imgUrl)"
                    style="width: 100px; height: 100px"
                  ></el-image>
                </template>
              </el-table-column>

              <el-table-column label="景区信息" width="270">
                <template slot-scope="scope">
                  <span
                    style="font-size: 23px; font-weight: bold; color:dodgerblue;"
                    >{{ scope.row.spotName }}</span
                  ><br />
                  <span
                    style="font-size: 20px; font-weight: bold; color: orange;"
                    >{{ scope.row.spotStar }}星级</span
                  ><br />
                  <span>时间：{{ scope.row.openTime }}</span>
                  <br />
                  <span>地址：{{ scope.row.spotAddress }}</span
                  ><br />
                </template>
              </el-table-column>

              <el-table-column label="">
                <template slot-scope="scope">
                  <!-- 写span标签接收价格数据并拼接信息-->
                  <span
                    style="display: block;
          font-weight: bold; color: red;
          font-size: 17px;margin-bottom: 30px;"
                    >￥{{ scope.row.ticketsMessage }}起</span
                  >
                  <!-- 设置“查看详情的按钮”-->
                  <el-button
                    size="medium"
                    type="warning"
                    @click="spotDetail(scope.row.id)"
                    >预定</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
            <!-- <div v-for="o in 4" :key="o" class="text item">
              {{ "列表内容 " + o }}
            </div> -->
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
      value5: 4,
      value55: "",
      hotelName: "",
      imgUrl: "",
      address: "",
      price: 0.0,
      bookingInstructions: "",
      datacalc: " ",
      scenicSpotId: 1,
      roomTypeInformation: "",
      facilityInformation: "",
      linkPhone: "",
      score: 3,
      hotelId1: 0,
      setoffTime: 0,
      dateHotel: 2020 - 11 - 19,
      isCollect: false,
      tableData: [],
    };
  },
  created() {
    this.getHotelData();
    this.hotelId1 = parseInt(this.$route.query.hotelid);
    this.timestampToTime(this.setoffTime);
    this.Collect();
    this.loadSpot();
  },
  methods: {
    toHotelOrder(id) {
      this.$router.push({
        path: "/hotelOrder",
        query: {
          hotelid: id,
          day: this.datacalc,
          sumPrice: this.price,
          setoffTime: this.setoffTime,
        },
      });
    },

    //

    // scenicSpotId
    //景区详情页跳转的方法
    spotDetail(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/scenicDetail",
        query: {
          spotId: id,
        },
      });
    },
    //获取景区的数据列表以及分页信息
    loadSpot() {
      console.log(this.$route.query.scenicSpotId);
      newAxios
        .get("/hotel/listSpotHotel", {
          params: {
            scenicSpotId: parseInt(this.$route.query.scenicSpotId),
          },
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data;
          } else {
            console.log(parseInt(this.$route.query.scenicSpotId));
          }
        });
    },

    //判断是否收藏
    Collect() {
      newAxios
        .get("/collection/isCollection", {
          params: {
            productId: parseInt(this.$route.query.hotelid),
            productType: 1,
          },
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.isCollect = res.data;
            this.getHotelData();
            // this.$message.success("收藏成功");
          } else {
            this.isCollect = res.data;
            this.getHotelData();
            //this.$message.success("取消收藏成功");
          }
        });
    },
    //收藏

    collected() {
      newAxios
        .post("/collection/save", {
          productId: parseInt(this.$route.query.hotelid),
          productType: 1,
          title: this.hotelName,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res.data);
          if (res.code == 200) {
            this.Collect();
            this.getHotelData();
          } else {
            this.Collect();
            this.getHotelData();
          }
        });
    },

    back() {
      this.$router.back();
    },
    timestampToTime(timestamp) {
      var date = new Date(timestamp * 1000); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + "-";
      var M =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "-";
      var D =
        (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
      var h =
        (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
      var m =
        (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
        ":";
      var s =
        date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      //   this.dateHotel=Y + M + D + h + m + s;
      return Y + M + D + h + m + s;
    },
    gethoteldate() {
      let date = new Date(this.value55[0]);
      let y = date.getFullYear();
      let m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = date.getDate();
      d = d < 10 ? "0" + d : d;
      const time = y + "-" + m + "-" + d;
      date = new Date(time);
      this.datacalc = date.getTime();
      this.setoffTime = time;
      console.log(time);
      date = new Date(this.value55[1]);
      y = date.getFullYear();
      m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      d = date.getDate();
      d = d < 10 ? "0" + d : d;
      const time1 = y + "-" + m + "-" + d;
      date = new Date(time1);
      this.datacalc = (date.getTime() - this.datacalc) / 86400000;
      if (this.datacalc == 0) {
        this.datacalc++;
      }
      this.price = this.price * this.datacalc;
      console.log(this.datacalc);
    },

    getHotelData() {
      console.log(this.$route.query.hotelid);
      newAxios
        .get("/hotel/findHotelById", {
          params: {
            id: this.$route.query.hotelid,
          },
        })
        .then((res) => {
          console.log(res.data.data);
          this.value5 = res.data.data.score;
          //this.score = res.data.data.score;
          this.hotelName = res.data.data.hotelName;
          this.imgUrl = res.data.data.imgUrl;
          this.address = res.data.data.address;
          this.price = res.data.data.price;
          this.hotelIntro = res.data.data.hotelIntro;
          this.linkPhone = res.data.data.linkPhone;
          this.roomTypeInformation = res.data.data.roomTypeInformation;
          this.bookingInstructions = res.data.data.bookingInstructions;
          this.scenicSpotId = res.data.data.scenicSpotId;
          this.facilityInformation = res.data.data.facilityInformation;
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
.textHead {
  float: left;
  font-weight: bold;
  color: orange;
}
.textBody {
  float: left;
  text-align: left;
  font-size: 16px;
  text-indent: 32px;
}
</style>
