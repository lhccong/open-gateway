package com.cong.gateway.session;

import com.cong.gateway.bind.GenericReferenceRegistry;
import com.cong.gateway.bind.IGenericReference;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;


/**
 * 会话生命周期配置项
 *
 * @author cong
 * @date 2025/02/07
 */
public class Configuration {

    private final GenericReferenceRegistry registry = new GenericReferenceRegistry(this);

    // RPC 应用服务配置项 open-gateway-test
    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    // RPC 注册中心配置项 zookeeper://127.0.0.1:2181
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    // RPC 泛化服务配置项 com.cong.gateway.rpc.IActivityBooth
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();

    public Configuration() {
        // TODO 后期从配置中获取
        ApplicationConfig application = new ApplicationConfig();
        application.setName("open-gateway-test");
        application.setQosEnable(false);

        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://127.0.0.1:2181");
        registry.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("com.cong.gateway.rpc.IActivityBooth");
        reference.setVersion("1.0.0");
        reference.setGeneric("true");

        applicationConfigMap.put("open-gateway-test", application);
        registryConfigMap.put("open-gateway-test", registry);
        referenceConfigMap.put("com.cong.gateway.rpc.IActivityBooth", reference);
    }

    public ApplicationConfig getApplicationConfig(String applicationName) {
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName) {
        return registryConfigMap.get(applicationName);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName) {
        return referenceConfigMap.get(interfaceName);
    }

    public void addGenericReference(String application, String interfaceName, String methodName) {
        registry.registerGenericReference(application, interfaceName, methodName);
    }

    public IGenericReference getGenericReference(String methodName) {
        return registry.getGenericReference(methodName);
    }

}
