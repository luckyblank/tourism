<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "UpdateStrategy",
  title: "修改景点",
  components: {
    Detail
  }
};
</script>

<style></style>
