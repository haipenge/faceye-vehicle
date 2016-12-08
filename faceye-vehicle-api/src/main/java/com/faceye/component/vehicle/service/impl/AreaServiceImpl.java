package com.faceye.component.vehicle.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.vehicle.entity.Area;

import com.faceye.component.vehicle.repository.mongo.AreaRepository;
import com.faceye.component.vehicle.repository.mongo.customer.AreaCustomerRepository;
import com.faceye.component.vehicle.service.AreaService;

import com.faceye.feature.util.ServiceException;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.querydsl.core.types.Predicate;
/**
 * Area 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class AreaServiceImpl extends BaseMongoServiceImpl<Area, Long, AreaRepository> implements AreaService {
	@Autowired
	private AreaCustomerRepository areaCustomerRepository=null;
	@Autowired
	public AreaServiceImpl(AreaRepository dao) {
		super(dao);
	}
	
	
	@Override
	public Page<Area> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Area> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Area> builder = new PathBuilder<Area>(entityPath.getType(), entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<Area> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Area>("id") {
			// })
			List<Area> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<Area>(items);

		}
		return res;
	}
	
}/**@generate-service-source@**/
