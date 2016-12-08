package com.faceye.component.vehicle.service;

import java.util.Map;

import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.feature.service.BaseService;
/**
 * 模块:车辆->com.faceye.compoent.vehicle.service<br>
 * 说明:<br>
 * 实体:车牌->com.faceye.component.vehicle.entity.entity.LicensePlate 服务层接口<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-4 14:26:36<br>
 */
public interface LicensePlateService extends BaseService<LicensePlate,Long>{

	
	/**
	 * 生成车牌
	 * @param params
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月5日 下午5:46:36
	 */
	public void generate(Map params);
	
	
	public LicensePlate getLicensePlateByPlateNum(String plateNum);
	
}/**@generate-service-source@**/
