package com.faceye.component.vehicle.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;



import com.faceye.component.vehicle.repository.mongo.customer.AreaCustomerRepository;
import com.faceye.component.vehicle.repository.mongo.gen.AreaGenRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Area 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface AreaRepository extends AreaGenRepository{
	
	
}/**@generate-repository-source@**/
