/**
 * Override convention configuration
 * https://cli.vuejs.org/config/
 */

module.exports = {
  // relative path for dev
  publicPath: process.env.NODE_ENV === "production" ? "/tourism/customer/" : "/tourism/customer/",
  // for gh-pages
  indexPath: "index.html",
  // 构建输出目录
  outputDir: 'dist',
  assetsDir: "assets",
  lintOnSave: process.env.NODE_ENV !== "production",
  productionSourceMap: false,
  css: {
    // sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer: {
    open: true,
    port: 8082
  }
};
