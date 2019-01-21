package com.nbh.wxprojectadmin.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "菜单管理")
@RequestMapping
@RestController
public class MenuController extends BaseController {

    @Autowired
    private MenuService service;

    /**
     * 查找单个用户
     *
     * @param menuId 菜单ID
     * @return Result
     */
    @ApiOperation("查找单个用户")
    @ApiImplicitParam(name = "menuId", value = "菜单ID", paramType = "path")
    @GetMapping("/sys/menu/get/{menuId}")
    public Result get(@PathVariable Long menuId){
        Menu menu = service.selectById(menuId);
        return Results.successWithData(menu);
    }

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return Result
     */
    @ApiOperation("保存菜单")
    @PostMapping("/sys/menu/save")
    public Result save(@ApiParam(name = "menu", value = "菜单")@RequestBody Menu menu){
        menu = service.save(menu);
        return Results.successWithData(menu);
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     * @return Result
     */
    @ApiOperation("删除菜单")
    @ApiImplicitParam(name = "menuId", value = "菜单ID", paramType = "path")
    @PostMapping("/sys/menu/delete/{menuId}")
    public Result delete(@PathVariable Long menuId){
        service.deleteById(menuId);
        return Results.success();
    }

}
