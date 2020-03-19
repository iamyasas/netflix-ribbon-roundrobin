package com.iamyasas.netflix_ribbon_roundrobin.configuration;

import com.netflix.archaius.api.annotations.DefaultValue;

public interface AppConfig {
	 @DefaultValue("${app.ribbon.ServerListRefreshInterval}")
     int getServerListRefreshInterval();
}
