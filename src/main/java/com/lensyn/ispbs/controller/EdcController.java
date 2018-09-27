package com.lensyn.ispbs.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lensyn.ispbs.entity.Response;
import com.lensyn.ispbs.entity.edc.PowerDto;
import com.lensyn.ispbs.entity.edc.UnitDto;
import com.lensyn.ispbs.service.EdcService;
@RestController
@RequestMapping(value = "/edc")
public class EdcController {
	
	@Autowired
	EdcService edcService;
	
	/**
     *** 机组状态
     * @param pointCode
     * @return
     */
    @RequestMapping(value = "/getUnitState", method = RequestMethod.POST)
    private Response<?> getUnitState() throws ParseException {
    	List<UnitDto> unitState = edcService.getUnitState();
    	Response success = new Response().success(unitState);
    	success.setType("unitState");
		return success;
    }
    
    /**
     *** 机组出力
     * @param pointCode
     * @return
     */
    @RequestMapping(value = "/getUnitPower", method = RequestMethod.POST)    
    private Response<?> getUnitPower() throws ParseException {
    	List<PowerDto> unitPower = edcService.getUnitPower();
    	Response success = new Response().success(unitPower);
    	success.setType("unitPower");
    	return success;
    }
    
}
