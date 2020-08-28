package x.k8s.manifest;

import java.io.IOException;
import java.util.List;

import io.kubernetes.client.openapi.models.V1Pod;
import x.k8s.prometheus.models.K8sVolumeVO;

public abstract class PodManifest {
	
	protected String podName;
	protected String labelName;
	protected String specificGpu;
	protected String gpuCount;
	protected String imageName;
	protected String nodeName;
	protected List<K8sVolumeVO> volumeList;
	protected boolean isMultiNode;
	protected String horovodPort;
	

	public PodManifest(String podName, String labelName, String specificGpu, String gpuCount, String imageName, String nodeName, List<K8sVolumeVO> volumeList, boolean isMultiNode, String horovodPort) {
		// TODO Auto-generated constructor stub
		this.podName = podName;
		this.labelName = labelName;
		this.specificGpu = specificGpu;
		this.gpuCount = gpuCount;
		this.imageName = imageName;
		this.nodeName = nodeName;
		this.volumeList = volumeList;
		this.isMultiNode = isMultiNode;
		this.horovodPort = horovodPort;
	}
	
	public abstract V1Pod getPod() throws IOException;

}
