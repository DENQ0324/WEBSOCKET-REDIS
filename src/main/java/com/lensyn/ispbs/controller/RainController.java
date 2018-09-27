package com.lensyn.ispbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.entity.rain.FlowData;
import com.lensyn.ispbs.entity.rain.RainFallData;
import com.lensyn.ispbs.entity.Response;
import com.lensyn.ispbs.entity.rain.RainRankData;
import com.lensyn.ispbs.service.RainService;

@RestController
@RequestMapping(value = "/rain")
public class RainController {
	@Autowired
	RainService rainService;

	/**
	 * 区域雨量排行
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rainRankByAreaId", method = RequestMethod.GET)
	public Response<?> getRainRankByAreaId(@Param("areaId") String areaId, @Param("stime") String stime,
			@Param("etime") String etime) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaId", areaId);
		map.put("stime", stime);
		map.put("etime", etime);
		List<RainRankData> areaStationList = rainService.getRainRankByAreaId(map);
		if (areaStationList != null) {
			return new Response<>().success(areaStationList);
		}
		return new Response<>().failure("失败");
	}

	/**
	 * 区域信息 降雨量 面雨量 流域面积 实测;
	 * 
	 * @return
	 */
	@RequestMapping(value = "/areaRainInfo", method = RequestMethod.GET)
	public Response<?> getAreaRainInfo() {

		Map<String, String> map = new HashMap<String, String>();
		List<AreaInfoData> areaInfoDataList = rainService.getAreaRainInfo(map);
		if (areaInfoDataList != null) {
			return new Response<>().success(areaInfoDataList);
		}
		return new Response<>().success("无数据");
	}

	/**
	 * 7天区域雨量站实测预测降雨数据;
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rainFallDataSevenDay", method = RequestMethod.GET)
	public Response<?> getRainFallListSevenDay(@Param("areaId") String areaId) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaId);
		List<RainFallData> rainFallListSevenDay = rainService.getRainFallListSevenDay(map);
		if (rainFallListSevenDay != null) {
			return new Response<>().success(rainFallListSevenDay);
		}
		return new Response<>().success("无数据");
	}

	/**
	 * 24小时区域雨量站实测预测降雨数据;
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rainFallDataTwentyFourHour", method = RequestMethod.GET)
	public Response<?> getRainFallListTwentyFourHour(@Param("areaId") String areaId) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaId);
		List<RainFallData> rainFallListTwentyFourHour = rainService.getRainFallListTwentyFourHour(map);
		if (rainFallListTwentyFourHour != null) {
			return new Response<>().success(rainFallListTwentyFourHour);
		}
		return new Response<>().success("无数据");
	}

	/**
	 * 六小时区域雨量站实测预测降雨数据;
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rainFallDataSixHour", method = RequestMethod.GET)
	public Response<?> getRainFallListSixHour(@Param("areaId") String areaId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaId);
		List<RainFallData> rainFallListSixHour = rainService.getRainFallListSixHour(map);
		if (rainFallListSixHour != null) {
			return new Response<>().success(rainFallListSixHour);
		}
		return new Response<>().success("无数据");
	}

	/**
	 * 
	 * @Title: getFlow
	 * @date: 2018年9月21日 上午9:19:33
	 * @Description: TODO(径流量)
	 * @param areaId
	 * @return
	 */
	@RequestMapping(value = "/getFlow", method = RequestMethod.GET)
	public Response<?> getFlow(@Param("areaId") String areaId) {
		Map<String, String> map = new HashMap<String, String>();
		if ("1".equals(areaId)) {
			map.put("id", "30000005");
		} else if ("2".equals(areaId)) {
			map.put("id", "30000018");
		} else if ("3".equals(areaId)) {
			map.put("id", "30000017 ");
		}else {
			Response<Object> success = new Response<>().success(null);
			success.setType("flow");
			return success;
		}
		List<FlowData> flow = rainService.getFlow(map);
		if (flow != null) {
			Response<Object> success = new Response<>().success(flow);
			success.setType("flow");
			return success;
		}
		return new Response<>().success("无数据");
	}
	
	@RequestMapping(value = "/getCloudImg", method = RequestMethod.GET)
	public Response<?> getCloudImg(){
		try {
			
			Map<String,List<String>> fileList=rainService.getCloudImg();
			Response<Object> success = new Response<>().success(fileList);
			success.setType("cloudImg");
			return success;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Response<>().success("无数据");
		
	}
}
