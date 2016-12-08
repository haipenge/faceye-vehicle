package com.faceye.component.customer.service;

import com.faceye.component.customer.entity.Customer;
import com.faceye.feature.service.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * 模块:客户->com.faceye.compoent.customer.service<br>
 * 说明:<br>
 * 实体:车主->com.faceye.component.customer.entity.entity.Customer 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-4 14:26:38<br>
 */
public interface CustomerService extends BaseService<Customer,Long>{

	public void onWeixinOAuth(HttpServletRequest request, HttpServletResponse response);
	
	public Customer getCustomerByMobile(String mobile);
	
	public Customer getCustomerByWebUserId(Long webUserId);
	
	public Customer getLoginedCustomer();
}/**@generate-service-source@**/
