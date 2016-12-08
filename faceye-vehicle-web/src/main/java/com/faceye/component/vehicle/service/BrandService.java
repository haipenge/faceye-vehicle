package com.faceye.component.vehicle.service;

import com.faceye.component.vehicle.entity.Brand;
import com.faceye.feature.service.BaseService;
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
/**
 * 模块:车辆->com.faceye.compoent.vehicle.service<br>
 * 说明:<br>
 * 实体:品牌->com.faceye.component.vehicle.entity.entity.Brand 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-4 14:26:38<br>
 */
public interface BrandService extends BaseService<Brand,Long>{

	
}/**@generate-service-source@**/
