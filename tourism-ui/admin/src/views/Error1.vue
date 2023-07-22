<template> </template>

<script>
export default {
  name: "Error1",
  created() {
    let _this = this;
    this.$alert("登录信息失败", "提示", {
      confirmButtonText: "确定"
    }).then(res => {
      localStorage.removeItem("access-admin");
      _this.$router.replace({ path: "/" });
    });
  }
};
</script>

<style></style>
