package com.shen.ssmschoolshop.dao;

import org.apache.ibatis.annotations.Param;
import com.shen.ssmschoolshop.entity.Admin;
import com.shen.ssmschoolshop.entity.AdminExample;

import java.util.List;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer adminid);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer adminid);

    Admin selectByName(Admin admin);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    /**
     * 查询admin库的所有管理员
     * @return
     */
    List<Admin> selectAllManager();

    /**
     * 超管添加单个普通管理员
     * @param admin
     */
    public void addManager(Admin admin);

    /**
     * 更新用户money接口
     * @param money 充值金额
     * @param userId 用户ID
     */
    public void userTopup(@Param("money") Integer money,@Param("userId") Integer userId);

    public Integer getMoneyByUserId(@Param("userId") Integer userId);
}