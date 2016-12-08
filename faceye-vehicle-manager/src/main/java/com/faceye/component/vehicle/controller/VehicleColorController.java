package com.faceye.component.vehicle.controller;

import java.util.List;
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

import com.faceye.component.vehicle.entity.Brand;
import com.faceye.component.vehicle.entity.VehicleColor;
import com.faceye.component.vehicle.service.BrandService;
import com.faceye.component.vehicle.service.VehicleColorService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.controller<br>
 * 说明:<br>
 * 实体:电动车颜色:com.faceye.component.vehicle.entity.entity.VehicleColor<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:36<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/vehicle/vehicleColor")
public class VehicleColorController extends BaseController<VehicleColor, Long, VehicleColorService> {
	@Autowired
	private BrandService brandService=null;
	@Autowired
	private VehicleColorService vehicleColorService=null;
	@Autowired
	public VehicleColorController(VehicleColorService service) {
		super(service);
	}
	
	
	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<VehicleColor> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.vehicleColor"));
		model.addAttribute("global",global);
		return "vehicle.vehicleColor.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			VehicleColor vehicleColor=this.service.get(id);
			beforeInput(vehicleColor,model,request);
			model.addAttribute("vehicleColor", vehicleColor);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.vehicleColor.edit"));
		model.addAttribute("global",global);
		return "vehicle.vehicleColor.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping(value="/input")
	public String input(VehicleColor vehicleColor, Model model,HttpServletRequest request){
		beforeInput(vehicleColor,model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.vehicleColor.add"));
		model.addAttribute("global",global);
		return "vehicle.vehicleColor.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid VehicleColor vehicleColor,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(vehicleColor,model,request);
		  return "vehicle.vehicleColor.update";
		}else{
		  this.beforeSave(vehicleColor,request);
	      this.service.save(vehicleColor);
	      this.afterSave(vehicleColor,request);
		  return "redirect:/vehicle/vehicleColor/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id,Model model) {
		if(id!=null){
			VehicleColor vehicleColor=this.service.get(id);
				if(vehicleColor!=null){
					if(beforeRemove(vehicleColor,model)){
						this.service.remove(vehicleColor);		
						//MessageBuilder.getInstance().setMessage(model,vehicleColor.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
		}
		//return "redirect:/vehicle/vehicleColor/home";
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				VehicleColor vehicleColor=this.service.get(Long.parseLong(id));
				if(vehicleColor!=null){
					if(beforeRemove(vehicleColor,model)){
						this.service.remove(vehicleColor);
						//MessageBuilder.getInstance().setMessage(model,vehicleColor.getName()+" "+ this.getI18N("global.remove.success"));		
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
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			VehicleColor vehicleColor=this.service.get(id);
			model.addAttribute("vehicleColor", vehicleColor);
		}
		return "vehicle.vehicleColor.detail";
	}
	///////////////////////////////////////////////以下为回调函数///////////////////////////////////////////////

	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	protected void beforeInput(VehicleColor vehicleColor,Model model,HttpServletRequest request){
		List<Brand> brands=this.brandService.getAll();
		List<VehicleColor> vehicleColors=this.vehicleColorService.getAll();
		model.addAttribute("brands", brands);
		model.addAttribute("vehicleColors", vehicleColors);
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param vehicleColor
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:36
	 */
	protected void beforeSave(VehicleColor vehicleColor,HttpServletRequest request){
	}
	
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(VehicleColor vehicleColor,Model model){
		boolean res=true;
		
		return res;
	}
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param vehicleColor
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:36
	 */
	protected void afterSave(VehicleColor vehicleColor,HttpServletRequest request){
	   
	}
	

}
