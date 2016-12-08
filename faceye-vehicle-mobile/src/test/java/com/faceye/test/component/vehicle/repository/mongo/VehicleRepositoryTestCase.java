package com.faceye.test.component.vehicle.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.vehicle.entity.Vehicle;
import com.faceye.component.vehicle.repository.mongo.VehicleRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Vehicle Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-4 14:26:38<br>
 */
public class VehicleRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private VehicleRepository vehicleRepository = null;

	@Before
	public void before() throws Exception {
		//this.vehicleRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Vehicle vehicle = new Vehicle();
		this.vehicleRepository.save(vehicle);
		Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();
		Assert.isTrue(vehicles.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Vehicle vehicle = new Vehicle();
		this.vehicleRepository.save(vehicle);
        this.vehicleRepository.delete(vehicle.getId());
        Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();
		Assert.isTrue(!vehicles.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Vehicle vehicle = new Vehicle();
		this.vehicleRepository.save(vehicle);
		vehicle=this.vehicleRepository.findOne(vehicle.getId());
		Assert.isTrue(vehicle!=null);
	}

	
}
