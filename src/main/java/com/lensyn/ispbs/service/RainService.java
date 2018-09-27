package com.lensyn.ispbs.service;

import java.util.List;
import java.util.Map;

import com.lensyn.ispbs.entity.rain.RainFallData;
import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.entity.rain.FlowData;
import com.lensyn.ispbs.entity.rain.RainRankData;

public interface RainService {
	List<RainRankData> getRainRankByAreaId(Map<String, String> map);

	List<AreaInfoData> getAreaRainInfo(Map<String, String> map);

	List<RainFallData> getRainFallListSevenDay(Map<String, String> map);

	List<RainFallData> getRainFallListTwentyFourHour(Map<String, String> map);

	List<RainFallData> getRainFallListSixHour(Map<String, String> map);

	List<FlowData> getFlow(Map<String, String> map);

	/**
	 * @Title: getCloudImg
	 * @author: DENQ
	 * @date: 2018年9月21日 上午11:26:19
	 * @Description: TODO(获取图片数据)
	 * @return
	 */
	 
	Map<String, List<String>> getCloudImg();
}
