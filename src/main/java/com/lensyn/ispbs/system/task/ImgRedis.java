package com.lensyn.ispbs.system.task;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lensyn.ispbs.entity.Response;
import com.lensyn.ispbs.service.RedisService;
import com.lensyn.ispbs.system.ws.WebSocketRainServer;

/**
 * @author DENQ
 * @date 创建时间：2018年9月20日 下午2:05:34
 * @version 1.0
 */
@Component
public class ImgRedis {
	private static final Logger logger = LoggerFactory.getLogger(ImgRedis.class);

	@Autowired
	RedisService redisService;
	@Autowired
	ObjectMapper mapper;
	/**
	 * 卫星云图读取地址
	 */
	@Value("${cloud.image.paths}")
	private  String paths;
	@Value("${cloud.image.filepaths}")
	private  String filepaths;
	
	
	/**
	 * 
	 * @Title: setImgRedis
	 * @author: DENQ
	 * @date: 2018年9月21日 下午2:06:11
	 * @Description: TODO(定时更新redis图片)
	 */
	@Scheduled(cron = "0 0/30 8-16 * * *")
	public void  setImgRedis() {
		try {
			File file = new File(filepaths);
			//获取最新图片
			Map<String,List<String>> fileList=func(file);
			
			String result = redisService.get("redis_cloud_img_list");
			String cloudImg = mapper.writeValueAsString(fileList);
			/**
			 * 2. 跟redis比较
			 */
			
			boolean equals = true;
			if (!StringUtils.isEmpty(result)) {
				equals = result.equals(cloudImg);
			} else {
				equals = false;
			}
			
			/**
			 * 3.往前端推数据;
			 */
			if (!equals && fileList != null) {
				redisService.set("redis_cloud_img_list", cloudImg);
				redisService.set("redis_cloud_img_list_time", new Date().getTime() +"");
				Response<Object> success = new Response<>().success(fileList);
				success.setType("cloudImg");
				for (WebSocketRainServer item : WebSocketRainServer.webSocketSet) {
					item.sendMessage(success);
					logger.info("推送 redis_cloud_img_list:"+cloudImg);
				}
			}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("失败:推送 redis_cloud_img_list:" +e.getMessage());
			}
	}

	public  Map<String,List<String>> func(File file) {
		return func(file,paths,filepaths);
	}
	/**
	 * 
	 * @Title: func
	 * @author: DENQ
	 * @date: 2018年9月20日 下午5:44:26
	 * @Description: TODO(获取最新图片)
	 * @param file
	 * @return
	 */
	
	public  Map<String,List<String>> func(File file,String paths,String filepaths) {
		Map<String,List<String>> fileList=new HashMap<String, List<String>>();
		File[] fs = file.listFiles();
		for (File file2 : fs) {
			if(!(file2.getName().endsWith("log"))){
				File[] fs2 = file2.listFiles();
				//遍历获取图片类型
				String type=file2.getName();
				List<Integer> date=new ArrayList<>();
				for (File file3 : fs2) {
					date.add(Integer.parseInt(file3.getName()));
				}
				Collections.sort(date);
				//获取本类型最大日期文件夹
				String maxdate=date.get(date.size()-1).toString();
				//获取最大日期中的文件
				File[] images = new File(file2.getPath()+"/"+maxdate).listFiles();
				List<String> typeList=new ArrayList<>();
				for (File image : images) {
					//剔除bak文件
					if(!(image.getName().contains("bak"))){
						typeList.add(paths+image.getPath().substring(filepaths.length()+1));
					}
				}
				Collections.sort(typeList);
				fileList.put(type, typeList);
			}
		}
		
		return fileList;
	}
}
