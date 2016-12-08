package com.faceye.component.vehicle.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.faceye.component.customer.entity.Customer;

/**
 * ScanLog ORM 实体<br>
 * 数据库表:vehicle_scanLog<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "vehicle_scanLog")
public class ScanLog implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DBRef
	private Customer customer = null;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@DBRef
	private Vehicle vehicle = null;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	/**
	 * 说明:上报纬度<br>
	 * 属性名: latitude<br>
	 * 类型: Double<br>
	 * 数据库字段:latitude<br>
	 * @author haipenge<br>
	 */

	private Double latitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 说明:上报经度<br>
	 * 属性名: longitude<br>
	 * 类型: Double<br>
	 * 数据库字段:longitude<br>
	 * @author haipenge<br>
	 */

	private Double longitude;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 说明:上报精度<br>
	 * 属性名: precision<br>
	 * 类型: Double<br>
	 * 数据库字段:precision<br>
	 * @author haipenge<br>
	 */

	private Double precision;

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	/**
	 * 说明:创建日期<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:create_date<br>
	 * 
	 * @author haipenge<br>
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
	private Date createDate = new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}/** @generate-entity-source@ **/
