package top.gzii.license.vo.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class LoginReqVo {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
