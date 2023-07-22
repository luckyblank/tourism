<template>
  <div class="app-container">
    <!-- 表单部分 -->
    <el-form class="actions" :inline="true" :model="filter">
      <el-form-item class="input-title" label="请输入角色名">
        <el-input v-model="filter.name" type="search" placeholder="角色名" />
      </el-form-item>
      <el-form-item>
        <el-button @click="filterquery">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="ReForm">重置</el-button>
      </el-form-item>

      <el-form-item class="btn-add">
        <el-button type="primary" icon="el-icon-plus" @click="handAdd()"
          >新增角色</el-button
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
        <el-table-column label="id" align="center" width="100">
          <template slot-scope="scope">{{ scope.$index + 1 }}</template>
        </el-table-column>

        <el-table-column label="角色名" align="center" width="150">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>

        <el-table-column label="创建人" align="center" width="150">
          <template slot-scope="scope">{{ scope.row.createBy }}</template>
        </el-table-column>
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          :formatter="dateFormat"
          width="120"
        >
        </el-table-column>
        <el-table-column label="备注" align="center" width="220">
          <template slot-scope="scope">{{ scope.row.remark }}</template>
        </el-table-column>

        <el-table-column label="操作" align="center" :data="list1">
          <template slot-scope="scope">
            <!-- <el-button size="mini" type="primary" @click="delete01(scope.row)"
              >删除</el-button
            > -->
            <span
              style="color: rgb(234, 195, 24); margin-left: 4px"
              @click="power(scope.row.id)"
              >授权</span
            >

            <span
              style="color: rgb(109, 182, 224); margin-left: 4px"
              @click="delete01(scope.row)"
              >删除</span
            >

            <span
              style="color: rgb(234, 195, 24); margin-left: 4px"
              @click="contact(scope.row.id)"
              >关联用户</span
            >

            <span
              style="color: rgb(109, 182, 224); margin-left: 4px"
              @click="handleUpdate(scope.row.id)"
              >编辑</span
            >
            <span
              style="color: rgb(234, 195, 24); margin: 4px"
              @click="contactUser(scope.row.id)"
              >用户</span
            >

            <el-switch
              v-model="scope.row.status"
              :active-value="0"
              :inactive-value="1"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="handleUpdateStatus(scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <el-form ref="form" :model="role" label-width="80px">
          <el-form-item label="角色名">
            <el-input v-model="role.name"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" v-model="role.remark"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleSaveOrUpdate()"
              >确 定</el-button
            >
          </el-form-item>
        </el-form>
      </el-dialog>

      <el-dialog
        title="提示"
        :visible.sync="dialogVisible1"
        width="30%"
        :before-close="handleClose"
      >
        <el-table
          :data="userList"
          style="width: 100%"
          v-loading="userLoading"
          border
        >
          <el-table-column label="编号" align="center" width="80">
            <template slot-scope="scope">{{ scope.$index + 1 }}</template>
          </el-table-column>

          <el-table-column label="用户名" align="center" width="130">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>

          <el-table-column label="手机号" align="center" width="90">
            <template slot-scope="scope">{{ scope.row.createBy }}</template>
          </el-table-column>

          <el-table-column label="操作" align="center" :data="list1">
            <template slot-scope="scope">
              <!-- <el-button size="mini" type="primary" @click="delete01(scope.row)"
              >删除</el-button
            > -->
              <span
                style="color: rgb(109, 182, 224); margin-left: 3px"
                @click="roleRelation(scope.row.id)"
                >关联用户</span
              >

              <span
                style="color: rgb(234, 195, 24); margin-left: 3px"
                @click="delete01(scope.row)"
                >关联角色</span
              >
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChangeTwo"
          @current-change="handleCurrentChangeTwo"
          :current-page="pageNum"
          :page-sizes="[8, 16, 24, 32]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </el-dialog>
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible1"
        width="30%"
        :before-close="handleClose"
      >
        <el-table
          :data="userList"
          style="width: 100%"
          v-loading="userLoading"
          border
        >
          <el-table-column label="编号" align="center" width="80">
            <template slot-scope="scope">{{ scope.$index + 1 }}</template>
          </el-table-column>

          <el-table-column label="用户名" align="center" width="130">
            <template slot-scope="scope">{{ scope.row.name }}</template>
          </el-table-column>

          <el-table-column label="手机号" align="center" width="90">
            <template slot-scope="scope">{{ scope.row.createBy }}</template>
          </el-table-column>

          <el-table-column label="操作" align="center" :data="list1">
            <template slot-scope="scope">
              <!-- <el-button size="mini" type="primary" @click="delete01(scope.row)"
              >删除</el-button
            > -->
              <span
                style="color: rgb(109, 182, 224); margin-left: 3px"
                @click="roleRelation(scope.row.id)"
                >关联用户</span
              >
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChangeTwo"
          @current-change="handleCurrentChangeTwo"
          :current-page="pageNum"
          :page-sizes="[8, 16, 24, 32]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </el-dialog>
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible2"
        width="30%"
        :before-close="handleClose"
      >
        <el-table
          :data="relateUserList"
          style="width: 100%"
          v-loading="userLoading"
          border
        >
          <el-table-column label="编号" align="center" width="80">
            <template slot-scope="scope">{{ scope.$index + 1 }}</template>
          </el-table-column>

          <el-table-column label="用户名" align="center" width="130">
            <template slot-scope="scope">{{ scope.row.userName }}</template>
          </el-table-column>

          <el-table-column label="操作" align="center" :data="list1">
            <template slot-scope="scope">
              <!-- <el-button size="mini" type="primary" @click="delete01(scope.row)"
              >删除</el-button
            > -->
              <span
                style="color: rgb(109, 182, 224); margin-left: 3px"
                @click="cancelUser(scope.row)"
                >取消用户</span
              >
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChangeThere"
          @current-change="handleCurrentChangeThere"
          :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </el-dialog>

      <el-dialog
        title="提示"
        :visible.sync="able"
        width="30%"
        :before-close="handleClose"
      >
        <!-- default-expand-all -->

        <el-tree
          :data="data2"
          show-checkbox
          node-key="id"
          :default-expanded-keys="[1]"
          :default-checked-keys="menuIdList"
          ref="tree"
          highlight-current
          :props="defaultProps"
        >
        </el-tree>
        <div class="buttons">
          <el-button @click="getCheckedKeys">授权</el-button>
          <el-button @click="resetChecked">清空</el-button>
        </div>
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
  name: "Role",
  data() {
    return {
      typeOptions: [
        {
          value: 0,
          label: "开启",
        },
        {
          value: 1,
          label: "关闭",
        },
      ],
      filter: { endPlace: "", endPlace: "" },
      list: [],
      userList: [],
      relateUserList: [],
      roleMenuList: [],
      menuIdList: [],
      role: { state: 1 },
      list1: { state: 1 },
      listLoading: false,
      userLoading: false,
      tableData: [],
      pageSize: 8,
      pageNum: 1,
      total: 0,
      state: false,
      able: false,
      roleId: 0,
      cancelRoleId: 0,
      roleIdState: 0,
      roleIdPower: 0,
      typeMap: {}, //保存广告位对象信息
      typeId: [],
      dialogVisible: false,
      dialogVisible1: false,
      dialogVisible2: false,
      data2: [],
      defaultProps: {
        children: "children",
        label: "menuName",
      },
    };
  },
  created() {
    //获取广告列表数据
    this.loadRole();
  },
  methods: {
    getCheckedKeys() {
      console.log(this.$refs.tree.getCheckedKeys());
      const menuIds = this.$refs.tree.getCheckedKeys();
      console.log(menuIds);
      ApiService.post("/role/volumeLicensing", {
        roleId: this.roleIdPower,
        menuIds: this.$refs.tree.getCheckedKeys(),
      })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.$message({
              message: "授权成功",
              type: "success",
            });
            this.able = false;
          } else {
            this.$message({
              message: "授权失败",
              type: "warning",
            });
          }
        });
    },

    resetChecked() {
      this.$refs.tree.setCheckedKeys([]);
    },

    contact(id) {
      this.dialogVisible1 = true;
      this.roleId = id;
      this.loadUser();
    },
    power(id) {
      this.able = true;
      this.roleIdPower = id;
      this.menuList(id);
    },

    contactUser(id) {
      this.dialogVisible2 = true;
      this.cancelRoleId = id;
      this.relationList(id);
    },
    handleUpdate(id) {
      this.dialogVisible = true;
      this.getRole(id);
    },
    handAdd() {
      this.dialogVisible = true;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((res) => {
          done();
        })
        .catch((err) => {});
    },
    loadRole() {
      ApiService.get("/role/list", {
        params: {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
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
            alert("获取数据失败");
          }
        });
    },
    //获取用户列表
    loadUser() {
      ApiService.post("/admin/listPage", {
        currentPage: this.pageNum,
        pageSize: this.pageSize,
      })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.userList = res.data;
          } else {
            this.$message({
              message: "获取数据失败",
              type: "warning",
            });
          }
        });
    },
    //获取关联用户列表
    relationList(id) {
      ApiService.get("/role/roleUserList", {
        params: {
          currentPage: this.pageNum,
          pageSize: this.pageSize,
          roleId: id,
        },
      })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.relateUserList = res.data;
          } else {
            this.$message({
              message: "获取数据失败",
              type: "warning",
            });
          }
        });
    },
    //获取权限列表
    menuList(id) {
      ApiService.get("/menu/list", {
        params: {
          roleId: id,
        },
      })
        .then((res) => res.data)
        .then((res) => {
          if (res.code == 200) {
            this.data2 = res.data.menuList;
            this.menuIdList = res.data.checkList;
          } else {
            this.$message({
              message: "获取数据失败",
              type: "warning",
            });
          }
        });
    },
    //2.条件分页查询
    filterquery() {
      this.loadRole();
    },
    //3.重置
    ReForm() {
      this.filter.name = "";
      this.loadRole();
    },

    //4.是否开启
    handleUpdateStatus(row) {
      ApiService.post("/role/updateState", {
        id: row.id,
        status: parseInt(row.status),
      })
        .then((res) => {
          console.log(res);
          this.loadRole();
        })
        .catch((err) => {
          this.$message = "修改状态失败";
        });
    },
    //5.修改
    //1.保存
    handleSaveOrUpdate() {
      //表单校验
      ApiService.post("/role/addOrUpdateRole", {
        ...this.role,
      })
        .then((res) => res.data)
        .then((res) => {
          console.log(res.code);
          this.loadRole();
          this.dialogVisible = false;
        })
        .catch((err) => {
          this.$message("角色名已存在");
        });
    },
    //2.回显信息
    getRole(id) {
      return ApiService.get("/role/getRole", id)
        .then((res) => {
          console.log(res.data);
          this.role = res.data.data;
          // this.role.id = id;
        })
        .catch((err) => {
          this.$message("修改数据处理失败");
        });
    },

    //7.删除
    delete01(row) {
      this.$confirm("你确定要修改状态吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        ApiService.get("role/delete", {
          params: {
            id: row.id,
          },
        })
          .then((res) => {
            this.loadRole();
            console.log(res);
          })
          .catch((err) => {
            this.$message({
              message: "删除角色失败",
              type: "warning",
            });
          });
      });
    },
    //7.关联用户
    roleRelation(id) {
      ApiService.post("role/associatedUser", {
        userId: id,
        roleId: this.roleId,
      })
        .then((res) => {
          this.loadRole();
          console.log(res);
          this.dialogVisible1 = false;
          this.$message({
            message: "关联成功",
            type: "success",
          });
        })
        .catch((err) => {
          this.$message({
            message: "关联失败",
            type: "warning",
          });
        });
    },
    //取消关联用户
    cancelUser(row) {
      ApiService.post("role/deleteAssociated", {
        userId: row.userId,
        roleId: row.roleId,
      })
        .then((res) => {
          this.loadRole();
          console.log(res);
          this.dialogVisible2 = false;
          this.$message({
            message: "取消关联成功",
            type: "success",
          });
        })
        .catch((err) => {
          this.$message({
            message: "取消关联失败",
            type: "warning",
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
      this.loadRole();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadRole();
    },
    handleSizeChangeTwo(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadUser();
    },
    handleCurrentChangeTwo(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.loadUser();
    },
    handleSizeChangeThere(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.relationList();
    },
    handleCurrentChangeThere(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.relationList();
    },
  },
};
</script>

<style></style>
