<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "UpdateHotel",
  title: "修改酒店",
  components: {
    Detail,
  },
};
</script>

<style></style>
