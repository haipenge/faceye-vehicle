package com.faceye.test.component.vehicle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.faceye.component.vehicle.entity.Area;
import com.faceye.component.vehicle.service.AreaService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Area  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class AreaServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private AreaService areaService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(areaService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.areaService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Area entity = new Area();
		this.areaService.save(entity);
		List<Area> entites = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Area entity = new Area();
		this.areaService.save(entity);
		List<Area> entites = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Area entity = new Area();
			this.areaService.save(entity);
		}
		List<Area> entities = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Area entity = new Area();
		this.areaService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Area e = this.areaService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Area entity = new Area();
		this.areaService.save(entity);
		this.areaService.remove(entity);
		List<Area> entities = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Area entity = new Area();
			this.areaService.save(entity);
		}
		List<Area> entities = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.areaService.removeAllInBatch();
		entities = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Area entity = new Area();
			this.areaService.save(entity);
		}
		this.areaService.removeAll();
		List<Area> entities = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Area> entities = new ArrayList<Area>();
		for (int i = 0; i < 5; i++) {
			Area entity = new Area();
			
			this.areaService.save(entity);
			entities.add(entity);
		}
		this.areaService.removeInBatch(entities);
		entities = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Area entity = new Area();
			this.areaService.save(entity);
		}
		List<Area> entities = this.areaService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Area entity = new Area();
			this.areaService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Area> page = this.areaService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.areaService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.areaService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Area entity = new Area();
			this.areaService.save(entity);
			id = entity.getId();
		}
		Area e = this.areaService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Area entity = new Area();
			this.areaService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Area> entities = this.areaService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
