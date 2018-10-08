package com.tangcheng.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.tangcheng.service.TvpService;
/**
 * 
 * @author esonlee
 * 腾讯视频去广告service
 */
@Service
public class TvpServiceImpl implements TvpService {
	
	private static final Logger logger = LogManager.getLogger(TvpServiceImpl.class);

	@Override
	public String getTvpUrl(String vid) {
		/*	
		String url = "http://vv.video.qq.com/getinfo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vids", vid);
		map.put("platform", "101001");
		map.put("charge", "0");
		map.put("otype", "json");
		try {
			HCB hcb = HCB.custom().timeout(2000).ssl().retry(5);
			HttpClient client = hcb.build();
			HttpConfig config = HttpConfig.custom().url(url).client(client).map(map).encoding("utf-8");
			String result = HttpClientUtil.post(config);
			int i = result.length();
			String json = result.substring(13, i-1);
//			JSONObject js = new JSONObject(json);
			logger.info(js);
			String tvurl = js.getJSONObject("vl").getJSONArray("vi").getJSONObject(0).getJSONObject("ul").getJSONArray("ui").getJSONObject(0).getString("url");
			String kid = js.getJSONObject("vl").getJSONArray("vi").getJSONObject(0).getJSONObject("cl").getString("keyid");
			String fvkey = js.getJSONObject("vl").getJSONArray("vi").getJSONObject(0).getString("fvkey");
			String guid = js.getJSONObject("vl").getJSONArray("vi").getJSONObject(0).getString("fmd5");
			String kids=kid.replace(".100", ".p");
			logger.info(tvurl+kid+"?sdtfrom=v1010&vkey="+fvkey);
			return tvurl+kids+".mp4?sdtfrom=v1010&guid="+guid+"&vkey="+fvkey;
		} catch (HttpProcessException e) {
			e.printStackTrace();
		}
		*/
		return "";
		
	}

	

}
