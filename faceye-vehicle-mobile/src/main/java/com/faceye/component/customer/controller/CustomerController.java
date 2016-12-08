package com.faceye.component.customer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.service.VehicleService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:客户->com.faceye.compoent.customer.controller<br>
 * 说明:<br>
 * 实体:车主:com.faceye.component.customer.entity.entity.Customer<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2016-8-4 14:26:38<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/customer/customer")
public class CustomerController extends BaseController<Customer, Long, CustomerService> {
	@Autowired
	private VehicleService vehicleService = null;

	@Autowired
	public CustomerController(CustomerService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge@gmail.com <br>
	 *                      创建日期2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<Customer> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("customer.customer"));
		model.addAttribute("global", global);
		return "customer.customer.mobile.home";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			Customer customer = this.service.get(id);
			beforeInput(customer, model, request);
			model.addAttribute("customer", customer);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("customer.customer.edit"));
		model.addAttribute("global", global);
		return "customer.customer.mobile.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Customer customer, Model model, HttpServletRequest request, HttpServletResponse response) {
		beforeInput(customer, model, request);
		this.service.onWeixinOAuth(request, response);
		GlobalEntity global = new GlobalEntity();
		// global.setTitle(this.getI18N("customer.customer.add"));
		global.setTitle("完善个人资料");
		model.addAttribute("global", global);
		Customer sessionCustomer = (Customer) request.getSession().getAttribute("ecustomer");
		if (sessionCustomer != null) {
			// customer.setId(sessionCustomer.getId());
			customer = sessionCustomer;
			logger.debug(">>FaceYe --> customer id is:" + customer.getId());
			model.addAttribute("customer", customer);
		} else {
			logger.debug(">>FaceYe --> have not got customer from session.");
		}
		String mobile = customer.getMobile();
		if (StringUtils.isEmpty(mobile)) {
			return "customer.customer.mobile.update";
		} else {
			return "redirect:/vehicle/vehicle/home";
//			List<Vehicle> vehicles = this.vehicleService.getVehicleByCustomer(customer);
//			if (CollectionUtils.isEmpty(vehicles)) {
//				return "redirect:/vehicle/vehicle/input?customerId=" + customer.getId();
//			} else {
//				// return "redirect:/vehicle/vehicle/detail/" + vehicle.getId();
//				logger.debug(">>FaceYe --> have got vehicles size is:" + vehicles.size());
//				return "redirect:/vehicle/vehicle/home";
//			}
		}
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Customer customer, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(customer, model, request);
			return "customer.customer.update";
		} else {
			this.beforeSave(customer, request);
			this.service.save(customer);
			this.afterSave(customer, request);
			// return "redirect:/customer/customer/home";
//			return "redirect:/vehicle/vehicle/input?customerId=" + customer.getId();
			return "redirect:/customer/customer/detail/"+customer.getId();
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, RedirectAttributesModelMap model) {
		if (id != null) {
			Customer customer = this.service.get(id);
			if (customer != null) {
				if (beforeRemove(customer, model)) {
					this.service.remove(customer);
					// MessageBuilder.getInstance().setMessage(model,customer.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/customer/customer/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Customer customer = this.service.get(Long.parseLong(id));
				if (customer != null) {
					if (beforeRemove(customer, model)) {
						this.service.remove(customer);
						// MessageBuilder.getInstance().setMessage(model,customer.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
			}
		}
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Customer customer = this.service.get(id);
			model.addAttribute("customer", customer);
		}
		return "customer.customer.mobile.detail";
	}

	/**
	 * 我的
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月11日 下午7:59:33
	 */
	@RequestMapping("/me")
	public String me(HttpServletRequest request, HttpServletResponse response, Model model) {
		this.service.onWeixinOAuth(request, response);
		Customer customer = (Customer) request.getSession().getAttribute("ecustomer");
		model.addAttribute("customer", customer);
		return "customer.customer.mobile.detail";
	}
	/////////////////////////////////////////////// 以下为回调函数///////////////////////////////////////////////

	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	protected void beforeInput(Customer customer, Model model, HttpServletRequest request) {
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param customer
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:38
	 */
	protected void beforeSave(Customer customer, HttpServletRequest request) {
	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Customer customer, Model model) {
		boolean res = true;

		return res;
	}

	/**
	 * 保存后的数据回调
	 * 
	 * @todo
	 * @param customer
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:38
	 */
	protected void afterSave(Customer customer, HttpServletRequest request) {

	}

}
