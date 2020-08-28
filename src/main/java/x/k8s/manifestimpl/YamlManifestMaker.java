package x.k8s.manifestimpl;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import x.k8s.prometheus.models.K8sPortVO;
import x.k8s.prometheus.models.K8sVolumeVO;

public class YamlManifestMaker {

	private VelocityEngine ve;

    public YamlManifestMaker() {
    	Properties p = new Properties();
    	p.setProperty("resource.loader", "class");
    	p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    	
        ve = new VelocityEngine();
        ve.init(p);
    }

    public String makeGpuDeploymentManifest(String deploymentName, String labelName, String imageName, String gpuCount, String specificGpu, String nodeName, List<K8sVolumeVO> volumeList) {
        VelocityContext context = new VelocityContext();
        context.put("name", deploymentName);
        context.put("labelName", labelName);
        context.put("gpuCount", gpuCount);
        context.put("imageName", imageName);
        context.put("specificGpu", specificGpu);
        context.put("nodeName", nodeName);
        context.put("volumeList", volumeList);
        

        Template t = ve.getTemplate("templates/k8s/gpu-deployment.txt");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String res = writer.toString();
        System.out.println(res);
        return res;
    }

    public String makeGpuServiceManifest(String serviceName, String labelName, List<K8sPortVO> ports) {
        VelocityContext context = new VelocityContext();
        context.put("name", serviceName);
        context.put("labelName", labelName);
        context.put("ports", ports);
        Template t = ve.getTemplate("templates/k8s/gpu-service.txt");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String res = writer.toString();
        System.out.println(res);
        return res;

    }

    public String makePodManifest(String podName, String labelName, String specificGpu, String gpuCount, String imageName, String nodeName, List<K8sVolumeVO> volumeList, boolean isMultiNode, String horovodPort) {
    	VelocityContext context = new VelocityContext();
    	context.put("podName", podName);
        context.put("labelName", labelName);
        context.put("gpuCount", gpuCount);
        context.put("specificGpu", specificGpu);
        context.put("imageName", imageName);
        context.put("nodeName", nodeName);
        context.put("volumeList", volumeList);
        context.put("multiNode", isMultiNode);
        context.put("horovodPort", horovodPort);
    	
        Template t = ve.getTemplate("templates/k8s/gpu-pod.txt");
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        String res = writer.toString();
        System.out.println(res);
        return res;
    }
    
    public String makeImageDaemonsetManifest(String name, String labelName, String imageName, String imageTag) {
    	
    	VelocityContext context = new VelocityContext();
    	context.put("name", name);
    	context.put("labelName", labelName);
    	context.put("imageName", imageName);
    	context.put("imageTag", imageTag);
    	
    	Template t = ve.getTemplate("templates/k8s/image-daemonset.txt");
    	StringWriter writer = new StringWriter();
    	t.merge(context, writer);
    	String res = writer.toString();
    	System.out.println(res);
    	
    	return res;
    }

}
