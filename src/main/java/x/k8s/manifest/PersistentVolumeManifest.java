package x.k8s.manifest;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1PersistentVolume;

public abstract class PersistentVolumeManifest {
	
	protected String pvName;
	protected String storage;
	protected String accessModes;
	protected String pvcName;
	protected String scName;
	protected String namespace;
	protected String pvType;
	protected String nfsServerIp;
	protected String nfsPath;
	protected String localPath;
	protected String hostName;
	
	
	
	public PersistentVolumeManifest(String pvName, String storage, String accessModes, String pvcName, String scName, String namespace,
			String pvType, String nfsServerIp, String nfsPath, String localPath, String hostName) {
		this.pvName = pvName;
		this.storage = storage;
		this.accessModes = accessModes;
		this.pvcName = pvcName;
		this.scName = scName;
		this.namespace = namespace;
		this.pvType = pvType;
		this.nfsServerIp = nfsServerIp;
		this.nfsPath = nfsPath;
		this.localPath = localPath;
		this.hostName = hostName;
		
		
	}
	
	public abstract V1PersistentVolume getPersistentVolume() throws IOException;
	
	


}
