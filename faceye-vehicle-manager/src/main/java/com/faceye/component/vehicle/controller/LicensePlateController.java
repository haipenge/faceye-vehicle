package com.faceye.component.vehicle.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.service.ExcelService;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.component.vehicle.service.VehicleService;
import com.faceye.component.weixin.service.qrcode.QRCodeService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.DateUtil;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.RandUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.controller<br>
 * 说明:<br>
 * 实体:车牌:com.faceye.component.vehicle.entity.entity.LicensePlate<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2016-8-4 14:26:36<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/vehicle/licensePlate")
public class LicensePlateController extends BaseController<LicensePlate, Long, LicensePlateService> {
	@Autowired
	private VehicleService vehicleService = null;
	@Autowired
	private QRCodeService qrCodeService = null;
	@Value("#{property['weixin.host']}")
	private String weixinHost = "";
	@Autowired
	private ExcelService excelService = null;

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
	 * 						haipenge@gmail.com <br>
	 *                      创建日期2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		String submit = MapUtils.getString(searchParams, "submit");
		beforeInput(model, request);
		if (StringUtils.equals(submit, "query") || StringUtils.isEmpty(submit)) {
			Page<LicensePlate> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			model.addAttribute("page", page);
			resetSearchParams(searchParams);
			model.addAttribute("searchParams", searchParams);
			GlobalEntity global = new GlobalEntity();
			global.setTitle(this.getI18N("vehicle.licensePlate"));
			model.addAttribute("global", global);
			model.addAttribute("plateStatus", LicensePlate.plateStatusPairs.getPairs());
			return "vehicle.licensePlate.manager";
		} else {
			// 只有已生成的车牌可被制牌
			searchParams.put("EQ|status", 0);
			Page<LicensePlate> page = this.service.getPage(searchParams, 1, 10);
			response.setContentType("octets/stream");
			Date date = new Date();
			String dateSign = DateUtil.formatDate(date, "yyyy-MM-dd");
			dateSign += "-" + RandUtil.getRandInt(100000, 999999);
			response.setCharacterEncoding("utf-8");
			response.setContentType("octets/stream");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.addHeader("Content-Disposition", "attachment;filename=chepai-" + dateSign + ".xls");
			try {
				excelService.export(page, response.getOutputStream());
			} catch (IOException e) {
				logger.error(">>FaceYe throws Exception: --->", e);
			}
			return null;
		}
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			LicensePlate licensePlate = this.service.get(id);
			beforeInput(licensePlate, model, request);
			model.addAttribute("licensePlate", licensePlate);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.licensePlate.edit"));
		model.addAttribute("global", global);
		return "vehicle.licensePlate.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping(value = "/input")
	public String input(LicensePlate licensePlate, Model model, HttpServletRequest request) {
		beforeInput(licensePlate, model, request);
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.licensePlate.add"));
		model.addAttribute("global", global);
		return "vehicle.licensePlate.update";
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid LicensePlate licensePlate, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(licensePlate, model, request);
			return "vehicle.licensePlate.update";
		} else {
			this.beforeSave(licensePlate, request);
			this.service.save(licensePlate);
			this.afterSave(licensePlate, request);
			return "redirect:/vehicle/licensePlate/home";
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id, Model model) {
		if (id != null) {
			LicensePlate licensePlate = this.service.get(id);
			if (licensePlate != null) {
				if (beforeRemove(licensePlate, model)) {
					this.service.remove(licensePlate);
					// MessageBuilder.getInstance().setMessage(model,licensePlate.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		// return "redirect:/vehicle/licensePlate/home";
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
	 *                      创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				LicensePlate licensePlate = this.service.get(Long.parseLong(id));
				if (licensePlate != null) {
					if (beforeRemove(licensePlate, model)) {
						this.service.remove(licensePlate);
						// MessageBuilder.getInstance().setMessage(model,licensePlate.getName()+" "+ this.getI18N("global.remove.success"));
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
	 *                      创建日期:2016-8-4 14:26:36<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			LicensePlate licensePlate = this.service.get(id);
			model.addAttribute("licensePlate", licensePlate);
		}
		return "vehicle.licensePlate.detail";
	}

	/**
	 * 转向生成车牌页面
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月5日 下午5:20:30
	 */
	@RequestMapping("/toGenerate")
	public String toGenerate() {
		return "vehicle.licensePlate.generate";
	}

	/**
	 * 生成车牌
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月5日 下午5:24:13
	 */
	@RequestMapping("/generate")
	public String generate(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		this.service.generate(params);
		return "redirect:/vehicle/licensePlate/home";
		// return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 转向上牌页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月6日 下午3:54:59
	 */
	@RequestMapping("/plateDistribute")
	public String plateDistribute(@RequestParam(required = true) Long vehicleId, HttpServletRequest request, Model model) {
		Vehicle vehicle = this.vehicleService.get(vehicleId);
		// model.addAttribute("vehicle", vehicle);
		model.addAttribute(vehicle);
		Map searchParams = new HashMap();
		searchParams.put("EQ|status", 2);
		Page<LicensePlate> licensePlates = this.service.getPage(searchParams, 1, 25);
		model.addAttribute("licensePlates", licensePlates);
		return "vehicle.licensePlate.distribute";
	}

	/**
	 * 上牌-保存车辆车牌关系
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月6日 下午3:36:54
	 */
	@RequestMapping("/doLicensePlateDistribute")
	public String doLicensePlateDistribute(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		Long vehicleId = MapUtils.getLong(params, "vehicleId");
		String plateNum = MapUtils.getString(params, "plateNum");
		if (vehicleId != null && StringUtils.isNotEmpty(plateNum)) {
			LicensePlate licensePlate = this.service.getLicensePlateByPlateNum(plateNum);
			Vehicle vehicle = this.vehicleService.get(vehicleId);
			if (vehicle != null && licensePlate != null) {
				vehicle.setLicensePlate(licensePlate);
				this.vehicleService.save(vehicle);
				licensePlate.setStatus(3);
				this.service.save(licensePlate);
			}
		}
		return "redirect:/vehicle/vehicle/detail/" + vehicleId;
	}

	/**
	 * 制牌
	 * 
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月6日 下午4:02:24
	 */
	@RequestMapping("/makeLicensePlates")
	@ResponseBody
	public String makeLicensePlates(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		String ids = MapUtils.getString(params, "ids");
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = StringUtils.split(ids, ",");
			if (idArray != null && idArray.length > 0) {
				for (String id : idArray) {
					LicensePlate licensePlate = this.service.get(Long.parseLong(id));
					licensePlate.setStatus(2);
					this.service.save(licensePlate);
				}
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 生成二维码
	 * 
	 * @param request
	 * @param response
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月12日 下午3:48:43
	 */
	@RequestMapping("/generateQRCode")
	// @ResponseBody
	public void generateQRCode(HttpServletRequest request, HttpServletResponse response) {
		Map params = HttpUtil.getRequestParams(request);
		Long id = MapUtils.getLong(params, "id");
		LicensePlate licensePlate = this.service.get(id);
		if (licensePlate != null) {
			String qrCodeStr = weixinHost + "/vehicle/vehicle/scan?plateNum=" + licensePlate.getPlateNum();
			try {
				ServletOutputStream op = response.getOutputStream();
				response.setContentType("image/jpg");
				this.qrCodeService.write2Stream(qrCodeStr, 300, 300, op);
				op.flush();
				op.close();
			} catch (IOException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
	}

	/**
	 * 导出车牌到Excel
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月12日 下午4:49:10
	 */
	@RequestMapping("/export")
	@ResponseBody
	public String export2Excel(HttpServletRequest request, HttpServletResponse response) {
		Map searchParams = new HashMap();
		// 只有已生成的车牌可被制牌
		searchParams.put("EQ|status", 0);
		Page<LicensePlate> page = this.service.getPage(searchParams, 1, 500);
		response.setContentType("octets/stream");
		response.addHeader("Content-Disposition", "attachment;filename=order.xls");
		try {
			excelService.export(page, response.getOutputStream());
		} catch (IOException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
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
	 *                      创建日期:2016-8-4 14:26:36<br>
	 */
	protected void beforeInput(LicensePlate licensePlate, Model model, HttpServletRequest request) {
		model.addAttribute("plateStatus", LicensePlate.plateStatusPairs.getPairs());
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param licensePlate
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:36
	 */
	protected void beforeSave(LicensePlate licensePlate, HttpServletRequest request) {
	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(LicensePlate licensePlate, Model model) {
		boolean res = true;

		return res;
	}

	/**
	 * 保存后的数据回调
	 * 
	 * @todo
	 * @param licensePlate
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:36
	 */
	protected void afterSave(LicensePlate licensePlate, HttpServletRequest request) {

	}

}
