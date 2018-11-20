package org.mountcloud.springmvc.framework.mysql.dao;

import org.mountcloud.springmvc.framework.mysql.entity.BaseEntity;
import org.mountcloud.springmvc.framework.mysql.entity.BaseExample;
import org.mountcloud.springmvc.framework.mysql.mapper.BaseMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 所有与数据库相关的Dao均继承次Dao
 * @author zhanghaishan
 * @version V1.0
 * date 2017年8月22日 下午5:00:27
 */
public abstract class BaseDao<T extends BaseEntity, D extends BaseMapper>
		extends
		SqlSessionDaoSupport {

	/**
	 * mapper
	 */
	@Autowired
	public D mapper;

	/**
	 * mapper.xml 的包名
	 * 如果mapper.xml在com.mountcloud.mapper.testMapper.xml，此值为com.mountcloud.mapper.testMapper
	 */
	public String mapperPreffix;

	/**
	 * 删除用户
	 * @param id 需要删除的id
	 * @return 删除结果
	 */
	public int delete(int id) {
		return mapper.deleteByPrimaryKey(id);
	}

	/**
	 * 查询总数
	 * 
	 * @param example 条件辅助类
	 * @param <E> example的泛型
	 * @return 总数
	 */
	public <E extends BaseExample> Long listCount(E example) {
		return mapper.countByExample(example);
	}

	/**
	 * 查找用户，根据vo
	 * 
	 * @param example 条件辅助类
	 * @param <E> 查询类的泛型
	 * @return AdminUser集合
	 */
	public <E extends BaseExample> List<T> list(E example) {
		// 如果page为空，则取全部
		return mapper.selectByExample(example);
	}

	/**
	 * 查找用户，根据example
	 * @param example 查询条件
	 * @param <E> 查询类的泛型
	 * @return 查询结果
	 */
	public <E extends BaseExample> T listOne(E example){
		List<T> lists = mapper.selectByExample(example);
		T obj = null;
		if(lists!=null&&lists.size()>0){
			obj = lists.get(0);
		}
		return obj;
	}
	/**
	 * 查询，根据id
	 * @param id ID
	 * @return 查询结果
	 */
	public T listById(Object id) {
		return mapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存实体bean
	 * @param bean 实体
	 * @return 保存结果
	 */
	public int save(BaseEntity bean) {
		return mapper.insert(bean);
	}

	/**
	 * 保存实体bean，仅保存非空属性的值
	 * @param bean 实体
	 * @return 保存结果
	 */
	public boolean saveSelective(BaseEntity bean) {
		return mapper.insertSelective(bean) > 0;
	}

	/**
	 * 更新实体类，根据bean
	 * @param bean 实体
	 * @return 更新结果
	 */
	public int update(T bean) {
		return mapper.updateByPrimaryKey(bean);
	}

	/**
	 * 更新实体类，根据bean，仅更新非空属性
	 * @param bean 实体
	 * @return 更新结果
	 */
	public int updateSelective(T bean) {
		return mapper.updateByPrimaryKeySelective(bean);
	}
	
	/**
	 * 自定义查询
	 * @param example 查询条件
	 * @param <E> example
	 * @return 查询结果
	 */
	public <E extends BaseExample> List listCustom(E example){
		return mapper.selectCustomByExample(example);
	}

	/**
	 * 返回mapperPreffix
	 * @return mapper路径
	 */
	public String getMapperPreffix() {

		if(mapperPreffix==null){
			Class <D> entityClass = (Class <D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
			String className = entityClass.getName();
			mapperPreffix = className+".";
		}

		return mapperPreffix;
	}

	public void setMapperPreffix(String mapperPreffix) {
		this.mapperPreffix = mapperPreffix;
	}

	public D getMapper() {
		return mapper;
	}

	public void setMapper(D mapper) {
		this.mapper = mapper;
	}

	/**
	 @Override
	 @Autowired
	 public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	 super.setSqlSessionFactory(sqlSessionFactory);
	 }
	 **/

}
