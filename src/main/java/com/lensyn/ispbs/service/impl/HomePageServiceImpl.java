package com.lensyn.ispbs.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lensyn.ispbs.entity.home.AreaStationInTimeSw;
import com.lensyn.ispbs.entity.home.EconomicOperationIndex;
import com.lensyn.ispbs.entity.rain.AreaInfoData;
import com.lensyn.ispbs.mapper.HomePageMapper;
import com.lensyn.ispbs.service.HomePageService;

/**
 * @author  DENQ
 * @date 创建时间：2018年9月26日 上午9:39:59  
 * @version 1.0 
 */
@Service
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	private HomePageMapper homePageMapper;
	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#getFlow(java.lang.String, java.lang.String, java.lang.String)
	 */
	 
	@Override
	public List<Object> getFlow(String datetime, String station, String type) {
		// TODO Auto-generated method stub
		if(datetime==null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
			datetime = simpleDateFormat.format(new Date());
		}else{
			datetime = datetime.substring(0,4);
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("datadate", datetime);
		params.put("station", station);
		if("1".equals(type)){
			return homePageMapper.getFlow(params);
		}else if("2".equals(type)){
			return homePageMapper.getFlowByTendays(params);
		}else if("3".equals(type)){
			return homePageMapper.getFlowMonth(params);
		}
		return null;
	}

	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#SpddrRealTimePowerGenerationList(java.util.Map)
	 */
	 
	@Override
	public List<EconomicOperationIndex> SpddrRealTimePowerGenerationList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		 return homePageMapper.SpddrRealTimePowerGenerationList(map);
	}


	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#getInTimeSwList()
	 */
	 
	@Override
	public List<AreaStationInTimeSw> getInTimeSwList() {
		// TODO Auto-generated method stub
		return homePageMapper.getInTimeSwList();
	}


	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#getProvinceSchedulingInstalledCapacity(java.lang.String)
	 */
	 
	@Override
	public List<Object> getProvinceSchedulingInstalledCapacity(String datetime) {
		// TODO Auto-generated method stub
		if(datetime==null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			datetime = simpleDateFormat.format(new Date());
		}else{
			datetime = datetime.substring(0,7);
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("datadate", datetime);
		return homePageMapper.getProvinceSchedulingInstalledCapacity(params);
	}


	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#getSchedulingRelationship(java.lang.String)
	 */
	 
	@Override
	public List<Object> getSchedulingRelationship(String datetime) {
		// TODO Auto-generated method stub
		if(datetime==null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			datetime = simpleDateFormat.format(new Date());
		}else{
			datetime = datetime.substring(0,7);
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("datadate", datetime);
		return homePageMapper.getSchedulingRelationship(params);
	}


	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#getGenerationByDaySUM(java.lang.String, java.lang.String)
	 */
	 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> getGenerationByDaySUM(String stationid, String stationname) throws ParseException {
		// TODO Auto-generated method stub
		Double monthvalue = 0.0;//本月实际发电
    	Double capacity = 0.0;//剩余本月发电能力
    	Double company = 0.0;//剩余本月公司发电计划
    	Double grid = 0.0;//剩余本月电网发电计划
    	int days = 0;//本月剩余天数
		if(stationname==null&&stationname==null){
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if("公司干流8站".equals(stationname)){
			List<String>  idList = new ArrayList<String>();
			idList.add("11000324");
    		idList.add("11000029");
    		idList.add("11000004");
    		idList.add("11000005");
    		idList.add("11000325");
    		idList.add("11000001");
    		idList.add("11000000");
    		idList.add("11000030");
    		params.put("ids", idList);
    		List<String>  nameList = new ArrayList<String>();
    		nameList.add("猴子岩");
    		nameList.add("大岗山");
    		nameList.add("瀑布沟");
    		nameList.add("深溪沟");
    		nameList.add("枕头坝");
    		nameList.add("沙南");
    		nameList.add("龚嘴");
    		nameList.add("铜街子");
			params.put("names", nameList);
		}else if("公司9站".equals(stationname)){
			List<String>  idList = new ArrayList<String>();
			idList.add("11000024");
			idList.add("11000324");
    		idList.add("11000029");
    		idList.add("11000004");
    		idList.add("11000005");
    		idList.add("11000325");
    		idList.add("11000001");
    		idList.add("11000000");
    		idList.add("11000030");
    		params.put("ids", idList);
    		List<String>  nameList = new ArrayList<String>();
    		nameList.add("吉牛");
    		nameList.add("猴子岩");
    		nameList.add("大岗山");
    		nameList.add("瀑布沟");
    		nameList.add("深溪沟");
    		nameList.add("枕头坝");
    		nameList.add("沙南");
    		nameList.add("龚嘴");
    		nameList.add("铜街子");
			params.put("names", nameList);
		}else {
			if(stationid==null||stationid.length()==0){
	    		return null;
	    	}else{
	    		List<String>  idList = new ArrayList<String>();
	    		idList.add(stationid);
	    		params.put("ids", idList);
	    		List<String>  nameList = new ArrayList<String>();
	    		nameList.add(stationname);
	    		params.put("names", nameList);
	    	}
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		Calendar now = Calendar.getInstance();//用现在的日期判断是否大于25号，如果大于25号就查询下个月的数据
		now.setTime(new Date());
		if(now.get(Calendar.DAY_OF_MONTH)>25){
			params.put("datadate", simpleDateFormat.format(new Date(new Date().getTime()+1000*60*60*24*10)));//往后加10天就是下个月
		}else{
			params.put("datadate", simpleDateFormat.format(new Date()));
		}
		List<Object> generationList = homePageMapper.getGenerationByDaySUM(params);
		List<Object> list = homePageMapper.getCapacity(params);
		if(list!=null&&list.size()>0){
			Map map = (Map)list.get(0);
			monthvalue = Double.parseDouble(map.get("MONTHVALUE")==null?"0":map.get("MONTHVALUE")+"");
			capacity = Double.parseDouble(map.get("CAPACITY")==null?"0":map.get("CAPACITY")+"");
			company = Double.parseDouble(map.get("COMPANY")==null?"0":map.get("COMPANY")+"");
			grid = Double.parseDouble(map.get("GRID")==null?"0":map.get("GRID")+"");
			Calendar startTtime = Calendar.getInstance();//开始时间（就是发电的最大时间）
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date maxDate = dateFormat.parse(map.get("MAXDAY")+"");
			startTtime.setTime(maxDate);
	        Calendar endTtime = Calendar.getInstance();//结束时间（如果是小于或等于25号，结束时间就是本月25号，如果大于25号，结束时间就是下月25号）
	        if(startTtime.get(Calendar.DAY_OF_MONTH)<=25){
	        	endTtime.setTime(new Date());
	        	endTtime.set(Calendar.DAY_OF_MONTH,25);
	        }else{
	        	endTtime.setTime(new Date());
	        	endTtime.set(Calendar.MONTH,endTtime.get(Calendar.MONTH)+1);
	        	endTtime.set(Calendar.DAY_OF_MONTH,25);
	        }
	        days = endTtime.get(Calendar.DAY_OF_YEAR)-startTtime.get(Calendar.DAY_OF_YEAR);
	        if(days>=1){
        		capacity=(capacity-monthvalue)/days;
        		company=(company-monthvalue)/days;
        		grid=(grid-monthvalue)/days;
        		Double capacitySum = 0.0;//本月发电能力剩余每日累计
    	    	Double companySum = 0.0;//本月公司发电计划剩余每日累计
    	    	Double gridSum = 0.0;//本月电网发电计划剩余每日累计
	        	if(generationList!=null&&generationList.size()>0){
	        		for(int i=0;i<generationList.size();i++){
	        			Map generation = (Map)generationList.get(i);
	        			if(generation!=null&&generation.get("DATADATE")!=null){
	        				Date datadate = dateFormat.parse(generation.get("DATADATE")+"");
	        				if(datadate.before(maxDate)){
	        					generation.put("本月发电能力累计", generation.get("本期累计"));
	        					generation.put("本月公司计划累计", generation.get("本期累计"));
	        					generation.put("本月电网计划累计", generation.get("本期累计"));
	        				}else{
	        					int index=1;
	        					capacitySum=monthvalue+capacity*index;
	    	        			companySum=monthvalue+company*index;
	    	        			gridSum=monthvalue+grid*index;
	        					generation.put("本月发电能力累计", capacitySum);
	        					generation.put("本月公司计划累计", companySum);
	        					generation.put("本月电网计划累计", gridSum);
	        					index++;
	        				}
	        			}
	        		}
	        	}
	        }
		}
		return generationList;
	}


	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#getAreaRainInfoOfDDH(java.util.Map)
	 */
	 
	@Override
	public List<AreaInfoData> getAreaRainInfoOfDDH(Map<String, String> map) {
		// TODO Auto-generated method stub
		return homePageMapper.getAreaRainInfoOfDDH(map);
	}


	
	 /* (non-Javadoc)
	 * @see com.lensyn.ispbs.service.HomePageService#getAreaRainInfo(java.util.Map)
	 */
	 
	@Override
	public List<AreaInfoData> getAreaRainInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return homePageMapper.getAreaRainInfo(map);
	}

}

