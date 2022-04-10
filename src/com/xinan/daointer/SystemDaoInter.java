package com.xinan.daointer;

import java.sql.SQLException;

/**
 * 系统表操作接口
 * @author 11940
 */
public interface SystemDaoInter {
    /**
     * 设置系统名字
     * @param name
     * @throws SQLException
     */
    public void setSysName( String name)throws SQLException;

    /**
     * 设置登陆显示
     * @param name
     * @throws SQLException
     */
    public void setLoginName(String name)throws SQLException;

    /**
     * 设置导航条显示
     * @param name
     * @throws SQLException
     */
    public void setNavName(String name)throws SQLException;

    /**
     * 获得登陆时显示的文字
     * @return
     * @throws SQLException
     */
    public String getLoginName()throws  SQLException;

    /**
     * 获得系统名字
     * @return
     * @throws SQLException
     */
    public String getSysName()throws  SQLException;

    /**
     * 获得导航条显示的文字
     * @return
     * @throws SQLException
     */
    public String getNavName()throws  SQLException;
}
