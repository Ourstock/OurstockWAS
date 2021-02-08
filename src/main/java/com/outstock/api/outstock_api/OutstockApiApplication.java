package com.outstock.api.outstock_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class OutstockApiApplication implements ApplicationListener {

	@Autowired
	private ApplicationContext applicationContext;
	public int portnum;

	public static void main(String[] args) {
		SpringApplication.run(OutstockApiApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		try{
			String ip = InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip);
			int port = applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class, 8081);
			portnum = port;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
