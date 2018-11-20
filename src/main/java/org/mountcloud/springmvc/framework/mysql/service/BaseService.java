package org.mountcloud.springmvc.framework.mysql.service;

import org.mountcloud.springmvc.framework.mysql.dao.BaseDao;
import org.mountcloud.springmvc.framework.mysql.entity.BaseEntity;
import org.mountcloud.springmvc.framework.mysql.entity.BaseExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 
* @Title: BaseService.java 
* @Package com.coconet.common.service 
* @Description: TODO 与数据库相关的服务父类，包含很多基础操作
* @author zhanghaishan
* @date 2017年8月22日 下午5:00:43 
* @version V1.0
 */
public abstract class BaseService<T extends BaseEntity, D extends BaseDao<T, ?>> {
	@Autowired
	public D generalDao;

	/**
	 * 通用删除方法，根据id
	 * @param id
	 */
	public boolean delete(int id) {
		return generalDao.delete(id) > 0;
	}

	/**
	 * 查询总数
	 * 
	 * @param bean 条件辅助类
	 * @return 总数
	 */
	public Long listCount(T bean) {
		return generalDao.listCount(getExample(bean));
	}

	/**
	 * 查找用户，根据bean
	 * 
	 * @param bean 条件辅助类
	 * @return AdminUser集合
	 */
	public List<T> list(T bean) {
		return generalDao.list(getExample(bean));
	}
	
	/**
	 * 查找用户，根据bean
	 * @param bean 条件辅助类
	 * @return AdminUser集合
	 */
	public T listOne(T bean) {
		List<T> list = generalDao.list(getExample(bean));
		T obj = null;
		if(list!=null&&list.size()>0){
			obj = list.get(0);
		}
		return obj;
	}

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	public T listById(Object id) {
		return generalDao.listById(id);
	}

	/**
	 * 保存实体bean
	 * 
	 * @param bean 实体bean
	 * @throws Exception
	 */
	public boolean save(BaseEntity bean) {
		return generalDao.save(bean) > 0;
	}

	/**
	 * 保存实体bean
	 * 
	 * @param bean 实体bean
	 * @throws Exception
	 */
	public boolean saveSelective(BaseEntity bean) {
		return generalDao.saveSelective(bean);
	}

	/**
	 * 更新实体类，根据bean
	 * @param bean 实体bean
	 */
	public boolean update(T bean) {
		return generalDao.update(bean) > 0;
	}

	/**
	 * 更新实体类，根据bean
	 * 
	 * @param bean 实体bean
	 */
	public boolean updateSelective(T bean) {
		int num = generalDao.updateSelective(bean);
		return num > 0;
	}

	public D getGeneralDao() {
		return generalDao;
	}

	public void setGeneralDao(D generalDao) {
		this.generalDao = generalDao;
	}

	/**
	 * 自定义查询条件查询
	 * @param example 查询条件
	 * @param <D> 实体类型
	 * @return 查询结果
	 */
	public <D extends BaseExample> List listCustom(D example){
		return this.generalDao.listCustom(example);
	}

	/**
	 * 获取example的虚拟类
	 * @return 查询条件
	 */
	public abstract <E extends BaseExample> E getExample(T vo);

	/**
	 * 判断list是不是空
	 * @param list 数组
	 * @param <T> 数组类型
	 * @return
	 */
	public <T> boolean listIsNull(List<T> list){
		if(list==null||list.size()==0){
			return true;
		}
		return false;
	}
}
