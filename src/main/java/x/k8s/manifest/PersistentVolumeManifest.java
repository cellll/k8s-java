package x.k8s.manifest;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1PersistentVolume;

public abstract class PersistentVolumeManifest {
	
	protected String name;
	protected String storage;
	protected String pvcName;
	protected String namespace;
	protected String nfsServerIp;
	protected String nfsPath;
	
	
	public PersistentVolumeManifest(String name, String storage, String pvcName, String namespace, String nfsServerIp, String nfsPath) {
		this.name = name;
		this.storage = storage;
		this.pvcName = pvcName;
		this.namespace = namespace;
		this.nfsServerIp = nfsServerIp;
		this.nfsPath = nfsPath;
		
		
	}
	
	public abstract V1PersistentVolume getPersistentVolume() throws IOException;
	
	


}
