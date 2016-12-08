package com.faceye.component.vehicle.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.repository.mongo.LicensePlateRepository;
import com.faceye.component.vehicle.repository.mongo.customer.LicensePlateCustomerRepository;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.RandUtil;
import com.faceye.feature.util.ServiceException;

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

	@Override
	public void generate(Map params) {
		String key=MapUtils.getString(params, "key");
		Integer count=MapUtils.getInteger(params, "count");
		if(count==null){
			count=10;
		}
		List<String> plates=this.generate(key, count);
		if(CollectionUtils.isNotEmpty(plates)){
			for(String plate:plates){
				Map searchParams=new HashMap();
				searchParams.put("EQ|plateNum",plate);
				Page<LicensePlate> licensePlates=this.getPage(searchParams, 1, 1);
				if(licensePlates!=null && CollectionUtils.isNotEmpty(licensePlates.getContent())){
					logger.debug(">>FaceYe --> plate :"+plate +" is exist now,can not generate.");
				}else{
					LicensePlate licensePlate=new LicensePlate();
					licensePlate.setPlateNum(plate);
					//设置为已生成
					licensePlate.setStatus(0);
					//车牌编号
					licensePlate.setSerialNum("");
					//车牌识别码
					licensePlate.setSignNum("");
					this.save(licensePlate);
				}
			}
		}
		
		
	}
	
	/**
	 * 生成指定范围内的车牌
	 * @param key
	 * @param count
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月6日 下午2:58:31
	 */
	private List<String> generate(String key,Integer count){
		List<String> res=new ArrayList<String>(0);
		int total=0;
		while(total<count){
			String plate=key.toUpperCase()+RandUtil.getRandInt(10000, 99999);
			plate=StringUtils.replace(plate, "4", "8");
			res.add(plate);
			total++;
		}
		return res;
	}

	@Override
	public LicensePlate getLicensePlateByPlateNum(String plateNum) {
		return this.dao.getLicensePlateByPlateNum(plateNum);
	}
	
	
}/**@generate-service-source@**/
