package top.gzii.license.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.gzii.license.common.result.Result;
import top.gzii.license.model.SystemInfo;
import top.gzii.license.service.SystemInfoService;
import top.gzii.license.vo.request.SystemPageReqVo;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/system")
public class SystemInfoController {
    @Autowired
    SystemInfoService service;



    @PostMapping("/insert")
    public Result insert(@RequestBody SystemInfo systemInfo){
        service.save(systemInfo);
        return Result.success();
    }


    @PostMapping("/update")
    public Result update(@RequestBody SystemInfo systemInfo){
        service.updateById(systemInfo);
        return  Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable("id") long id){
        SystemInfo systemInfo = service.getById(id);
        return Result.success(systemInfo);
    }


    @GetMapping("/page")
    public Result page(@Valid SystemPageReqVo request){
        Page<SystemInfo> page = new Page<>();
        page.setCurrent(request.getCurrent());
        page.setSize(request.getSize());
        LambdaQueryWrapper<SystemInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(request.getName()))
            lambdaQueryWrapper.like(SystemInfo::getName,request.getName());
        if (request.isDesc())
        lambdaQueryWrapper.orderByDesc(SystemInfo::getId);

        lambdaQueryWrapper.eq(SystemInfo::getDeleted,0);
        Page<SystemInfo> result = service.page(page, lambdaQueryWrapper);
        return Result.success(result);
    }

    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> ids){
        service.deleteBatch(ids);

        return Result.success();
    }

    @GetMapping("/count")
    public Result count(){
        LambdaQueryWrapper<SystemInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemInfo::getDeleted,0);
        return Result.success(service.count(lambdaQueryWrapper));
    }
}
