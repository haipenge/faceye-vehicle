package com.faceye.test.component.vehicle.repository.mongo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.vehicle.entity.Brand;
import com.faceye.component.vehicle.repository.mongo.BrandRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Brand Repository 测试
 * @author haipenge 
 * 联系:haipenge@gmail.com
*  创建日期:2016-8-4 14:26:38<br>
 */
public class BrandRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private BrandRepository brandRepository = null;

	@Before
	public void before() throws Exception {
		//this.brandRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Brand brand = new Brand();
		this.brandRepository.save(brand);
		Iterable<Brand> brands = this.brandRepository.findAll();
		Assert.isTrue(brands.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Brand brand = new Brand();
		this.brandRepository.save(brand);
        this.brandRepository.delete(brand.getId());
        Iterable<Brand> brands = this.brandRepository.findAll();
		Assert.isTrue(!brands.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Brand brand = new Brand();
		this.brandRepository.save(brand);
		brand=this.brandRepository.findOne(brand.getId());
		Assert.isTrue(brand!=null);
	}

	
}
