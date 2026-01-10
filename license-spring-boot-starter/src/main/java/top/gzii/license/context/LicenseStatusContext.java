package top.gzii.license.context;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.gzii.license.autofigure.LicenseProperty;

import java.util.Date;

@Component
public class LicenseStatusContext {
    private boolean ok;
    private Date lastVerifyTime=new Date();

    @Autowired
    private LicenseProperty licenseProperty;

    public boolean needUpdate(){
        int cacheTtl = licenseProperty.getCacheTtl();
        Date date = new Date(System.currentTimeMillis() - cacheTtl * 1000L);
        return lastVerifyTime.before(date);
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Date getLastVerifyTime() {
        return lastVerifyTime;
    }

    public void setLastVerifyTime(Date lastVerifyTime) {
        this.lastVerifyTime = lastVerifyTime;
    }

    public LicenseProperty getLicenseProperty() {
        return licenseProperty;
    }

    public void setLicenseProperty(LicenseProperty licenseProperty) {
        this.licenseProperty = licenseProperty;
    }
}
