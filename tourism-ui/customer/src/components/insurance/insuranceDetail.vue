<template>
  <div>
    <span style="color: cadetblue; margin-right: 1000px;" @click="back"
      >返回</span
    >
    <el-row>
      <el-col :span="17">
        <div class="block">
          <el-carousel trigger="click" height="300px">
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
          保险名：{{ title }}
        </h1>

        <p>
          <span style="font-weight: bold;  color: orange;">保险介绍：</span
          >{{ resume }}
        </p>
        <br />
        <el-button size="medium" type="warning" @click="toInsuarnce()"
          >保险预订</el-button
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
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >保险简介</span
              >
            </div>

            <div>
              <span class="textHead">保险期限</span><br />
              <div class="textBody">人的一生</div>
              <br />
              <span class="textHead">保险介绍</span><br />
              <div class="textBody">{{ resume }}</div>
              <br />
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-card class="box-card" style="width: 99%;">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold; color: green; font-size: 25px;"
                >保险预定事项</span
              >
            </div>

            <div>
              <span class="textHead">适用人群</span><br />
              <div class="textBody">3-65周岁</div>
              <br />
              <span class="textHead">保单形式</span><br />
              <div class="textBody">电子保单 纸质保单</div>
              <br />
              <span class="textHead">投保须知</span><br />
              <div class="textBody">
                1、本保险每位被保险人限投一份，多投无效。
              </div>
              <div class="textBody">
                2、本保险意外伤害医疗保险责任及高风险运动意外医疗责任绝对免赔额为300元。
              </div>

              <div class="textBody">
                3、本保险意外伤害住院津贴责任免赔天数有3天。
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
      title: "",
      imgUrl: "",
      spotAddress: "",
      price: 0.0,
      linkPhone: "",
      resume: "",
      type: 3,
      setoffTime: 0,
      insuranceCompany: "",
      isCollect: false,
    };
  },
  created() {
    this.getHotelData();
    this.Collect();
  },
  methods: {
    toInsuarnce() {
      this.$router.push({
        path: "/insuranceOrder",
        query: {
          insuranceId: this.$route.query.insuranceId,
          price: this.price,
        },
      });
    },
    toSpotOrder() {
      this.$router.push({
        path: "/routeOrder",
        query: {
          routeId: this.$route.query.insuranceId,
          price: this.price,
        },
      });
    },
    //判断是否收藏
    Collect() {
      newAxios
        .get("/collection/isCollection", {
          params: {
            productId: parseInt(this.$route.query.insuranceId),
            productType: 4,
          },
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.isCollect = res.data;
            this.getHotelData();
          } else {
            this.isCollect = res.data;
            this.getHotelData();
          }
        });
    },
    //收藏

    collected() {
      newAxios
        .post("/collection/save", {
          productId: parseInt(this.$route.query.insuranceId),
          productType: 4,
          title: this.title,
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
    getHotelData() {
      console.log(this.$route.query.insuranceId);
      newAxios
        .get("/insurance/findInsuranceById", {
          params: {
            id: this.$route.query.insuranceId,
          },
        })
        .then((res) => {
          console.log(res.data.data);
          this.title = res.data.data.title;
          this.imgUrl = res.data.data.imgUrl;
          this.price = res.data.data.price;
          this.resume = res.data.data.resume;
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
