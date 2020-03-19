package com.iamyasas.netflix_ribbon_roundrobin.configuration;

import com.netflix.archaius.ConfigProxyFactory;
import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.api.Config;
import com.netflix.archaius.api.exceptions.ConfigException;
import com.netflix.archaius.readers.PropertiesConfigReader;

public class ConfigurationProvider {
	
	public AppConfig appConfig;
	
	private static ConfigurationProvider configs;

	private ConfigurationProvider() throws ConfigException {
		
		//Config config = new PropertiesConfigReader().load
				//.load(null, "app", null, null);
		//ConfigProxyFactory proxyFactory = new ConfigProxyFactory(config, config.getDecoder(), new DefaultPropertyFactory(config.getPrefixedView("app")));
		//appConfig = proxyFactory.newProxy(AppConfig.class);
	}
	
	public static ConfigurationProvider getInstance() throws ConfigException {
		
		if (configs == null) {
			
			synchronized (ConfigurationProvider.class) {
				
				if (configs == null) {
					configs = new ConfigurationProvider();
				}
			}
		}
		
		return configs;
	}

}
