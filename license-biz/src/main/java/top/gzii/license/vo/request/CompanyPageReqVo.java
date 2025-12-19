package top.gzii.license.vo.request;

import lombok.Data;

@Data
public class CompanyPageReqVo extends PageReqVo{
    private String companyName;
    private String code;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String address;
}
