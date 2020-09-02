package x.k8s.manifestimpl;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1PersistentVolume;
import io.kubernetes.client.util.Yaml;
import x.k8s.manifest.PersistentVolumeManifest;

public class YamlPersistentVolumeManifest extends PersistentVolumeManifest {

	private YamlManifestMaker manifestMaker = new YamlManifestMaker();

	public YamlPersistentVolumeManifest(String pvName, String storage, String accessModes, String pvcName, String scName, String namespace,
			String pvType, String nfsServerIp, String nfsPath, String localPath, String hostName) {
		// TODO Auto-generated constructor stub
		super(pvName, storage, accessModes, pvcName, scName, namespace, pvType,
				nfsServerIp, nfsPath, localPath, hostName);
	}

	@Override
	public V1PersistentVolume getPersistentVolume() throws IOException {
		// TODO Auto-generated method stub
		String pvManifest = manifestMaker.makePvManifest(pvName, storage, accessModes, pvcName, scName, namespace, pvType,
				nfsServerIp, nfsPath, localPath, hostName);

		return (V1PersistentVolume) Yaml.load(pvManifest);
	}

}
