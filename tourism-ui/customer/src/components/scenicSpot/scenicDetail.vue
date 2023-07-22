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
          景区名：<span style="color: black;font-size: 23px;">
            {{ spotName }}</span
          >
        </h1>

        <h1 style="font-weight: bold;color: rgb(60, 66, 66); font-size: 18px;">
          地址：<span style="color: black;font-size: 23px;">
            {{ spotAddress }}</span
          >
        </h1>

        <div style="margin-top: 102px">
          <div style="display:flex">
            <span style="font-weight: bold; color: rgb(23, 24, 26);"
              >星级:</span
            >
            <el-rate
              v-model="spotStar"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{value}"
            >
            </el-rate>
          </div>
        </div>

        <br />

        <el-button size="medium" type="warning" @click="toSpotOrder()"
          >景区预订</el-button
        >

        <span style="margin-left: 10px;">
          <i class="el-icon-star-on" v-if="isCollect" @click="collected"></i>
          <i class="el-icon-star-off" v-else @click="collected"></i>
        </span>
      </el-col>
    </el-row>

    <el-row
      style="width: 100%; display: block; float: left; margin-top: 30px;margin-bottom: 30px;"
    >
    </el-row>
    <el-row>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >景区简介</span
              >
            </div>
            <div>
              <span class="textHead">入离时间</span><br />
              <div class="textBody">{{ openTime }}</div>
              <br />
              <span class="textHead">景区介绍</span><br />
              <div class="textBody" style="padding-bottom: 20px;">
                {{ spotIntro }}
              </div>
            </div>
          </el-card>
        </div>
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >景区预定事项</span
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
                儿童：身高1.2米（含）-1.4米（含）之间，儿童一定要在成年人的陪同下方可入园；
                老人：年龄60周岁（含）-65周岁（不含）之间凭有效证件，按生日计算年龄；
                学生：全日制大学本科及以下学历的本人有效学生证。
                以上信息仅供参考，具体以景区当日实际公示信息为准。
              </div>
              <span class="textHead">温馨提示</span>
              <div class="textBody" style="padding-bottom: 20px;">
                为保证入景区顺利，预订时请务必填写真实姓名、手机号码等信息。
              </div>
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple-light">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 20px;"
                >周边酒店</span
              >
            </div>
            <div v-for="i in 2" :key="i">
              <!-- 酒店介绍表格-->
              <el-table :data="hotelData" style="width: 100%">
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

                <el-table-column label="酒店信息" width="280">
                  <template slot-scope="scope">
                    <span
                      style="font-size: 23px; font-weight: bold; color:dodgerblue;"
                      >{{ scope.row.hotelName }}</span
                    ><br />
                    <span
                      style="font-size: 20px; font-weight: bold; color: orange;"
                      >{{ scope.row.hotelStar }}星级</span
                    ><br />
                    <span>地址：{{ scope.row.address }}</span
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
                      >￥{{ scope.row.price }}起</span
                    >

                    <!-- 设置“查看详情的按钮”-->
                    <el-button
                      size="medium"
                      type="warning"
                      @click="hotel_detail(scope.row)"
                      >预定</el-button
                    >
                  </template>
                </el-table-column>
              </el-table>
            </div>
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
      spotName: "",
      imgUrl: "",
      spotAddress: "",
      ticketsMessage: 0.0,
      linkPhone: "",
      spotIntro: "",
      spotStar: 3.0,
      setoffTime: 0,
      openTime: "",
      isCollect: false,
      hotelData: [],
    };
  },
  created() {
    this.getSpoyData();
    this.Collect();
    this.loadHotel();
  },
  methods: {
    back() {
      this.$router.back();
      //this.$router.replace("/scenic");
    },

    //判断是否收藏
    Collect() {
      newAxios
        .get("/collection/isCollection", {
          params: {
            productId: parseInt(this.$route.query.spotId),
            productType: 3,
          },
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.isCollect = res.data;
            this.getSpoyData();
          } else {
            this.isCollect = res.data;
            this.getSpoyData();
          }
        });
    },
    //收藏

    collected() {
      newAxios
        .post("/collection/save", {
          productId: parseInt(this.$route.query.spotId),
          productType: 3,
          title: this.spotName,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res.data);
          if (res.code == 200) {
            this.Collect();
            this.getSpoyData();
          } else {
            this.Collect();
            this.getSpoyData();
          }
        });
    },
    toSpotOrder() {
      this.$router.push({
        path: "/scenicOrder",
        query: {
          spotId: this.$route.query.spotId,
          price: this.ticketsMessage,
        },
      });
    },

    getSpoyData() {
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
          this.price = res.data.data.price;
        });
    },

    //获取酒店的数据列表以及分页信息
    loadHotel() {
      console.log("获取酒店的数据列表以及分页信息"); //这个怎么都没输出啊
      newAxios
        .post("/hotel/listPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          scenicSpotId: parseInt(this.$route.query.spotId),
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.hotelData = res.data;
          } else {
            alert("获取数据失败");
          }
        });
    },
    //酒店详情页跳转的方法
    hotel_detail(row) {
      this.$router.push({
        path: "/hotel_detail",
        query: {
          hotelid: parseInt(row.id),
          scenicSpotId: parseInt(row.scenicSpotId),
        },
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
  font-size: 18px;
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
