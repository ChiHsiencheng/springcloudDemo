package filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @auther chihsien on 2021/5/29
 * 全局过滤 实现GlobalFilter, Ordered接口
 * @Component 注入
 * Mono ----- spring 5.0封装的模板代码 类似springmvc里的ModelAndView
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    /**
     * 用参数username过滤
     * @param: exchange
     * @param: chain
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @throws
     * @author ChiHsien<br>
     * @version <strong> </strong><br>
     */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        System.out.println("time:"+new Date()+"\t 执行了自定义的全局过滤器: "+"MyLogGateWayFilter"+"hello");
        //获取Request中的参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        //判断是否合法
        if (uname == null) {
            System.out.println("****用户名为null，无法登录");
            //回给响应 状态码：NOT_ACCEPTABLE 不被接受
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //响应告知错误在哪儿
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的顺序 数字越小优先级越高
     */
    
    @Override
    public int getOrder() {
        return 0;
    }
}
