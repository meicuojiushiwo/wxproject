<template>
        <el-menu :default-active=activeIndex
                 class="el-menu-demo"
                 mode="horizontal"
                 @select="handleSelect"
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#fff">
           <SingleItem :items="menuData"  style="width:800px;overflow-x: auto;"/>
        </el-menu>

</template>


<script>
    import SingleItem from "./SingleItem.vue";

    export default {
        name: 'HeadNav',
        components: {SingleItem},
        data() {
            return {
                activeIndex: 1,
                menuData:[
                        {"id":1,name:"处理中心"},
                        {"id":2,name:"我的工作台","childrens":[
                                {"id":21,"name":"选项1"},
                                {"id":22,"name":"选项2"},
                                {"id":23,"name":"选项3"},
                                {"id":24,"name":"选项4"}
                            ]},
                        {"id":3,name:"我的工作台2","childrens":[
                                {"id":31,"name":"选项1"},
                                {"id":32,"name":"选项2"},
                                {"id":33,"name":"选项3"},
                                {"id":34,"name":"选项4"}
                            ]},
                        {"id":4,name:"我的工作台4","childrens":[
                                {"id":41,"name":"选项1"},
                                {"id":42,"name":"选项2"},
                                {"id":43,"name":"选项3"},
                                {"id":44,"name":"选项4","childrens":[
                                        {"id":51,"name":"选项1"},
                                        {"id":52,"name":"选项2"},
                                        {"id":53,"name":"选项3"},
                                        {"id":54,"name":"选项4"}
                                    ]}
                            ]}
                    ]
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped></style>
