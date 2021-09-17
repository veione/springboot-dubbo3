package com.example.demo.router;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcContextAttachment;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.router.AbstractRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义路由规则(根据服务器ID进行消息调用转发)
 *
 * @author veione
 */
public class CustomRouter extends AbstractRouter {

    public CustomRouter(URL url) {
        super(url);
    }

    @Override
    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        List<Invoker<T>> bestServers = new ArrayList<>();
        RpcContextAttachment attachment = RpcContext.getClientAttachment();
        String sid = attachment.getAttachment("sid");
        if (StringUtils.isEmpty(sid)) {
            throw new IllegalArgumentException("Missing required parameter sid");
        }
        for (Invoker<T> invoker : invokers) {
            String clusterId = invoker.getUrl().getParameter("REGISTRY_CLUSTER");
            if (clusterId.contains(sid)) {
                bestServers.add(invoker);
            }
        }
        return bestServers;
    }
}
