<template>
  <!-- 主键传参 -->

  <detail :isEdit="false"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "AddNotic",
  title: "添加管理员",
  components: {
    Detail,
  },
};
</script>

<style></style>
