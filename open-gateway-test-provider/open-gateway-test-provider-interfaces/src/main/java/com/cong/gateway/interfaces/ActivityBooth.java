package com.cong.gateway.interfaces;

import com.alibaba.fastjson.JSON;
import com.cong.gateway.rpc.IActivityBooth;
import com.cong.gateway.rpc.dto.CReq;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class ActivityBooth implements IActivityBooth {

    @Override
    public String hello(String str) {
        return "hello " + str + " by open-gateway-provider";
    }

    @Override
    public String insert(CReq req) {
        return "hi there, " + JSON.toJSONString(req) + " by open-gateway-test-provider";
    }

    @Override
    public String test(String str, CReq req) {
        return "hello client, " + str + JSON.toJSONString(req) + " by open-gateway-test-provider";
    }

}
