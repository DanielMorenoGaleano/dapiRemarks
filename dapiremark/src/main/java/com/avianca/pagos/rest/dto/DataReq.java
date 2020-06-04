package com.avianca.pagos.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

//@JsonAutoDetect
public class DataReq {

	@NotNull
	@JsonProperty(value = "remarkType")
	private String remarkType;
	@NotNull
	@JsonProperty(value = "freetext")
	private String freetext;

	public String getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(String remarkType) {
		this.remarkType = remarkType;
	}

	public String getFreetext() {
		return freetext;
	}

	public void setFreetext(String freetext) {
		this.freetext = freetext;
	}

	
}
