<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "UpdateMessage",
  title: "修改留言",
  components: {
    Detail
  }
};
</script>

<style></style>
