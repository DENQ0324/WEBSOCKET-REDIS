package com.lensyn.ispbs.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lensyn.ispbs.entity.edc.PowerDto;
import com.lensyn.ispbs.entity.edc.UnitDto;
import com.lensyn.ispbs.service.EdcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EdcServiceImpl implements EdcService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper jsonMapper;

	// 机组状态
	private String wdsReal = "http://10.176.156.136:8081/interface/api/water/wdsReal/1.0";
	private String address = "http://10.176.156.136:8081/interface/api/power/lineGenPowerHour/1.0";

	@Override
	public List<UnitDto> getUnitState() {

		List<UnitDto> unitList = new ArrayList<>();
		String[] pointCode = { "WSPDPJZD1F003103", "WSPDPJZD2F003103", "WSPDPJZD3F003103", "WSPDPJZD4F003103",
				"WSPDPJZD5F003103", "WSPDPJZD6F003103", "WSPDSJZD1F003103", "WSPDSJZD2F003103", "WSPDSJZD3F003103",
				"WSPDSJZD4F003103", "WSZDYJZD1F003103", "WSZDYJZD2F003103", "WSZDYJZD3F003103", "WSZDYJZD4F003103" };

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "aaaaa");
		for (String code : pointCode) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pointCode", code);
			String str = "";
			try {
				str = jsonMapper.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			HttpEntity<String> formEntity = new HttpEntity<String>(str, headers);
			String result = restTemplate.postForObject(wdsReal, formEntity, String.class);
			try {
				JsonNode path = jsonMapper.readTree(result).path("data");
				String string = path.toString().replace("[", "").replace("]", "");
				UnitDto unitDto = jsonMapper.readValue(string, UnitDto.class);
				unitList.add(unitDto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return unitList;
	}

	@Override
	public List<PowerDto> getUnitPower() {
		List<PowerDto> unitList = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = sdf.format(date);
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		calender.set(Calendar.HOUR, calender.get(Calendar.HOUR) - 3);
		calender.get(Calendar.DATE);
		String startTime = sdf.format(calender.getTime());
		String[] stationCode = { "PDP", "PDS", "ZDY" };

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "aaaaa");
		for (String code : stationCode) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("stationCode", code);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			String str = "";
			try {
				str = jsonMapper.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			HttpEntity<String> formEntity = new HttpEntity<String>(str, headers);
			String result = restTemplate.postForObject(address, formEntity, String.class);
			try {
				JsonNode path = jsonMapper.readTree(result).path("data");
				String string = path.toString();
				List<PowerDto> powerDto = jsonMapper.readValue(string, List.class);
				unitList.addAll(powerDto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return unitList;
	}
}
