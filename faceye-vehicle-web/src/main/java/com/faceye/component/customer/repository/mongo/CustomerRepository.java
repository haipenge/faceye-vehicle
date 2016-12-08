package com.faceye.component.customer.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.customer.entity.Customer;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
import com.faceye.component.customer.repository.mongo.customer.CustomerCustomerRepository;
import com.faceye.component.customer.repository.mongo.gen.CustomerGenRepository;
/**
 * 模块:客户->com.faceye.compoent.customer.repository.mongo<br>
 * 说明:<br>
 * 实体:车主->com.faceye.component.customer.entity.entity.Customer 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:38<br>
* CustomerCustomerRepository,
 */
public interface CustomerRepository extends CustomerGenRepository {
	
	
}/**@generate-repository-source@**/
