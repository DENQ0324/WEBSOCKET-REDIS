package com.lensyn.ispbs.entity.edc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PowerDto implements Serializable {

	private static final long serialVersionUID = 2350190252561533538L;

	@JsonProperty("data_name")
	private String dataName;
	@JsonProperty("point_code")
	private String pointCode;
	@JsonProperty("data_time")
	private String dataTime;
	@JsonProperty("value")
	private String value;
	@JsonProperty("power_type")
	private String powerType;

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getPointCode() {
		return pointCode;
	}

	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
