package com.chihsien.springcloud;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther chihsien on 2021/5/29
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //访问9527/guonei将会转发到 http://news.baidu.com/guonei
        routes.route("path_routr_chihsien", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return  routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //访问9527/guonei将会转发到 http://news.baidu.com/guonei
        routes.route("path_routr_chihsien2", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return  routes.build();
    }

}
