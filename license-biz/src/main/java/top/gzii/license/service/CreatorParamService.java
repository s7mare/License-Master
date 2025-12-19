package top.gzii.license.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.ResponseEntity;
import top.gzii.license.common.license.LicenseCreatorParam;
import top.gzii.license.model.CreatorParam;
import com.baomidou.mybatisplus.extension.service.IService;
import top.gzii.license.vo.request.CreatorParamPageReqVo;
import top.gzii.license.vo.response.CreatorParamPageRespVo;

import java.io.IOException;
import java.util.List;

/**
* @author admin
* @description 针对表【license_creator_param(证书生成参数表)】的数据库操作Service
* @createDate 2025-12-09 15:01:11
*/
public interface CreatorParamService extends IService<CreatorParam> {

    void deleteBatch(List<Long> ids);

    IPage<CreatorParamPageRespVo> pageInfo(CreatorParamPageReqVo reqVo);

    ResponseEntity<byte[]> generateLicense(LicenseCreatorParam param);

    ResponseEntity<byte[]> genPublicKey() throws IOException;
}
