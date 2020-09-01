package x.k8s.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import x.k8s.manifest.PodManifest;
import x.k8s.manifestimpl.YamlPodManifest;
import x.k8s.prometheus.models.K8sVolumeVO;

public class main_createPod {

//	public static void main(String[] args) throws IOException, ApiException {
//		// TODO Auto-generated method stub
//		ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new InputStreamReader(main_createPod.class.getResourceAsStream("/k8s/config")))).build();
//		client.setDebugging(true);
//		Configuration.setDefaultApiClient(client);
//		
//		CoreV1Api api = new CoreV1Api();
//		
//		
//		List<K8sVolumeVO> volumeList = new ArrayList<K8sVolumeVO>();
//		
//		K8sVolumeVO user1Volume = new K8sVolumeVO();
//		user1Volume.setVolumeMountPath("/root/user_data");
//		user1Volume.setVolumeName("user1-volume");
//		user1Volume.setReadOnly("false");
//		user1Volume.setClaimName("user1-pvc");
//		volumeList.add(user1Volume);
//		
//		K8sVolumeVO user2Volume = new K8sVolumeVO();
//		user2Volume.setVolumeMountPath("/root/user_data/ref/d21");
//		user2Volume.setSubPath("datasets/d21");
//		user2Volume.setVolumeName("user2-volume");
//		user2Volume.setReadOnly("true");
//		user2Volume.setClaimName("user2-pvc");
//		volumeList.add(user2Volume);
//		
//		String namespace = "default";
//		String podName = "hi1";
//		String labelName = "hi1";
//		String specificGpu = "0";
//		String gpuCount = "1";
//		String imageName = "xiilab/nvidia";
//		String nodeName = "xiilab-gpunode2";
//		boolean isMultiNode = false;
//		
//		
//		YamlPodManifest manifest = new YamlPodManifest(podName, labelName, specificGpu, gpuCount, imageName, nodeName, volumeList, isMultiNode, null);
//		V1Pod pod = manifest.getPod();
//				
//		api.createNamespacedPod("default", pod, null, null, null);
//		
//		
//		
//		
//		
//		
//		
//		
//		
//
//	}

}
