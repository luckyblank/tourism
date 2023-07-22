<template>
  <div>
    <el-table :data="tableData2" v-loading="loading" style="width: 50%">
      <el-table-column label="热门前五推荐" width="180">
        <template slot-scope="scope">
          <span style="font-size: 10px; font-weight: bold; color:black;">
            {{ scope.row.title }}</span
          >
          <span>
            <img
              style=" width: 20px;
  height: 25px;"
              src="../../assets/images/icon/收藏.png"
            />
            {{ scope.row.collects }}</span
          >
          <span>
            <img
              style=" width: 20px;
  height: 25px;"
              src="../../assets/images/icon/赞.png"
            />

            {{ scope.row.liked }}</span
          >
        </template>
      </el-table-column>
      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 设置“查看详情的按钮”-->
          <span
            style="width: 12px;color:blue"
            @click="orderDetail(scope.row.id)"
          >
            查看</span
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;"></span>
    </el-divider>

    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item class="input-title" label="请输入攻略名">
        <el-input
          v-model="filter.title"
          type="search"
          size="small"
          placeholder="攻略名"
        />
      </el-form-item>

      <el-form-item label="攻略类型">
        <el-select
          v-model="filter.productType"
          size="small"
          style="width: 110px;"
          @change="filterquery"
        >
          <el-option
            v-for="item in typeStategy"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button @click="filterquery" size="small" type="primary"
          >查询</el-button
        >
        <el-button @click="ReForm" size="small" type="info">重置</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="publish">发表攻略</el-button>
      </el-form-item>
    </el-form>

    <!-- 订单介绍表格-->
    <el-table :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column header-align="center" align="center" label="图片">
        <template slot-scope="scope">
          <el-image
            :src="require('@/assets/images/' + scope.row.imgUrl)"
            style="width: 100px; height: 100px"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="攻略信息" width="620">
        <template slot-scope="scope">
          <el-row>
            <el-image
              :src="require('@/assets/images/' + scope.row.icon)"
              style=" width: 50px;

  height: 50px;
  border-radius: 100px;"
            ></el-image>

            <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">
              {{ scope.row.userName }}</span
            >
          </el-row>
          <br />
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">
            {{ scope.row.title }}</span
          ><br />
          <span>
            <img
              style=" width: 25px;
  height: 25px;"
              src="../../assets/images/icon/收藏.png"
            />
            {{ scope.row.collects }}</span
          >
          <span> </span>
          <span>
            <img
              style=" width: 25px;
  height: 25px;"
              src="../../assets/images/icon/赞.png"
            />

            {{ scope.row.liked }}</span
          ><br />
          <span>{{ scope.row.addTime }}</span>
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
      tableData2: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
      loading: false,
      icon: "",
      typeStategy: [
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
    //获取攻略列表数据
    this.loadStrategy();
    this.listRanking();
  },
  methods: {
    //酒店详情页跳转的方法
    orderDetail(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/strategyDetail",
        query: {
          strategyId: id,
        },
      });
    },

    //获取酒店的数据列表以及分页信息
    loadStrategy() {
      newAxios
        .post("/strategy/listPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          title: this.filter.title,
          productType: this.filter.productType,
          state: 1,
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
    listRanking() {
      newAxios
        .post("/strategy/listRanking")
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.tableData2 = res.data;
          } else {
            alert("获取数据失败");
          }
        });
    },

    //条件分页查询
    filterquery() {
      this.loadStrategy();
    },
    //重置查询
    ReForm() {
      this.filter.title = null;
      this.filter.productType = null;
      this.loadStrategy();
    },
    //发表攻略
    publish() {
      this.$router.push({
        path: "/Detail",
      });
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadStrategy();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadStrategy();
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
