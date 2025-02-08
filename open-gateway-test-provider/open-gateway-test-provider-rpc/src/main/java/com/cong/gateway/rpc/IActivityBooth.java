package com.cong.gateway.rpc;

import com.cong.gateway.rpc.dto.CReq;

public interface IActivityBooth {

    String hello(String str);

    String insert(CReq req);

    String test(String str, CReq req);

}