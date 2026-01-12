package top.gzii.license.autofigure;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "license")
public class LicenseProperty {

    /**
     * 验证模式：
     * global - 全局拦截器模式（默认）
     * annotation - 注解验证模式（推荐）
     * hybrid - 混合模式
     */
    private String mode="global";

    /**
     * 是否启用严格模式
     */
    private boolean strict =true;

    /**
     *  证书主题
     */
    private String subject;

    /**
     *  公钥别名
     */

    private String publicAlias="publicCert";

    /**
     *  访问公钥库的密码
     */
    private String storePass="public_password1234";

    /**
     * License文件路径，默认路径为resources下
     */
    private String licensePath="/publicCerts.keystore" ;

    /**
     * 公钥文件路径
     */
    private String publicKeysStorePath ;

    /**
     * 拦截器排除路径
     */
    private List<String> excludePaths = new ArrayList<>();

    /**
     * 验证失败HTTP状态码
     */
    private int errorCode = 403;

    /**
     * 验证失败错误消息
     */
    private String errorMessage ;

    /**
     * 是否启用缓存
     */
    private boolean cacheEnabled = true;

    /**
     * 缓存有效期（秒）
     */
    private int cacheTtl = 300;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublicAlias() {
        return publicAlias;
    }

    public void setPublicAlias(String publicAlias) {
        this.publicAlias = publicAlias;
    }

    public String getStorePass() {
        return storePass;
    }

    public void setStorePass(String storePass) {
        this.storePass = storePass;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    public String getPublicKeysStorePath() {
        return publicKeysStorePath;
    }

    public void setPublicKeysStorePath(String publicKeysStorePath) {
        this.publicKeysStorePath = publicKeysStorePath;
    }

    public List<String> getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(List<String> excludePaths) {
        this.excludePaths = excludePaths;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public int getCacheTtl() {
        return cacheTtl;
    }

    public void setCacheTtl(int cacheTtl) {
        this.cacheTtl = cacheTtl;
    }
}