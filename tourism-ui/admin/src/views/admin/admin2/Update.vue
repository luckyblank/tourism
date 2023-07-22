<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "UpdateUser",
  title: "修改管理员",
  components: {
    Detail,
  },
};
</script>

<style></style>
