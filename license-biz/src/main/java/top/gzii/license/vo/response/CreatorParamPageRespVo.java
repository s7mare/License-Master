package top.gzii.license.vo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;



@Data
public class CreatorParamPageRespVo {
    Long id;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 系统id
     */
    private Long systemId;
    /**
     *  公司名称
     */

    private String companyName;
    /**
     *  系统名称
     */

    private String systemName;

    /**
     * 证书主题
     */
    private String subject;



    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issuedTime;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiryTime;





}
