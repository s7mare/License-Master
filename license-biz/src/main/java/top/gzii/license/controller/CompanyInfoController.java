package top.gzii.license.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.gzii.license.common.result.Result;
import top.gzii.license.model.CompanyInfo;
import top.gzii.license.service.CompanyInfoService;
import top.gzii.license.vo.request.CompanyPageReqVo;
import top.gzii.license.vo.request.PageReqVo;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyInfoController {
    @Autowired
    CompanyInfoService service;


    @PostMapping("/insert")
    public Result insert(@RequestBody CompanyInfo companyInfo){
        service.save(companyInfo);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable("id") Long id){
        CompanyInfo companyInfo = service.getById(id);
        return Result.success(companyInfo);
    }

    @PostMapping("/update")

    public Result update(@RequestBody CompanyInfo companyInfo){
            service.updateById(companyInfo);
            return Result.success();
    }

    @GetMapping ("/page")

    public Result page(@Valid CompanyPageReqVo request){
        Page<CompanyInfo> page = new Page<>();
        page.setCurrent(request.getCurrent());
        page.setSize(request.getSize());

        LambdaQueryWrapper<CompanyInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(request.getCompanyName()))
        lambdaQueryWrapper.like(CompanyInfo::getName,request.getCompanyName());
        if (StringUtils.hasText(request.getCode()))
        lambdaQueryWrapper.eq(CompanyInfo::getCode,request.getCode());
        if (StringUtils.hasText(request.getContactPerson()))
            lambdaQueryWrapper.eq(CompanyInfo::getContactPerson,request.getContactPerson());
        if (StringUtils.hasText(request.getContactPhone()))
            lambdaQueryWrapper.eq(CompanyInfo::getContactPhone,request.getContactPhone());
        if (StringUtils.hasText(request.getContactEmail()))
            lambdaQueryWrapper.eq(CompanyInfo::getContactEmail,request.getContactEmail());
        if (StringUtils.hasText(request.getAddress()))
            lambdaQueryWrapper.like(CompanyInfo::getAddress,request.getAddress());

        if (request.isDesc())
            lambdaQueryWrapper.orderByDesc(CompanyInfo::getId);

        lambdaQueryWrapper.eq(CompanyInfo::getDeleted,0);

        Page<CompanyInfo> result = service.page(page,lambdaQueryWrapper);
        return Result.success(result);
    }
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> ids){
        service.deleteBatch(ids);

        return Result.success();
    }

    @GetMapping("/count")
    public Result count()
    {
        LambdaQueryWrapper<CompanyInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CompanyInfo::getDeleted,0);
        long count = service.count(lambdaQueryWrapper);
        return Result.success(count);
    }
}
