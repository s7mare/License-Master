package top.gzii.license.service;

import top.gzii.license.model.SystemInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【license_system_info(部署系统信息表)】的数据库操作Service
* @createDate 2025-12-09 15:01:12
*/
public interface SystemInfoService extends IService<SystemInfo> {

    void deleteBatch(List<Long> ids);
}
