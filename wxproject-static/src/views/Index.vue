<template>
    <div style="height: auto">
        <el-container>
            <el-aside class="el-aside-left-right"></el-aside>
            <el-container>
                <el-header>
                    <HeadNav title="head"/>
                </el-header>
                <el-container>
                    <el-main>
                        <FirstContainer/>
                    </el-main>
                    <el-aside class="el-aside-content-aside">aside</el-aside>
                </el-container>
                <el-footer>footer</el-footer>
            </el-container>
            <el-aside class="el-aside-left-right"></el-aside>
        </el-container>
        <BackTop/>
    </div>
</template>

<script>
    // @ is an alias to /src
    import HeadNav from '../components/index/head/HeadNav'
    import BackTop from "../components/assist/BackTop";
    import FirstContainer from "../components/index/main/FirstContainer";

    export default {
        name: 'index',
        components: {
            FirstContainer,
            BackTop,
            HeadNav
        }
    }
</script>


<style>

    .el-aside-content-aside{
        padding-left: 20px;
    }

    .el-aside-left-right {
        background-color: white !important;
    }

    .el-header, .el-footer {
        background-color: #B3C0D1;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        text-align: center;
    }

    .el-main {
        background-color: #E9EEF3;
    }

    /*body > .el-container {*/
    /*    margin-bottom: 40px;*/
    /*}*/

    /*.el-container:nth-child(5) .el-aside,*/
    /*.el-container:nth-child(6) .el-aside {*/
    /*    line-height: 260px;*/
    /*}*/

    /*.el-container:nth-child(7) .el-aside {*/
    /*    line-height: 320px;*/
    /*}*/
</style>


