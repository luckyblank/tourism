<template>
  <div class="app-container">
    <h4>用户状态分析：</h4>
    <ve-pie :data="chartData"></ve-pie>
    <h4>注册用户省份分析：</h4>
    <ve-pie :data="chartPorvice"></ve-pie>
  </div>
</template>

<script>
import { ApiService } from "@/services";
export default {
  name: "UserManager",
  data() {
    return {
      chartData: {
        columns: ["key", "value"],
        rows: [
          { key: "2018-05-22", value: 32371 },
          { key: "2018-05-23", value: 12328 },
          { key: "2018-05-24", value: 92381 },
        ],
      },
      chartPorvice: {
        columns: ["key", "value"],
        rows: [
          { key: "2018-05-22", value: 32371 },
          { key: "2018-05-23", value: 12328 },
          { key: "2018-05-24", value: 92381 },
        ],
      },
    };
  },
  created() {
    //获取广告列表数据
    this.getChartsData();
    this.getChartsPorvice();
  },
  methods: {
    getChartsData() {
      ApiService.get("/admin/stateAll").then((res) => {
        if (res.data.code == 200) {
          this.chartData.rows = res.data.data;
        } else {
          alert("获取数据失败");
        }
      });
    },
    getChartsPorvice() {
      ApiService.get("/admin/findProvinceByid").then((res) => {
        console.log(res.data);
        if (res.data.code == 200) {
          this.chartPorvice.rows = res.data.data;
        } else {
          alert("获取数据失败");
        }
      });
    },
  },
};
</script>

<style></style>
