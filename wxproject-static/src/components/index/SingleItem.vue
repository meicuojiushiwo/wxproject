<template>
    <div class="SingleItem">
        <template v-for="item of items">
            <!--多层导航栏-->
            <el-submenu v-if="item.childrens" v-bind:key="item.id" :index="item.id">
                <template slot="title">{{item.name}}</template>
                <SingleItem :items="item.childrens"></SingleItem>
            </el-submenu>
            <!--单层导航栏-->
            <el-menu-item v-else v-bind:key="item.id" :data="item" :index="item.id">
                {{item.name}}
            </el-menu-item>
        </template>
    </div>
</template>

<script>
    export default {
        name: "SingleItem",
        props: ["items"],
        data() {
            return {}
        },
        methods: {}
    }
</script>

<style>

    /* 解决下图1 下拉三角图标 */
    .el-menu--horizontal>div>.el-submenu .el-submenu__icon-arrow {
        position: static;
        vertical-align: middle;
        margin-left: 8px;
        margin-top: -3px;
    }

</style>


<style scoped>
    /* 水平样式 */
    .el-menu--horizontal>div>.el-submenu {
        float: left;
    }
    /* 一级菜单的样式 */
    .el-menu--horizontal>div>.el-menu-item {
        float: left;
        height: 60px;
        line-height: 60px;
        margin: 0;
        border-bottom: 2px solid transparent;
        color: #909399;
    }

    .el-submenu__icon-arrow {
        position: static !important;
        vertical-align: middle !important;;
        margin-left: 8px !important;;
        margin-top: -3px !important;;
    }

    /* 解决下图2 无下拉菜单时 不对齐问题 */
    .el-menu--horizontal>div>.el-submenu .el-submenu__title {
        height: 60px;
        line-height: 60px;
        border-bottom: 2px solid transparent;
        color: #909399;
    }

    *{
        outline:none;
    }


</style>

