package com.faceye.component.vehicle.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.faceye.component.vehicle.entity.LicensePlate;
import com.faceye.component.vehicle.service.ExcelService;
import com.faceye.component.vehicle.service.LicensePlateService;
import com.faceye.feature.util.DateUtil;

/**
 * Excel的导入，导出操作
 * 
 * @author @haipenge
 * @联系:haipenge@gmail.com 创建时间:2015年5月11日
 */
@Service
public class ExcelServiceImpl implements ExcelService {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private LicensePlateService licensePlateService=null;
	@Value("#{property['weixin.host']}")
	private String weixinHost = "";
	public List<Map<Integer, String>> read(InputStream is) {
		List<Map<Integer, String>> items = new ArrayList<Map<Integer, String>>();
		Map<Integer, String> content = new HashMap<Integer, String>();
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		String str = "";
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);

		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int colNum = row.getPhysicalNumberOfCells();
			int j = 0;
			Map<Integer, String> map = new HashMap<Integer, String>();
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim() +
				// "-";
				String cellValue = getCellFormatValue(row.getCell(j));
				// str += getCellFormatValue(row.getCell((short) j)).trim() + " ";
				map.put(j, cellValue);
				j++;
			}
			items.add(map);
		}
		return items;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return StringUtils.trim(cellvalue);
	}

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param InputStream
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitle(InputStream is) {
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		String str = "";
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			// title[i] = getStringCellValue(row.getCell((short) i));
			title[i] = getCellFormatValue(row.getCell(i));
		}
		return title;
	}

	@Override
	public void export(Page<LicensePlate> licensePlates, OutputStream os) {
		String[] title = new String[] { "车牌号", "二维码识别", "日期" };
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("电动自行车二维码车牌制作工单");
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < title.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(title[i]);
			cell.setCellValue(text);
		}
		int index = 1;
		for (LicensePlate licensePlate : licensePlates.getContent()) {
			HSSFRow dataRow = sheet.createRow(index);
			// if (order.getCustomer() == null) {
			// continue;
			// }
			for (int i = 0; i < title.length; i++) {

				HSSFCell cell = dataRow.createCell(i);
				cell.setCellStyle(style2);
				// if (i == 0) {
				// if (null != order.getCustomer()) {
				// cell.setCellValue(order.getCustomer().getName());
				// }
				// }
				if (i == 0) {
					// StringBuilder orderItemInfo = new StringBuilder("");
					// List<OrderItem> orderItems = order.getOrderItems();
					// if (CollectionUtils.isNotEmpty(orderItems)) {
					// for (OrderItem orderItem : orderItems) {
					// if (orderItem.getProduct() == null) {
					// continue;
					// }
					// orderItemInfo.append(orderItem.getProduct().getName());
					// orderItemInfo.append("(共 ");
					// orderItemInfo.append(orderItem.getTotalCount());
					// orderItemInfo.append(" 份,");
					// orderItemInfo.append(orderItem.getWeight());
					// orderItemInfo.append(" 斤)");
					// orderItemInfo.append(",");
					// orderItemInfo.append("\n\r");
					// }
					// }
					// if (StringUtils.isNotEmpty(orderItemInfo.toString())) {
					// orderItemInfo.deleteCharAt(orderItemInfo.lastIndexOf(","));
					// cell.setCellValue(orderItemInfo.toString());
					// }
					cell.setCellValue(licensePlate.getPlateNum().toUpperCase());
				}
				// 二维码字符串
				if (i == 1) {
					String qrCodeStr = weixinHost + "/vehicle/vehicle/scan/" + licensePlate.getPlateNum();
					cell.setCellValue(qrCodeStr);
				}
				// 日期
				if (i == 2) {
					cell.setCellValue(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
				}
				// // 街道
				// if (i == 4) {
				// cell.setCellValue(order.getCustomer().getStreetArea());
				// }
				// if (i == 5) {
				// cell.setCellValue(order.getCustomer().getAddressDetail());
				// }
				// if (i == 6) {
				// if (order.getDeliverStaff() != null) {
				// cell.setCellValue(order.getDeliverStaff().getName() + "(" + order.getDeliverStaff().getMobile() + ")");
				// }
				// }
				// if (i == 7) {
				// cell.setCellValue(DateUtil.formatDate(order.getDeliverDistributeDate(), "yyyy-MM-dd"));
				// }
				
			}
			index++;
			//将车牌状态设置为制作中
			licensePlate.setStatus(1);
			this.licensePlateService.save(licensePlate);
		}
		try {
			workbook.write(os);
		} catch (IOException e) {
			logger.error(">>FaceYe throws Exception: --->", e);
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				logger.error(">>FaceYe throws Exception: --->", e);
			}
		}

	}

}
