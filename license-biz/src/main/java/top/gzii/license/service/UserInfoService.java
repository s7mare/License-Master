package top.gzii.license.service;

import top.gzii.license.model.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import top.gzii.license.vo.request.LoginReqVo;

import java.util.List;

/**
* @author admin
* @description 针对表【license_user_info(用户信息表)】的数据库操作Service
* @createDate 2025-12-09 15:01:12
*/
public interface UserInfoService extends IService<UserInfo> {

    String login(LoginReqVo loginReqVo);

    void deleteBatch(List<Long> ids);
}
