package x.k8s.manifest;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1PersistentVolume;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaim;

public abstract class PersistentVolumeClaimManifest {
	
	protected String pvcName;
	protected String storage;
	
	
	public PersistentVolumeClaimManifest(String pvcName, String storage) {
		
		this.pvcName = pvcName;
		this.storage = storage;
		
	}
	
	public abstract V1PersistentVolumeClaim getPersistentVolumeClaim() throws IOException;
	
	


}
