package com.lensyn.ispbs.mapper;

import java.util.List;
import java.util.Map;

import com.lensyn.ispbs.entity.home.AreaStationInTimeSw;
import com.lensyn.ispbs.entity.home.EconomicOperationIndex;
import com.lensyn.ispbs.entity.rain.AreaInfoData;

/**
 * @author  DENQ
 * @date 创建时间：2018年9月26日 上午9:56:13  
 * @version 1.0 
 */
public interface HomePageMapper {

	/**
	 * @Title: getFlow
	 * @author: DENQ
	 * @date: 2018年9月26日 上午9:57:44
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	 
	List<Object> getFlow(Map<String, String> params);

	/**
	 * @Title: getFlowByTendays
	 * @author: DENQ
	 * @date: 2018年9月26日 上午9:57:56
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	 
	List<Object> getFlowByTendays(Map<String, String> params);

	/**
	 * @Title: getFlowMonth
	 * @author: DENQ
	 * @date: 2018年9月26日 上午9:58:01
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	 
	List<Object> getFlowMonth(Map<String, String> params);

	/**
	 * @Title: SpddrRealTimePowerGenerationList
	 * @author: DENQ
	 * @date: 2018年9月26日 上午10:21:27
	 * @Description: TODO(实时发电-计划发电)
	 * @param map
	 * @return
	 */
	 
	List<EconomicOperationIndex> SpddrRealTimePowerGenerationList(Map<String, Object> map);

	/**
	 * @Title: getInTimeSwList
	 * @author: DENQ
	 * @date: 2018年9月26日 上午10:52:22
	 * @Description: TODO(实时水位)
	 * @return
	 */
	 
	List<AreaStationInTimeSw> getInTimeSwList();

	/**
	 * @Title: getProvinceSchedulingInstalledCapacity
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:16:26
	 * @Description: TODO(省调装机)
	 * @param params
	 * @return
	 */
	 
	List<Object> getProvinceSchedulingInstalledCapacity(Map<String, String> params);

	/**
	 * @Title: getSchedulingRelationship
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:26:01
	 * @Description: TODO(调度关系)
	 * @param params
	 * @return
	 */
	 
	List<Object> getSchedulingRelationship(Map<String, String> params);

	/**
	 * @Title: getGenerationByDaySUM
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:40:22
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	 
	List<Object> getGenerationByDaySUM(Map<String, Object> params);

	/**
	 * @Title: getCapacity
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:40:26
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	 
	List<Object> getCapacity(Map<String, Object> params);

	/**
	 * @Title: getAreaRainInfoOfDDH
	 * @author: DENQ
	 * @date: 2018年9月26日 下午3:56:45
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param map
	 * @return
	 */
	 
	List<AreaInfoData> getAreaRainInfoOfDDH(Map<String, String> map);

	/**
	 * @Title: getAreaRainInfo
	 * @author: DENQ
	 * @date: 2018年9月26日 下午3:56:51
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param map
	 * @return
	 */
	 
	List<AreaInfoData> getAreaRainInfo(Map<String, String> map);

}

