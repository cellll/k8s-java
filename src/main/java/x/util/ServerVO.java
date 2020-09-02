package x.util;

public class ServerVO {
	
	private String ip;
	private String hostName;
	private String user;
	private String pw;
	private int port;
	
	
	public ServerVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ServerVO(String ip, String hostName, String user, String pw, int port) {
		// TODO Auto-generated constructor stub
		
		this.ip = ip;
		this.hostName = hostName;
		this.user = user;
		this.pw = pw;
		this.port = port;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}
	
	
	

}
