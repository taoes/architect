package com.zhoutao123.article.dubbo.consumer;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.service.EchoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class EchoController {

    /**
     * <ul>
     * <li>配置重试次数: retries</>
     * <li>配置负载均衡: load balance {@link com.alibaba.dubbo.rpc.cluster.LoadBalance} </>
     * <li>配置超时时间: timeout</>
     * </>
     */
    @Reference()
    private EchoService echoService;


    @GetMapping("/ping")
    private Object ping() {
        ExtensionLoader.getExtensionLoader(String.class).getAdaptiveExtension();
        return echoService.$echo("OK!!!");
    }

}
