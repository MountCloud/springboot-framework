package org.mountcloud.springmvc.framework.mongodb.controller;

import org.mountcloud.springmvc.framework.mongodb.entity.BaseEntity;
import org.mountcloud.springmvc.framework.mongodb.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 夫controller
 * @author zhanghaishan
 * @version V1.0
 * date 2018/1/26.
 */
public class BaseController<E extends BaseEntity,S extends BaseService<E,?>> {

    /**
     * service
     */
    @Autowired
    protected S service;

}
