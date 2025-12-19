package top.gzii.license.service;

import top.gzii.license.model.CompanyInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【license_company_info(公司信息表)】的数据库操作Service
* @createDate 2025-12-09 15:01:12
*/
public interface CompanyInfoService extends IService<CompanyInfo> {

    void deleteBatch(List<Long> ids);
}
