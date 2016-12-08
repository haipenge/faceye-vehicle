package com.faceye.test.component.vehicle.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.vehicle.entity.ScanLog;
import com.faceye.component.vehicle.repository.mongo.ScanLogRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * ScanLog DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class ScanLogRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private ScanLogRepository scanLogRepository = null;

	@Before
	public void before() throws Exception {
		//this.scanLogRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		ScanLog entity = new ScanLog();
		this.scanLogRepository.save(entity);
		Iterable<ScanLog> entities = this.scanLogRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		ScanLog entity = new ScanLog();
		this.scanLogRepository.save(entity);
        this.scanLogRepository.delete(entity.getId());
        Iterable<ScanLog> entities = this.scanLogRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		ScanLog entity = new ScanLog();
		this.scanLogRepository.save(entity);
		ScanLog scanLog=this.scanLogRepository.findOne(entity.getId());
		Assert.isTrue(scanLog!=null);
	}

	
}
