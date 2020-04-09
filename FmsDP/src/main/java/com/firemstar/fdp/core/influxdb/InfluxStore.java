package com.firemstar.fdp.core.influxdb;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
	private InfluxDB influx;
	private String dbname;
	
	public InfluxStore(String host, String port, String user, String password, String dbname) {
		String url = "http://" + host + ":" + port;
		this.influx = InfluxDBFactory.connect(url, user, password);
		this.influx.setDatabase(dbname);
		this.dbname = dbname;
	}
	
	
	public QueryResult query(String query) {
		return this.influx.query(new Query(query, this.dbname));
	}
	
	public void writeLog(InfluxLog log) {
		this.influx.write(Point.measurement("log")
				.addField("type", log.getLogType())
				.addField("key", log.getLogKey())
				.addField("msg", log.getMsg())
				.build()
				);
	}
	

}
