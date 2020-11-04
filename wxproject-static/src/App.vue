<template>
  <div id="app" >
      <router-view></router-view>
  </div>
</template>

<script>

    export default {
        components: {},
        data(){
            return{
            }
        }
    }
</script>


<style>
#app {
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.el-header, .el-footer {
  padding: 0 !important;
}


::-webkit-scrollbar {
  width: 6px;
  height: 6px;
  top: 2px;
  opacity: 1;
  transition: opacity .34s ease-out;
}

::-webkit-scrollbar-thumb {
  background-color: #BEC8D0;
  border-radius: 3px;
}

</style>
