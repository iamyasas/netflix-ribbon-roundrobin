package com.iamyasas.netflix_ribbon_roundrobin.loadbalancer;

import com.iamyasas.netflix_ribbon_roundrobin.configuration.AppConfig;
import com.iamyasas.netflix_ribbon_roundrobin.configuration.ConfigurationProvider;
import com.netflix.archaius.api.ConfigReader;
import com.netflix.archaius.readers.PropertiesConfigReader;
import com.netflix.archaius.readers.URLConfigReader;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.PollingServerListUpdater;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListUpdater;

public class RibbonLoadBalancer {

	private ILoadBalancer loadBalancer;

	public RibbonLoadBalancer(String service) {
		
		//AppConfig config = ConfigurationProvider.getInstance().appConfig;
		//System.out.println(config.getServerListRefreshInterval());
		
		ServerList<Server> serverList = new RibbonServerListConfig(service);
		
		ServerListUpdater updater = new PollingServerListUpdater(0, 10000);
		
		ConfigReader reader = new PropertiesConfigReader();
		//reader.
		
		loadBalancer = LoadBalancerBuilder.newBuilder()
				.withRule(new RoundRobinRule())
				.withDynamicServerList(serverList)
				//.withServerListUpdater(updater)
				.buildDynamicServerListLoadBalancerWithUpdater();
	}
	
	public Server nextServer() {
		return loadBalancer.chooseServer(null);
	}

}
