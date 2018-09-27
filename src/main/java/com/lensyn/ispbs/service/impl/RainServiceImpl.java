package com.lensyn.ispbs.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lensyn.ispbs.entity.rain.RainFallData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.entity.rain.FlowData;
import com.lensyn.ispbs.entity.rain.RainRankData;
import com.lensyn.ispbs.mapper.RainMapper;
import com.lensyn.ispbs.service.RainService;
import com.lensyn.ispbs.service.RedisService;
import com.lensyn.ispbs.system.task.ImgRedis;

@Service
@SuppressWarnings("unchecked")
public class RainServiceImpl implements RainService {
	@Autowired
	ObjectMapper mapper;
	@Autowired
	RainMapper rainMapper;
	@Autowired
	RedisService redisService;

	/**
	 * 卫星云图读取地址
	 */
	@Value("${cloud.image.paths}")
	private String paths;
	@Value("${cloud.image.filepaths}")
	private String filepaths;

	@Override
	public List<RainRankData> getRainRankByAreaId(Map<String, String> map) {
		// TODO Auto-generated method stub
		return rainMapper.getRainRankByAreaId(map);
	}

	@Override
	public List<AreaInfoData> getAreaRainInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		try {
			String result = redisService.get("redis_area_info_list");
			//获取redis_area_info_list 保存时间戳
			Long saveTime =Long.parseLong(redisService.get("redis_area_info_list_time"));
			long nowTime =System.currentTimeMillis();
			//获取今日0点时间戳
			long todayStartTime = nowTime - (nowTime + TimeZone.getDefault().getRawOffset())% (1000*3600*24);
			List<AreaInfoData> resultMap= mapper.readValue(result, List.class);
			//如果今日没有更新过redis，就更新
			if(todayStartTime>saveTime){
				// 获取数据库最新数据
				List<AreaInfoData> listnew = rainMapper.getAreaRainInfo();
				listnew.sort((o1, o2) -> o1.getAreaId().compareTo(o2.getAreaId()));
				String areaInfoListStr = mapper.writeValueAsString(listnew);
				// 与redis作对比，数据不为空并且不同则存入新数据,并且返回新数据
				if (!(areaInfoListStr.isEmpty()&&areaInfoListStr.equals(result))){
					redisService.set("redis_area_info_list", areaInfoListStr);
					redisService.set("redis_area_info_list_time", new Date().getTime() +"");
					return listnew;
				}
			}
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RainFallData> getRainFallListSevenDay(Map<String, String> map) {
		// TODO Auto-generated method stub
		return rainMapper.getRainFallListSevenDay(map);
	}

	@Override
	public List<RainFallData> getRainFallListTwentyFourHour(Map<String, String> map) {
		// TODO Auto-generated method stub
		return rainMapper.getRainFallListTwentyFourHour(map);
	}

	@Override
	public List<RainFallData> getRainFallListSixHour(Map<String, String> map) {
		// TODO Auto-generated method stub
		return rainMapper.getRainFallListSixHour(map);
	}

	@Override
	public List<FlowData> getFlow(Map<String, String> map) {
		// TODO Auto-generated method stub
		return rainMapper.getFlow(map);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lensyn.ispbs.service.RainService#getCloudImg()
	 */

	@Override
	public Map<String, List<String>> getCloudImg() {
		// TODO Auto-generated method stub
		try {
			String result = redisService.get("redis_cloud_img_list");
			//获取redis_area_info_list 保存时间戳
			Long saveTime =Long.parseLong(redisService.get("redis_area_info_list_time"));
			long nowTime =System.currentTimeMillis();
			//获取今日0点时间戳
			long todayStartTime = nowTime - (nowTime + TimeZone.getDefault().getRawOffset())% (1000*3600*24);
			Map<String, List<String>>  resultMap=(Map<String, List<String>>) mapper.readValue(result, Map.class);
			//如果今日没有更新过redis，就更新
			if(todayStartTime>saveTime){
				ImgRedis imgredis = new ImgRedis();
				File file = new File(filepaths);
				// 获取最新图片
				Map<String, List<String>> listnew = imgredis.func(file, paths, filepaths);
				String filelist = mapper.writeValueAsString(listnew);
				if (!(filelist.isEmpty()&&filelist.equals(result))){
					redisService.set("redis_cloud_img_list", filelist);
					redisService.set("redis_cloud_img_list_time", new Date().getTime() +"");
					return listnew;
				}
			}
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
