package com.faceye.component.vehicle.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.faceye.component.customer.entity.Customer;
import com.faceye.feature.service.PropertyService;
import com.faceye.feature.util.bean.BeanContextUtil;
import com.faceye.feature.util.pair.Pairs;

/**
 * 模块:车辆->vehicle->Vehicle<br>
 * 说明:<br>
 * 实体:电动车->com.faceye.component.vehicle.entity.Vehicle Mongo 对像<br>
 * mongo数据集:vehicle_vehicle<br>
 * 
 * @author haipenge <br>
 *         联系:haipenge@gmail.com<br>
 *         创建日期:2016-8-4 14:26:37<br>
 *         spring-data-mongo支持的注释类型<br>
 * @Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。<br>
 * @Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。<br>
 * @DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存<br>
 * @Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。<br>
 * @CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。<br>
 * @GeoSpatialIndexed - 声明该字段为地理信息的索引。<br>
 * @Transient - 映射忽略的字段，该字段不会保存到<br>
 * @PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。<br>
 * @CompoundIndexes({ @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}") })
 * @Indexed(unique = true)
 */

@Document(collection = "faceye_12_vehicle_vehicle")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	/**
	 * 构造电动车状态
	 */
	public static Pairs<String, String> statusTexts = new Pairs<String, String>().add("0", "审核中").add("1", "审核通过").add("2", "审核拒绝").add("3", "报失").add("4","报警");

	/**
	 * 构建电动车类型
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年8月4日 下午4:07:34
	 */
	public static Pairs<String, String> types = new Pairs<String, String>().add("0", "两轮").add("1", "三轮").add("2", "四轮");
	@Id
	@Indexed(direction = IndexDirection.DESCENDING)
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 说明:电动车状态广西<br>
	 * 属性名: statusText<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:status_text<br>
	 * 
	 * @author haipenge<br>
	 */
	@Transient
	private String statusText = "";

	public String getStatusText() {
		statusText = statusTexts.getPair("" + status) == null ? "" : statusTexts.getPair("" + status).getValue();
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * 说明:电动车状态<br>
	 * 属性名: status<br>
	 * 类型: java.lang.Integer<br>
	 * 数据库字段:status<br>
	 * 
	 * @author haipenge<br>
	 */
	private Integer status = 0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 说明:发票图片<br>
	 * 属性名: faPiaoImgUrl<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:fa_piao_img_url<br>
	 * 
	 * @author haipenge<br>
	 */
	private String faPiaoImgUrl = "";

	public String getFaPiaoImgUrl() {
		// if(StringUtils.isNotEmpty(faPiaoImgUrl)&&!StringUtils.startsWith(faPiaoImgUrl, "http")){
		// faPiaoImgUrl=BeanContextUtil.getInstance().getBean(PropertyService.class).get("image.host")+faPiaoImgUrl;
		// }
		return faPiaoImgUrl;
	}

	public void setFaPiaoImgUrl(String faPiaoImgUrl) {
		this.faPiaoImgUrl = faPiaoImgUrl;
	}

	/**
	 * 说明:车架图片<br>
	 * 属性名: cheJiaImgUrl<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:che_jia_img_url<br>
	 * 
	 * @author haipenge<br>
	 */
	private String cheJiaImgUrl = "";

	public String getCheJiaImgUrl() {
		// if(StringUtils.isNotEmpty(cheJiaImgUrl)&&!StringUtils.startsWithIgnoreCase(cheJiaImgUrl, "http")){
		// cheJiaImgUrl=BeanContextUtil.getInstance().getBean(PropertyService.class).get("image.host")+cheJiaImgUrl;
		// }
		return cheJiaImgUrl;
	}

	public void setCheJiaImgUrl(String cheJiaImgUrl) {
		this.cheJiaImgUrl = cheJiaImgUrl;
	}

	/**
	 * 说明:电机图片<br>
	 * 属性名: dianJiImgUrl<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:dian_ji_img_url<br>
	 * 
	 * @author haipenge<br>
	 */
	private String dianJiImgUrl = "";

	public String getDianJiImgUrl() {
		// if(StringUtils.isNotEmpty(dianJiImgUrl)&&!StringUtils.startsWithIgnoreCase(dianJiImgUrl, "http")){
		// dianJiImgUrl=BeanContextUtil.getInstance().getBean(PropertyService.class).get("image.host")+dianJiImgUrl;
		// }
		return dianJiImgUrl;
	}

	public void setDianJiImgUrl(String dianJiImgUrl) {
		this.dianJiImgUrl = dianJiImgUrl;
	}

	/**
	 * 说明:合格证图片<br>
	 * 属性名: heGeZhengImgUrl<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:he_ge_zheng_img_url<br>
	 * 
	 * @author haipenge<br>
	 */
	private String heGeZhengImgUrl = "";

	public String getHeGeZhengImgUrl() {
		// if(StringUtils.isNotEmpty(heGeZhengImgUrl)&&!StringUtils.startsWithIgnoreCase(heGeZhengImgUrl, "http")){
		// heGeZhengImgUrl=BeanContextUtil.getInstance().getBean(PropertyService.class).get("image.host")+heGeZhengImgUrl;
		// }
		return heGeZhengImgUrl;
	}

	public void setHeGeZhengImgUrl(String heGeZhengImgUrl) {
		this.heGeZhengImgUrl = heGeZhengImgUrl;
	}

	/**
	 * 说明:车辆图片<br>
	 * 属性名: vehicleImgUrl<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:vehicle_img_url<br>
	 * 
	 * @author haipenge<br>
	 */
	private String vehicleImgUrl = "";

	public String getVehicleImgUrl() {
		// if(StringUtils.isNotEmpty(vehicleImgUrl)&&!StringUtils.startsWithIgnoreCase(vehicleImgUrl, "http")){
		// vehicleImgUrl=BeanContextUtil.getInstance().getBean(PropertyService.class).get("image.host")+vehicleImgUrl;
		// }
		return vehicleImgUrl;
	}

	public void setVehicleImgUrl(String vehicleImgUrl) {
		this.vehicleImgUrl = vehicleImgUrl;
	}

	/**
	 * 身份证正面照
	 */
	private String personalIdImgUrl = "";

	public String getPersonalIdImgUrl() {
		// if(StringUtils.isNotEmpty(personalIdImgUrl)&&!StringUtils.startsWithIgnoreCase(personalIdImgUrl, "http")){
		// personalIdImgUrl=BeanContextUtil.getInstance().getBean(PropertyService.class).get("image.host")+personalIdImgUrl;
		// }
		return personalIdImgUrl;
	}

	public void setPersonalIdImgUrl(String personalIdImgUrl) {
		this.personalIdImgUrl = personalIdImgUrl;
	}

	/**
	 * 说明:电动车颜色<br>
	 * 属性名: vehicleColor<br>
	 * 类型: com.faceye.component.vehicle.entity.VehicleColor<br>
	 * 数据库字段:vehicleColor<br>
	 * 
	 * @author haipenge<br>
	 */
	@DBRef
	private VehicleColor vehicleColor = null;

	public VehicleColor getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(VehicleColor vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	/**
	 * 说明:品牌<br>
	 * 属性名: brand<br>
	 * 类型: com.faceye.component.vehicle.entity.Brand<br>
	 * 数据库字段:brand<br>
	 * 
	 * @author haipenge<br>
	 */
	@DBRef
	private Brand brand = null;

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	/**
	 * 说明:电动车类型文本<br>
	 * 属性名: typeText<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:type_text<br>
	 * 
	 * @author haipenge<br>
	 */
	@Transient
	private String typeText = "";

	public String getTypeText() {
		typeText = types.getPair("" + type) == null ? "" : types.getPair("" + type).getValue();
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	/**
	 * 说明:电动车类型<br>
	 * 属性名: type<br>
	 * 类型: java.lang.Integer<br>
	 * 数据库字段:type<br>
	 * 
	 * @author haipenge<br>
	 */
	private Integer type = 0;

	public Integer getType() {

		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 说明:购买日期<br>
	 * 属性名: buyDate<br>
	 * 类型: java.util.Date<br>
	 * 数据库字段:buy_date<br>
	 * 
	 * @author haipenge<br>
	 */
	private Date buyDate = new Date();

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * 说明:车架编号<br>
	 * 属性名: cheJiaBianHao<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:che_jia_bian_hao<br>
	 * 
	 * @author haipenge<br>
	 */
	private String cheJiaBianHao = "";

	public String getCheJiaBianHao() {
		return cheJiaBianHao;
	}

	public void setCheJiaBianHao(String cheJiaBianHao) {
		this.cheJiaBianHao = cheJiaBianHao;
	}

	/**
	 * 说明:电机编号<br>
	 * 属性名: dianJiBianHao<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:dian_ji_bian_hao<br>
	 * 
	 * @author haipenge<br>
	 */
	private String dianJiBianHao = "";

	public String getDianJiBianHao() {
		return dianJiBianHao;
	}

	public void setDianJiBianHao(String dianJiBianHao) {
		this.dianJiBianHao = dianJiBianHao;
	}

	/**
	 * 说明:用户<br>
	 * 属性名: customer<br>
	 * 类型: com.faceye.component.customer.entity.Customer<br>
	 * 数据库字段:customer<br>
	 * 
	 * @author haipenge<br>
	 */
	@DBRef
	private Customer customer = null;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 说明:电动车编号<br>
	 * 属性名: serialNum<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:serial_num<br>
	 * 
	 * @author haipenge<br>
	 */
	private String serialNum = "";

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	/**
	 * 说明:创建日期<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:createDate<br>
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

	/**
	 * 车牌
	 */
	@DBRef
	private LicensePlate licensePlate = null;

	public LicensePlate getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(LicensePlate licensePlate) {
		this.licensePlate = licensePlate;
	}

	// public Pair<String,String> getType(Integer type){
	// Pair<String,String> res=null;
	// List<Pair<String,String>> items=getTypes();
	// for(Pair<String,String> pair:items){
	// if(StringUtils.equals(pair.getKey(), ""+type)){
	// res=pair;
	// break;
	// }
	// }
	// return res;
	// }
	/**
	 * 发牌员
	 */
	@DBRef
	private Customer staff = null;

	public Customer getStaff() {
		return staff;
	}

	public void setStaff(Customer staff) {
		this.staff = staff;
	}

	@DBRef
	private Area area = null;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}/** @generate-entity-source@ **/
