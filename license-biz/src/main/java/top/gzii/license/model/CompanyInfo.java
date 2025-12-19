package top.gzii.license.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 公司信息表
 * @TableName license_company_info
 */
@Data
@TableName(value = "license_company_info" ,autoResultMap = true)
public class CompanyInfo implements Serializable {
    /**
     * 公司id
     */
    @TableId(value = "id",type=IdType.INPUT)
    private Long id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司编码
     */
    private String code;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 是否删除
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
        CompanyInfo other = (CompanyInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getContactPerson() == null ? other.getContactPerson() == null : this.getContactPerson().equals(other.getContactPerson()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getContactPerson() == null) ? 0 : getContactPerson().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", address=").append(address);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}