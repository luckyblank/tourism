<template>
  <div class="app-container">
    <h4>用户状态分析：</h4>
    <ve-pie :data="chartData"></ve-pie>
  </div>
</template>

<script>
import { ApiService } from "@/services";
export default {
  name: "Scient",
  data() {
    return {
      chartData: {
        columns: ["key", "value"],
        rows: [
          { key: "2018-05-22", value: 32371 },
          { key: "2018-05-23", value: 12328 },
          { key: "2018-05-24", value: 92381 }
        ]
      }
    };
  },
  created() {
    //获取广告列表数据
    this.getChartsData();
  },
  methods: {
    getChartsData() {
      ApiService.get("/scenic/stateAll").then(res => {
        if (res.data.code == 200) {
          this.chartData.rows = res.data.data;
        } else {
          alert("获取数据失败");
        }
      });
    }
  }
};
</script>

<style></style>
