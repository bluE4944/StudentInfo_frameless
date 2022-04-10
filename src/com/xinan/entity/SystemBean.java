package com.xinan.entity;

import java.io.Serializable;

/**
 * @author 11940
 */
public class SystemBean implements Serializable {
    private String systemName;
    private String loginName;
    private String navName;

    public SystemBean() {
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    @Override
    public String toString() {
        return "SystemBean{" +
                "systemName='" + systemName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", navName='" + navName + '\'' +
                '}';
    }
}
