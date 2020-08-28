package x.k8s.manifestimpl;

import java.io.IOException;
import java.util.List;

import io.kubernetes.client.openapi.models.V1Service;
import io.kubernetes.client.util.Yaml;
import x.k8s.manifest.ServiceManifest;
import x.k8s.prometheus.models.K8sPortVO;

public class YamlServiceManifest extends ServiceManifest {
	private YamlManifestMaker manifestMaker = new YamlManifestMaker();

    public YamlServiceManifest(String serviceName, String labelName, List<K8sPortVO> ports) {
        super(serviceName, labelName, ports);
    }

    @Override
    public V1Service getServiceManifest() throws IOException {
        String serviceManifest = manifestMaker.makeGpuServiceManifest(
                serviceName, labelName, ports);
        return (V1Service) Yaml.load(serviceManifest);
    }
}
