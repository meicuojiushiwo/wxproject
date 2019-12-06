<template>
  <div id="app">
    <el-scrollbar class="el-scrollbar ">
      <router-view/>
    </el-scrollbar>
  </div>
</template>

<script>


</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

.el-header, .el-footer {
  padding: 0 !important;
}



.el-scrollbar {
  height: 100%;
}

::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-thumb {
  background-color: #BEC8D0;
  border-radius: 3px;
}

</style>
