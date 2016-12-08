package com.faceye.test.component.vehicle.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.vehicle.entity.Area;
import com.faceye.component.vehicle.repository.mongo.AreaRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Area DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class AreaRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private AreaRepository areaRepository = null;

	@Before
	public void before() throws Exception {
		//this.areaRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Area entity = new Area();
		this.areaRepository.save(entity);
		Iterable<Area> entities = this.areaRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Area entity = new Area();
		this.areaRepository.save(entity);
        this.areaRepository.delete(entity.getId());
        Iterable<Area> entities = this.areaRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Area entity = new Area();
		this.areaRepository.save(entity);
		Area area=this.areaRepository.findOne(entity.getId());
		Assert.isTrue(area!=null);
	}

	
}
