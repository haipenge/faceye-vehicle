package com.faceye.component.vehicle.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.util.ServiceException;

import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.repository.mongo.LicensePlateRepository;
import com.faceye.component.vehicle.repository.mongo.customer.LicensePlateCustomerRepository;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.service.impl<br>
 * 说明:<br>
 * 实体:车牌->com.faceye.component.vehicle.entity.entity.LicensePlate 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-4 14:26:36<br>
 */
@Service
public class LicensePlateServiceImpl extends BaseMongoServiceImpl<LicensePlate, Long, LicensePlateRepository> implements LicensePlateService {
    @Autowired
    private LicensePlateCustomerRepository licensePlateCustomerRepository=null;
	@Autowired
	public LicensePlateServiceImpl(LicensePlateRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2016-8-4 14:26:36<br>
	*/
	@Override
	public Page<LicensePlate> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<LicensePlate> entityPath = resolver.createPath(entityClass);
		// PathBuilder<LicensePlate> builder = new PathBuilder<LicensePlate>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		//Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		//if (predicate != null) {
		//	logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		//}
		//Sort sort = new Sort(Direction.DESC, "id");
		//Page<LicensePlate> res = null;
		//if (size != 0) {
		//	Pageable pageable = new PageRequest(page, size, sort);
		//	res = this.dao.findAll(predicate, pageable);
		//} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<LicensePlate>("id") {
			// })
		//	List<LicensePlate> items = (List) this.dao.findAll(predicate);
		//	res = new PageImpl<LicensePlate>(items);

		//}
		return dao.getPage(searchParams,page,size);
	}
	
	
}/**@generate-service-source@**/
