package com.example.demo.router;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;
import org.apache.dubbo.rpc.cluster.CacheableRouterFactory;
import org.apache.dubbo.rpc.cluster.Router;

@SPI
public class CustomRouterFactory extends CacheableRouterFactory {

    @Override
    protected Router createRouter(URL url) {
        return new CustomRouter(url);
    }
}
