<template>
    <el-backtop :bottom="40" :right="40" >
<!--        <div style="{-->
<!--        height: 100%;-->
<!--        width: 100%;-->
<!--        background-color: #f2f5f6;-->
<!--        box-shadow: 0 0 6px rgba(0,0,0, .12);-->
<!--        text-align: center;-->
<!--        line-height: 40px;-->
<!--        color: #1989fa;-->
<!--      }">-->
<!--            UP-->
<!--        </div>-->
        <i class="el-icon-caret-top"></i>
    </el-backtop>
</template>

<script>
    export default {
        name: "BackTop",
        props: [],
        data() {
            return {}
        },
        method: {}
    }
</script>

<style scoped>
    .el-icon-caret-top{
        font-family:element-icons!important;
        speak:none;
        font-style:normal;
        font-weight:400;
        font-variant:normal;
        text-transform:none;
        line-height:1;
        vertical-align:baseline;
        display:inline-block;
        -webkit-font-smoothing:antialiased;
        -moz-osx-font-smoothing:grayscale}
</style>
