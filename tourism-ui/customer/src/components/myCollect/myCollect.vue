<template>
  <div>
    <el-form class="actions" :inline="true" :model="filter">
      <!-- 表单部分 -->
      <el-form-item label="收藏类别">
        <el-select v-model="filter.productType" @change="filterquery">
          <el-option
            v-for="item in produceType"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="filterquery">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="ReForm">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 订单介绍表格-->
    <el-table :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column label="收藏信息" width="620">
        <template slot-scope="scope">
          <el-row>
            <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">
              {{ scope.row.userName }}</span
            >
          </el-row>
          <br />
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">
            标题:{{ scope.row.title }}</span
          ><br />
          收藏类别:
          <span v-if="scope.row.productType == 1">酒店</span>
          <span v-if="scope.row.productType == 2">路线</span>
          <span v-if="scope.row.productType == 3">景点</span>

          <span v-if="scope.row.productType == 4">保险</span>
          <span v-if="scope.row.productType == 5">攻略</span>

          <br />
          <span>收藏时间:{{ scope.row.addTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 设置“查看详情的按钮”-->
          <el-button
            size="success"
            type="warning"
            @click="hotelDetail(scope.row.productId)"
            v-if="scope.row.productType == 1"
            >查看详情</el-button
          >
          <el-button
            size="success"
            type="warning"
            @click="routeDetail(scope.row.productId)"
            v-if="scope.row.productType == 2"
            >查看详情</el-button
          >
          <el-button
            size="success"
            type="warning"
            @click="spotDetail(scope.row.productId)"
            v-if="scope.row.productType == 3"
            >查看详情</el-button
          >
          <el-button
            size="success"
            type="warning"
            @click="insuranceDetail(scope.row.productId)"
            v-if="scope.row.productType == 4"
            >查看详情</el-button
          >
          <el-button
            size="success"
            type="warning"
            @click="strategyceDetail(scope.row.productId)"
            v-if="scope.row.productType == 5"
            >查看详情</el-button
          >
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
      icon: "",
      produceType: [
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
        {
          value: 5,
          label: "攻略",
        },
      ],
    };
  },
  created() {
    //获取收藏列表数据
    this.loadCollect();
  },
  methods: {
    //收藏详情页跳转的方法
    //1.酒店
    hotelDetail(id) {
      this.$router.push({
        path: "/hotel_detail",
        query: {
          hotelid: id,
        },
      });
    },

    //1.路线
    routeDetail(id) {
      this.$router.push({
        path: "/routeDetail",
        query: {
          routeId: id,
        },
      });
    },

    //3.景点
    spotDetail(id) {
      this.$router.push({
        path: "/scenicDetail",
        query: {
          spotId: id,
        },
      });
    },
    //4.保险
    insuranceDetail(id) {
      this.$router.push({
        path: "/insuranceDetail",
        query: {
          insuranceId: id,
        },
      });
    },
    //4.保险
    strategyceDetail(id) {
      this.$router.push({
        path: "/strategyDetail",
        query: {
          strategyId: id,
          collect: 1,
        },
      });
    },

    loadCollect() {
      newAxios
        .post("/collection/findCollectionByPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          productType: this.filter.productType,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data;
            this.total = res.total;
          } else {
            this.$message("还没收藏哦V.V");
          }
        });
    },

    delete() {
      newAxios
        .post("/collection/findCollectionByPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          productType: this.filter.productType,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData = res.data;
            this.total = res.total;
          } else {
            this.$message("找不到该类别收藏哦");
          }
        });
    },
    //条件分页查询
    filterquery() {
      this.loadCollect();
    },
    ReForm() {
      this.filter.productType = null;
      this.loadCollect();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadCollect();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadCollect();
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
