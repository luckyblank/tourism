<template>
  <!-- 主键传参 -->

  <detail :isEdit="false"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "AddMessage",
  title: "新增留言",
  components: {
    Detail
  }
};
</script>

<style></style>
