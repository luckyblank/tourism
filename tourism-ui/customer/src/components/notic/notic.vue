<template>
  <div style="color: #666;font-size: 14px;">
    <el-card style="margin: 10px 0">
      <div style="padding-bottom: 20px; font-size: 24px">
        秃游中国系统公告
      </div>
      <el-collapse accordion v-for="(item, index) in noticeRich" :key="index">
        <el-collapse-item :name="index + ''">
          <template slot="title">
            <span
              style="font-size: 20px; color: #E6A23C; margin-left: 300px; "
              >{{ item.title }}</span
            ><i style="color: #E6A23C" class="header-icon el-icon-info"></i>
            <span style="margin-left: 15px">发布时间</span>

            <span style="margin-left: 10px">{{ item.createTime }}</span>
          </template>
          <div>
            <div v-html="item.content"></div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </el-card>
  </div>
</template>

<script>
import newAxios from "../../utils/axios";

export default {
  name: "Home",
  data() {
    return {
      noticeRich: [],
    };
  },
  created() {
    this.getNotic();
  },
  methods: {
    getNotic() {
      newAxios
        .get("/notic/getByNoticAll")
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.code == 200) {
            this.noticeRich = res.data;
            this.notice = res.data;
          } else {
            this.$message = "正在更新公告ing....";
          }
        });
    },
  },
};
</script>
