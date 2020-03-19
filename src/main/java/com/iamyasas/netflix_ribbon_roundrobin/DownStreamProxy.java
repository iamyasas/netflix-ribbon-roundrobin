package com.iamyasas.netflix_ribbon_roundrobin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.iamyasas.netflix_ribbon_roundrobin.loadbalancer.RibbonLoadBalancer;
import com.netflix.loadbalancer.Server;

public class DownStreamProxy {
	
	private static DownStreamProxy proxy;
	
	private DownStreamProxy() {}
	
	public static DownStreamProxy getInstance() {
		
		if (proxy == null) {
			synchronized (DownStreamProxy.class) {
				
				if (proxy == null) {
					proxy = new DownStreamProxy();
				}
			}
		}
		
		return proxy;
	}
	
	private Map<String, RibbonLoadBalancer> serviceToLoadBalancer = new ConcurrentHashMap<String, RibbonLoadBalancer>();
	
	public Server getServer(String service) {
		
		RibbonLoadBalancer loadBalancer = serviceToLoadBalancer.get(service);
		
		if (loadBalancer == null) {
			
			synchronized (RibbonLoadBalancer.class) {
				
				loadBalancer = serviceToLoadBalancer.get(service);
				
				if (loadBalancer == null) {
					
					loadBalancer = new RibbonLoadBalancer(service);
					serviceToLoadBalancer.put(service, loadBalancer);
					System.out.println("size of the map is : " + serviceToLoadBalancer.size());
				}
			}
			
		}
		
		return loadBalancer.nextServer();
	}
}
