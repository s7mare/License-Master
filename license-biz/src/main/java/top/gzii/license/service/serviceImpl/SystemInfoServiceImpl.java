package top.gzii.license.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.gzii.license.common.exception.LicenseException;
import top.gzii.license.common.result.ResultCodeEnum;
import top.gzii.license.mapper.CreatorParamMapper;
import top.gzii.license.model.CreatorParam;
import top.gzii.license.model.SystemInfo;
import top.gzii.license.service.SystemInfoService;
import top.gzii.license.mapper.SystemInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* @author admin
* @description 针对表【license_system_info(部署系统信息表)】的数据库操作Service实现
* @createDate 2025-12-09 15:01:12
*/
@Service
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoMapper, SystemInfo>
    implements SystemInfoService{
    @Autowired
    SystemInfoMapper systemInfoMapper;
    @Autowired
    CreatorParamMapper creatorParamMapper;

      private final Lock delLock=new ReentrantLock();



    @Override
    public  void deleteBatch(List<Long> ids) {
         delLock.lock();
            try {
            LambdaQueryWrapper<CreatorParam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(CreatorParam::getDeleted, 0);
            lambdaQueryWrapper.in(CreatorParam::getSystemId, ids);
            List<CreatorParam> list = creatorParamMapper.selectList(lambdaQueryWrapper);
            if (list != null && !list.isEmpty())
                throw new LicenseException(ResultCodeEnum.DELETE_CHILD);


            LambdaUpdateWrapper<SystemInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.set(SystemInfo::getDeleted, 1);
            lambdaUpdateWrapper.in(SystemInfo::getId, ids);
            systemInfoMapper.update(lambdaUpdateWrapper);
        }finally {
                delLock.unlock();
            }
    }
}




