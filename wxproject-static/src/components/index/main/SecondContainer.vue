<template>
    <div style="border: 1px solid black">
        <!-- 二级标题-->
        <el-row>
            <el-col :span="24">
                <div class="grid-content bg-purple-dark" style="font-size: 16px;">
                    <i class="el-icon-delete" style="width:40px;"></i>
                    <span class="tag-group__title" >二级标题表</span>
                    <el-button  style="float: right;border:none;" >更多 <i class="el-icon-more  el-icon--right"></i> </el-button>
                </div>
            </el-col>
        </el-row>
        <!-- 内容-->
        <el-row>
            <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
        </el-row>
        <el-row>
            <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
            <el-col :span="4"><div class="grid-content bg-purple-light"></div></el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "SecondContainer",
        props: {
            dataItems:{
                require:true,
                type:Array
            }
        },
        data() {
            return {}
        },
        methods: {}
    }
</script>

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
</style>
