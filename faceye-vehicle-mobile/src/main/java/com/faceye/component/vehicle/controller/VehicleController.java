package com.faceye.component.vehicle.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.vehicle.entity.Area;
import com.faceye.component.vehicle.entity.Brand;
import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.entity.VehicleColor;
import com.faceye.component.vehicle.service.AreaService;
import com.faceye.component.vehicle.service.BrandService;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.component.vehicle.service.VehicleColorService;
import com.faceye.component.vehicle.service.VehicleService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.entity.JSAPITicket;
import com.faceye.component.weixin.entity.WeixinUser;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.api.MsgApi;
import com.faceye.component.weixin.service.message.request.WeixinConfigRequest;
import com.faceye.component.weixin.service.message.request.WeixinConfigRequestObject;
import com.faceye.component.weixin.service.pay.WeixinPayService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.GlobalEntity;
import com.faceye.feature.util.RandUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:车辆->com.faceye.compoent.vehicle.controller<br>
 * 说明:<br>
 * 实体:电动车:com.faceye.component.vehicle.entity.entity.Vehicle<br>
 * 
 * @author haipenge <br>
 *         haipenge@gmail.com<br>
 *         创建日期:2016-8-4 14:26:38<br>
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
	private AccountService accountService = null;
	@Autowired
	private WeixinPayService weixinPayService = null;
	@Autowired
	private MsgApi msgApi = null;
	@Autowired
	private LicensePlateService licensePlateService = null;
	@Autowired
	private AreaService areaServie = null;

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
	 *                      创建日期2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		this.customerService.onWeixinOAuth(request, response);
		beforeInput(model, request);
		if (searchParams == null) {
			searchParams = new HashMap();
		}
		Customer customer = (Customer) request.getSession().getAttribute("ecustomer");
		if (customer != null) {
			if (customer.getType() == 1) {
				searchParams.put("EQ|staff.$id", customer.getId());
			} else if (customer.getType() == 0) {
				searchParams.put("EQ|customer.$id", customer.getId());
			}
			Page<Vehicle> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
			model.addAttribute("page", page);
			resetSearchParams(searchParams);
			model.addAttribute("searchParams", searchParams);
		}
		GlobalEntity global = new GlobalEntity();
		// global.setTitle(this.getI18N("vehicle.vehicle"));
		global.setTitle("电动车");
		model.addAttribute("global", global);
		return "vehicle.vehicle.mobile.home";
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
			Vehicle vehicle = this.service.get(id);
			beforeInput(vehicle, model, request);
			model.addAttribute("vehicle", vehicle);
		}
		GlobalEntity global = new GlobalEntity();
		global.setTitle(this.getI18N("vehicle.vehicle.edit"));
		model.addAttribute("global", global);
		return "vehicle.vehicle.mobile.update";
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
	public String input(Vehicle vehicle, Model model, HttpServletRequest request) {
		beforeInput(vehicle, model, request);
		GlobalEntity global = new GlobalEntity();
		// global.setTitle(this.getI18N("vehicle.vehicle.add"));
		global.setTitle("电动车登记");
		model.addAttribute("global", global);
		return "vehicle.vehicle.mobile.update";
	}

	/**
	 * 构造微信请求对像
	 * 
	 * @param url
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年5月9日 下午3:45:38
	 */
	@RequestMapping("/buildWeixinConfigRequestObject")
	@ResponseBody
	public WeixinConfigRequestObject buildWeixinConfigRequestObject(HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		WeixinUser weixinUser = (WeixinUser) request.getSession().getAttribute("weixinUser");
		Account account = null;
		if (weixinUser != null) {
			account = weixinUser.getAccount();
		}
		String url = MapUtils.getString(params, "url");
		// logger.debug(">>FaceYe --> Weixin account id is :" + accountId);
		// Account account = this.accountService.get(accountId);
		if (account != null) {
			WeixinConfigRequestObject weixinConfigRequestObject = null;
			JSAPITicket jsapiTicket = this.msgApi.getJSAPITicket(account);
			WeixinConfigRequest weixinConfigRequest = this.weixinPayService.buildWeixinConfigRequest(jsapiTicket, url);
			weixinConfigRequestObject = new WeixinConfigRequestObject();
			weixinConfigRequestObject.setAppId(account.getAppId());
			weixinConfigRequestObject.setJsapi_ticket(weixinConfigRequest.getJsapi_ticket());
			weixinConfigRequestObject.setNoncestr(weixinConfigRequest.getNoncestr());
			weixinConfigRequestObject.setSignature(weixinConfigRequest.getSignature());
			weixinConfigRequestObject.setTimestamp(weixinConfigRequest.getTimestamp());
			return weixinConfigRequestObject;
		} else {
			logger.debug(">>>FaceYe --> account is null.");
			return null;
		}
	}

	/**
	 * 数据保存<br>
	 * @author:haipenge<br>
	 * 						haipenge @gmail.com <br>
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Vehicle vehicle, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String plateNum = vehicle.getLicensePlate().getPlateNum();
		plateNum = StringUtils.upperCase(plateNum);
		String mobile = vehicle.getCustomer().getMobile();
		Customer customer = this.customerService.getCustomerByMobile(mobile);
		String name = vehicle.getCustomer().getName();
		String personalId = vehicle.getCustomer().getPersonalId();
		Long areaId = vehicle.getArea().getId();
		LicensePlate licensePlate = this.licensePlateService.getLicensePlateByPlateNum(plateNum);
		if (licensePlate == null) {
			// vehicle.setLicensePlate(licensePlate);
			// bindingResult.addError(new ObjectError("vehicle", "车牌号输入错误,不存在车牌:" + plateNum));
			MessageBuilder.getInstance().setMessage(model, "车牌号输入错误,不存在车牌:" + plateNum);
			beforeInput(vehicle, model, request);
			// model.addAttribute("vehicle", vehicle);
			vehicle.getLicensePlate().setPlateNum(plateNum);
			//
			vehicle.getCustomer().setMobile(mobile);
			vehicle.getCustomer().setName(name);
			vehicle.getCustomer().setPersonalId(personalId);
			vehicle.getArea().setId(areaId);
			return "vehicle.vehicle.mobile.update";
		}
		if (bindingResult.hasErrors()) {
			beforeInput(vehicle, model, request);
			return "vehicle.vehicle.mobile.update";
		} else {
			this.beforeSave(vehicle, request);
			// 审核中
			vehicle.setStatus(0);
			this.service.save(vehicle);
			this.afterSave(vehicle, request);
			return "redirect:/vehicle/vehicle/home";
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
			Vehicle vehicle = this.service.get(id);
			if (vehicle != null) {
				if (beforeRemove(vehicle, model)) {
					this.service.remove(vehicle);
					// MessageBuilder.getInstance().setMessage(model,vehicle.getName()+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return "redirect:/vehicle/vehicle/home";
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
	 *                      创建日期:2016-8-4 14:26:38<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Vehicle vehicle = this.service.get(id);
			model.addAttribute("vehicle", vehicle);
		}
		return "vehicle.vehicle.mobile.detail";
	}

	/**
	 * 扫码响应页
	 * 
	 * @param plateNum
	 * @param model
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月12日 下午4:25:35
	 */
	@RequestMapping("/scan")
	public String scan(@RequestParam(required = true) String plateNum, Model model) {
		logger.debug(">>FaceYe plate num is :" + plateNum);
		LicensePlate licensePlate = this.licensePlateService.getLicensePlateByPlateNum(plateNum);
		if (licensePlate != null) {
			Integer status = licensePlate.getStatus();
			if (status != null) {
				// 3=已安装
				if (status.intValue() != 3) {
					model.addAttribute("licensePlate", licensePlate);
					return "redirect:/vehicle/vehicle/input?plateNum=" + licensePlate.getPlateNum();
				} else {
					Vehicle vehicle = this.service.getVehicleByLicensePlate(licensePlate);
					model.addAttribute("vehicle", vehicle);
				}
			}
		} else {
			return "redirect:/vehicle/vehicle/input";
		}
		return "vehicle.vehicle.mobile.detail";
	}

	@RequestMapping("/generateImg")
	@ResponseBody
	public void writePersonal2Stream(HttpServletRequest request, HttpServletResponse response) {
		// this.loadFont(fontFilePath, 1f);
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
		} catch (IOException e1) {
			logger.error(">>FaceYe Throws Exception:", e1);
		}
		Map params = HttpUtil.getRequestParams(request);
		String plateNum = MapUtils.getString(params, "plateNum");
		if (StringUtils.isEmpty(plateNum)) {
			plateNum = "无效车牌";
		}
		logger.debug(">>FaceYe --> plateNum is :"+plateNum);
		int width = 779;
		int height = 496;

		// FontMetrica fm=null;
		File file = new File("/tmp/img-bg-" + RandUtil.getRandInt(1, 1000) + ".png");
		// Font font = new Font("Serif", Font.BOLD, 40);
		Font font = new Font(null, Font.BOLD, 200);
		BufferedImage bi = new BufferedImage(width-60, height/2-50,BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = (Graphics2D) bi.getGraphics();
		
//		Color color=new Color(253,208,0);
		Color color=new Color(200,200,0);
		gd.setBackground(color);
		gd.clearRect(0, 0, width-30, height/2-50);
//		gd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gd.setPaint(Color.black);
		gd.setFont(font);
		gd.drawString(plateNum, 15, 170);
		//gd.drawString(plateNum, width-30, height/2);
//		
//		char [] texts=plateNum.toCharArray();
//		int x=30;
//		int y=30;
//		int size=30;
//		for (int i = 0; i < texts.length; i++) {
//			// logger.debug(">>FaceYe --> Char is:" + chars[i]);
//			gd.drawString(String.valueOf(texts[i]), x, y);
//			x += size;
//		}
		
		gd.dispose();
		// this.setText(" <搞T让T爆表!", 30, 140, 550, gd,null);
		// this.setText(" <搞T让T爆表!", 30, 310, 860, gd,null);
		try {
			ImageIO.write(bi, "jpg", file);
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		BufferedImage image = mergeImage(new File("/data/deploy/upload/vehicle_img.jpg"),file , width-60, height/2);
		if (image != null) {
			try {
				if (outputStream != null) {
					ImageIO.write(image, "png", outputStream);
					// ImageIO.write(ImageIO.read(file), "jpg", outputStream);
				}
			} catch (IOException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
		// mergeImage(file, new File("/work/29.pic.jpg"),width,height);
		// mergeImage(file, new File("/work/38.pic.jpg"), width, height);
	}

	private BufferedImage mergeImage(File file1, File file2, int width, int height) {
		BufferedImage img = null;
		BufferedImage image1 = null;
		String fileName = "img-end-" + RandUtil.getRandInt(1, 1000) + ".png";
		try {
			image1 = ImageIO.read(file1);
			BufferedImage image2 = ImageIO.read(file2);
			BufferedImage combined = new BufferedImage(image1.getWidth(), image1.getHeight(), BufferedImage.TYPE_INT_RGB);
			// paint both images, preserving the alpha channels
			// Graphics g = combined.getGraphics();
			Graphics2D g = (Graphics2D) combined.getGraphics();
//			g.setBackground(Color.white);
//			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.clearRect(0, 0, width, height);
			g.setPaint(Color.black);
			g.drawImage(image1, 0,0, null);
			g.drawImage(image2, 30, image1.getHeight()/2+30, null);
			Font font = new Font(null, Font.PLAIN, 100);
			g.setFont(font);
			g.drawString("广东", 15, 15);
			g.drawString("深圳", image1.getWidth()-300, 15);
			g.dispose();
			// Save as new image
			ImageIO.write(combined, "png", new File("/tmp/", fileName));
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		try {
			img = ImageIO.read(new File("/tmp/" + fileName));
		} catch (IOException e) {
			logger.error(">>FaceYe Throws Exception:", e);
		}
		return img;
	}
	// @RequestMapping("/scanTest")
	// public String scanTest(@RequestParam(required=true) String plateNum,Model model){
	// LicensePlate licensePlate=this.licensePlateService.getLicensePlateByPlateNum(plateNum);
	// if(licensePlate!=null){
	// Vehicle vehicle=this.service.getVehicleByLicensePlate(licensePlate);
	// model.addAttribute("vehicle", vehicle);
	// }
	// return "vehicle.vehicle.mobile.detail";
	// }
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
	protected void beforeInput(Vehicle vehicle, Model model, HttpServletRequest request) {
		Map params = HttpUtil.getRequestParams(request);
		List<VehicleColor> vehicleColors = this.vehicleColorService.getAll();
		model.addAttribute("vehicleColors", vehicleColors);
		List<Brand> brands = this.brandService.getAll();
		model.addAttribute("brands", brands);
		List<Customer> customers = this.customerService.getAll();
		model.addAttribute("customers", customers);
		Customer staff = (Customer) request.getSession().getAttribute("ecustomer");
		Customer customer = new Customer();
		if (customer != null) {
			vehicle.setCustomer(customer);
		} else {
			logger.debug(">>FaceYe  customer is null when to save Vehicle.");
		}
		if (staff != null) {
			vehicle.setStaff(staff);
		}
		String plateNum = MapUtils.getString(params, "plateNum");
		LicensePlate licensePlate = null;
		if (StringUtils.isNotEmpty(plateNum)) {
			licensePlate = this.licensePlateService.getLicensePlateByPlateNum(plateNum);
		}
		if (licensePlate == null) {
			licensePlate = new LicensePlate();
		}
		vehicle.setLicensePlate(licensePlate);
		List<Area> areas = this.areaServie.getAll();
		model.addAttribute("areas", areas);
	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param vehicle
	 * @param request
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:38
	 */
	protected void beforeSave(Vehicle vehicle, HttpServletRequest request) {
		// 手机号
		String mobile = vehicle.getCustomer().getMobile();
		// 车牌
		String plateNum = vehicle.getLicensePlate().getPlateNum();
		Customer customer = this.customerService.getCustomerByMobile(mobile);
		if (customer == null) {
			customer = vehicle.getCustomer();
			this.customerService.save(customer);
			vehicle.setCustomer(customer);
		} else {
			vehicle.setCustomer(customer);
		}
		if (StringUtils.isNotEmpty(plateNum)) {
			plateNum = StringUtils.upperCase(plateNum);
			LicensePlate licensePlate = this.licensePlateService.getLicensePlateByPlateNum(plateNum);
			if (licensePlate != null) {
				licensePlate.setStatus(3);
				this.licensePlateService.save(licensePlate);
				vehicle.setLicensePlate(licensePlate);

			}
		}
		Customer staff = (Customer) request.getSession().getAttribute("ecustomer");
		vehicle.setStaff(staff);
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
	 * @author:haipenge 联系:haipenge@gmail.com 创建日期:2016-8-4 14:26:38
	 */
	protected void afterSave(Vehicle vehicle, HttpServletRequest request) {

	}

}