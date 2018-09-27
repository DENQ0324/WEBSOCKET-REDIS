package com.lensyn.ispbs.service;

import java.util.List;

import com.lensyn.ispbs.entity.edc.PowerDto;
import com.lensyn.ispbs.entity.edc.UnitDto;

public interface EdcService {
	List<UnitDto> getUnitState();

	List<PowerDto> getUnitPower();
}
