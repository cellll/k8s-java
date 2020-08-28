package x.k8s.manifest;

import java.io.IOException;

import io.kubernetes.client.openapi.models.V1DaemonSet;

public abstract class DaemonsetManifest {
	protected String name;
	protected String labelName;
	protected String imageName;
	protected String imageTag;
	
	public DaemonsetManifest(String name, String labelName, String imageName, String imageTag) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.labelName = labelName;
		this.imageName = imageName;
		this.imageTag = imageTag;
	}
	
	public abstract V1DaemonSet getDaemonSet() throws IOException;
	

}
