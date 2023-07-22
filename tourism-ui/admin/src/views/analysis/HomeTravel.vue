<template>
  <div class="app-container">
    <div className="runs-paginator-flex-container">
      <img class="image1" src="../admin/img/dog1.jpg" />
      <span> {{ "欢迎你！" + formUser.name }}</span>
    </div>
    <div style="display: flex; width: 600px; background: pink">
      <span style="font-size: 40px; color: burlywood"
        >当前时间: {{ newTime }}
      </span>
    </div>

    <el-divider content-position="right"></el-divider>
    <h4>订单下单情况：</h4>
    <ve-pie :data="chartCar"></ve-pie>
  </div>
</template>
<script>
import { ApiService } from "@/services";
export default {
  name: "HomeTravel",
  data() {
    return {
      chartCar: {
        columns: ["key", "value"],
        rows: [
          { key: "2018-05-22", value: 32371 },
          { key: "2018-05-23", value: 12328 },
          { key: "2018-05-24", value: 92381 },
          { key: "2018-05-23", value: 12328 },
          { key: "2018-05-24", value: 92381 },
        ],
      },
      user: {},
      formUser: {},
      newTime: "",
    };
  },
  created() {
    this.user = sessionStorage.getItem("id");
    this.getChartsCar();
    this.getUser();
  },
  mounted() {
    this.getNowTime(); //进入页面调用该方法获取当前时间
    clearInterval(myTimeDisplay); //销毁之前定时器
    var myTimeDisplay = setInterval(() => {
      this.getNowTime(); //每秒更新一次时间
    }, 1000);
  },

  methods: {
    getChartsCar() {
      ApiService.get("/order/productTypeALL").then((res) => {
        if (res.data.code == 200) {
          this.chartCar.rows = res.data.data;
        } else {
          alert("获取数据失败");
        }
      });
    },
    getUser() {
      ApiService.get("/admin/findAdminById", {
        params: {
          id: this.user,
        },
      }).then((res) => {
        console.log(this.user);
        console.log(res);
        if (res.data.code == 200) {
          console.log(res);
          this.formUser = res.data.data;
        } else {
          alert("获取数据失败");
        }
      });
    },

    getNowTime() {
      var date = new Date();
      var time =
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1) +
        "-" +
        date.getDate() +
        " " +
        date.getHours() +
        ":" +
        date.getMinutes() +
        ":" +
        date.getSeconds();
      this.newTime = time;
    },
    //根据自己的需求，看要不要在时间不大于10的时候在前面补0，如果需要直接this.addZero(date.getMinutes()),其它与之相同，如果不需要删掉addZero（）方法即可。
    //小于10的拼接上0字符串
    addZero(s) {
      return s < 10 ? "0" + s : s;
    },
  },
};
</script>

<style>
.image1 {
  width: 100px;
  height: 100px;
  border-radius: 200px;
}
.el-descriptions {
  width: 90%;
  margin: 0 auto;
  text-align: center;
}
.runs-paginator-flex-container {
  flex: 1 1 auto;
  flex-direction: row-reverse;
  display: flex;
}
</style>
