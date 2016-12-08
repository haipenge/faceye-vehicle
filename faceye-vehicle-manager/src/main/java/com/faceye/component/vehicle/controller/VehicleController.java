package com.faceye.component.vehicle.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.MapUtils;
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

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.vehicle.entity.Brand;
import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.entity.VehicleColor;
import com.faceye.component.vehicle.service.BrandService;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.component.vehicle.service.VehicleColorService;
import com.faceye.component.vehicle.service.VehicleService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.pair.Pair;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.controller<br>
 * 说明:<br>
 * 实体:电动车:com.faceye.component.vehicle.entity.entity.Vehicle<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2016-8-4 14:26:37<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/vehicle/vehicle")
public class VehicleController extends BaseController<Vehicle, Long, VehicleService> {
	@Autowired
	protected VehicleColorService vehicleColorService = null;
	@Autowired
	protected BrandService brandService = null;
	@Autowired
	protected CustomerService customerService = null;
	@Autowired
	protected LicensePlateService licensePlateService = null;

	@Autowired
	public VehicleController(VehicleService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge@gmail.com <br>
	 *                      创建日期2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Customer customer = null;
		Map searchParams = HttpUtil.getRequestParams(request);
		beforeInput(model, request);
		Page<Vehicle> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		Long customerId = MapUtils.getLong(searchParams, "customerid");
		if (customerId != null) {
			customer = this.customerService.get(customerId);
			model.addAttribute("customer", customer);
		}
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.vehicle"));
		model.addAttribute("global", global);
		return "vehicle.vehicle.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			Vehicle vehicle = this.service.get(id);
			beforeInput(vehicle, model, request);
			model.addAttribute("vehicle", vehicle);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.vehicle.edit"));
		model.addAttribute("global", global);
		return "vehicle.vehicle.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Vehicle vehicle, Model model, HttpServletRequest request) {
		beforeInput(vehicle, model, request);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.vehicle.add"));
		model.addAttribute("global", global);
		return "vehicle.vehicle.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Vehicle vehicle, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(vehicle, model, request);
			return "vehicle.vehicle.update";
		} else {
			this.beforeSave(vehicle, request);
			this.service.save(vehicle);
			this.afterSave(vehicle, request);
			// return "redirect:/vehicle/vehicle/home";
//			return "redirect:/vehicle/licensePlate/plateDistribute?vehicleId=" + vehicle.getId();
			return "redirect:/vehicle/vehicle/detail/"+vehicle.getId();
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id, Model model) {
		if (id != null) {
			Vehicle vehicle = this.service.get(id);
			if (vehicle != null) {
				if (beforeRemove(vehicle, model)) {
					this.service.remove(vehicle);
					// MessageBuilder.getInstance().setMessage(model,vehicle.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		// return "redirect:/vehicle/vehicle/home";
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Vehicle vehicle = this.service.get(Long.parseLong(id));
				if (vehicle != null) {
					if (beforeRemove(vehicle, model)) {
						this.service.remove(vehicle);
						// MessageBuilder.getInstance().setMessage(model,vehicle.getName()+" "+ this.getI18N("global.remove.success"));
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
	 *                      创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Vehicle vehicle = this.service.get(id);
			model.addAttribute("vehicle", vehicle);
		}
		return "vehicle.vehicle.detail";
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
	 *                      创建日期:2016-8-4 14:26:37<br>
	 */
	protected void beforeInput(Vehicle vehicle, Model model, HttpServletRequest request) {
		List<VehicleColor> vehicleColors = this.vehicleColorService.getAll();
		model.addAttribute("vehicleColors", vehicleColors);
		List<Brand> brands = this.brandService.getAll();
		model.addAttribute("brands", brands);
		List<Customer> customers = this.customerService.getAll();
		model.addAttribute("customers", customers);
		List<Pair<String, String>> types = Vehicle.types.getPairs();
		List<Pair<String, String>> status = Vehicle.statusTexts.getPairs();
		model.addAttribute("types", types);
		model.addAttribute("status", status);
		Map params = HttpUtil.getRequestParams(request);
		Long customerId = MapUtils.getLong(params, "customerId");
		if (customerId != null) {
			Customer customer = this.customerService.get(customerId);
			model.addAttribute("customer", customer);
			vehicle.setCustomer(customer);
		}
		List<Pair<String, String>> sexs = Customer.sexTexts.getPairs();
		model.addAttribute("sexs", sexs);
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param vehicle
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:37
	 */
	protected void beforeSave(Vehicle vehicle, HttpServletRequest request) {
		Customer customer = vehicle.getCustomer();
		if (customer != null) {
			Customer existCustomer = this.customerService.getCustomerByMobile(customer.getMobile());
			existCustomer.setName(customer.getName());
			existCustomer.setSex(customer.getSex());
			existCustomer.setPersonalId(customer.getPersonalId());
			this.customerService.save(existCustomer);
			vehicle.setCustomer(existCustomer);
		}
		LicensePlate licensePlate = vehicle.getLicensePlate();
        if(licensePlate!=null){
        	licensePlate=this.licensePlateService.get(licensePlate.getId());
        	vehicle.setLicensePlate(licensePlate);
        }
	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Vehicle vehicle, Model model) {
		boolean res = true;

		return res;
	}

	/**
	 * 保存后的数据回调
	 * 
	 * @todo
	 * @param vehicle
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:37
	 */
	protected void afterSave(Vehicle vehicle, HttpServletRequest request) {

	}

}
