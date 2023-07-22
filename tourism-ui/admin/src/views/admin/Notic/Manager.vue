<template>
  <div class="app-container">
    <!-- 表单部分 -->
    <div class="table-container">
      <el-table
        ref="homeAdvertiseTable"
        :data="list"
        style="width: 100%"
        v-loading="listLoading"
        borderloading
      >
        <el-table-column label="编号" align="center" width="80">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>
        <el-table-column
          label="标题"
          align="center"
          width="90"
          :show-overflow-tooltip="true"
        >
          >
          <template slot-scope="scope">{{ scope.row.title }}</template>
        </el-table-column>
        <el-table-column
          label="公告内容"
          align="center"
          width="190"
          :show-overflow-tooltip="true"
        >
          <template slot-scope="scope">{{ scope.row.content }}</template>
        </el-table-column>

        <el-table-column label="公告状态" align="center" width="90">
          <template slot-scope="scope">
            <span v-if="scope.row.state == 0">注销</span>
            <span v-else-if="scope.row.state == 1">发布</span>
            <span v-else>待发布</span>
          </template>
        </el-table-column>

        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          :formatter="dateFormat"
          width="120"
        >
        </el-table-column>
        <el-table-column
          label="修改时间"
          align="center"
          prop="updateTime"
          :formatter="dateFormat"
          width="120"
        >
        </el-table-column>

        <el-table-column label="操作" align="center" :data="list1">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              @click="delete01(scope.row.id)"
              >删除</el-button
            >
            <el-button size="mini" type="primary" @click="handleAdd(scope.row)"
              >新增</el-button
            >
            <el-button
              size="mini"
              type="primary"
              @click="handleUpdate(scope.row)"
              >修改</el-button
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
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { ApiService } from "@/services";
export default {
  name: "OrderManager",
  data() {
    return {
      list: [],
      list1: { state: 1 },
      listLoading: false,
      tableData: [],

      typeMap: {}, //保存广告位对象信息
      typeId: [],
      dialogVisible: false,
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
    };
  },
  created() {
    //获取订单列表数据
    this.loadOrder();
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
    loadOrder() {
      ApiService.get("/notic/getByNoticList")
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.list = res.data;
            this.list1 = res.data;
          } else {
            this.$message = "正在加载ing.....";
          }
        });
    },

    //4.修改状态
    handleUpdateStatus() {
      ApiService.post("/notic/updateStates", {
        id: this.list1.id,
        state: this.list1.state,
      })
        .then((res) => {
          this.loadOrder();
          this.dialogVisible = false;
        })
        .catch((err) => {
          this.$message = "修改状态失败";
        });
    },
    //5.修改
    handleUpdate(row) {
      this.$message = "修改";

      this.$router.push({
        //path: "/layout/updateOrder",
        name: "UpdateNotic",
        query: { id: row.id },
      });
    },

    //新增
    handleAdd(row) {
      this.$message = "新增";
      this.$router.push({
        //path: "/layout/updateOrder",
        name: "AddNotic",
      });
    },

    //7.删除
    delete01(id) {
      this.$confirm("你确定要删除公告吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        ApiService.get("/notic/delete", {
          params: {
            id: id,
          },
        })
          .then((res) => {
            this.loadOrder();
          })
          .catch((err) => {
            this.$message = "删除失败";
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
  },
};
</script>

<style></style>
