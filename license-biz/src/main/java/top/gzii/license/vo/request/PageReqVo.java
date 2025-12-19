package top.gzii.license.vo.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
public class PageReqVo {
    @NotNull
    private int current;
    @NotNull
    private  int size;

    //是否降序
    private boolean desc;
}
