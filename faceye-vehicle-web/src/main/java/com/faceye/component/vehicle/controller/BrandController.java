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


import com.faceye.component.vehicle.entity.Brand;
import com.faceye.component.vehicle.service.BrandService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.view.MessageBuilder;
import com.faceye.feature.util.GlobalEntity;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.controller<br>
 * 说明:<br>
 * 实体:品牌:com.faceye.component.vehicle.entity.entity.Brand<br>
 * @author haipenge <br>
 * haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:38<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/vehicle/brand")
public class BrandController extends BaseController<Brand, Long, BrandService> {
	@Autowired
	public BrandController(BrandService service) {
		super(service);
	}
	
	
	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br> 
	 * haipenge@gmail.com <br>
	 * 创建日期2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams=HttpUtil.getRequestParams(request);
		beforeInput(model,request);
		Page<Brand> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.brand"));
		model.addAttribute("global",global);
		return "vehicle.brand.home";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,Model model,HttpServletRequest request) {
		if(id!=null){
			Brand brand=this.service.get(id);
			beforeInput(brand,model,request);
			model.addAttribute("brand", brand);
		}
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.brand.edit"));
		model.addAttribute("global",global);
		return "vehicle.brand.update";
	}
	
	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping(value="/input")
	public String input(Brand brand, Model model,HttpServletRequest request){
		beforeInput(brand,model,request);
		GlobalEntity global=new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.brand.add"));
		model.addAttribute("global",global);
		return "vehicle.brand.update";
	}
	

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Brand brand,BindingResult bindingResult,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
		  beforeInput(brand,model,request);
		  return "vehicle.brand.update";
		}else{
		  this.beforeSave(brand,request);
	      this.service.save(brand);
	      this.afterSave(brand,request);
		  return "redirect:/vehicle/brand/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,RedirectAttributesModelMap model) {
		if(id!=null){
			Brand brand=this.service.get(id);
				if(brand!=null){
					if(beforeRemove(brand,model)){
						this.service.remove(brand);		
						//MessageBuilder.getInstance().setMessage(model,brand.getName()+" "+ this.getI18N("global.remove.success"));
					}
				}
		}
		return "redirect:/vehicle/brand/home";
	}
	
	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	  * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required=true) String  ids, RedirectAttributes redirectAttributes,Model model) {
		if(StringUtils.isNotEmpty(ids)){
			String [] idArray=ids.split(",");
			for(String id:idArray){
				Brand brand=this.service.get(Long.parseLong(id));
				if(brand!=null){
					if(beforeRemove(brand,model)){
						this.service.remove(brand);
						//MessageBuilder.getInstance().setMessage(model,brand.getName()+" "+ this.getI18N("global.remove.success"));		
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
	 * 创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id,Model model){
		if(id!=null){
			Brand brand=this.service.get(id);
			model.addAttribute("brand", brand);
		}
		return "vehicle.brand.detail";
	}
	///////////////////////////////////////////////以下为回调函数///////////////////////////////////////////////

	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:haipenge<br>
	 * haipenge @gmail.com <br>
	 * 创建日期:2016-8-4 14:26:38<br>
	 */
	protected void beforeInput(Brand brand,Model model,HttpServletRequest request){
	}
	
	/**
	 * 保存前的数据回调
	 * @todo
	 * @param brand
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:38
	 */
	protected void beforeSave(Brand brand,HttpServletRequest request){
	}
	
	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Brand brand,Model model){
		boolean res=true;
		
		return res;
	}
	
	/**
	 * 保存后的数据回调
	 * @todo
	 * @param brand
	 * @param request
	 * @author:haipenge
	 * 联系:haipenge@gmail.com
	 * 创建日期:2016-8-4 14:26:38
	 */
	protected void afterSave(Brand brand,HttpServletRequest request){
	   
	}
	

}
