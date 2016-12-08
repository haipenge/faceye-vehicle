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

import com.faceye.component.vehicle.entity.ScanLog;
import com.faceye.component.vehicle.service.ScanLogService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * ScanLog  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class ScanLogServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private ScanLogService scanLogService = null;
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
		Assert.isTrue(scanLogService != null);
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
		//this.scanLogService.removeAllInBatch();
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
		ScanLog entity = new ScanLog();
		this.scanLogService.save(entity);
		List<ScanLog> entites = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		ScanLog entity = new ScanLog();
		this.scanLogService.save(entity);
		List<ScanLog> entites = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			ScanLog entity = new ScanLog();
			this.scanLogService.save(entity);
		}
		List<ScanLog> entities = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		ScanLog entity = new ScanLog();
		this.scanLogService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		ScanLog e = this.scanLogService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		ScanLog entity = new ScanLog();
		this.scanLogService.save(entity);
		this.scanLogService.remove(entity);
		List<ScanLog> entities = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			ScanLog entity = new ScanLog();
			this.scanLogService.save(entity);
		}
		List<ScanLog> entities = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.scanLogService.removeAllInBatch();
		entities = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ScanLog entity = new ScanLog();
			this.scanLogService.save(entity);
		}
		this.scanLogService.removeAll();
		List<ScanLog> entities = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<ScanLog> entities = new ArrayList<ScanLog>();
		for (int i = 0; i < 5; i++) {
			ScanLog entity = new ScanLog();
			
			this.scanLogService.save(entity);
			entities.add(entity);
		}
		this.scanLogService.removeInBatch(entities);
		entities = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			ScanLog entity = new ScanLog();
			this.scanLogService.save(entity);
		}
		List<ScanLog> entities = this.scanLogService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			ScanLog entity = new ScanLog();
			this.scanLogService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<ScanLog> page = this.scanLogService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.scanLogService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.scanLogService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			ScanLog entity = new ScanLog();
			this.scanLogService.save(entity);
			id = entity.getId();
		}
		ScanLog e = this.scanLogService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			ScanLog entity = new ScanLog();
			this.scanLogService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<ScanLog> entities = this.scanLogService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
