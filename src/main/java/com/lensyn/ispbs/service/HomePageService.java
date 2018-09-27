package com.lensyn.ispbs.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.lensyn.ispbs.entity.home.AreaStationInTimeSw;
import com.lensyn.ispbs.entity.home.EconomicOperationIndex;
import com.lensyn.ispbs.entity.rain.AreaInfoData;

/**
 * @author  DENQ
 * @date 创建时间：2018年9月26日 上午9:39:00  
 * @version 1.0 
 */
public interface HomePageService {

	/**
	 * @Title: getFlow
	 * @author: DENQ
	 * @date: 2018年9月26日 上午9:53:02
	 * @Description: TODO(查询某电站各年的流量过程数据)
	 * @param datetime
	 * @param station
	 * @param type
	 * @return
	 */
	 
	List<Object> getFlow(String datetime, String station, String type);

	/**
	 * @Title: SpddrRealTimePowerGenerationList
	 * @author: DENQ
	 * @date: 2018年9月26日 上午10:17:13
	 * @Description: TODO(实时发电-计划发电)
	 * @param map
	 * @return
	 */
	 
	List<EconomicOperationIndex> SpddrRealTimePowerGenerationList(Map<String, Object> map);

	/**
	 * @Title: getInTimeSwList
	 * @author: DENQ
	 * @date: 2018年9月26日 上午10:51:21
	 * @Description: TODO(实时水位)
	 * @return
	 */
	 
	List<AreaStationInTimeSw> getInTimeSwList();

	/**
	 * @Title: getProvinceSchedulingInstalledCapacity
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:10:21
	 * @Description: TODO(省调装机)
	 * @param datetime
	 * @return
	 */
	 
	List<Object> getProvinceSchedulingInstalledCapacity(String datetime);

	/**
	 * @Title: getSchedulingRelationship
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:23:34
	 * @Description: TODO(调度关系)
	 * @param datetime
	 * @return
	 */
	 
	List<Object> getSchedulingRelationship(String datetime);

	/**
	 * @Title: getGenerationByDaySUM
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:37:52
	 * @Description: TODO(累计发电量)
	 * @param stationid
	 * @param stationname
	 * @return
	 * @throws ParseException
	 */
	List<Object> getGenerationByDaySUM(String stationid, String stationname)throws ParseException;

	/**
	 * @Title: getAreaRainInfoOfDDH
	 * @author: DENQ
	 * @date: 2018年9月26日 下午3:54:58
	 * @Description: TODO(大渡河)
	 * @param map
	 * @return
	 */
	 
	List<AreaInfoData> getAreaRainInfoOfDDH(Map<String, String> map);

	/**
	 * @Title: getAreaRainInfo
	 * @author: DENQ
	 * @date: 2018年9月26日 下午3:55:02
	 * @Description: TODO(全川)
	 * @param map
	 * @return
	 */
	 
	List<AreaInfoData> getAreaRainInfo(Map<String, String> map);

}

