package com.firemstar.fdp.core.influxdb;

public class InfluxLog {
	private String logType;
	private String logKey;
	private String msg;
	
	public InfluxLog() {
		logType = "";
		logKey = "";
		msg = "";
	}
	
	public InfluxLog(String ltype, String lkey, String msg) {
		this.logType = ltype;
		this.logKey = lkey;
		this.msg = msg;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "InfluxLogger [logType=" + logType + ", logKey=" + logKey + ", msg=" + msg + "]";
	}
	
	

}
