package x.k8s.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.kubernetes.client.Exec;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1DeleteOptions;
import io.kubernetes.client.openapi.models.V1DeleteOptionsBuilder;
import io.kubernetes.client.openapi.models.V1Node;
import io.kubernetes.client.openapi.models.V1NodeAddress;
import io.kubernetes.client.openapi.models.V1NodeList;
import io.kubernetes.client.openapi.models.V1NodeStatus;
import io.kubernetes.client.openapi.models.V1PersistentVolume;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaim;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import x.k8s.manifestimpl.YamlPersistentVolumeClaimManifest;
import x.k8s.manifestimpl.YamlPersistentVolumeManifest;
import x.k8s.manifestimpl.YamlPodManifest;
import x.k8s.prometheus.models.K8sVolumeVO;
import x.util.ServerVO;
import x.util.SshClient;

public class main_createNodeSSHPV {

	public static void main(String[] args) throws IOException, ApiException {
		// TODO Auto-generated method stub
		
		ApiClient client = ClientBuilder
				.kubeconfig(KubeConfig
						.loadKubeConfig(new InputStreamReader(main_createNodeSSHPV.class.getResourceAsStream("/k8s/config"))))
				.build();
		client.setDebugging(true);
		Configuration.setDefaultApiClient(client);
		
		CoreV1Api coreApi = new CoreV1Api();
		AppsV1Api api = new AppsV1Api();
		
		
		V1NodeList v1NodeList = coreApi.listNode("true", null, null, null, null, null, null, 60, false);
		List<V1Node> nodeList = v1NodeList.getItems();
		
		String sshPodLabelName = String.format("register-ssh-key");
		String namespace = "default";
		
		for (V1Node node : nodeList) {
			
			V1NodeStatus nodeStatus = node.getStatus();
			List<V1NodeAddress> nodeAddressList = nodeStatus.getAddresses();
			
			String ipAddress = "";
			String hostName = "";
			
			for (V1NodeAddress nodeAddress : nodeAddressList) {
				
				String addressType = nodeAddress.getType();
				
				if (addressType.equals("InternalIP")) {
					ipAddress = nodeAddress.getAddress();

				} else if (addressType.equals("Hostname")) {
					hostName = nodeAddress.getAddress();

				} else {

				}
			}
			
			/*
			 * CREATE NODE SSH PV, PVC 
			 */
			String pvName = String.format("%s-ssh-pv", hostName);
			String storage = "100Mi";
			String accessModes = "ReadWriteMany";
			
			String pvcName = String.format("%s-ssh-pvc", hostName);
			String scName = "local-storage";
			String pvType = "local";
			String localPath = "/root/.ssh";
			
			YamlPersistentVolumeManifest manifest = new YamlPersistentVolumeManifest(pvName, storage, accessModes, pvcName, scName, namespace, pvType, null, null, localPath, hostName);
			V1PersistentVolume pv = manifest.getPersistentVolume();
//			coreApi.createPersistentVolume(pv, null, null, null);
			
			
			YamlPersistentVolumeClaimManifest pvcManifest = new YamlPersistentVolumeClaimManifest(pvcName, accessModes, storage);
			V1PersistentVolumeClaim pvc = pvcManifest.getPersistentVolumeClaim();
//			coreApi.createNamespacedPersistentVolumeClaim(namespace, pvc, null, null, null);
			
			/*
			 * CREATE POD : SSH KEY 
			 */
			
			List<K8sVolumeVO> volumeList = new ArrayList<K8sVolumeVO>();
			K8sVolumeVO sshVolume = new K8sVolumeVO();
			sshVolume.setVolumeName("ssh-volume");
			sshVolume.setVolumeMountPath("/root/.ssh");
			sshVolume.setReadOnly("false");
			sshVolume.setClaimName(String.format("%s-ssh-pvc", hostName));
			volumeList.add(sshVolume);
			
			
			String podName = String.format("register-ssh-pod-%s", hostName);
			String specificGpu = "0";
			String gpuCount = "1";
			String imageName = "xiilab/nvidia";
			
			String nodeName = hostName;
			boolean isMultiNode = true;
			
			YamlPodManifest podManifest = new YamlPodManifest(podName, sshPodLabelName, specificGpu, gpuCount, imageName, nodeName, volumeList, isMultiNode, null);
			V1Pod pod = podManifest.getPod();
			
			coreApi.createNamespacedPod(namespace, pod, null, null, null);
			
		}
		
		// pod 배포 대기 확인해야함 
		
		/*
		 * KUBECTL EXEC : ssh-keygen -t rsa -N '' -f ~/.ssh/id_rsa <<< y
		 * yes y | ssh-keygen -t rsa -N "" -f ~/.ssh/id_rsa
		 * ssh -> master
		 */
		SshClient sshClient = new SshClient();
		ServerVO serverVO = new ServerVO("192.168.1.32", "sol1-nvidia", "root", "xiirocks", 22);
		
		String sshKeygenCmd = "yes y | ssh-keygen -t rsa -N \"\" -f ~/.ssh/id_rsa";
		String getSshPublicKeyCmd = "cat /root/.ssh/id_rsa.pub";
		String putSshPublicKeyCmd = "echo %s >> /root/.ssh/authorized_keys";			
		
		String kubectlExecCmd = "kubectl exec %s -- bash -c '%s'";
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		V1PodList v1podList = coreApi.listPodForAllNamespaces(null, null, null, 
				String.format("app = %s", sshPodLabelName), null, null, null, null, null);
		List<V1Pod> podList = v1podList.getItems();
	
		
		for (V1Pod pod : podList) {
			System.out.println("===");
			String podName = pod.getMetadata().getName();
			System.out.println(podName);
			
			Map<String, Object> keygenResultMap = sshClient.execjsch(serverVO, String.format(kubectlExecCmd, podName, sshKeygenCmd));
			Map<String, Object> getSshPubKeyResult = sshClient.execjsch(serverVO, String.format(kubectlExecCmd, podName, getSshPublicKeyCmd));
			String sshKey = ((String) getSshPubKeyResult.get("log")).replace("\n", "");
			
			String putCmd = String.format(putSshPublicKeyCmd, sshKey);
			
			for (V1Pod targetPod : podList) {
				String targetPodName = targetPod.getMetadata().getName();
				String cmd = String.format(kubectlExecCmd, targetPodName, putCmd);
				
				Map<String, Object> putSshKeyResult = sshClient.execjsch(serverVO, cmd);
			}
		}
		
		// 완료 확인해야함
		coreApi.deleteCollectionNamespacedPod(namespace, null, null, null, null, null, String.format("app = %s", sshPodLabelName), null, null, null, null, null, null);
				
	}
}
