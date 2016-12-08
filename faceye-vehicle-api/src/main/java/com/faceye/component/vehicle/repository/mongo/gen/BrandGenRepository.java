package com.faceye.component.vehicle.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.vehicle.entity.Brand;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:车辆->com.faceye.compoent.vehicle.repository.mongo<br>
 * 说明:<br>
 * 实体:品牌->com.faceye.component.vehicle.entity.entity.Brand 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:38<br>
 */
public interface BrandGenRepository extends BaseMongoRepository<Brand,Long> {
	
	
}/**@generate-repository-source@**/
