package x.k8s.manifest;

import java.io.IOException;
import java.util.List;

import io.kubernetes.client.openapi.models.V1Service;
import x.k8s.prometheus.models.K8sPortVO;


public abstract class ServiceManifest {

    protected String serviceName;
    protected String labelName;
    protected List<K8sPortVO> ports;

    public ServiceManifest(String serviceName, String labelName, List<K8sPortVO> ports) {
        this.serviceName = serviceName;
        this.labelName = labelName;
        this.ports = ports;
    }

    public abstract V1Service getServiceManifest() throws IOException;
}
