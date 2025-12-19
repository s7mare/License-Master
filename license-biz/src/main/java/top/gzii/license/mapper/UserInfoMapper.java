package top.gzii.license.mapper;

import top.gzii.license.model.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【license_user_info(用户信息表)】的数据库操作Mapper
* @createDate 2025-12-09 15:01:12
* @Entity top.gzii.license.model.UserInfo
*/
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo selectByUsername(String username);
}




