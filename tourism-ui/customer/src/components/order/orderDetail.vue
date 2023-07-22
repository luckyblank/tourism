<template>
  <div>
    <el-row v-if="listOrder.productType != 0">
      <el-col :span="17">
        <div class="block">
          <el-carousel trigger="click" height="325px">
            <el-carousel-item>
              <el-image
                style="width:100%;height: 100%; "
                :src="require('@/assets/images/' + listOrder.imgUrl)"
              >
              </el-image>
            </el-carousel-item>
          </el-carousel>
        </div>
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
                >订单详情</span
              >
            </div>
            <div>
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >商品名：</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                listOrder.productName
              }}</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >价格：</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;"
                >￥{{ listOrder.fee }}元</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >订单号</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                listOrder.id
              }}</span
              ><br /><br />

              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >下单编号</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                listOrder.orderCode
              }}</span
              ><br /><br />

              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >下单时间</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                listOrder.orderTime
              }}</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >预定时间</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                listOrder.setoffTime
              }}</span
              ><br /><br />
              <span
                style="float: left; font-weight: bold; color: orange; margin-left: 20px;"
                >要求</span
              ><br /><br />
              <span style="float: left; margin-left: 20px;">{{
                listOrder.requirement
              }}</span
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
      setoffTime: "",
      imgUrl: "",
      requirement: "",
      setoffTime: 0.0,
      productName: "",
      orderCode: "",
      spotStar: 3,
      id: 0,
      orderTime: "",
      productType: "",
      listOrder: {},
    };
  },
  created() {
    this.getHotelData();
  },
  methods: {
    toOrderDetail() {
      this.$router.push({
        path: "/myOrder",
      });
    },

    getHotelData() {
      console.log("11111");
      console.log(this.$route.query.orderId);
      newAxios
        .get("/order/findOrderById", {
          params: {
            id: this.$route.query.orderId,
          },
        })
        .then((res) => {
          console.log(res.data.data);
          this.listOrder = res.data.data;

          // this.id = res.data.data.id;
          // this.imgUrl = res.data.data.imgUrl;
          // this.setoffTime = res.data.data.setoffTime;
          // this.orderCode = res.data.data.orderCode;
          // this.orderTime = res.data.data.orderTime;
          // this.productName = res.data.data.productName;
          // this.requirement = res.data.data.requirement;
          // this.fee = res.data.data.fee;
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
