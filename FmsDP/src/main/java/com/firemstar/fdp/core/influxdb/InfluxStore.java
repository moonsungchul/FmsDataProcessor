package com.firemstar.fdp.core.influxdb;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfluxStore {
	
	private Logger logger = LoggerFactory.getLogger(InfluxStore.class);
	private String url;
	private String userName;
	private String password;
	private String dbname;
	private InfluxDB influx;
	
	public InfluxStore(String url, String user, String password, String dbname) {
		this.dbname = dbname;
		this.influx = InfluxDBFactory.connect(this.url, this.userName, this.password);
		this.influx.setDatabase(this.dbname);
	}
	
	
	public QueryResult query(String query) {
		return this.influx.query(new Query(query, this.dbname));
	}
	
	public void writeLog(InfluxLog log) {
		this.influx.write(Point.measurement("log")
				.time(System.currentTimeMillis(), TimeUnit.MICROSECONDS)
				.addField("type", log.getLogType())
				.addField("key", log.getLogKey())
				.addField("msg", log.getMsg())
				.build()
				);
	}
	
	
	
	
	
	
	

}
