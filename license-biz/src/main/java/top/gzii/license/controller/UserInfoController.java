package top.gzii.license.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.gzii.license.common.context.LoginUserHolder;
import top.gzii.license.common.exception.LicenseException;
import top.gzii.license.common.result.Result;
import top.gzii.license.common.result.ResultCodeEnum;
import top.gzii.license.mapper.UserInfoMapper;
import top.gzii.license.model.UserInfo;
import top.gzii.license.service.UserInfoService;
import top.gzii.license.vo.request.LoginReqVo;
import top.gzii.license.vo.request.UserPageReqVo;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService service;
@Autowired
    UserInfoMapper userInfoMapper;

    @PostMapping("/insert")
    public Result insert(@RequestBody UserInfo userInfo){
        UserInfo userInfo1 = userInfoMapper.selectByUsername(userInfo.getUsername());
        if (!ObjectUtils.isEmpty(userInfo1))
            throw new LicenseException(ResultCodeEnum.REPEAT_REGIST);

        service.save(userInfo);
        return Result.success();

}

    @PostMapping("/update")
    public Result update(@RequestBody UserInfo userInfo){
        service.updateById(userInfo);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginReqVo loginReqVo){
        String token=service.login(loginReqVo);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token",token);
        return Result.success(hashMap);
    }

    @GetMapping("/list")
    public Result list(){
        List<UserInfo> list = service.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result page(@Valid UserPageReqVo userPageReqVo){
        Page<UserInfo> page = new Page<>();
        page.setCurrent(userPageReqVo.getCurrent());
        page.setSize(userPageReqVo.getSize());
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(userPageReqVo.getUsername()))
            lambdaQueryWrapper.like(UserInfo::getUsername,userPageReqVo.getUsername());
        if (StringUtils.hasText(userPageReqVo.getNickName()))
            lambdaQueryWrapper.like(UserInfo::getNickName,userPageReqVo.getNickName());
        if (userPageReqVo.isDesc())
            lambdaQueryWrapper.orderByDesc(UserInfo::getId);

        lambdaQueryWrapper.eq(UserInfo::getDeleted,0);
        Page<UserInfo> result = service.page(page, lambdaQueryWrapper);
       return Result.success(result);
    }

    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> ids){
        service.deleteBatch(ids);

        return Result.success();
    }

    @GetMapping("/userInfo")
    public Result userInfo(){
        UserInfo userInfo = LoginUserHolder.get();
        return Result.success(userInfo);
    }

    @GetMapping("/selectByUsername")
    public Result selectByUsername(String username){
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        userInfo.setPassword("");
        return Result.success(userInfo);
    }

    @GetMapping("/count")
    public Result count(){
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInfo::getDeleted,0);
        long count = service.count(lambdaQueryWrapper);
        return Result.success(count);
    }

}
