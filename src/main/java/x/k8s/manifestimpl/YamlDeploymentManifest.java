package x.k8s.manifestimpl;

import java.io.IOException;
import java.util.List;

import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.util.Yaml;
import x.k8s.manifest.DeploymentManifest;
import x.k8s.prometheus.models.K8sVolumeVO;

public class YamlDeploymentManifest extends DeploymentManifest{
	private YamlManifestMaker manifestMaker = new YamlManifestMaker();

    public YamlDeploymentManifest(String name, String labelName, String imageName, String gpuCount, String specificGpu, String nodeName, List<K8sVolumeVO> volumeList) {
        super(name, labelName, imageName, gpuCount, specificGpu, nodeName, volumeList);
    }
    

	@Override
    public V1Deployment getDeployment() throws IOException {
        String deploymentManifest = manifestMaker.makeGpuDeploymentManifest(
                name, labelName, imageName, gpuCount, specificGpu, nodeName, volumeList);
        return (V1Deployment) Yaml.load(deploymentManifest);
    }
}
