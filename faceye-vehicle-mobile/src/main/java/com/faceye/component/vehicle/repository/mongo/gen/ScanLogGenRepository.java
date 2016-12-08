package com.faceye.component.vehicle.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.vehicle.entity.ScanLog;
import com.faceye.component.vehicle.repository.mongo.customer.ScanLogCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * ScanLog 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ScanLogGenRepository extends BaseMongoRepository<ScanLog,Long>  {
	 
	
}/**@generate-repository-source@**/
