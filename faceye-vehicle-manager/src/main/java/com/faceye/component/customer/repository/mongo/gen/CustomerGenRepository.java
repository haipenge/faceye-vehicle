package com.faceye.component.customer.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.customer.entity.Customer;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:客户->com.faceye.compoent.customer.repository.mongo<br>
 * 说明:<br>
 * 实体:车主->com.faceye.component.customer.entity.entity.Customer 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:38<br>
 */
public interface CustomerGenRepository extends BaseMongoRepository<Customer,Long> {
	
	
}/**@generate-repository-source@**/
