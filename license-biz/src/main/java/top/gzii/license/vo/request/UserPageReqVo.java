package top.gzii.license.vo.request;

import lombok.Data;

@Data
public class UserPageReqVo extends PageReqVo{
    private String username;
    private String nickName;
}
