package com.ourstock.api.ourstock_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OurstockApiApplication {

//	@Autowired
//	private ApplicationContext applicationContext;
//	public int portnum;

	public static void main(String[] args) {
		SpringApplication.run(OurstockApiApplication.class, args);
	}

//	@Override
//	public void onApplicationEvent(ApplicationEvent event) {
//		try{
//			String ip = InetAddress.getLocalHost().getHostAddress();
//			int port = applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class, 8081);
//			portnum = port;
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//	}
}
