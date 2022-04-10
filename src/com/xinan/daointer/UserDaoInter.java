package com.xinan.daointer;

import com.xinan.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 11940
 */
public interface UserDaoInter {
    /**
     *  插入对应 user对象 到数据库中
     * @param user
     * @throws SQLException
     */
    public void save( User user)throws SQLException;

    /**
     * 更新对应对应user的 user表 数据库信息
     * @param user
     * @throws SQLException
     */
    public void update( User user)throws SQLException;

    /**
     * 删除 对应的user 的数据库记录
     * @param user_id
     * @throws SQLException
     */
    public void delete(int user_id)throws SQLException;

    /**
     * 查找对应user的user 数据库信息
     * @param user
     * @return
     * @throws SQLException
     */
    public List<User> userSelect(User user)throws SQLException;

    /**
     * 查找所有user 数据库记录
     * @return User 对象的集合
     * @throws SQLException
     */
    public List<User> findAllUser()throws SQLException;

    /**
     * 查找一页的User 数据库数据
     * @param startIndex
     * @param pageSize
     * @return  User 对象的集合
     * @throws SQLException
     */
    public List<User> findPartUser(int startIndex,int pageSize)throws SQLException;

    /**
     * 根据cookie 的账号以及原密码判断是否正确  正确则根据cookie中的账号设置密码
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return 设置成功还是失败
     * @throws SQLException
     */
    public boolean setPassword( String oldPassword, String newPassword, HttpServletRequest request)throws SQLException;

    /**
     *  获得查询后的 user集合 并且是分页的
     * @param startIndex
     * @param pageSize
     * @param user
     * @return
     * @throws SQLException
     */
    public List<User> findSelectUser(int startIndex, int pageSize,User user)throws SQLException;

    /**
     * 根据传进来的user对象 查询表里是否有这条信息，如果有 则返回true 反之返回false
     * @param user
     * @return
     * @throws SQLException
     */
    public boolean haveUser(User user)throws SQLException;

    /**
     * 根据id查询表中是否有这个id 主键，有则返回 true 反之返回false
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean haveId(int id)throws SQLException;
}
