package x.k8s.manifestimpl;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1DaemonSet;
import io.kubernetes.client.util.Yaml;
import x.k8s.manifest.DaemonsetManifest;

public class YamlDaemonSetManifest extends DaemonsetManifest{
	private YamlManifestMaker manifestMaker = new YamlManifestMaker();
	
	public YamlDaemonSetManifest(String name, String labelName, String imageName, String imageTag) {
		// TODO Auto-generated constructor stub
		super(name, labelName, imageName, imageTag);
	}
	
	@Override
	public V1DaemonSet getDaemonSet() throws IOException {
		// TODO Auto-generated method stub
		
		String daemonSetManifest = manifestMaker.makeImageDaemonsetManifest(name, labelName, imageName, imageTag);
		
		
		return (V1DaemonSet) Yaml.load(daemonSetManifest);
	}
	
	
}
