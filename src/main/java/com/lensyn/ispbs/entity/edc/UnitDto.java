package com.lensyn.ispbs.entity.edc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("SITE_CODE")
    private String siteCode;
    @JsonProperty("NAME_ORG")
    private String nameOrg;
    @JsonProperty("LOAD_DATE")
    private String loadDate;
    @JsonProperty("VALUE")
    private String value;
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getNameOrg() {
		return nameOrg;
	}
	public void setNameOrg(String nameOrg) {
		this.nameOrg = nameOrg;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLoadDate() {
		return loadDate;
	}
	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
}
