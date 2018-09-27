package com.lensyn.ispbs.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lensyn.ispbs.entity.rain.RainFallData;
import com.lensyn.ispbs.service.RainService;
import com.lensyn.ispbs.service.RedisService;
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
	@Autowired
	ObjectMapper mapper;
	@Autowired
	RainService rainService;
	
	@Autowired
    private RedisService redisService;
	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public void setString() {
        redisService.set("redis_string_test", new Date().toString());
    }
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getString() {
        String result = redisService.get("redis_string_test");
        return result;
    }
	@RequestMapping(value = "/setobj", method = RequestMethod.GET)
	public void setObject() throws JsonProcessingException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("areaid", "2");
		List<RainFallData> rainFallListSevenDay = rainService.getRainFallListSevenDay(map);
        redisService.set("redis_obj_test2", mapper.writeValueAsString(rainFallListSevenDay));
    }
	@RequestMapping(value = "/getobj", method = RequestMethod.GET)
	public List<RainFallData> getObject() throws JsonParseException, JsonMappingException, IOException {
        String result = redisService.get("redis_obj_test");
        String result2 = redisService.get("redis_obj_test2");
		List<RainFallData> rainFallDataList = mapper.readValue(result, List.class);
		List<RainFallData> rainFallDataList2 = mapper.readValue(result2, List.class);
		boolean equals = rainFallDataList.equals(rainFallDataList2);
		System.err.println(equals);
        return rainFallDataList;
    }
}
