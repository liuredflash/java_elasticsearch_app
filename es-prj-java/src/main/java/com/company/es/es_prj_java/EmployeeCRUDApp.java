package com.company.es.es_prj_java;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateAction;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
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
		IndexResponse response = client.prepareIndex("company", "employee", "1")
		    .setSource(XContentFactory.jsonBuilder()
		    		.startObject()
		    		.field("name", "jack")
		    		.field("age", 27)
		    		.field("position", "technique")
		    		.field("country", "china")
		    		.field("join_date", "2019-01-01")
		    		.field("salary", 10000)
		    		.endObject())
		    .get();
		System.out.println(response.getResult());
	}
	
	private void getEmployee(TransportClient client) throws Exception{
		GetResponse response = client.prepareGet("company", "employee", "1").get();
		System.out.println(response.getSourceAsString());
	}
	
	private void updateEmployee(TransportClient client) throws Exception{
		UpdateResponse response = client.prepareUpdate("company", "employee", "1")
		.setDoc(XContentFactory.jsonBuilder()
				.startObject()
				.field("position", "technique")
				.endObject())
		.get();
		
		System.out.println(response.getResult());
	}
	
	private void deleteEmployee(TransportClient client) throws Exception{
		DeleteResponse response = client.prepareDelete("company", "employee", "1").get();
		System.out.println(response.getResult());
	}
}
