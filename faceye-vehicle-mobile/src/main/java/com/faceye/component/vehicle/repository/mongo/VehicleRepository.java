package com.faceye.component.vehicle.repository.mongo;

import java.util.List;

import com.faceye.component.customer.entity.Customer;
import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.repository.mongo.gen.VehicleGenRepository;
/**
 * 模块:车辆->com.faceye.compoent.vehicle.repository.mongo<br>
 * 说明:<br>
 * 实体:电动车->com.faceye.component.vehicle.entity.entity.Vehicle 实体DAO<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:2016-8-4 14:26:38<br>
* VehicleCustomerRepository,
 */
public interface VehicleRepository extends VehicleGenRepository {
	
	public List<Vehicle> getVehicleByCustomer(Customer customer);
	
	public Vehicle getVehicleByLicensePlate(LicensePlate licensePlate);
}/**@generate-repository-source@**/
