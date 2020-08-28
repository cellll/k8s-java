package x.k8s.prometheus.models;

public class K8sVolumeVO {

	private String volumeName;
	private String volumeMountPath;
	private String readOnly;
	private String subPath;
	private String claimName;
	

	public K8sVolumeVO() {
	}

	public K8sVolumeVO(String volumeName, String volumeMountPath, String readOnly, String subPath, String claimName) {
		this.volumeName = volumeName;   
		this.volumeMountPath = volumeMountPath;
		this.readOnly = readOnly;
		this.subPath = subPath;
		this.claimName = claimName;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public String getVolumeMountPath() {
		return volumeMountPath;
	}

	public void setVolumeMountPath(String volumeMountPath) {
		this.volumeMountPath = volumeMountPath;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getSubPath() {
		return subPath;
	}

	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}

	public String getClaimName() {
		return claimName;
	}

	public void setClaimName(String claimName) {
		this.claimName = claimName;
	}
	

}
