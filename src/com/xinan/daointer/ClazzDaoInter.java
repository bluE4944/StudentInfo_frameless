package com.xinan.daointer;

import com.xinan.entity.Clazz;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 11940
 */
public interface ClazzDaoInter {
    /**
     * 保存clazz对象到 class表中
     * @param clazz
     * @throws SQLException
     */
    public void save(Clazz clazz) throws SQLException;

    /**
     * 删除对应clas_id 的记录
     * @param clas_id
     * @throws SQLException
     */
    public void delete(int clas_id) throws SQLException;

    /**
     * 查找所有class 记录
     * @return
     * @throws SQLException
     */
    public List<Clazz> findAllClazz() throws SQLException;

    /**
     * 根据teac_id 查找一页class记录
     * @param teac_id
     * @return
     * @throws SQLException
     */
    public List<Clazz> findClazzWithTeac_id(int teac_id,int startIndex, int pageSize) throws SQLException;

    /**
     * 根据teac_id 查找一页class记录
     * @param teac_id
     * @return
     * @throws SQLException
     */
    public List<Clazz> allClazzWithTeac_id(int teac_id) throws SQLException;

    /**
     * 查找一页的clazz 记录
     * @param startIndex
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<Clazz> findPartClazz(int startIndex, int pageSize)throws SQLException;
}
