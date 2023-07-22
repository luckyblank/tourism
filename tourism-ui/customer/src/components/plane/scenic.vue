<template>
  <div>
    <!-- 酒店顶部轮播图 -->
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in imgs" :key="item.src">
        <el-image style="width: 100%; height: 100%; " :src="item" fit="cover">
        </el-image>
      </el-carousel-item>
    </el-carousel>

    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;">景区预订</span>
    </el-divider>
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item class="input-title" label="请输入景区名">
        <el-input
          v-model="filter.spotName"
          type="search"
          placeholder="景区名"
        />
      </el-form-item>

      <el-form-item class="input-title" label="最低">
        <el-input v-model="filter.lowPrice" type="search" placeholder="最低" />
      </el-form-item>
      <el-form-item class="input-title" label="最高">
        <el-input v-model="filter.highPrice" type="search" placeholder="最高" />
      </el-form-item>

      <el-form-item label="景区星级">
        <el-select v-model="filter.spotStar">
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
        <el-button @click="filterquery">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="ReForm">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 酒店介绍表格-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column header-align="center" align="center" label="图片">
        <template width="40" slot-scope="scope">
          <el-image
            :src="require('@/assets/images/' + scope.row.imgUrl)"
            style="width: 100px; height: 100px"
          ></el-image>
        </template>
      </el-table-column>

      <el-table-column label="景区信息" width="620">
        <template slot-scope="scope">
          <span style="font-size: 23px; font-weight: bold; color:dodgerblue;">{{
            scope.row.spotName
          }}</span
          ><br />
          <span style="font-size: 20px; font-weight: bold; color: orange;"
            >{{ scope.row.spotStar }}星级</span
          ><br />
          <span>时间：{{ scope.row.openTime }}</span>
          <br />
          <span>地址：{{ scope.row.spotAddress }}</span
          ><br />
          <span>简介：{{ scope.row.spotIntro }}</span>
        </template>
      </el-table-column>

      <el-table-column label="">
        <template slot-scope="scope">
          <!-- 写span标签接收价格数据并拼接信息-->
          <span
            style="display: block;
          font-weight: bold; color: red;
          font-size: 17px;margin-bottom: 30px;"
            >￥{{ scope.row.ticketsMessage }}起</span
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
        require("../../assets/images/sceintSpot/1.jpg"),
        require("../../assets/images/sceintSpot/2.jpg"),
        require("../../assets/images/sceintSpot/3.jpg"),
        require("../../assets/images/sceintSpot/6.jpg"),
      ],
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
      filter: {},
      typeStar: [
        {
          value: 1,
          label: "一星",
        },
        {
          value: 2,
          label: "二星",
        },
        {
          value: 3,
          label: "三星",
        },
        {
          value: 4,
          label: "四星",
        },
        {
          value: 5,
          label: "五星",
        },
      ],
    };
  },
  created() {
    //获取酒店列表数据
    this.loadSpot();
  },
  methods: {
    //酒店详情页跳转的方法
    spotDetail(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/scenicDetail",
        query: {
          spotId: id,
        },
      });
    },

    //获取酒店的数据列表以及分页信息
    loadSpot() {
      console.log("获取酒店的数据列表以及分页信息"); //这个怎么都没输出啊
      //这样子，后买你你发请求，都这样子发，这样子才会走你自定义的，之前那个时APISERCER,你这里也可以改，就是名字问题而已
      newAxios
        .post("/scenic/listPage", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          spotName: this.filter.spotName,
          spotStar: this.filter.spotStar,
          lowPrice: this.filter.lowPrice,
          highPrice: this.filter.highPrice,
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
      this.loadSpot();
    },
    //重置查询
    ReForm() {
      this.filter.spotName = null;
      this.filter.spotStar = null;
      this.filter.lowPrice = "";
      this.filter.highPrice = "";
      this.loadSpot();
    },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadSpot();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadSpot();
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
