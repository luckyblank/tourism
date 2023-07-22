<template>
  <div class="app-container">
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item class="input-title" label="管理名">
        <el-input
          v-model="filter.name"
          type="search"
          placeholder="请输入管理名"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="filterquery">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="ReForm">重置</el-button>
      </el-form-item>

      <el-form-item class="btn-add">
        <el-button type="primary" icon="el-icon-plus" @click="adminAdd"
          >新增管理员</el-button
        >
      </el-form-item>
    </el-form>

    <div class="table-container">
      <el-table
        ref="homeAdvertiseTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        border
      >
        <el-table-column label="编号" align="center" width="70">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>

        <el-table-column label="用户名" align="center" width="120">
          <template slot-scope="scope">{{ scope.row.username }}</template>
        </el-table-column>
        <el-table-column label="管理名" align="center" width="110">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>
        <!-- 
        <el-table-column label="密码" align="center" width="120">
          <template slot-scope="scope">{{ scope.row.password }}</template>
        </el-table-column> -->
        <el-table-column header-align="center" align="center" label="图片">
          <template slot-scope="scope">
            <el-image
              :src="require('@/assets/images/' + scope.row.icon)"
              style="width: 100px; height: 100px"
            ></el-image>
          </template>
        </el-table-column>

        <el-table-column label="联系电话" align="center" width="120">
          <template slot-scope="scope">{{ scope.row.linkTel }}</template>
        </el-table-column>

        <el-table-column
          label="创建时间"
          align="center"
          prop="addTime"
          :formatter="dateFormat"
          width="110"
        >
        </el-table-column>

        <el-table-column label="状态" align="center" width="80">
          <template slot-scope="scope">
            <span v-if="scope.row.state == 0">注销</span>
            <span v-else-if="scope.row.state == 1">发布</span>
            <span v-else>待发布</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center" width="230" :data="list1">
          <template slot-scope="scope">
            <!-- <el-button size="mini" type="primary" @click="delete01(scope.row)"
              >删除</el-button
            > -->
            <el-button
              size="mini"
              type="primary"
              @click="handleUpdate(scope.row)"
              >编辑</el-button
            >

            <el-button size="mini" type="primary" @click="handle(scope.row)"
              >改状态</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <el-input disabled v-model="list1.id" style="display: none"></el-input>
        <el-select v-model="list1.state">
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleUpdateStatus()"
            >确 定</el-button
          >
        </span>
      </el-dialog>

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[8, 16, 24, 32]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { ApiService } from "@/services";
export default {
  name: "User",
  data() {
    return {
      typeOptions: [
        {
          value: 0,
          label: "注销",
        },
        {
          value: 1,
          label: "发布",
        },
        {
          value: 2,
          label: "待发布",
        },
      ],
      filter: { name: "" },
      list: [],
      list1: { state: 1 },
      listLoading: false,
      tableData: [],
      pageSize: 8,
      pageNum: 1,
      total: 0,
      typeMap: {}, //保存广告位对象信息
      typeId: [],
      dialogVisible: false,
    };
  },
  created() {
    //获取广告列表数据
    this.loadAdmin();
  },
  methods: {
    handle(row) {
      this.dialogVisible = true;
      this.list1.id = row.id;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((res) => {
          done();
        })
        .catch((err) => {});
    },
    loadAdmin() {
      ApiService.post("/admin/listPageAdmin", {
        currentPage: this.pageNum,
        pageSize: this.pageSize,
        param: {
          name: this.filter.name,
        },
      })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.list = res.data;
            this.total = res.total;
            this.list1 = res.data;
          } else {
            this.$message = "获取数据失败";
          }
        });
    },

    //2.条件分页查询
    filterquery() {
      this.loadAdmin();
    },
    //3.重置
    ReForm() {
      this.filter.name = "";
      this.loadAdmin();
    },

    //4.修改状态
    handleUpdateStatus() {
      ApiService.post("/admin/updateStates", {
        id: this.list1.id,
        state: this.list1.state,
      })
        .then((res) => {
          this.loadAdmin();
          this.dialogVisible = false;
        })
        .catch((err) => {
          this.$message = "修改状态失败";
        });
    },
    //5.修改
    handleUpdate(row) {
      this.$router.push({
        path: "updateAdmin",
        query: { id: row.id },
      });
    },
    //6.新增
    adminAdd() {
      //this.$router.push({ name: "AddAdmin", path: "addAdmin" });
      this.$router.push({ name: "Register", path: "register" });
    },
    //7.删除
    delete01(row) {
      this.$confirm("你确定要删除吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        ApiService.get("/admin/deleteById", {
          params: {
            id: row.id,
          },
        })
          .then((res) => {
            this.loadAdmin();
          })
          .catch((err) => {
            this.$message = "修改状态失败";
          });
      });
    },

    dateFormat: function (row, column) {
      var date = row[column.property];

      if (date == undefined) {
        return "";
      }
      return moment(date).format("YYYY-MM-DD ");
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadAdmin();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadAdmin();
    },
  },
};
</script>

<style></style>
