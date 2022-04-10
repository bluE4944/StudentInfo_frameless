package com.xinan.dao;

import com.xinan.daointer.UserDaoInter;
import com.xinan.entity.SelectSqlBean;
import com.xinan.entity.User;
import com.xinan.util.DBUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User 数据库操作
 * @author 11940
 */
public class UserDao implements UserDaoInter {

    /**
     * 传给外部调用的静态User对象
     */
    private static UserDao userDao = new UserDao();
    /**
     * 调用数据库连接工厂 获得数据库 连接
     */
    private DBUtil dbUtil = DBUtil.getInstance();
    private Connection conn =dbUtil.getConnection();

    /**
     * 课拼接的sql语句
     */
    private StringBuilder sbSql;

    public static UserDao getInstance (){return userDao;}

    /**
     * 私有化构造器
     */
    private UserDao(){

    }

    @Override
    public void save( User user) throws SQLException {
        StringBuilder saveSql = new StringBuilder("INSERT INTO user(user_name,phoneNumber,lastTime,administer");
        if(user.getId()!=-1){
            saveSql.append(",user_id) values(?,?,?,?,?)");
        }else {
            saveSql.append(") values(?,?,?,?)");
        }
        PreparedStatement ps = conn.prepareStatement(saveSql.toString());
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPhoneNumber());
        ps.setString(4,user.getRegisterTime());
        ps.setInt(5, user.getAdminister());
        if(user.getId()!=-1) {
            ps.setInt(6, user.getId());
        }
        ps.execute();
    }


    @Override
    public void update( User user) throws SQLException {
        //循环用
        int i=0;
        SelectSqlBean<Integer> selectSqlBean = getSql("update",user);
        //时间不能由用户 一定是修改了某样属性 所以一定有逗号
        selectSqlBean.getSbSql().append(", lastTime=?");

        //添加根据id
        selectSqlBean.getSbSql().append(" WHERE user_id = ? ");

        PreparedStatement ps = conn.prepareStatement(selectSqlBean.getSbSql().toString());
        for (int temp: selectSqlBean.getIntNum()) {
            i++;
            ps.setInt(i,temp);
        }

        for (String str: selectSqlBean.getStringNum()) {
            i++;
            ps.setString(i,str);
        }
        //设置时间
        i++;
        ps.setString(i,user.getRegisterTime());
        //设置id
        i++;
        ps.setInt(i,user.getId());
        ps.execute();
    }

    @Override
    public void delete(int user_id) throws SQLException {
        if(user_id==-1){
            System.out.println("UserDao.delete------>删除失败，user_id=-1");
            return;
        }
        String sql = "DELETE FROM user WHERE user_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,user_id);
        ps.execute();
    }

    @Override
    public List<User> userSelect(User user) throws SQLException {
        //循环用
        int i=0;
        SelectSqlBean<Integer> selectSqlBean = getSql("SELECT *",user);

        PreparedStatement ps = conn.prepareStatement(selectSqlBean.getSbSql().toString());
        for (int temp: selectSqlBean.getIntNum()) {
            i++;
            ps.setInt(i,temp);
        }

        for (String str: selectSqlBean.getStringNum()) {
            i++;
            ps.setString(i,str);
        }
        ResultSet rs = ps.executeQuery();
        List<User> userList = new ArrayList<>();
        while(rs.next()){
            User newUser = new User();
            newUser.setPhoneNumber(rs.getString("phoneNumber"));
            newUser.setPassword(rs.getString("password"));
            newUser.setAdminister(rs.getInt("administer"));
            newUser.setId(rs.getInt("user_id"));
            newUser.setUsername(rs.getString("user_name"));
            newUser.setRegisterTime(rs.getTimestamp("lastTime"));
            //username传过来就是中文 乱码 (已解决  原因：Mysql和Navicat编码不一致)
            userList.add(newUser);
        }
        return userList;
    }



    @Override
    public List<User> findAllUser() throws SQLException {
        String selectSql = "SELECT * FROM user ";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ResultSet rs = ps.executeQuery();
        List<User> userList = new ArrayList<User>();
        while(rs.next()){
            User user = new User();
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setPassword(rs.getString("password"));
            user.setAdminister(rs.getInt("administer"));
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("user_name"));
            user.setRegisterTime(rs.getTimestamp("lastTime"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> findSelectUser(int startIndex, int pageSize,User user)throws SQLException {
        //循环用
        int i=0;
        SelectSqlBean<Integer> selectSqlBean = getSql("SELECT *",user);
         selectSqlBean.getSbSql().append(" LIMIT ?,?");
        PreparedStatement ps = conn.prepareStatement(sbSql.toString());
        for (int temp: selectSqlBean.getIntNum()) {
            i++;
            ps.setInt(i,temp);
        }

        for (String str: selectSqlBean.getStringNum()) {
            i++;
            ps.setString(i,str);
        }
        i++;
        ps.setInt(i,startIndex);
        i++;
        ps.setInt(i,pageSize);
        ResultSet rs = ps.executeQuery();
        List<User> userList = new ArrayList<User>();
        while (rs.next()){
            User newUser = new User();
            newUser.setPhoneNumber(rs.getString("phoneNumber"));
            newUser.setPassword(rs.getString("password"));
            newUser.setAdminister(rs.getInt("administer"));
            newUser.setId(rs.getInt("user_id"));
            newUser.setUsername(rs.getString("user_name"));
            newUser.setRegisterTime(rs.getTimestamp("lastTime"));
            userList.add(newUser);
        }
        return userList;
    }

    @Override
    public boolean haveUser(User user) throws SQLException {
        //循环用
        int i=0;
        SelectSqlBean<Integer> selectSqlBean = getSql("SELECT *",user);

        PreparedStatement ps = conn.prepareStatement(selectSqlBean.getSbSql().toString());
        for (int temp: selectSqlBean.getIntNum()) {
            i++;
            ps.setInt(i,temp);
        }

        for (String str: selectSqlBean.getStringNum()) {
            i++;
            ps.setString(i,str);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            return true;
        }
        return false;
    }

    @Override
    public boolean haveId(int id) throws SQLException {
        String selectSql = "SELECT * FROM user WHERE user_id = ?";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            //有相同的id
            return true;
        }
        return false;
    }

    @Override
    public List<User> findPartUser(int startIndex, int pageSize) throws SQLException {
        String selectSql = "SELECT * FROM user LIMIT ?,?";
        PreparedStatement ps = conn.prepareStatement(selectSql);
        ps.setInt(1,startIndex);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        List<User> userList = new ArrayList<User>();
        while (rs.next()){
            User user = new User();
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setPassword(rs.getString("password"));
            user.setAdminister(rs.getInt("administer"));
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("user_name"));
            user.setRegisterTime(rs.getTimestamp("lastTime"));

            userList.add(user);
        }
        return userList;
    }

    @Override
    public boolean setPassword(String oldPassword, String newPassword, HttpServletRequest request) throws SQLException {
        //获得cookie
        Cookie[] cookies = request.getCookies();
        LoginDAO loginDAO = LoginDAO.getInstance();
        String phoneNumber = "";
        //判断cookie有内容
        if (cookies != null && cookies.length > 0) {
            //获得账号
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(User.PHONENUMBER)) {
                    phoneNumber = cookie.getValue();
                }
            }
        }
        if(loginDAO.isLoginOK(phoneNumber,oldPassword)){
            String sql = "update user SET password=? WHERE phoneNumber=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,newPassword);
            ps.setString(2,phoneNumber);
            ps.execute();
            return true;
        }

        return false;
    }

    /**
     * 是否添加AND
     */
    private void needAddAnd(){
        String str = sbSql.toString();
        String pattern = ".*=.*";
        //利用正则表达式 检查 查询语句里是否有 = 号
        boolean isMatch = Pattern.matches(pattern, str);
        if(isMatch){
            //有等于号，表示之前有数据 ，添加AND
            sbSql.append(" AND ");
        }
        //没有等号就表示之前没数据 ，不用添加AND
    }

    /**
     * 是否添加逗号
     */
    private void needAddComma(){
        String str = sbSql.toString();
        String pattern = ".*=.*";
        //利用正则表达式 检查 查询语句里是否有 = 号
        boolean isMatch = Pattern.matches(pattern, str);
        if(isMatch){
            //有等于号，表示之前有数据 ，添加AND
            sbSql.append(" , ");
        }
        //没有等号就表示之前没数据 ，不用添加AND
    }

    /**
     * 根据user对象获得SelectSqlBean 对象 包含拼接的sql语句 和 其中参数的值
     * @param user
     * @return
     */
    private SelectSqlBean getSql(String sql , User user){
        //获得id
        int id = user.getId();
        //获得administer
        int administer = user.getAdminister();
        //获得名字
        String username = user.getUsername();
        //获得phoneNumber
        String phoneNumber = user.getPhoneNumber();
        //有多少int类型的数据
        ArrayList<Integer> intNum = new ArrayList<>();
        //有多少String类型的数据
        ArrayList<String> stringNum = new ArrayList<>();

        SelectSqlBean<Integer> selectSqlBean = new SelectSqlBean<>();

        //拼接sql语句
        if(sql.equals("update")){
            sbSql = new StringBuilder(sql+" user SET ");
        }else {
            sbSql = new StringBuilder(sql+" FROM user where ");
        }
        /**
         * int类型的数据加在前面
         * 按照
         * id administer username phoneNumber emailAddress 的顺序来添加
         */
        //update 前面不需要 id
        if(!sql.equals("update")){
            //写了id
            if(id!=-1){
                if(sql.equals("update")){
                    needAddComma();
                }else {
                    needAddAnd();
                }
                sbSql.append(" user_id=? ");
                intNum.add(id);
            }
        }else {
            //只有update需要 administer
            needAddComma();
            sbSql.append(" administer=? ");
            intNum.add(administer);
        }

        //username不为空
        if (username!=null&&!username.equals("")){
            if(sql.equals("update")){
                needAddComma();
            }else {
                needAddAnd();
            }
            sbSql.append(" user_name=? ");
            stringNum.add(username);
        }

        //phoneNumber 不为空
        if(phoneNumber!=null&&!phoneNumber.equals("")){
            if(sql.equals("update")){
                needAddComma();
            }else {
                needAddAnd();
            }
            sbSql.append(" phoneNumber=? ");
            stringNum.add(phoneNumber);
        }
        selectSqlBean.setSbSql(sbSql);
        selectSqlBean.setIntNum(intNum);
        selectSqlBean.setStringNum(stringNum);
        return selectSqlBean;
    }
}
