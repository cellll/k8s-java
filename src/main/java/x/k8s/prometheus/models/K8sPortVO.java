package x.k8s.prometheus.models;

public class K8sPortVO {
	private String portName;
    private int portNumber;
    private Integer nodePort;

    public K8sPortVO(String portName, int portNumber, Integer nodePort) {
        this.portName = portName;
        this.portNumber = portNumber;
        this.nodePort = nodePort;
    }

    public String getPortName() {
        return portName;
    }

    public int getPortNumber() {
        return portNumber;
    }

	public Integer getNodePort() {
		return nodePort;
	}
    
}
