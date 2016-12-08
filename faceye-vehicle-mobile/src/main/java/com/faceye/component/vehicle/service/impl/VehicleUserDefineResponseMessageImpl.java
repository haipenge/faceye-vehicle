package com.faceye.component.vehicle.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.security.web.entity.User;
import com.faceye.component.security.web.service.UserService;
import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.entity.ScanLog;
import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.component.vehicle.service.ScanLogService;
import com.faceye.component.vehicle.service.VehicleService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.entity.Msg;
import com.faceye.component.weixin.entity.WeixinUser;
import com.faceye.component.weixin.service.MsgService;
import com.faceye.component.weixin.service.UserDefineResponseMessage;
import com.faceye.component.weixin.service.WeixinUserService;
import com.faceye.component.weixin.service.message.receive.EventMessage;
import com.faceye.component.weixin.service.message.receive.RMessage;
import com.faceye.component.weixin.service.message.receive.ScanCodeInfo;
import com.faceye.component.weixin.service.message.response.ResponseMessage;
import com.faceye.component.weixin.service.message.response.RichText;
import com.faceye.component.weixin.service.message.response.RichTextResponseMessage;
import com.faceye.component.weixin.util.WeixinConstants;
import com.faceye.feature.util.regexp.RegexpUtil;

@Service("vehicleUserDefineResponseMessage")
public class VehicleUserDefineResponseMessageImpl implements UserDefineResponseMessage {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LicensePlateService licensePlateService = null;
	@Autowired
	private VehicleService vehicleService = null;
	@Autowired
	private WeixinUserService weixinUserService = null;
	@Autowired
	private UserService userService = null;
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private ScanLogService scanLogService = null;
	@Autowired
	private MsgService msgService = null;

	@Value("#{property['mobile.host']}")
	private String mobileHost = "";

	@Override
	public ResponseMessage build(Account account, RMessage receiveMessage) {
		Vehicle vehicle = null;
		LicensePlate licensePlate = null;
		WeixinUser weixinUser = null;
		Customer customer = null;
		User user = null;
		RichTextResponseMessage responseMessage = null;
		// 发送方openId
		String fromUserName = receiveMessage.getFromUserName();
		EventMessage eventMessage = (EventMessage) receiveMessage;
		ScanCodeInfo scanCodeInfo = eventMessage.getScanCodeInfo();
		String scanResult = scanCodeInfo.getScanResult();
		String plateNum = "";
		if (StringUtils.isNotEmpty(scanResult)) {
			String pattern = "([A-Z]\\d{5})";
			try {
				plateNum = RegexpUtil.match(scanResult, pattern).get(0).get("1");
			} catch (Exception e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
			if (StringUtils.isNotEmpty(plateNum)) {

			}
		}
		if (StringUtils.isNotEmpty(plateNum)) {
			logger.debug(">>FaceYe -->Plate num is :" + plateNum);
			licensePlate = this.licensePlateService.getLicensePlateByPlateNum(plateNum);
			if (licensePlate != null) {
				vehicle = this.vehicleService.getVehicleByLicensePlate(licensePlate);
				responseMessage = new RichTextResponseMessage();
				responseMessage.setFromUserName(receiveMessage.getToUserName());
				responseMessage.setToUserName(receiveMessage.getFromUserName());
				responseMessage.setMsgType(WeixinConstants.MSG_TYPE_RICH_TEXT);
				responseMessage.setCreateTime(System.currentTimeMillis());
				List<RichText> richTexts = new ArrayList<RichText>(0);
				RichText richText = new RichText();
				richText.setTitle("电动车【" + licensePlate.getPlateNum() + "】状态为『" + vehicle.getStatusText() + "』");
				StringBuilder sb = new StringBuilder();
				sb.append("车牌号:");
				sb.append(licensePlate.getPlateNum());
				// sb.append("\\r\\n");
				sb.append("    ");
				sb.append("手机号:");
				sb.append(vehicle.getCustomer().getMobile());
				richText.setDescription(sb.toString());
				String url = "";
				customer = this.customerService.getLoginedCustomer();
				if (licensePlate.getStatus() != 3 && customer.getType() == 3) {
					url = mobileHost + "/vehicle/vehicle/input?plateNum=" + licensePlate.getPlateNum();
				} else if (vehicle != null && vehicle.getStatus() == 3 || vehicle.getStatus() == 4) {
					url = mobileHost + "/vehicle/vehicle/detail/" + vehicle.getId();
				} else if (vehicle == null) {
					// 如果是上牌员
					if (customer != null && customer.getType() == 1) {
						url = mobileHost + "/vehicle/vehicle/input?plateNum=" + licensePlate.getPlateNum();
					} else {
						url = mobileHost + "/vehicle/vehicle/detail/" + vehicle.getId();
					}
				}
				richText.setUrl(url);
				if (vehicle != null && StringUtils.isNotEmpty(vehicle.getVehicleImgUrl())) {
					richText.setPicUrl(vehicle.getVehicleImgUrl());
				}
				// richText.setPicUrl(mobileHost + "/vehicle/vehicle/generateImg?plateNum=" + licensePlate.getPlateNum());
				richTexts.add(richText);
				responseMessage.setArticles(richTexts);
			} else {
				logger.error(">>FaceYe --> licensePlate is not exist.");
			}
		} else {
			logger.error(">>FaceYe --> plateNum is empty.");
		}
		if (vehicle != null) {
			// weixinUser =this.weixinUserService.getWeixinUserByOpenid(fromUserName);
			// if (weixinUser != null) {
			user = this.userService.getUserByWeixinOpenId(fromUserName);
			if (user != null) {
				ScanLog scanLog = new ScanLog();
				customer = this.customerService.getCustomerByWebUserId(user.getId());
				// 获取用户最后一次上报的坐标
				Map params = new HashMap();
				params.put("EQ|fromUserName", fromUserName);
				params.put("EQ|event", WeixinConstants.EVENT_LOCATION);
				Page<Msg> msgs = this.msgService.getPage(params, 1, 5);
				if (msgs != null && CollectionUtils.isNotEmpty(msgs.getContent())) {
					scanLog.setLatitude(msgs.getContent().get(0).getLatitude());
					scanLog.setLongitude(msgs.getContent().get(0).getLongitude());
					scanLog.setPrecision(msgs.getContent().get(0).getPrecision());
				}
				scanLog.setCustomer(customer);
				scanLog.setVehicle(vehicle);
				this.scanLogService.save(scanLog);
			}
			// }
		}
		//
		logger.debug(">>FaceYe --> come to vehicle user define response message .");
		return responseMessage;
	}

}
