package com.firemstar.fdp.core.influxdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firemstar.fdp.AppProperies;

@Component
public class InfluxLoggerCM {
	
	private AppProperies property;
	private InfluxLogger logger;

	@Autowired
	public InfluxLoggerCM(AppProperies property) {
		this.property = property;
		logger = new InfluxLogger(property.getInfluxdb().getHost(), 
				property.getInfluxdb().getPort(), 
				property.getInfluxdb().getUser(), 
				property.getInfluxdb().getPassword(), 
				property.getInfluxdb().getDbname());
	}

	public InfluxLogger getLogger() {
		return logger;
	}

	public void setLogger(InfluxLogger logger) {
		this.logger = logger;
	}

}
