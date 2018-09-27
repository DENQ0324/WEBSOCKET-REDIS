package com.lensyn.ispbs.system.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lensyn.ispbs.entity.Response;
import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.service.RainService;
import com.lensyn.ispbs.service.RedisService;
import com.lensyn.ispbs.system.ws.WebSocketRainServer;

@Component
public class RainTask {
	private static final Logger logger = LoggerFactory.getLogger(ImgRedis.class);
	@Autowired
	RainService rainService;
	@Autowired
	RedisService redisService;
	@Autowired
	ObjectMapper mapper;

	/**
	 * 	雨量站信息推送计划任务
	 *  redis字段: redis_area_info_list
	 */
	@Scheduled(cron = "0 0 0/2 * * *")
	public void areaRainInfoTask()  {
		try{
		/**
		 * 1. 定时任务跑数据
		 */
		Map<String, String> map = new HashMap<String, String>();
		List<AreaInfoData> areaInfoList = rainService.getAreaRainInfo(map);
		areaInfoList.sort((o1, o2) -> o1.getAreaId().compareTo(o2.getAreaId()));
		String areaInfoListStr = mapper.writeValueAsString(areaInfoList);
		/**
		 * 2. 跟redis比较
		 */
		String result = redisService.get("redis_area_info_list");
		boolean equals = true;
		if (!StringUtils.isEmpty(result)) {
			equals = result.equals(areaInfoListStr);
		} else {
			equals = false;
		}

		/**
		 * 3.往前端推数据;
		 */
		if (!equals && areaInfoList != null) {
			redisService.set("redis_area_info_list", areaInfoListStr);
			redisService.set("redis_area_info_list_time", new Date().getTime() +"");
			Response<Object> success = new Response<>().success(areaInfoList);
			success.setType("rainInfo");
			for (WebSocketRainServer item : WebSocketRainServer.webSocketSet) {
				item.sendMessage(success);
				logger.info("推送redis_area_info_list:"+areaInfoListStr);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		logger.error("失败:推送redis_area_info_list:" +e.getMessage());
	}
	}
}
