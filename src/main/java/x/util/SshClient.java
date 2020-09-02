package x.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SshClient {
	
	public Map<String, Object> execjsch(ServerVO serverVO, String command) {
		// 1. JSch 객체를 생성한다.
		JSch jsch = new JSch();
		String result = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Session session = null;
		String username = null;
		String host = null;
		String password = null;
		int port = 0;

		Channel channel = null;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		username = serverVO.getUser();
		host = serverVO.getIp();
		port = serverVO.getPort();
		password = serverVO.getPw();


		try {
			// 2. 세션 객체를 생성한다 (사용자 이름, 접속할 호스트, 포트를 인자로 준다.)
			session = jsch.getSession(username, host, port);
			// 3. 패스워드를 설정한다.
			session.setPassword(password);
			// 4. 세션과 관련된 정보를 설정한다.
			java.util.Properties config = new java.util.Properties();
			// 4-1. 호스트 정보를 검사하지 않는다.
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			// 5. 접속한다.
			session.connect();
			// 6. sftp 채널을 연다.
			channel = session.openChannel("exec");
			// 7. 채널을 SSH용 채널 객체로 캐스팅한다
			ChannelExec channelExec = ((ChannelExec) channel);

			channelExec.setCommand(command);
			channel.setInputStream(null);
			// 8. 채널에 아웃풋설정을 한다.
			InputStream in = channel.getInputStream(); // channel.getInputStream();
			// 18.05.28 je.kim error stream 추가
			final InputStream err = channelExec.getErrStream();// <- 일반 에러 스트림
			channelExec.connect();
			result = getChannelOutput(channel, in, err);

		} catch (Exception e) {
			// 실패시 로그와 결과값 출력
			resultMap.put("status", false);
			resultMap.put("log", e.getMessage());
	 		
			return resultMap;
		} finally {
			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}

		resultMap.put("status", true);
		resultMap.put("log", result);
		return resultMap;
	}
	
	private String getChannelOutput(Channel channel, InputStream in, InputStream err) throws IOException {

		byte[] buffer = new byte[1024];
		StringBuilder strBuilder = new StringBuilder();

		String line = "";
		while (true) {
			while (in.available() > 0) {
				int i = in.read(buffer, 0, 1024);
				if (i < 0) {
					break;
				}
				strBuilder.append(new String(buffer, 0, i));
//                System.out.println(line);
			}

			while (err.available() > 0) {
				int i = err.read(buffer, 0, 1024);
				if (i < 0) {
					break;
				}
				strBuilder.append(new String(buffer, 0, i));
			}

			if (line.contains("logout")) {
				break;
			}

			if (channel.isClosed()) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception ee) {
			}
		}

		return strBuilder.toString();
	}

}
