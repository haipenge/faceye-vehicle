package com.faceye.component.vehicle.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
import com.faceye.component.vehicle.repository.mongo.customer.LicensePlateCustomerRepository;
import com.faceye.component.vehicle.repository.mongo.gen.LicensePlateGenRepository;
/**
 * 模块:车辆->com.faceye.compoent.vehicle.repository.mongo<br>
 * 说明:<br>
 * 实体:车牌->com.faceye.component.vehicle.entity.entity.LicensePlate 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:36<br>
* LicensePlateCustomerRepository,
 */
public interface LicensePlateRepository extends LicensePlateGenRepository {
	
	
}/**@generate-repository-source@**/
