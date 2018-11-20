package org.mountcloud.springmvc.framework.mongodb.service;

import org.mountcloud.springmvc.framework.mongodb.entity.BaseEntity;
import org.mountcloud.springmvc.framework.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.common.service
 * @Description: TODO 封装一下父类，以后简单的查询起码不需要重复写了
 * @date 2018/1/26.
 */
public class BaseService<E extends BaseEntity,S extends MongoRepository<E,?>>{

    @Autowired
    protected S repository;

    private List<String> notset = new ArrayList<String>();

    public BaseService(){
        notset.add("id");
    }

    /**
     * 查询列表
     * @param bean 实体
     * @return 查询结果
     */
    public List<E> findList(E bean){
        Example<E> example = Example.of(bean);
        List<E> list = repository.findAll(example);
        return list;
    }

    /**
     * 查询一个
     * @param bean 实体
     * @return 查询结果 如果没有查到结果则为空
     */
    public E findOne(E bean){
        Example<E> example = Example.of(bean);
        return repository.findOne(example);
    }

    /**
     * 保存一个bean
     * @param bean 实体
     * @return 保存结果
     */
    public E save(E bean){
        ObjectUtil.setNullFields(bean,notset);
        bean = repository.save(bean);
        return bean;
    }

    /**
     * 更新一个bean
     * @param bean 实体
     * @return 更新结果
     */
    public E update(E bean){
        bean = repository.save(bean);
        return bean;
    }

    /**
     * 删除一个bean
     * @param bean 实体
     * @return 删除结果
     */
    public boolean delete(E bean){
        boolean state = true;
        try{
            repository.delete(bean);
        }catch (Exception e){
            state = false;
        }
        return state;
    }


}
