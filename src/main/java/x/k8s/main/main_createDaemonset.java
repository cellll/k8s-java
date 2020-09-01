package x.k8s.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ContainerState;
import io.kubernetes.client.openapi.models.V1ContainerStateTerminated;
import io.kubernetes.client.openapi.models.V1ContainerStatus;
import io.kubernetes.client.openapi.models.V1DaemonSet;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.openapi.models.V1Status;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import x.k8s.manifestimpl.YamlDaemonSetManifest;

public class main_createDaemonset {
//
//	public static void main(String[] args) throws IOException, ApiException {
//		// TODO Auto-generated method stub
//
//		ApiClient client = ClientBuilder
//				.kubeconfig(KubeConfig
//						.loadKubeConfig(new InputStreamReader(main_createPod.class.getResourceAsStream("/k8s/config"))))
//				.build();
////		client.setDebugging(true);
//		Configuration.setDefaultApiClient(client);
//
//		CoreV1Api coreApi = new CoreV1Api();
//		AppsV1Api api = new AppsV1Api();
//
//		// 1. daemonset 생성
//
//		String namespace = "default";
//		String name = "test-pre-pull";
//		String labelName = "test-pre-pull";
//		String imageName = "tensorflow/tensorflow";
//		String imageTag = "latest";
//
//		YamlDaemonSetManifest manifest = new YamlDaemonSetManifest(name, labelName, imageName, imageTag);
//		V1DaemonSet daemonSet = manifest.getDaemonSet();
//		daemonSet = api.createNamespacedDaemonSet(namespace, daemonSet, null, null, null);
//
//		System.out.println("Daemonset deployed");
//
//		// 2. 생성, pull 완료 확인
//
//		String labelSelector = "name = test-pre-pull";
//		V1PodList podList = new V1PodList();
////		V1Pod pod = new V1Pod();
//		List<V1ContainerStatus> containerStatusList = new ArrayList<V1ContainerStatus>();
//
//		V1ContainerStatus containerStatus = new V1ContainerStatus();
//		V1ContainerState containerState = new V1ContainerState();
//		V1ContainerStateTerminated terminated = new V1ContainerStateTerminated();
//
//		boolean isPulled = false;
//		boolean isPodDeployed = false;
//		boolean isTerminated = true;
//
//		while (!isPulled) {
//
//			try {
//				// 2-1. pod 배포 확인
//				podList = coreApi.listNamespacedPod(namespace, null, null, null, null, labelSelector, null, null, null,
//						null);
//
//				if (!isPodDeployed && podList.getItems().size() > 0) {
//					System.out.println("Pod deployed");
//					isPodDeployed = true;
//				}
//				
//				
//				// 2-2. initContainer 생성 확인
//				if (isPodDeployed) {
//					
//					
//					isTerminated = true;	
//					
//					for (V1Pod pod : podList.getItems()) {
//						
//						if (!isTerminated) {
//							continue;
//						}
//						
//						containerStatusList = pod.getStatus().getInitContainerStatuses();
//						
//						if (containerStatusList != null) {
//							if (containerStatusList.size() > 0) {
//								
//								System.out.println("Pulling image...");
//								
//								containerStatus = containerStatusList.get(0);
//								containerState = containerStatus.getState();
//								terminated = containerState.getTerminated();
//								
//								if (terminated == null) {
//									isTerminated = false;
//								} else {
//									if (!"Completed".equals(terminated.getReason().trim())){
//										isTerminated = false;
//									}
//								}
//							}
//						} else {
//							System.out.println("Creating init containers...");
//						}
//					}
//					
//					if(isTerminated) {
//						isPulled = true;
//					}
//				}
//				
//				Thread.sleep(1000);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//
//			}
//		}
//		
//		System.out.println("Pull image completed");
//		
//		
//		// 3. delete daemonset 
//		V1Status deleteDaemonsetStatus = api.deleteNamespacedDaemonSet(labelName, namespace, null, null, null, null, null, null);
//		
//		if ("Success".equals(deleteDaemonsetStatus.getStatus().trim())){
//			System.out.println("Daemonset deleted");
//		}
//	}

}
