package com.lensyn.ispbs.mapper;

import java.util.List;
import java.util.Map;

import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.entity.rain.FlowData;
import com.lensyn.ispbs.entity.rain.RainFallData;
import com.lensyn.ispbs.entity.rain.RainRankData;

public interface RainMapper {
	List<RainRankData> getRainRankByAreaId(Map<String, String> map);

	List<AreaInfoData> getAreaRainInfo();

	List<RainFallData> getRainFallListSevenDay(Map<String, String> map);

	List<RainFallData> getRainFallListTwentyFourHour(Map<String, String> map);

	List<RainFallData> getRainFallListSixHour(Map<String, String> map);

	List<FlowData> getFlow(Map<String, String> map);

}
