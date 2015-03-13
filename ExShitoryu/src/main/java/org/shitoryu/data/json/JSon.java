package org.shitoryu.data.json;

import java.util.List;
import java.util.Map;

import org.shitoryu.data.dto.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author SUCCESS\tungo
 * @date Oct 30, 2014 11:55:17 AM
 */
public class JSon<T extends DTO> {

	public enum JSonResult {
		OK, ERROR
	}

	@JsonProperty("Result")
	private String result;

	@JsonProperty("source")
	private String source;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("Record")
	private T record;

	@JsonProperty("Records")
	private List<T> records;

	@JsonProperty("Options")
	private List<Map<String, Object>> options;

	public JSon() {
	}

	public JSon(JSonResult result) {
		this.result = result.name();
	}

	public JSon(JSonResult result, String message) {
		this.result = result.name();
		this.message = message;
	}

	public JSon(JSonResult result, T record) {
		this.result = result.name();
		this.record = record;
	}

	public JSon(JSonResult result, List<T> records) {
		this.result = result.name();
		this.records = records;
	}

	public JSon(String result, String source, List<Map<String, Object>> options) {
		this.result = result;
		this.source = source;
		this.options = options;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public List<Map<String, Object>> getOptions() {
		return options;
	}

	public void setOptions(List<Map<String, Object>> options) {
		this.options = options;
	}

}
