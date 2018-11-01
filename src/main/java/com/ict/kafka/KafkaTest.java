package com.ict.kafka;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.ict.utils.Properties;
import com.ict.utils.Utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class KafkaTest {

	public static void main(String[] args) {

		System.out.println("ConsumerStart");
		Consumer consumer = new Consumer();

		while(true) {
			
			try{

				long time = System.currentTimeMillis();
				long dev = 30 * 24 * 60 * 60 * 1000l;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				List<String> res = consumer.getConsume();
				for(String str:res) {
					String temp = null;
					JSONObject msgBody = null;
					System.out.println(res);
					try{
						temp = JSONObject.fromObject(str).getString("msgBody");
						msgBody = (JSONObject) JSONArray.fromObject(Utils.base64Decoder(temp)).get(0);
					}catch(Exception e1){
						System.out.println("sparse error:" + e1.getMessage());
						e1.printStackTrace();
						continue;
					}
					
					String spec = msgBody.getString("_spec");
					
					System.out.println("###spec###:"+spec);
					if (!spec.equals("M-MS26-A")) {
						continue;
					}
					JSONArray dataTemp = msgBody.getJSONArray("data");
					JSONArray data = new JSONArray();
					HashMap<String, Integer> index = new HashMap<>();
					System.out.println("metaDataSize###" + dataTemp.size());
					for (int i = 0; i < dataTemp.size(); i++) {
						if (index.containsKey(dataTemp.getJSONObject(i).getString("hsw"))) {
							long ptTemp = dataTemp.getJSONObject(i).getLong("pt");
							long ptO = data.getJSONObject(index.get(dataTemp.getJSONObject(i).getString("hsw")))
									.getLong("pt");
							if (ptO < ptTemp) {
								data.add(index.get(dataTemp.getJSONObject(i).getString("hsw")),
										dataTemp.getJSONObject(i));
							}
							
						} else {
							data.add(index.size(), dataTemp.getJSONObject(i));
							index.put(dataTemp.getJSONObject(i).getString("hsw"), index.size());
						}
					}
					System.out.println("metaDataSize###" + data.size());
					for (int i = 0; i < data.size(); i++) {
						
						JSONObject metaData = data.getJSONObject(i);
						if (metaData.containsKey("hsw") && !metaData.getString("hsw").equals("")) {
							String hsw = metaData.getString("hsw");
							String title = metaData.getString("title");
							String cont = metaData.getString("cont");
							String url = metaData.getString("url");
							long hswi = metaData.getLong("hswi");
							long pt = metaData.getLong("pt");
							long gt = metaData.getLong("gt");
							long hcid = metaData.getLong("hcid");
							if (time - pt * 1000l > dev) {
								continue;
							}
							System.out.println(hsw+","+title+","+cont+","+url+","+hswi+","+pt+","+gt+","+hcid);
						}
					}
				}
				
				Thread.sleep(10*1000);
			}catch(Exception e){
				System.out.println("#########MetaData:"+e.getMessage());
			}finally {
				consumer.closeConsumer();
			}
			
		}
		
	
	}
}
