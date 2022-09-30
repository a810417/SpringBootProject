package com.ispan.springbootdemo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageDto implements Serializable {

	// 序列化 -> 系統預設產生
	// 幫 Json 轉成 Byte 型別
	// 若要從 Json 轉成 JAVA 物件就要進行"反序列化"
	private static final long serialVersionUID = 1L;

	@JsonProperty("text")
	private String inputText;

	public MessageDto() {
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
}
