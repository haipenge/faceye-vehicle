package com.faceye.component.vehicle.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.service.VehicleService;
import com.faceye.component.vehicle.entity.VehicleColor;
import com.faceye.component.vehicle.service.VehicleColorService;
import com.faceye.component.vehicle.entity.Brand;
import com.faceye.component.vehicle.service.BrandService;
import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.GlobalEntity;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.controller<br>
 * 说明:<br>
 * 实体:电动车:com.faceye.component.vehicle.entity.entity.Vehicle<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:37<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/vehicle/vehicle")
public class VehicleController extends BaseController<Vehicle, Long, VehicleService> {
	@Autowired
	private VehicleColorService vehicleColorService=null; 
	@Autowired
	private BrandService brandService=null; 
	@Autowired
	private CustomerService customerService=null; 
	
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
	 * haipenge@gmail.com <br>
	 * 创建日期2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/home")
	@ResponseBody
	public Page<Vehicle> home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<Vehicle> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return page;
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/edit/{id}")
	@ResponseBody
	public Vehicle edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		beforeInput(model,request);
		Vehicle vehicle=this.service.get(id);
		return vehicle;
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping(value="/input")
	public String input(Vehicle vehicle, Model model,HttpServletRequest request){
		beforeInput(model,request);
		return "vehicle.vehicle.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@Valid Vehicle vehicle,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(model,request);
		  return AjaxResult.getInstance().buildDefaultResult(false);
		}else{
		  this.beforeSave(vehicle,request);
	      this.service.save(vehicle);
	      this.afterSave(vehicle,request);
	      return  AjaxResult.getInstance().buildDefaultResult(true);
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		if(id!=null){
			this.service.remove(id);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				this.service.remove(Long.parseLong(id));
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}
	
	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:37<br>
	 */
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public Vehicle detail(@PathVariable Long id,Model model){
		Vehicle vehicle=this.service.get(id);
		return vehicle;
	}
	
	///////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:37<br>
	 */
	protected void beforeInput(Model model,HttpServletRequest request){
		    List<VehicleColor> vehicleColors=this.vehicleColorService.getAll();
		    model.addAttribute("vehicleColors", vehicleColors);
		    List<Brand> brands=this.brandService.getAll();
		    model.addAttribute("brands", brands);
		    List<Customer> customers=this.customerService.getAll();
		    model.addAttribute("customers", customers);
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param vehicle
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:37
	 */
	protected void beforeSave(Vehicle vehicle,HttpServletRequest request){
	}
	
	
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param vehicle
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:37
	 */
	protected void afterSave(Vehicle vehicle,HttpServletRequest request){
	   
	}
	

}
