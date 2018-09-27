package com.lensyn.ispbs.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lensyn.ispbs.entity.Response;
import com.lensyn.ispbs.entity.home.AreaStationInTimeSw;
import com.lensyn.ispbs.entity.home.EconomicOperationIndex;
import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.service.HomePageService;


/**
 * @author  DENQ
 * @date 创建时间：2018年9月26日 上午9:36:21  
 * @version 1.0 
 */
@RestController
@RequestMapping(value = "/home")
public class HomePageController {

	@Autowired
	HomePageService homePageService;
	/**
	 * 
	 * @Title: getFlow
	 * @author: DENQ
	 * @date: 2018年9月26日 上午9:53:20
	 * @Description: TODO(查询某电站各年的流量过程数据)
	 * @param datetime
	 * @param station
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getFlow", method = RequestMethod.GET)
	public Response<?> getFlow(@Param("datetime")String datetime,@Param("station")String station,@Param("type")String type) {
		try{
			List<Object> flowList = homePageService.getFlow(datetime,station,type);
			if (flowList != null&&flowList.size()>0) {
				return new Response<>().success(flowList);
			}
			return new Response<>().success("无数据");
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>().failure("失败");
		}
	}
	/**
	 * 
	 * @Title: SpddrRealTimePowerGenerationList
	 * @author: DENQ
	 * @date: 2018年9月26日 上午10:13:18
	 * @Description: TODO(实时发电-计划发电)
	 * @param timeSelection
	 * @return
	 */
	@RequestMapping(value = "/spddrRealTimePowerGenerationList", method = RequestMethod.GET)
    private Response<?> SpddrRealTimePowerGenerationList(@Param("timeSelection") String timeSelection) {
		try{
        Date date = new Date();
        SimpleDateFormat sdfdd = new SimpleDateFormat("yyyy-MM-dd");

        //昨天
        Calendar timeC = Calendar.getInstance();
        timeC.add(Calendar.DATE,   -1);

        String dataTime="";
        if(timeSelection.equals("01")){
            dataTime=sdfdd.format(date);
        }else if(timeSelection.equals("02")){
            dataTime=sdfdd.format(timeC.getTime());
        }
        Map<String,Object> map=new HashMap<String,Object>();
        //map.put("dataTime",dataTime);
        List<EconomicOperationIndex> spddrRealTimePowerGenerationList = homePageService.SpddrRealTimePowerGenerationList(map);
        if (spddrRealTimePowerGenerationList != null) {
            return new Response<>().success(spddrRealTimePowerGenerationList);
        }
        return new Response<>().success("无数据");
		}catch(Exception e){
			
			return new Response<>().failure("失败");
		}
    }
	/**
	 * 
	 * @Title: getInTimeSwList
	 * @author: DENQ
	 * @date: 2018年9月26日 上午10:49:53
	 * @Description: TODO(实时水位)
	 * @return
	 */
	@RequestMapping(value = "/inTimeSw", method = RequestMethod.GET)
	public Response<?> getInTimeSwList() {
		try{
		List<AreaStationInTimeSw> inTimeSwList = homePageService.getInTimeSwList();
		if (inTimeSwList != null) {
			return new Response<>().success(inTimeSwList);
		}
		return new Response<>().success("无数据");
		}catch(Exception e){
			 return new Response<>().failure("失败");
		}
	}
	/**
	 * 
	 * @Title: getProvinceSchedulingInstalledCapacity
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:09:50
	 * @Description: TODO(省调装机)
	 * @param datetime
	 * @return
	 */
	@RequestMapping(value = "/getProvinceSchedulingInstalledCapacity", method = RequestMethod.GET)
	public Response<?> getProvinceSchedulingInstalledCapacity(@Param("datetime")String datetime) {
		try{
			List<Object> provinceSchedulingInstalledCapacityList = homePageService.getProvinceSchedulingInstalledCapacity(datetime);
			if (provinceSchedulingInstalledCapacityList != null&&provinceSchedulingInstalledCapacityList.size()>0) {
				return new Response<>().success(provinceSchedulingInstalledCapacityList);
			}
			return new Response<>().success("无数据");
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>().failure("失败");
		}
	}
	/**
	 * 
	 * @Title: getSchedulingRelationship
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:23:17
	 * @Description: TODO(调度关系)
	 * @param datetime
	 * @return
	 */
	@RequestMapping(value = "/getSchedulingRelationship", method = RequestMethod.GET)
	public Response<?> getSchedulingRelationship(@Param("datetime")String datetime) {
		try{
			List<Object> schedulingRelationshipList = homePageService.getSchedulingRelationship(datetime);
			if (schedulingRelationshipList != null&&schedulingRelationshipList.size()>0) {
				return new Response<>().success(schedulingRelationshipList);
			}
			return new Response<>().success("无数据");
		}catch(Exception e){
			return new Response<>().failure("失败");
		}
	}
	/**
	 * 
	 * @Title: getGenerationByDaySum
	 * @author: DENQ
	 * @date: 2018年9月26日 上午11:37:35
	 * @Description: TODO(累计发电量)
	 * @param stationid
	 * @param stationname
	 * @return
	 */
	@RequestMapping(value = "/getGenerationByDaySum", method = RequestMethod.GET)
	public Response<?> getGenerationByDaySum(@Param("stationid")String stationid,@Param("stationname")String stationname) {
		try{
			List<Object> generationList = homePageService.getGenerationByDaySUM(stationid,stationname);
			if (generationList != null&&generationList.size()>0) {
				return new Response<>().success(generationList);
			}
			return new Response<>().success("无数据");
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>().failure("失败");
		}
	}
	
	/**
	 * 
	 * @Title: getAreaRainInfo
	 * @author: DENQ
	 * @date: 2018年9月26日 下午3:54:13
	 * @Description: TODO(区域信息 降雨量 面雨量 流域面积 实测)
	 * @param areaId
	 * @param stime
	 * @param etime
	 * @return
	 */
	@RequestMapping(value = "/areaRainInfo", method = RequestMethod.GET)
	public Response<?> getAreaRainInfo(@Param("areaId")String areaId, @Param("stime")String stime, @Param("etime")String etime) {
		if(areaId.equals("0")){
			Map<String,String> map = new HashMap<String,String>();
			map.put("stime", stime);
			map.put("etime", etime);
			List<AreaInfoData> areaInfoDataList = homePageService.getAreaRainInfoOfDDH(map);
			if (areaInfoDataList != null) {
				return new Response<>().success(areaInfoDataList);
			}
			return new Response<>().success("无数据");
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("areaId", areaId);
			map.put("stime", stime);
			map.put("etime", etime);
			List<AreaInfoData> areaInfoDataList = homePageService.getAreaRainInfo(map);
			if (areaInfoDataList != null) {
				return new Response<>().success(areaInfoDataList);
			}
			return new Response<>().success("无数据");
		}
	}
}

