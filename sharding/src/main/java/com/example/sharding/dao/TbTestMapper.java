package com.example.sharding.dao;

import com.baw.sharding.entity.TbTest;
import com.baw.sharding.entity.TbTestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbTestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    long countByExample(TbTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int deleteByExample(TbTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int insert(TbTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int insertSelective(TbTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    List<TbTest> selectByExample(TbTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    TbTest selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TbTest record, @Param("example") TbTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TbTest record, @Param("example") TbTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TbTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_test
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbTest record);
}