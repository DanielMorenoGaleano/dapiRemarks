package com.avianca.pagos.rest.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Assert Solutions S.A.S <info@assertsolutions.com> <br/>
 *         Date: 10/04/2018 9:04:30 a.m.
 *
 *
 */
@JsonAutoDetect
public class RequestDTO {

	@NotNull
	@NotEmpty
	@JsonProperty(value = "remarkType")
	private String remarkType;
	@NotNull
	@NotEmpty
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

	@Override
	public String toString() {
		RequestDTO dto = new RequestDTO();
		dto.setFreetext(freetext);
		dto.setRemarkType(remarkType);
		String jsonp = null;
		try {
			jsonp = "[" + new ObjectMapper().writeValueAsString(dto) + "]";
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonp;
	}

}
