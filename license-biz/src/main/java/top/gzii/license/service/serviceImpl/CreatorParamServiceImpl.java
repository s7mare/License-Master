package top.gzii.license.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.gzii.license.common.context.LoginUserHolder;
import top.gzii.license.common.license.LicenseCreator;
import top.gzii.license.common.license.LicenseCreatorParam;
import top.gzii.license.mapper.CompanyInfoMapper;
import top.gzii.license.mapper.GenerationRecordMapper;
import top.gzii.license.mapper.SystemInfoMapper;
import top.gzii.license.model.*;
import top.gzii.license.service.CreatorParamService;
import top.gzii.license.mapper.CreatorParamMapper;
import org.springframework.stereotype.Service;
import top.gzii.license.vo.request.CreatorParamPageReqVo;
import top.gzii.license.vo.response.CreatorParamPageRespVo;

import java.io.*;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @author admin
* @description 针对表【license_creator_param(证书生成参数表)】的数据库操作Service实现
* @createDate 2025-12-09 15:01:11
*/

@Slf4j
@Service
public class CreatorParamServiceImpl extends ServiceImpl<CreatorParamMapper, CreatorParam>
    implements CreatorParamService{
    @Autowired
    CreatorParamMapper creatorParamMapper;
    @Autowired
    CompanyInfoMapper companyInfoMapper;
    @Autowired
    SystemInfoMapper systemInfoMapper;
    @Autowired
    GenerationRecordMapper generationRecordMapper;

    @Value("${app.private-key-path}")
    String privateKeyPath;
    @Value("${app.license-store-path}")
    String licenseStorePath;
    @Value("${app.public-key-path}")
    String publicKeyPath;
    private final ExecutorService executorService= Executors.newFixedThreadPool(10);


    @Override
    public void deleteBatch(List<Long> ids) {
        LambdaUpdateWrapper<CreatorParam> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(CreatorParam::getDeleted,1);
        lambdaUpdateWrapper.in(CreatorParam::getId,ids);
       creatorParamMapper.update(lambdaUpdateWrapper);

    }

    @Override
    public IPage<CreatorParamPageRespVo> pageInfo(CreatorParamPageReqVo reqVo) {
        Page<Object> page = new Page<>(reqVo.getCurrent(), reqVo.getSize());
        IPage<CreatorParamPageRespVo> pagedInfo = creatorParamMapper.pageInfo(page, reqVo);
        return pagedInfo;
    }

    @Override
    public ResponseEntity<byte[]> generateLicense(LicenseCreatorParam param) {
        UserInfo userInfo = LoginUserHolder.get();

        //没有私钥路径，使用配置的私钥路径
        if (!StringUtils.hasText(param.getPrivateKeysStorePath()))
            param.setPrivateKeysStorePath(privateKeyPath);
        if(!StringUtils.hasText(param.getLicensePath()))
            param.setLicensePath(licenseStorePath);

        LicenseCreator licenseCreator = new LicenseCreator(param);
        byte[] licenseBytes = licenseCreator.generateLicenseBytes();



        // 设置响应头，告诉浏览器这是需要下载的文件
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("content-disposition", "attachment;filename=license.lic");
        headers.setContentDispositionFormData("attachment",
                "license.lic");
        headers.setContentLength(licenseBytes.length);

        //向记录表中插入数据
        executorService.submit(()->{

        try {
            //从数据库中获得参数信息
            LambdaQueryWrapper<CreatorParam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            CreatorParam creatorParam = creatorParamMapper.selectById(param.getId());

            //构造记录

            GenerationRecord generationRecord = new GenerationRecord();
            generationRecord.setData(licenseBytes);
            generationRecord.setParamId(param.getId());
            generationRecord.setCompanyId(creatorParam.getCompanyId());
            CompanyInfo companyInfo = companyInfoMapper.selectById(creatorParam.getCompanyId());
            if(!ObjectUtils.isEmpty(companyInfo))
            generationRecord.setCompanyName(companyInfo.getName());
            generationRecord.setSystemId(creatorParam.getSystemId());
            SystemInfo systemInfo = systemInfoMapper.selectById(creatorParam.getSystemId());
            if(!ObjectUtils.isEmpty(systemInfo))
            generationRecord.setSystemName(systemInfo.getName());
            generationRecord.setUserId(userInfo.getId());
            generationRecord.setOperator(userInfo.getNickName());
            generationRecord.setIssuedTime(param.getIssuedTime());
            generationRecord.setEndTime(param.getExpiryTime());
            generationRecord.setCreateTime(new Date());

            generationRecordMapper.insert(generationRecord);
        }catch (Exception e){
            e.printStackTrace();
            log.error("异步插入记录失败,信息:{}",param.toString());
        }


        });

        return new ResponseEntity<>(licenseBytes, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> genPublicKey() throws IOException {
        //从配置的文件路径获取输入流
        File file=null;
        FileInputStream fileInputStream=null;
        BufferedInputStream bufferedInputStream=null;
            file = new File(publicKeyPath);
            long length = file.length();
            fileInputStream = new FileInputStream(publicKeyPath);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] bytes = new byte[(int) length];
            IOUtils.read(bufferedInputStream, bytes);
            // 设置响应头，告诉浏览器这是需要下载的文件
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("content-disposition", "attachment;filename=license.keystore");
            headers.setContentDispositionFormData("attachment",
                    "publicCerts" + ".keystore");
            headers.setContentLength(bytes.length);

            ResponseEntity<byte[]> responseEntity =
                    new ResponseEntity<>(bytes, headers, HttpStatus.OK);
            bufferedInputStream.close();
            fileInputStream.close();
            return responseEntity;

    }
}




