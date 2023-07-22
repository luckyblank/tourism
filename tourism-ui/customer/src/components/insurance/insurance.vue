<template>
  <div>
    <!-- 酒店顶部轮播图 -->
    <el-carousel :interval="4000" type="card" height="280px">
      <el-carousel-item v-for="item in imgs" :key="item.src">
        <el-image style="width: 100%; height: 100%; " :src="item" fit="cover">
        </el-image>
      </el-carousel-item>
    </el-carousel>

    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;">保险预订</span>
    </el-divider>
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter" size="small">
      <el-form-item class="input-title" label="请输入保险名">
        <el-input v-model="filter.title" type="search" placeholder="保险名" />
      </el-form-item>

      <el-form-item class="input-title" label="最低">
        <el-input
          v-model="filter.price"
          type="search"
          placeholder="最低"
          size="small"
        />
      </el-form-item>

      <el-form-item label="保险类型">
        <el-select v-model="filter.type" @change="filterquery">
          <el-option
            v-for="item in typeStar"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
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
        <el-button @click="ReForm" size="small" type="info">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 保险介绍表格-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column header-align="center" align="center" label="图片">
        <template slot-scope="scope">
          <el-image
            :src="require('@/assets/images/' + scope.row.imgUrl)"
            style="width: 100px; height: 100px"
          ></el-image>
        </template>
      </el-table-column>

      <el-table-column label="保险信息" width="620">
        <template slot-scope="scope">
          <span
            style="font-size: 23px; font-weight: bold; color:palevioletred;"
          >
            {{ scope.row.title }}</span
          ><br />
          保险公司：
          <span v-if="scope.row.insuranceCompany == 0">人寿保险</span>
          <span v-else-if="scope.row.insuranceCompany == 1">健康平安</span>
          <span v-else-if="scope.row.insuranceCompany == 2">天天飘保险</span>
          <span v-else>一生无忧</span>
          <br />
          保险类型：
          <span v-if="scope.row.type == 0">全身险</span>
          <span v-else-if="scope.row.type == 1">酒店保险</span>
          <span v-else-if="scope.row.type == 2">景区保险</span>
          <span v-else-if="scope.row.type == 3">出行保险</span>
          <span v-else>路线保险</span>
          <br />
          <span>简介：{{ scope.row.resume }}</span>
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
            @click="spotDetail(scope.row.id)"
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
      imgs: [
        require("../../assets/images/insurance/3.png"),
        require("../../assets/images/insurance/8.png"),
        require("../../assets/images/insurance/10.png"),
        require("../../assets/images/insurance/13.png"),
      ],
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
      typeStar: [
        {
          value: 0,
          label: "全身险",
        },
        {
          value: 1,
          label: "酒店保险",
        },
        {
          value: 2,
          label: "景区保险",
        },
        {
          value: 3,
          label: "出行保险",
        },
        {
          value: 4,
          label: "路线保险",
        },
      ],
    };
  },
  created() {
    //获取酒店列表数据
    this.loadInsurance();
  },
  methods: {
    //酒店详情页跳转的方法
    spotDetail(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/insuranceDetail",
        query: {
          insuranceId: id,
        },
      });
    },

    //获取酒店的数据列表以及分页信息
    loadInsurance() {
      console.log("获取酒店的数据列表以及分页信息"); //这个怎么都没输出啊
      //这样子，后买你你发请求，都这样子发，这样子才会走你自定义的，之前那个时APISERCER,你这里也可以改，就是名字问题而已
      newAxios
        .post("/insurance/listPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          title: this.filter.title,
          price: parseInt(this.filter.price),
          type: this.filter.type,
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
    //条件分页查询
    filterquery() {
      this.loadInsurance();
    },
    //重置查询
    ReForm() {
      this.filter.type = null;
      this.filter.price = null;
      this.filter.title = "";
      this.loadInsurance();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadInsurance();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadInsurance();
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
