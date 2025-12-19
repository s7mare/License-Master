package top.gzii.license.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 许可证生成记录表
 * @TableName license_generation_record
 */
@Data
@TableName(value = "license_generation_record",autoResultMap = true)
public class GenerationRecord implements Serializable {
    /**
     * 生成记录id
     */
    @TableId(value = "id",type=IdType.INPUT)
    private Long id;

    private byte[] data;

    /**
     * 参数id
     */
    private Long paramId;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 系统id
     */
    private Long systemId;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 操作者名称
     */
    private String operator;

    /**
     *  生效时间
     */
    private Date issuedTime;
    /**
     *  结束时间
     */
    private Date endTime;
    /**
     * 操作时间
     */
    private Date createTime;

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
        GenerationRecord other = (GenerationRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParamId() == null ? other.getParamId() == null : this.getParamId().equals(other.getParamId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getSystemName() == null ? other.getSystemName() == null : this.getSystemName().equals(other.getSystemName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParamId() == null) ? 0 : getParamId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getSystemName() == null) ? 0 : getSystemName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paramId=").append(paramId);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", systemId=").append(systemId);
        sb.append(", systemName=").append(systemName);
        sb.append(", userId=").append(userId);
        sb.append(", operator=").append(operator);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}