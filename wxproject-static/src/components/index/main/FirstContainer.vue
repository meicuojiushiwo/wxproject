<template>
    <div style="border: 1px solid red">
        <!-- 一级、标题-->
        <el-row align="middle">
            <el-col :span="24" >
                <div class="grid-content bg-purple-dark" style="font-size:18px;">
                    <i class="el-icon-delete" style="width:40px;"></i>
                    <span class="tag-group__title" >一级标题表</span>
                </div>
            </el-col>
        </el-row>
        <!-- 二级分割栏-->
        <SecondContainer :data-items="secDataItems"></SecondContainer>
    </div>
</template>

<script>

    import SecondContainer from "./SecondContainer";
    export default {
        name: "FirstContainer",
        components: {SecondContainer},
        props: [],
        data() {
            return {
                secDataItems:[]
            }
        },
        methods: {}
    }
</script>

<style module>
    .firstTag{
        display: inline-block;
        height:40px;
        border:1px solid rebeccapurple;
        font: 18px large;
    }
</style>

<style scoped>
    .el-row {
        margin-bottom: 20px;
    &:last-child {
         margin-bottom: 0;
     }
    }
    .el-col {
        border-radius: 4px;
    }
    .bg-purple-dark {
        background: #99a9bf;
    }
    .bg-purple {
        background: #d3dce6;
    }
    .bg-purple-light {
        background: #e5e9f2;
    }
    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }
    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
    .demo-layout .bg-purple-dark {
        background:#99a9bf;
    }
</style>
