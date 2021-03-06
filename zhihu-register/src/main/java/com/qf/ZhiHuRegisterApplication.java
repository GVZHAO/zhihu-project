package com.qf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
//标注当前工程开启feign远程调用
@EnableFeignClients
//标注当前项目启动hystrix的服务降级
@EnableHystrix
public class ZhiHuRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhiHuRegisterApplication.class);
    }
}
