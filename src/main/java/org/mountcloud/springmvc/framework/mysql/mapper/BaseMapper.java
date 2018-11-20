package org.mountcloud.springmvc.framework.mysql.mapper;

import org.apache.ibatis.annotations.Param;
import org.mountcloud.springmvc.framework.mysql.entity.BaseEntity;
import org.mountcloud.springmvc.framework.mysql.entity.BaseExample;

import java.util.List;

/**
 * Dao层Mapper的父类
 * @author zhanghaishan
 * @version V1.0
 * date 2017年8月22日 下午5:00:39
 */
public interface BaseMapper {

	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param <D> example
     * @param example example
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseExample> Long countByExample(D example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param <D> example
     * @param example example
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseExample> int deleteByExample(D example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table
     *
     * @param id id
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param record record
     * @param <D> entity
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity> int insert(D record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param <D> entity
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity> int insertSelective(D record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param example example
     * @param <D> entity
     * @param <E> example
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity,E extends BaseExample> List<D> selectByExample(E example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param id id
     * @param <D> entity
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity> D selectByPrimaryKey(Object id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param record record
     * @param example example
     * @param <D> entity
     * @param <E> example
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity,E extends BaseExample> int updateByExampleSelective(@Param("record") D record, @Param("example") E example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param record record
     * @param example example
     * @param <D> entity
     * @param <E> example
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity,E extends BaseExample> int updateByExample(@Param("record") D record, @Param("example") E example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param record record
     * @param <D> entity
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity> int updateByPrimaryKeySelective(D record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param record record
     * @param <D> entity
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseEntity> int updateByPrimaryKey(D record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 
     *
     * @param example example
     * @param <D> example
     * mbggenerated Tue Aug 22 15:20:02 CST 2017
     */
    public <D extends BaseExample> List selectCustomByExample(D example);
}
