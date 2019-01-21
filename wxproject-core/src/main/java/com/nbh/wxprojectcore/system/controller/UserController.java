package com.nbh.wxprojectcore.system.controller;

import com.nbh.wxprojectcore.base.BaseController;
import com.nbh.wxprojectcore.base.Result;
import com.nbh.wxprojectcore.constants.BaseEnums;
import com.nbh.wxprojectcore.system.dto.User;
import com.nbh.wxprojectcore.system.service.UserService;
import com.nbh.wxprojectcore.util.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户Controller
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
/**
 * 用户Controller
 *
 * @version 1.0
 * @author bojiangzhou 2017-12-31
 */
@RequestMapping
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);


    @PostMapping("/sys/user/queryAll")
    public Result queryAll(){
        List<User> list = userService.selectAll();
        return Results.successWithData(list, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @RequestMapping("/sys/user/queryOne/{userId}")
    public Result queryOne(@PathVariable Long userId){
        User user = userService.get(userId);

        logger.debug("userId:{},userName{}.birthday:{}",user.getUserId(),user.getUsername(),user.getBirthday());
        logger.info("userId:{},userName{}.birthday:{}",user.getUserId(),user.getUsername(),user.getBirthday());
        logger.error("userId:{},userName{}.birthday:{}",user.getUserId(),user.getUsername(),user.getBirthday());
        return Results.successWithData(user);
    }

    @PostMapping("/sys/user/save")
    public Result save(@Valid @RequestBody User user){
        user = userService.insertSelective(user);
        return Results.successWithData(user);
    }

    @PostMapping("/sys/user/update")
    public Result update(@Valid @RequestBody List<User> user){
        user = userService.persistSelective(user);
        return Results.successWithData(user);
    }

    @RequestMapping("/sys/user/delete")
    public Result delete(User user){
        userService.delete(user);
        return Results.success();
    }

    @RequestMapping("/sys/user/delete/{userId}")
    public Result delete(@PathVariable Long userId){
        userService.delete(userId);
        return Results.success();
    }
}
