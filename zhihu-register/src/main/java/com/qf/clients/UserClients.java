package com.qf.clients;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "zhihu-user",fallback = UserFallBack.class)
public interface UserClients {

}
