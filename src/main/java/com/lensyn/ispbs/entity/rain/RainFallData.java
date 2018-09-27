package com.lensyn.ispbs.entity.rain;

public class RainFallData {

	private String areaId;
	private String infoTime;
	private String rainFallData;
	private String type;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getInfoTime() {
		return infoTime;
	}

	public void setInfoTime(String infoTime) {
		this.infoTime = infoTime;
	}

	public String getRainFallData() {
		return rainFallData;
	}

	public void setRainFallData(String rainFallData) {
		this.rainFallData = rainFallData;
	}

	
	 /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((infoTime == null) ? 0 : infoTime.hashCode());
		result = prime * result + ((rainFallData == null) ? 0 : rainFallData.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	
	 /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RainFallData other = (RainFallData) obj;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (infoTime == null) {
			if (other.infoTime != null)
				return false;
		} else if (!infoTime.equals(other.infoTime))
			return false;
		if (rainFallData == null) {
			if (other.rainFallData != null)
				return false;
		} else if (!rainFallData.equals(other.rainFallData))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
}