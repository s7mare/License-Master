package top.gzii.license.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import top.gzii.license.common.constants.RedisConstants;
import top.gzii.license.common.exception.LicenseException;
import top.gzii.license.common.result.ResultCodeEnum;
import top.gzii.license.common.utils.RedisUtils;
import top.gzii.license.model.CreatorParam;
import top.gzii.license.model.UserInfo;
import top.gzii.license.service.UserInfoService;
import top.gzii.license.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;
import top.gzii.license.vo.request.LoginReqVo;

import java.util.List;
import java.util.UUID;

/**
* @author admin
* @description 针对表【license_user_info(用户信息表)】的数据库操作Service实现
* @createDate 2025-12-09 15:01:12
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public String login(LoginReqVo loginReqVo) {

       UserInfo userInfo= userInfoMapper.selectByUsername(loginReqVo.getUsername());
       if (ObjectUtils.isEmpty(userInfo))
           throw new LicenseException(ResultCodeEnum.NO_SUCH_USER);

       if (!loginReqVo.getPassword().equals(userInfo.getPassword()))
           throw new LicenseException(ResultCodeEnum.INCORRECT_USERNAME_PASSWORD);
        String token = UUID.randomUUID().toString();
        String key= RedisConstants.LOGIN+token;
       userInfo.setPassword("");

       redisUtils.set(key,userInfo,RedisConstants.LOGIN_EXPIRE_TIME);
       return token;

    }
    @Override
    public void deleteBatch(List<Long> ids) {
        LambdaUpdateWrapper<UserInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(UserInfo::getDeleted,1);
        lambdaUpdateWrapper.in(UserInfo::getId,ids);
       userInfoMapper.update(lambdaUpdateWrapper);

    }
}




