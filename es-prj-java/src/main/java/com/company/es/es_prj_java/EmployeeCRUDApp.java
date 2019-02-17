package com.company.es.es_prj_java;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
public class EmployeeCRUDApp {

	public static void main(String[] args) throws Exception{
		
		Settings settings = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9200));
		
		client.close();
		
				
	}
	
	private void createEmployee(TransportClient client) throws Exception{
		client.prepareIndex()
	}

}
