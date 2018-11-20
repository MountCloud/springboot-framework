package org.mountcloud.springmvc.framework.mysql.controller;

import org.mountcloud.springmvc.framework.mysql.entity.BaseEntity;
import org.mountcloud.springmvc.framework.mysql.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 所有controller都需要继承这个Controller
 * @author zhanghaishan
 * @version V1.0
 * date 2017年8月22日 下午5:02:35
 */
public class BaseController<T extends BaseEntity, D extends BaseService<T, ?>> {

	/**
	 * 服务
	 */
	@Autowired
	public D baseService;

	public D getBaseService() {
		return baseService;
	}

	public void setBaseService(D baseService) {
		this.baseService = baseService;
	}

}
