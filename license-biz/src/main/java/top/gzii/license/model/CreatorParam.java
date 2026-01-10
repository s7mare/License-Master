package top.gzii.license.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 证书生成参数表
 * @TableName license_creator_param
 */
@Data
@TableName(value = "license_creator_param", autoResultMap = true)
public class CreatorParam implements Serializable {
    @TableId(value = "id",type=IdType.INPUT)
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
     * 证书主题
     */
    private String subject;

    /**
     * 私钥别名
     */
    private String privateAlias;

    /**
     * 私钥
     */
    private String keyPass;

    /**
     * 公钥
     */
    private String storePass;

    /**
     * 证书生成路径
     */
    private String licensePath;

    /**
     * 私钥存储路径
     */
    private String privateKeyStorePath;

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

    /**
     * 用户类型
     */
    private String consumerType;

    /**
     * 用户总数
     */
    private Integer consumerAmount;

    /**
     * 描述
     */
    private String description;

    /**
     * 允许的ip地址列表
     */
    @TableField(value = "ip_address",typeHandler = JacksonTypeHandler.class)
    private List<String> ipAddress;

    /**
     * 允许的mac地址列表
     */
    @TableField(value = "mac_address", typeHandler = JacksonTypeHandler.class)
    private List<String> macAddress;

    /**
     * 允许的cpu序列号
     */
    private String cpuSerial;

    /**
     * 允许的主板序列号
     */
    private String mainBoardSerial;

    /**
     * 是否删除，0：否，1；是
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CreatorParam other = (CreatorParam) that;
        return (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
            && (this.getPrivateAlias() == null ? other.getPrivateAlias() == null : this.getPrivateAlias().equals(other.getPrivateAlias()))
            && (this.getKeyPass() == null ? other.getKeyPass() == null : this.getKeyPass().equals(other.getKeyPass()))
            && (this.getStorePass() == null ? other.getStorePass() == null : this.getStorePass().equals(other.getStorePass()))
            && (this.getLicensePath() == null ? other.getLicensePath() == null : this.getLicensePath().equals(other.getLicensePath()))
            && (this.getPrivateKeyStorePath() == null ? other.getPrivateKeyStorePath() == null : this.getPrivateKeyStorePath().equals(other.getPrivateKeyStorePath()))
            && (this.getIssuedTime() == null ? other.getIssuedTime() == null : this.getIssuedTime().equals(other.getIssuedTime()))
            && (this.getExpiryTime() == null ? other.getExpiryTime() == null : this.getExpiryTime().equals(other.getExpiryTime()))
            && (this.getConsumerType() == null ? other.getConsumerType() == null : this.getConsumerType().equals(other.getConsumerType()))
            && (this.getConsumerAmount() == null ? other.getConsumerAmount() == null : this.getConsumerAmount().equals(other.getConsumerAmount()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress()))
            && (this.getMacAddress() == null ? other.getMacAddress() == null : this.getMacAddress().equals(other.getMacAddress()))
            && (this.getCpuSerial() == null ? other.getCpuSerial() == null : this.getCpuSerial().equals(other.getCpuSerial()))
            && (this.getMainBoardSerial() == null ? other.getMainBoardSerial() == null : this.getMainBoardSerial().equals(other.getMainBoardSerial()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getPrivateAlias() == null) ? 0 : getPrivateAlias().hashCode());
        result = prime * result + ((getKeyPass() == null) ? 0 : getKeyPass().hashCode());
        result = prime * result + ((getStorePass() == null) ? 0 : getStorePass().hashCode());
        result = prime * result + ((getLicensePath() == null) ? 0 : getLicensePath().hashCode());
        result = prime * result + ((getPrivateKeyStorePath() == null) ? 0 : getPrivateKeyStorePath().hashCode());
        result = prime * result + ((getIssuedTime() == null) ? 0 : getIssuedTime().hashCode());
        result = prime * result + ((getExpiryTime() == null) ? 0 : getExpiryTime().hashCode());
        result = prime * result + ((getConsumerType() == null) ? 0 : getConsumerType().hashCode());
        result = prime * result + ((getConsumerAmount() == null) ? 0 : getConsumerAmount().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getMacAddress() == null) ? 0 : getMacAddress().hashCode());
        result = prime * result + ((getCpuSerial() == null) ? 0 : getCpuSerial().hashCode());
        result = prime * result + ((getMainBoardSerial() == null) ? 0 : getMainBoardSerial().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", companyId=").append(companyId);
        sb.append(", systemId=").append(systemId);
        sb.append(", subject=").append(subject);
        sb.append(", privateAlias=").append(privateAlias);
        sb.append(", keyPass=").append(keyPass);
        sb.append(", storePass=").append(storePass);
        sb.append(", licensePath=").append(licensePath);
        sb.append(", privateKeyStorePath=").append(privateKeyStorePath);
        sb.append(", issuedTime=").append(issuedTime);
        sb.append(", expiryTime=").append(expiryTime);
        sb.append(", consumerType=").append(consumerType);
        sb.append(", consumerAmount=").append(consumerAmount);
        sb.append(", description=").append(description);
        sb.append(", ipAddress=").append(ipAddress);
        sb.append(", macAddress=").append(macAddress);
        sb.append(", cpuSerial=").append(cpuSerial);
        sb.append(", mainBoardSerial=").append(mainBoardSerial);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}