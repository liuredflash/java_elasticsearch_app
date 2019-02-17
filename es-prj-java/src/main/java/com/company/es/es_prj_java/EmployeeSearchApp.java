package com.company.es.es_prj_java;

import java.net.InetAddress;
import java.security.PublicKey;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import joptsimple.util.InetAddressConverter;

public class EmployeeSearchApp {

	public EmployeeSearchApp() {
		// TODO Auto-generated constructor stu
	}
	
	public static void main(String[] args) throws Exception{
		Settings settings  = Settings.builder()
				.put("cluster.name", "elasticsearch")
				.build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		client.close();
				
	}
	
	public static void prepareData(TransportClient client) throws Exception{
		client.prepareIndex("company", "employee", "1")
		.setSource(XContentFactory.jsonBuilder()
				.startObject()
				.field("name", "jack")
				.field("age", 27)
				.field("position", "tech")
				.field("country", "china")
				.field("join_date", "2019-01-01")
				.field("salary", 10000)
				.endObject())
		.get();
		
		client.prepareIndex("company", "employee", "1")
		.setSource(XContentFactory.jsonBuilder()
				.startObject()
				.field("name", "mary")
				.field("age", 32)
				.field("position", "tech")
				.field("country", "china")
				.field("join_date", "2019-03-01")
				.field("salary", 1000)
				.endObject())
		.get();
		
		client.prepareIndex("company", "employee", "1")
		.setSource(XContentFactory.jsonBuilder()
				.startObject()
				.field("name", "tony")
				.field("age", 37)
				.field("position", "tech")
				.field("country", "china")
				.field("join_date", "2019-02-01")
				.field("salary", 15000)
				.endObject())
		.get();
	}
	
	public static void executeSearch(TransportClient client) throws Exception{
		SearchResponse response = client.prepareSearch("company")
		.setTypes("employee")
		.setQuery(QueryBuilders.matchQuery("position", "tech"))
		.setPostFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
		.setFrom(0).setSize(1)
		.get();
		
		SearchHit[] searchHits = response.getHits().getHits();
		
		for(int i=0; i < searchHits.length; i++) {
			System.out.println(searchHits[i].getSourceAsString());
		}
	}

}
