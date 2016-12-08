package com.faceye.component.vehicle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.faceye.feature.util.pair.Pair;
import com.faceye.feature.util.pair.Pairs;
/**
 * 模块:车辆->vehicle->LicensePlate<br>
 * 说明:<br>
 * 实体:车牌->com.faceye.component.vehicle.entity.LicensePlate Mongo 对像<br>
 * mongo数据集:vehicle_licensePlate<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-4 14:26:35<br>
 *spring-data-mongo支持的注释类型<br>
 *@Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。<br>
 *@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。<br>
 *@DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存<br>
 *@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。<br>
 *@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。<br>
 *@GeoSpatialIndexed - 声明该字段为地理信息的索引。<br>
 *@Transient - 映射忽略的字段，该字段不会保存到<br>
 *@PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。<br>
 *@CompoundIndexes({
 *    @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
 *})
 *@Indexed(unique = true)
 */
 
@Document(collection="faceye_12_vehicle_licenseplate")
public class LicensePlate implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	public static Pairs<String,String> plateStatusPairs=new Pairs<String,String>().add("0", "已生成").add("1", "制作中").add("2", "已制作").add("3", "已安装").add("4", "已失效");
	@Id
    @Indexed(direction=IndexDirection.DESCENDING)
	private  Long id=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
   
  
     /**
    * 说明:车牌编号<br>
    * 属性名: serialNum<br>
    * 类型: java.lang.String<br>
    * 数据库字段:serial_num<br>
    * @author haipenge<br>
    */
	private  String serialNum = "";
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
   
  
     /**
    * 说明:识别号<br>
    * 属性名: signNum<br>
    * 类型: java.lang.String<br>
    * 数据库字段:sign_num<br>
    * @author haipenge<br>
    */
	private  String signNum = "";
	public String getSignNum() {
		return signNum;
	}
	public void setSignNum(String signNum) {
		this.signNum = signNum;
	}
   
  
     /**
    * 说明:车牌<br>
    * 属性名: plateNum<br>
    * 类型: java.lang.String<br>
    * 数据库字段:plate_num<br>
    * @author haipenge<br>
    */
	private  String plateNum = "";
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
		
   /**
    * 说明:创建日期<br>
    * 属性名: createDate<br>
    * 类型: Date<br>
    * 数据库字段:createDate<br>
    * @author haipenge<br>
    */
    @DateTimeFormat(pattern="yyyy-MM-dd hh24:mi:ss")
	private  Date createDate=new Date();
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

	
   /**
    * 说明:状态<br>
    * 属性名: status<br>
    * 类型: Integer<br>
    * 数据库字段:status<br>
    * @author haipenge<br>
    */
    
	private  Integer status=0;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

	
   /**
    * 说明:状态<br>
    * 属性名: statusText<br>
    * 类型: String<br>
    * 数据库字段:status_text<br>
    * @author haipenge<br>
    */
    
	private  String statusText="";
	public String getStatusText() {
		statusText=plateStatusPairs.getPair(""+status)==null?"":plateStatusPairs.getPair(""+status).getValue();
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
}/**@generate-entity-source@**/
	
