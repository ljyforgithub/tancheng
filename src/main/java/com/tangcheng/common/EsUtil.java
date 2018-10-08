package com.tangcheng.common;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsUtil {

	private static RestHighLevelClient client = null;
	public static  RestHighLevelClient init() {
	 client = new RestHighLevelClient(
	        RestClient.builder(
	                new HttpHost("localhost", 9200, "http")));
	        
	 return client;
	}
	
	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
