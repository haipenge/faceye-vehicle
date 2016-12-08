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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.GlobalEntity;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.controller<br>
 * 说明:<br>
 * 实体:车牌:com.faceye.component.vehicle.entity.entity.LicensePlate<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:36<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/vehicle/licensePlate")
public class LicensePlateController extends BaseController<LicensePlate, Long, LicensePlateService> {
	@Autowired
	public LicensePlateController(LicensePlateService service) {
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
		Page<LicensePlate> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.licensePlate"));
		model.addAttribute("global",global);
		return "vehicle.licensePlate.home";
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
			LicensePlate licensePlate=this.service.get(id);
			beforeInput(licensePlate,model,request);
			model.addAttribute("licensePlate", licensePlate);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.licensePlate.edit"));
		model.addAttribute("global",global);
		return "vehicle.licensePlate.update";
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
	public String input(LicensePlate licensePlate, Model model,HttpServletRequest request){
		beforeInput(licensePlate,model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.licensePlate.add"));
		model.addAttribute("global",global);
		return "vehicle.licensePlate.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid LicensePlate licensePlate,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(licensePlate,model,request);
		  return "vehicle.licensePlate.update";
		}else{
		  this.beforeSave(licensePlate,request);
	      this.service.save(licensePlate);
	      this.afterSave(licensePlate,request);
		  return "redirect:/vehicle/licensePlate/home";
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
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(id!=null){
			LicensePlate licensePlate=this.service.get(id);
				if(licensePlate!=null){
					if(beforeRemove(licensePlate,model)){
						this.service.remove(licensePlate);		
						//MessageBuilder.getInstance().setMessage(model,licensePlate.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
		}
		return "redirect:/vehicle/licensePlate/home";
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
				LicensePlate licensePlate=this.service.get(Long.parseLong(id));
				if(licensePlate!=null){
					if(beforeRemove(licensePlate,model)){
						this.service.remove(licensePlate);
						//MessageBuilder.getInstance().setMessage(model,licensePlate.getName()+" "+ this.getI18N("global.remove.success"));		
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
			LicensePlate licensePlate=this.service.get(id);
			model.addAttribute("licensePlate", licensePlate);
		}
		return "vehicle.licensePlate.detail";
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
	protected void beforeInput(LicensePlate licensePlate,Model model,HttpServletRequest request){
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param licensePlate
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:36
	 */
	protected void beforeSave(LicensePlate licensePlate,HttpServletRequest request){
	}
	
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(LicensePlate licensePlate,Model model){
		boolean res=true;
		
		return res;
	}
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param licensePlate
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:36
	 */
	protected void afterSave(LicensePlate licensePlate,HttpServletRequest request){
	   
	}
	

}
