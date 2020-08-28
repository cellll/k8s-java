package x.k8s.manifestimpl;

import java.io.IOException;
import java.util.List;

import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.util.Yaml;
import x.k8s.manifest.PodManifest;
import x.k8s.prometheus.models.K8sVolumeVO;

public class YamlPodManifest extends PodManifest{
	
	private YamlManifestMaker manifestMaker = new YamlManifestMaker();
	
	public YamlPodManifest(String podName, String labelName, String specificGpu, String gpuCount, String imageName, String nodeName, List<K8sVolumeVO> volumeList, boolean isMultiNode, String horovodPort) {
		// TODO Auto-generated constructor stub
		super(podName, labelName, specificGpu, gpuCount, imageName, nodeName, volumeList, isMultiNode, horovodPort);
	}
	
	
	@Override
	public V1Pod getPod() throws IOException {
		// TODO Auto-generated method stub
		String podManifest = manifestMaker.makePodManifest(podName, labelName, specificGpu, gpuCount, imageName, nodeName, volumeList, isMultiNode, horovodPort);
		return (V1Pod) Yaml.load(podManifest);
	}
	

}
