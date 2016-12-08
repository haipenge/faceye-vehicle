package com.faceye.test.component.vehicle.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.repository.mongo.LicensePlateRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * LicensePlate Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-4 14:26:36<br>
 */
public class LicensePlateRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private LicensePlateRepository licensePlateRepository = null;

	@Before
	public void before() throws Exception {
		//this.licensePlateRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		LicensePlate licensePlate = new LicensePlate();
		this.licensePlateRepository.save(licensePlate);
		Iterable<LicensePlate> licensePlates = this.licensePlateRepository.findAll();
		Assert.isTrue(licensePlates.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		LicensePlate licensePlate = new LicensePlate();
		this.licensePlateRepository.save(licensePlate);
        this.licensePlateRepository.delete(licensePlate.getId());
        Iterable<LicensePlate> licensePlates = this.licensePlateRepository.findAll();
		Assert.isTrue(!licensePlates.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		LicensePlate licensePlate = new LicensePlate();
		this.licensePlateRepository.save(licensePlate);
		licensePlate=this.licensePlateRepository.findOne(licensePlate.getId());
		Assert.isTrue(licensePlate!=null);
	}

	
}
