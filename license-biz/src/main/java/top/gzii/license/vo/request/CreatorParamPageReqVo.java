package top.gzii.license.vo.request;

import lombok.Data;

@Data
public class CreatorParamPageReqVo extends PageReqVo{
    private long companyId;
    private long systemId;
    private String companyName;
    private String systemName;
}
