package com.faceye.component.vehicle.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;



import com.faceye.component.vehicle.repository.mongo.customer.ScanLogCustomerRepository;
import com.faceye.component.vehicle.repository.mongo.gen.ScanLogGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * ScanLog 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ScanLogRepository extends ScanLogGenRepository{
	
	
}/**@generate-repository-source@**/
