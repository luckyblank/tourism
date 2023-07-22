<template>
  <div>
    <!-- 分界线-->
    <el-divider>
      <span style=" font-size: 25px; font-weight: bold;"></span>
    </el-divider>
    <!-- 表单部分 -->
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
          <span
            style="color:blue;font-size:20px"
            @click="orderDetail(scope.row.id)"
            >查看</span
          >

          <span
            style="color:pink;margin-left:8px;margin-right:8px;font-size:20px"
            @click="updateBy(scope.row.id)"
            >修改</span
          >
          <span
            style="color:blue; font-size:20px"
            @click="deleteBy(scope.row.id)"
            >删除</span
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
    };
  },
  created() {
    //获取攻略列表数据
    this.loadStrategy();
  },
  methods: {
    updateBy() {},

    //删除攻略
    deleteBy(id) {
      this.$confirm("此操作将永久删除该文章, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          newAxios
            .post("/strategy/deleteByUser", {
              productId: id,
              productType: 5,
            })
            .then((res) => res.data)
            .then((res) => {
              console.log(id);
              console.log(res);
              if (res.code == 200) {
                this.loadStrategy();
                this.$message({
                  type: "success",
                  message: "删除成功!",
                });
              } else {
                alert("获取数据失败");
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    //攻略详情页跳转的方法
    orderDetail(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/StrategyDetail",
        query: {
          strategyId: id,
        },
      });
    },

    //修改攻略跳转的方法
    updateBy(id) {
      console.log("id:" + id);
      this.$router.push({
        path: "/updateStrategy",
        query: {
          strategyId: id,
        },
      });
    },

    //获取酒店的数据列表以及分页信息
    loadStrategy() {
      newAxios
        .post("/strategy/listMyStrategy", {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
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
      this.loadStrategy();
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
