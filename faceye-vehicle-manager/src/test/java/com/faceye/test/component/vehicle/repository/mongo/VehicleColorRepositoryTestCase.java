package com.faceye.test.component.vehicle.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.vehicle.entity.VehicleColor;
import com.faceye.component.vehicle.repository.mongo.VehicleColorRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * VehicleColor Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-4 14:26:36<br>
 */
public class VehicleColorRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private VehicleColorRepository vehicleColorRepository = null;

	@Before
	public void before() throws Exception {
		//this.vehicleColorRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		VehicleColor vehicleColor = new VehicleColor();
		this.vehicleColorRepository.save(vehicleColor);
		Iterable<VehicleColor> vehicleColors = this.vehicleColorRepository.findAll();
		Assert.isTrue(vehicleColors.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		VehicleColor vehicleColor = new VehicleColor();
		this.vehicleColorRepository.save(vehicleColor);
        this.vehicleColorRepository.delete(vehicleColor.getId());
        Iterable<VehicleColor> vehicleColors = this.vehicleColorRepository.findAll();
		Assert.isTrue(!vehicleColors.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		VehicleColor vehicleColor = new VehicleColor();
		this.vehicleColorRepository.save(vehicleColor);
		vehicleColor=this.vehicleColorRepository.findOne(vehicleColor.getId());
		Assert.isTrue(vehicleColor!=null);
	}

	
}
