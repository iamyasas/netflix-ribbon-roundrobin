package com.iamyasas.netflix_ribbon_roundrobin.loadbalancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

public class RibbonServerListConfig implements ServerList<Server> {
	
	Logger logger = Logger.getAnonymousLogger();
	
	private String service;

	public RibbonServerListConfig(String service) {
		this.service = service;
	}

	public List<Server> getInitialListOfServers() {
		
		return new ArrayList<Server>();
	}

	public List<Server> getUpdatedListOfServers() {
		
		logger.log(Level.INFO, "Getting updated list of servers for service " + service + " at " + new Date().toString());;
		
		return Arrays.asList(
			new Server(service.concat("-node1"), 80), 
			new Server(service.concat("-node2"), 80),
			new Server(service.concat("-node3"), 80)
		);
	}
	
}
