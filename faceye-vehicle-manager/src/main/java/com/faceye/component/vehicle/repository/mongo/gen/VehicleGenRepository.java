package com.faceye.component.vehicle.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:车辆->com.faceye.compoent.vehicle.repository.mongo<br>
 * 说明:<br>
 * 实体:电动车->com.faceye.component.vehicle.entity.entity.Vehicle 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:37<br>
 */
public interface VehicleGenRepository extends BaseMongoRepository<Vehicle,Long> {
	
	
}/**@generate-repository-source@**/
