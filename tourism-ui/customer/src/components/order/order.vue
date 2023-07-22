<template>
  <div>
    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;">我的订单</span>
    </el-divider>
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item label="支付状态">
        <el-select
          v-model="filter.state"
          size="small"
          style="width: 120px;margin-right: 8px;"
          @change="filterquery"
        >
          <el-option
            v-for="item in typeState"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>

        <el-form-item label="订单类型">
          <el-select
            v-model="filter.productType"
            size="small"
            style="width: 120px;"
            @change="filterquery"
          >
            <el-option
              v-for="item in typeOrder"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form-item>
      <el-form-item>
        <el-button
          @click="filterquery"
          type="primary"
          icon="el-icon-search"
          size="small"
          >查询</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button
          @click="ReForm"
          type="info"
          class="el-icon-delete"
          size="small"
          >重置</el-button
        >
      </el-form-item>
    </el-form>
    <!-- 订单介绍表格-->
    <el-table :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column label="订单信息" width="620">
        <template slot-scope="scope">
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">
            订单编号：
            {{ scope.row.id }}</span
          ><br />
          <span style="font-size: 20px; font-weight: bold; color: orange;"
            >产品名：{{ scope.row.productName }}</span
          ><br />
          <span>价格：{{ scope.row.fee }}</span>
          <br />
          <span>订单号：{{ scope.row.icCode }}</span
          ><br />
          <span>下单时间：{{ scope.row.orderTime }}</span>
        </template>
      </el-table-column>

      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 写span标签接收价格数据并拼接信息-->
          <span
            style="display: block;
          font-weight: bold; color: red;
          font-size: 17px;margin-bottom: 30px;"
            >￥{{ scope.row.fee }}</span
          >
          <!-- 设置“查看详情的按钮”-->
          <el-button
            size="medium"
            type="warning"
            @click="orderDetail(scope.row.id)"
            >查看详情</el-button
          >
          <!-- 设置“查看详情的按钮”-->
          <el-button
            size="medium"
            type="success"
            @click="toPay(scope.row)"
            v-if="scope.row.state === 0"
            >去支付</el-button
          >
          <!--支付”-->
          <el-button
            size="medium"
            type="danger"
            @click="refund(scope.row)"
            v-if="scope.row.state === 1 && scope.row.productType !== 4"
            >去退款</el-button
          >
          <el-button
            size="medium"
            type="danger"
            @click="detete(scope.row)"
            v-if="scope.row.productType !== 4"
            >删除订单</el-button
          >
          <span
            style="font-size: 23px; font-weight: bold; color:#F56C6C;"
            v-if="scope.row.state === 4"
          >
            已退款 </span
          ><br />
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[5, 10, 20, 30]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
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
        {
          value: 2,
          label: "已取消",
        },
        // {
        //   value: 3,
        //   label: "退款中",
        // },
        {
          value: 4,
          label: "已退款",
        },
      ],
      typeOrder: [
        {
          value: 0,
          label: "出行",
        },
        {
          value: 1,
          label: "酒店",
        },
        {
          value: 2,
          label: "路线",
        },
        {
          value: 3,
          label: "景点",
        },
        {
          value: 4,
          label: "保险",
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
        path: "/orderDetail",
        query: {
          orderId: id,
        },
      });
    },

    //7.退款
    refund(row) {
      this.$confirm("你确定要退款吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        newAxios
          .post("/order/refunded", {
            state: row.state,
            id: row.id,
          })
          .then((res) => {
            this.$message({
              type: "success",
              message: "退款成功!",
            });
            this.loadOrder();
          })
          .catch((err) => {
            this.$message({
              type: "info",
              message: "退款失败！请留言联系客服",
            });
          });
      });
    },

    //7.支付
    toPay(row) {
      this.$confirm("你确定要支付吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        newAxios
          .post("/order/toBePaid", {
            productId: row.productId,
            productType: row.productType,
            state: 0,
            id: row.id,
          })
          .then((res) => {
            this.$message({
              type: "success",
              message: "支付成功!",
            });

            this.loadOrder();
          })
          .catch((err) => {
            this.$message({
              type: "info",
              message: "支付失败！请留言联系客服",
            });
          });
      });
    },

    //删除订单
    detete(row) {
      this.$confirm("你确定要删除订单吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        newAxios
          .get("/order/deleteByUser", {
            params: {
              id: row.id,
            },
          })
          .then((res) => {
            this.$message({
              type: "success",
              message: "删除订单成功!",
            });
            this.loadOrder();
          })
          .catch((err) => {
            this.$message({
              type: "info",
              message: "删除订单失败！请留言联系客服",
            });
          });
      });
    },

    //获取酒店的数据列表以及分页信息
    loadOrder() {
      console.log("获取酒店的数据列表以及分页信息"); //这个怎么都没输出啊
      //这样子，后买你你发请求，都这样子发，这样子才会走你自定义的，之前那个时APISERCER,你这里也可以改，就是名字问题而已
      newAxios
        .post("/order/orderListPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          state: this.filter.state,
          productType: this.filter.productType,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data;
            this.total = res.total;
          } else {
            alert("获取数据失败");
          }
        });
    },
    //条件分页查询
    filterquery() {
      this.loadOrder();
    },
    //重置查询
    ReForm() {
      this.filter.state = null;
      this.filter.productType = null;
      this.loadOrder();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadOrder();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadOrder();
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
