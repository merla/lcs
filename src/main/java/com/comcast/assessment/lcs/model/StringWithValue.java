package com.comcast.assessment.lcs.model;

import java.io.Serializable;

public class StringWithValue implements Serializable {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
