package top.gzii.license.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.gzii.license.common.exception.LicenseException;
import top.gzii.license.common.result.Result;
import top.gzii.license.common.result.ResultCodeEnum;
import top.gzii.license.model.GenerationRecord;
import top.gzii.license.service.GenerationRecordService;
import top.gzii.license.vo.request.PageReqVo;

@RestController
@CrossOrigin
@RequestMapping("/genRecord")
public class GenerationRecordController {
    @Autowired
    GenerationRecordService service;
    @Value("${app.clean-job-day}")
    int cleanDay;

@GetMapping("/page")
    public Result<IPage<GenerationRecord>> page(PageReqVo pageReqVo){
    Page<GenerationRecord> page = new Page<>(pageReqVo.getCurrent(), pageReqVo.getSize());
    LambdaQueryWrapper<GenerationRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    if (pageReqVo.isDesc())
        lambdaQueryWrapper.orderByDesc(GenerationRecord::getCreateTime);
    Page<GenerationRecord> recordPage = service.page(page, lambdaQueryWrapper);
    return Result.success(recordPage);

}

@GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable("id") long id){
    GenerationRecord generationRecord = service.getById(id);
    if (ObjectUtils.isEmpty(generationRecord))
       throw new LicenseException(ResultCodeEnum.PARAMETER_ERROR);

    byte[] data = generationRecord.getData();
    // 设置响应头，告诉浏览器这是需要下载的文件
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.add("content-disposition", "attachment;filename=license.lic");
    headers.setContentDispositionFormData("attachment",
             "license.lic");
    headers.setContentLength(data.length);

    return new ResponseEntity<>(data, headers, HttpStatus.OK);


}


@GetMapping("/cleanDays")
public Result cleanDays(){
    return Result.success(cleanDay);
}
}
