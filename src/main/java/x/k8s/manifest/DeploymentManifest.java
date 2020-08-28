package x.k8s.manifest;

import java.io.IOException;
import java.util.List;

import io.kubernetes.client.openapi.models.V1Deployment;
import x.k8s.prometheus.models.K8sVolumeVO;

public abstract class DeploymentManifest {
    protected String name;
    protected String labelName;
    protected String gpuCount;
    protected String imageName;
    protected String specificGpu;
    protected String nodeName; 
    protected List<K8sVolumeVO> volumeList;

    public DeploymentManifest(String name, String labelName, String imageName, String gpuCount, String specificGpu, String nodeName, List<K8sVolumeVO> volumeList) {
        this.name = name;
        this.labelName = labelName;
        this.gpuCount = gpuCount;
        this.imageName = imageName;
        this.specificGpu = specificGpu;
        this.nodeName = nodeName;
        this.volumeList = volumeList;
    }


	public abstract V1Deployment getDeployment() throws IOException;

}
