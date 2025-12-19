package top.gzii.license.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.gzii.license.common.exception.LicenseException;
import top.gzii.license.common.result.ResultCodeEnum;
import top.gzii.license.mapper.CreatorParamMapper;
import top.gzii.license.model.CompanyInfo;
import top.gzii.license.model.CreatorParam;
import top.gzii.license.service.CompanyInfoService;
import top.gzii.license.mapper.CompanyInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* @author admin
* @description 针对表【license_company_info(公司信息表)】的数据库操作Service实现
* @createDate 2025-12-09 15:01:12
*/
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo>
    implements CompanyInfoService{
    @Autowired
    CompanyInfoMapper companyInfoMapper;
    @Autowired
    CreatorParamMapper creatorParamMapper;
    private final Lock  delLock=new ReentrantLock();


    @Override
    public  void deleteBatch(List<Long> ids) {
        delLock.lock();
        try {

            //检查有无子表数据
            LambdaQueryWrapper<CreatorParam> lambdaQueryWrapper = new LambdaQueryWrapper<CreatorParam>();
            lambdaQueryWrapper.eq(CreatorParam::getDeleted, 0);
            lambdaQueryWrapper.in(CreatorParam::getCompanyId, ids);
            List<CreatorParam> list = creatorParamMapper.selectList(lambdaQueryWrapper);
            if (list != null && !list.isEmpty())
                throw new LicenseException(ResultCodeEnum.DELETE_CHILD);


            LambdaUpdateWrapper<CompanyInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.set(CompanyInfo::getDeleted, 1);
            lambdaUpdateWrapper.in(CompanyInfo::getId, ids);
            companyInfoMapper.update(lambdaUpdateWrapper);
        }finally {
            delLock.unlock();
        }
    }
}




