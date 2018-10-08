package com.tangcheng.http;

import java.util.Map;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Component;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;

/**
 * 
 * @author esonlee
 * http bean
 *
 */
@Component
public class HttpUtil {
	
	
	public  String Https(Map<String, Object> map, String url) {
		try {
			HCB hcb = HCB.custom().timeout(2000).ssl().retry(5);
			HttpClient client = hcb.build();
			HttpConfig config = HttpConfig.custom().url(url).client(client).map(map).encoding("utf-8");
			String result = HttpClientUtil.post(config);
			return result;
		} catch (HttpProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
}
