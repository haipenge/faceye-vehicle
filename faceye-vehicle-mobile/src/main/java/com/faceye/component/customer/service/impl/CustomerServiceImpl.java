package com.faceye.component.customer.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.repository.mongo.CustomerRepository;
import com.faceye.component.customer.repository.mongo.customer.CustomerCustomerRepository;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.security.web.entity.User;
import com.faceye.component.security.web.service.UserService;
import com.faceye.component.weixin.entity.WeixinUser;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.WeixinUserService;
import com.faceye.component.weixin.service.oauth2.OAuth2Service;
import com.faceye.feature.service.Reporter;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.ServiceException;
import com.faceye.feature.util.bean.BeanContextUtil;
import com.faceye.feature.util.http.HttpUtil;

/**
 * 模块:客户->com.faceye.compoent.customer.service.impl<br>
 * 说明:<br>
 * 实体:车主->com.faceye.component.customer.entity.entity.Customer 服务实现类<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-4 14:26:38<br>
 */
@Service
public class CustomerServiceImpl extends BaseMongoServiceImpl<Customer, Long, CustomerRepository> implements CustomerService {
    @Autowired
    private CustomerCustomerRepository customerCustomerRepository=null;
    @Autowired
    private AccountService accountService=null;
    @Autowired
    private WeixinUserService weixinUserService=null;
    @Autowired
    private UserService userService=null;
    @Autowired
    private OAuth2Service oAuth2Service=null;
	@Autowired
	public CustomerServiceImpl(CustomerRepository dao) {
		super(dao);
	}
	
	/**
	 *数据分页查询
	 * @author haipenge <br>
     * 联系:haipenge@gmail.com<br>
     * 创建日期:2016-8-4 14:26:38<br>
	*/
	@Override
	public Page<Customer> getPage(Map<String, Object> searchParams, int page, int size) throws ServiceException {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<Customer> entityPath = resolver.createPath(entityClass);
		// PathBuilder<Customer> builder = new PathBuilder<Customer>(entityPath.getType(), entityPath.getMetadata());
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
		//Page<Customer> res = null;
		//if (size != 0) {
		//	Pageable pageable = new PageRequest(page, size, sort);
		//	res = this.dao.findAll(predicate, pageable);
		//} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new NumberExpression<Customer>("id") {
			// })
		//	List<Customer> items = (List) this.dao.findAll(predicate);
		//	res = new PageImpl<Customer>(items);

		//}
		return dao.getPage(searchParams,page,size);
	}
	
	
	@Override
	public void onWeixinOAuth(HttpServletRequest request, HttpServletResponse response) {
		Map params = HttpUtil.getRequestParams(request);
//		String appid = this.accountService.get(accountId).getAppId();
		String appid=MapUtils.getString(params, "appId");
		logger.debug(">>FaceYe --> get app id from params is:"+appid);
		String code = MapUtils.getString(params, "code");
		BeanContextUtil.getInstance().getBean(Reporter.class).reporter(params);
		if (StringUtils.isNotEmpty(code)) {
			logger.debug(">>FaceYe --> appid is:" + appid + ",code is:" + code);
			WeixinUser weixinUser = this.oAuth2Service.oauth2(appid, code);
			if (weixinUser != null) {
				logger.debug(">>FaceYe --> weixin user opeid is:" + weixinUser.getOpenid());
				this.userService.weixinOAuth2AndAutoLogin(request, response, weixinUser.getOpenid());
				User user=this.userService.getUserByWeixinOpenId(weixinUser.getOpenid());
//				Rabbit rabbit = this.getRabbitByWeixinUser(weixinUser);
				Customer customer=this.dao.getCustomerByWebUserId(user.getId());
				if (customer == null) {
					customer = new Customer();
					customer.setName(weixinUser.getNickname());
				} else {
					if (StringUtils.isEmpty(customer.getName())) {
						customer.setName(weixinUser.getNickname());
					}
				}
				customer.setWebUserId(user.getId());
				this.save(customer);
				// model.addAttribute("weixinUser", weixinUser);
				// 将用户信息放在session里面
				logger.debug(">>FaceYe --> weixin user headimg is:"+weixinUser.getHeadimgurl()+",weixin nickname:"+weixinUser.getNickname());
				request.getSession().setAttribute("weixinUser", weixinUser);
				request.getSession().setAttribute("ecustomer", customer);
			} else {
				logger.debug(">>FaceYe --> Have not got weixinUser.");
			}
		} else {
			logger.debug(">>FaceYe appid or code is emty now .............................................");
		}
	}

	@Override
	public Customer getCustomerByMobile(String mobile) {
		return this.dao.getCustomerByMobile(mobile);
	}

	@Override
	public Customer getCustomerByWebUserId(Long webUserId) {
		return this.dao.getCustomerByWebUserId(webUserId);
	}

	@Override
	public Customer getLoginedCustomer() {
		Customer customer=null;
		User user=this.userService.getCurrentLoginUser();
		if(user!=null){
			customer=this.getCustomerByWebUserId(user.getId());
		}
		return customer;
	}
	
}/**@generate-service-source@**/
