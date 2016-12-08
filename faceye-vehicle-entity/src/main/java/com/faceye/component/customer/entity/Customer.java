package com.faceye.component.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.faceye.feature.util.pair.Pairs;

/**
 * 模块:客户->customer->Customer<br>
 * 说明:<br>
 * 实体:车主->com.faceye.component.customer.entity.Customer Mongo 对像<br>
 * mongo数据集:customer_customer<br>
 * 
 * @author haipenge <br>
 *         联系:haipenge@gmail.com<br>
 *         创建日期:2016-8-4 14:26:38<br>
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

@Document(collection = "faceye_12_customer_customer")
public class Customer implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;

	public static Pairs<String, String> sexTexts = new Pairs<String, String>().add("1", "男").add("0", "女");

	public static Pairs<String, String> typeTexts = new Pairs<String, String>().add("0", "车主").add("1", "上牌员").add("3", "警察");
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
	 * 说明:头像地址<br>
	 * 属性名: headImgUrl<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:head_img_url<br>
	 * 
	 * @author haipenge<br>
	 */
	private String headImgUrl = "";

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * 说明:身份证号<br>
	 * 属性名: personalId<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:personal_id<br>
	 * 
	 * @author haipenge<br>
	 */
	private String personalId = "";

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	/**
	 * 说明:邮箱<br>
	 * 属性名: email<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:email<br>
	 * 
	 * @author haipenge<br>
	 */
	private String email = "";

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 说明:性别名称<br>
	 * 属性名: sexText<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:sex_text<br>
	 * 
	 * @author haipenge<br>
	 */
	@Transient
	private String sexText = "";

	public String getSexText() {
		sexText = sexTexts.getPair("" + sex) == null ? "男" : sexTexts.getPair("" + sex).getValue();
		return sexText;
	}

	public void setSexText(String sexText) {
		this.sexText = sexText;
	}

	/**
	 * 说明:性别<br>
	 * 属性名: sex<br>
	 * 类型: java.lang.Integer<br>
	 * 数据库字段:sex<br>
	 * 
	 * @author haipenge<br>
	 */
	private Integer sex = 1;

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	// 默认车主
	private Integer type = 0;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Transient
	private String typeText = "";
	public String getTypeText() {
		return typeTexts.getPair(""+type).getValue();
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	/**
	 * 说明:手机号<br>
	 * 属性名: mobile<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:mobile<br>
	 * 
	 * @author haipenge<br>
	 */
	private String mobile = "";

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 说明:姓名<br>
	 * 属性名: name<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:name<br>
	 * 
	 * @author haipenge<br>
	 */
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:昵称<br>
	 * 属性名: nickName<br>
	 * 类型: java.lang.String<br>
	 * 数据库字段:nick_name<br>
	 * 
	 * @author haipenge<br>
	 */
	private String nickName = "";

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	 * 关联faceye-security-web-entity-mongo ->User对像
	 */
	private Long webUserId = null;

	public Long getWebUserId() {
		return webUserId;
	}

	public void setWebUserId(Long webUserId) {
		this.webUserId = webUserId;
	}

}/** @generate-entity-source@ **/
