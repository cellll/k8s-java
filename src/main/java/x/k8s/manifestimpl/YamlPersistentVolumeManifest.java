package x.k8s.manifestimpl;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1PersistentVolume;
import io.kubernetes.client.util.Yaml;
import x.k8s.manifest.PersistentVolumeManifest;

public class YamlPersistentVolumeManifest extends PersistentVolumeManifest{
	
	private YamlManifestMaker manifestMaker = new YamlManifestMaker();
	
	public YamlPersistentVolumeManifest(String name, String storage, String pvcName, String namespace, String nfsServerIp, String nfsPath) {
		// TODO Auto-generated constructor stub
		super(name, storage, pvcName, namespace, nfsServerIp, nfsPath);
	}
	
	@Override
	public V1PersistentVolume getPersistentVolume() throws IOException {
		// TODO Auto-generated method stub
		String pvManifest = manifestMaker.makePVManifest(name, storage, pvcName, namespace, nfsServerIp, nfsPath);
		return (V1PersistentVolume) Yaml.load(pvManifest);
	}

}
