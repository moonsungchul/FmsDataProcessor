package com.firemstar.fdp.core.influxdb;

public class InfluxLogger {
	
	private InfluxStore store = null;
	
	public InfluxLogger(String host, String port, String user, String password, String dbname) {
		this.store = new InfluxStore(host, port, user, password, dbname);
	}
	
	public void info(String key, String msg) {
		store.writeLog(new InfluxLog("INFO", key, msg));
	}
	
	public void warning(String key, String msg) {
		store.writeLog(new InfluxLog("WARN", key, msg));
	}
	
	public void error(String key, String msg) {
		store.writeLog(new InfluxLog("ERROR", key, msg));
	}
	
	public void debug(String key, String msg) {
		store.writeLog(new InfluxLog("DEBUG", key, msg));
	}
}
