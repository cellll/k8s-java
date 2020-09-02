package x.k8s.main;

import java.io.IOException;
import java.io.InputStreamReader;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PersistentVolume;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaim;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import x.k8s.manifestimpl.YamlPersistentVolumeClaimManifest;
import x.k8s.manifestimpl.YamlPersistentVolumeManifest;

public class main_createUserPV {
//
//	public static void main(String[] args) throws IOException, ApiException {
//		// TODO Auto-generated method stub
//		
//		ApiClient client = ClientBuilder
//				.kubeconfig(KubeConfig
//						.loadKubeConfig(new InputStreamReader(main_createPod.class.getResourceAsStream("/k8s/config"))))
//				.build();
//		client.setDebugging(true);
//		Configuration.setDefaultApiClient(client);
//		
//		CoreV1Api coreApi = new CoreV1Api();
//		AppsV1Api api = new AppsV1Api();
//		
//		String namespace = "default";
//		String storage = "10Gi";
//		String accessModes = "ReadWriteMany";
//		
//		/*  
//		 * NFS VOLUME
//		 */
//		String userName = "user1";
//		String name = String.format("%s-pv", userName);
//		String pvcName = String.format("%s-pvc", userName);
//		
//		String pvType = "nfs";
//		String nfsServerIp = "192.168.1.32";
//		String nfsPath = String.format("/root/Uyuni/users/%s", userName);
//		
//		YamlPersistentVolumeManifest manifest = new YamlPersistentVolumeManifest(name, storage, accessModes, pvcName, null, namespace, pvType, nfsServerIp, nfsPath, null, null);
//		
//		V1PersistentVolume pv = manifest.getPersistentVolume();
//		V1PersistentVolume deployedPv = coreApi.createPersistentVolume(pv, null, null, null);
//		System.out.println(deployedPv);
//		
//		YamlPersistentVolumeClaimManifest pvcManifest = new YamlPersistentVolumeClaimManifest(pvcName, accessModes, storage);
//		V1PersistentVolumeClaim pvc = pvcManifest.getPersistentVolumeClaim();
//		coreApi.createNamespacedPersistentVolumeClaim(namespace, pvc, null, null, null);
//		
//		/*
//		 * LOCAL VOLUME 
//		 */
//		
////		String hostName = "sol1-nvidia";
////		name = String.format("%s-ssh-pv", hostName);
////		pvcName = String.format("%s-ssh-pvc", hostName);
////		pvType = "local";
////		String localPath = "/root/.ssh";
////		String scName = "local-storage";
////		
////		YamlPersistentVolumeManifest sshManifest = new YamlPersistentVolumeManifest(name, storage, accessModes, pvcName, scName, namespace, pvType, null, null, localPath, hostName);
////		pv = sshManifest.getPersistentVolume();
////		deployedPv = coreApi.createPersistentVolume(pv, null, null, null);
////		System.out.println(deployedPv);
////		
////		pvcManifest = new YamlPersistentVolumeClaimManifest(pvcName, accessModes, storage);
////		pvc = pvcManifest.getPersistentVolumeClaim();
////		coreApi.createNamespacedPersistentVolumeClaim(namespace, pvc, null, null, null);
////		
//		
////		YamlPersistentVolumeClaimManifest pvcManifest = new YamlPersistentVolumeClaimManifest(pvcName, storage);
////		V1PersistentVolumeClaim pvc = pvcManifest.getPersistentVolumeClaim();
////		coreApi.createNamespacedPersistentVolumeClaim(namespace, pvc, null, null, null);
////		
//
//	}

}
