package com.faceye.component.vehicle.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.security.web.entity.User;
import com.faceye.component.security.web.service.UserService;
import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.service.VehicleService;
import com.faceye.component.weixin.entity.Account;
import com.faceye.component.weixin.entity.WeixinUser;
import com.faceye.component.weixin.service.AccountService;
import com.faceye.component.weixin.service.WeixinUserService;
import com.faceye.component.weixin.service.media.MediaService;
import com.faceye.feature.service.job.impl.BaseJob;

/**
 * 从微信下载临时媒体
 * 
 * @author haipenge
 *
 */
@Service
public class DownloadMediaJob extends BaseJob {

	@Autowired
	private AccountService accountService = null;
	@Autowired
	private MediaService mediaService = null;
	@Autowired
	private VehicleService vehicleService = null;
	@Autowired
	private WeixinUserService weixinUserService = null;
	@Autowired
	private UserService userService = null;

	@Override
	public void run() {
		int page = 1;
		Account account = null;
		Page<Vehicle> vehicles = this.vehicleService.getPage(null, page, 100);
		while (vehicles != null && CollectionUtils.isNotEmpty(vehicles.getContent())) {
			for (Vehicle vehicle : vehicles.getContent()) {
				if (account == null) {
					Long webUserId = null;
					Customer staff = vehicle.getStaff();
					if (staff != null) {
						webUserId = staff.getWebUserId();
					} else {
						Customer customer = vehicle.getCustomer();
						if (customer != null) {
							webUserId = customer.getWebUserId();
						}
					}
					if (webUserId != null) {
						User user = userService.get(webUserId);
						WeixinUser weixinUser = weixinUserService.getWeixinUserByOpenid(user.getWeixinOpenId());
						account = weixinUser.getAccount();
					}
				}
				if (account != null) {
					// 身份证
					String persionIdImg = StringUtils.trim(vehicle.getPersonalIdImgUrl());
					if (StringUtils.isNotEmpty(persionIdImg) && !StringUtils.endsWith(persionIdImg, "jpg") && !StringUtils.contains(persionIdImg, ".")) {
						String path = this.mediaService.getTempMedia(account.getAccessToken(), persionIdImg);
						vehicle.setPersonalIdImgUrl(path);
					}
					// 车架
					String chejiaImg = StringUtils.trim(vehicle.getCheJiaImgUrl());
					if (StringUtils.isNotEmpty(chejiaImg) && !StringUtils.endsWith(chejiaImg, "jpg") && !StringUtils.contains(chejiaImg, ".")) {
						String path = this.mediaService.getTempMedia(account.getAccessToken(), chejiaImg);
						vehicle.setCheJiaImgUrl(path);
					}
					// 发票
					String fapiaoImg = StringUtils.trim(vehicle.getFaPiaoImgUrl());
					if (StringUtils.isNotEmpty(fapiaoImg) && !StringUtils.endsWith(fapiaoImg, "jpg") && !StringUtils.contains(fapiaoImg, ".")) {
						String path = this.mediaService.getTempMedia(account.getAccessToken(), fapiaoImg);
						vehicle.setFaPiaoImgUrl(path);
					}
					// 电机
					String dianjiImg = StringUtils.trim(vehicle.getDianJiImgUrl());
					if (StringUtils.isNotEmpty(dianjiImg) && !StringUtils.endsWith(dianjiImg, "jpg") && !StringUtils.contains(dianjiImg, ".")) {
						String path = this.mediaService.getTempMedia(account.getAccessToken(), dianjiImg);
						vehicle.setDianJiImgUrl(path);
					}
					// 车辆
					String cheliangImg = StringUtils.trim(vehicle.getVehicleImgUrl());
					if (StringUtils.isNotEmpty(cheliangImg)&&!StringUtils.endsWith(cheliangImg, "jpg") && !StringUtils.contains(cheliangImg, ".")) {
						String path = this.mediaService.getTempMedia(account.getAccessToken(), cheliangImg);
						vehicle.setVehicleImgUrl(path);
					}
					// 合格证
					String hegezhengImg = StringUtils.trim(vehicle.getHeGeZhengImgUrl());
					if (StringUtils.isNotEmpty(hegezhengImg) && !StringUtils.endsWith(hegezhengImg, "jpg") && !StringUtils.contains(hegezhengImg, ".")) {
						String path = this.mediaService.getTempMedia(account.getAccessToken(), hegezhengImg);
						vehicle.setHeGeZhengImgUrl(path);
					}
					this.vehicleService.save(vehicle);
				}else{
					logger.error(">>FaceYe --> error account is null when download media.");
				}
			}
			page++;
			vehicles = this.vehicleService.getPage(null, page, 100);
		}
	}

}
