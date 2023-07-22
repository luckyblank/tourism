<template>
  <!-- 主键传参 -->
  <detail :isEdit="true"></detail>
</template>

<script>
import Detail from "./Detail.vue";
export default {
  name: "UpdateInsurance",
  title: "修改保险",
  components: {
    Detail
  }
};
</script>

<style></style>
