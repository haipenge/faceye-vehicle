package com.faceye.component.vehicle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 模块:车辆->vehicle->VehicleColor<br>
 * 说明:<br>
 * 实体:电动车颜色->com.faceye.component.vehicle.entity.VehicleColor Mongo 对像<br>
 * mongo数据集:vehicle_vehicleColor<br>
 * @author haipenge <br>
 * 联系:haipenge@gmail.com<br>
 * 创建日期:2016-8-4 14:26:36<br>
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
 
@Document(collection="faceye_12_vehicle_vehiclecolor")
public class VehicleColor implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
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
    * 说明:名称<br>
    * 属性名: name<br>
    * 类型: java.lang.String<br>
    * 数据库字段:name<br>
    * @author haipenge<br>
    */
	private  String name = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}/**@generate-entity-source@**/
	
