package top.gzii.license.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import de.schlichtherle.license.LicenseManager;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;
import top.gzii.license.common.license.LicenseCreator;
import top.gzii.license.common.license.LicenseCreatorParam;
import top.gzii.license.common.result.Result;
import top.gzii.license.model.CreatorParam;
import top.gzii.license.service.CreatorParamService;
import top.gzii.license.vo.request.CreatorParamPageReqVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/param")
public class LicenseInfoController {
    @Autowired
     CreatorParamService service;
    @Value("${app.private-key-path}")
     String privateKeyPath;
    @Value("${app.license-store-path}")
     String licenseStorePath;
    @Value("${app.public-key-path}")
    String publicKeyPath;

    /**
     * 生成并下载License文件
     */
    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateLicense(@RequestBody LicenseCreatorParam param) {

        return service.generateLicense(param);

    }



@GetMapping("/list")
    public Result  list(){
    List<CreatorParam> list = service.list();
    return Result.success(list);
}

@PostMapping("/insert")
    public Result insert(@RequestBody CreatorParam param){
        param.setPrivateKeyStorePath(privateKeyPath);
       param.setLicensePath(licenseStorePath);
       param.setStorePass(publicKeyPath);
        service.save(param);
        return Result.success();
}
@PostMapping("/update")
    public Result update(@RequestBody CreatorParam param){
        service.updateById(param);
        return Result.success();
}

@GetMapping("/page")
    public Result page(@Valid CreatorParamPageReqVo request){
    Page<CreatorParam> page = new Page<>();
    page.setSize(request.getSize());
    page.setCurrent(request.getCurrent());
    LambdaQueryWrapper<CreatorParam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    if(request.getCompanyId()!=0)
        lambdaQueryWrapper.eq(CreatorParam::getCompanyId,request.getCompanyId());
    if (request.getSystemId()!=0)
        lambdaQueryWrapper.eq(CreatorParam::getSystemId,request.getSystemId());

    lambdaQueryWrapper.eq(CreatorParam::getDeleted,0);
    Page<CreatorParam> result = service.page(page, lambdaQueryWrapper);
    return Result.success(result);

}
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Long> ids){
        service.deleteBatch(ids);

        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable("id") long id){
        CreatorParam creatorParam = service.getById(id);
        return Result.success(creatorParam);
    }
    @GetMapping("/pageInfo")
    public Result pageInfo( CreatorParamPageReqVo reqVo){
      return Result.success(service.pageInfo(reqVo))  ;
    }


    @GetMapping("/genPublicKey")
    public ResponseEntity<byte[]> genPublicKey() throws IOException {
   return   service.genPublicKey();

    }
}
