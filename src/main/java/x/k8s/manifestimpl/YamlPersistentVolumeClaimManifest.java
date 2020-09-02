package x.k8s.manifestimpl;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1PersistentVolume;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaim;
import io.kubernetes.client.util.Yaml;
import x.k8s.manifest.PersistentVolumeClaimManifest;

public class YamlPersistentVolumeClaimManifest extends PersistentVolumeClaimManifest{
	
	private YamlManifestMaker manifestMaker = new YamlManifestMaker();
	
	public YamlPersistentVolumeClaimManifest(String pvcName, String accessModes, String storage) {
		// TODO Auto-generated constructor stub
		super(pvcName, accessModes, storage);
	}
	
	@Override
	public V1PersistentVolumeClaim getPersistentVolumeClaim() throws IOException {
		// TODO Auto-generated method stub
		
		String pvcManifest = manifestMaker.makePvcManifest(pvcName, accessModes, storage);
		return (V1PersistentVolumeClaim) Yaml.load(pvcManifest);
		
	}

}
