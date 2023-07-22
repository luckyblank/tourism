<template>
  <div>
    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;">我的保险</span>
    </el-divider>

    <!-- 订单介绍表格-->
    <el-table :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column label="订单信息" width="620">
        <template slot-scope="scope">
          <span style="font-size: 20px; font-weight: bold; color: orange;"
            >投保人：{{ scope.row.userName }}</span
          ><br />
          <span>购买时间：{{ scope.row.addTiem }}</span>
        </template>
      </el-table-column>

      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 设置“查看详情的按钮”-->
          <el-button
            size="medium"
            type="warning"
            @click="orderDetail(scope.row.id)"
            >查看详情</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import newAxios from "../../utils/axios";
export default {
  name: "hotel",
  data() {
    return {
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
      loading: false,
      typeState: [
        {
          value: 0,
          label: "未支付",
        },
        {
          value: 1,
          label: "已支付",
        },
      ],
    };
  },
  created() {
    //获取酒店列表数据
    this.loadOrder();
  },
  methods: {
    //酒店详情页跳转的方法
    orderDetail(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/myInsuranceDetail",
        query: {
          myInsuranceId: id,
        },
      });
    },

    //获取保险信息
    loadOrder() {
      newAxios
        .get("/insuranceUserInfo/getUserInfoList")
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data;
          } else {
            this.$message({
              message: "请联系客服！",
              type: "warning",
            });
          }
        });
    },
  },
};
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
