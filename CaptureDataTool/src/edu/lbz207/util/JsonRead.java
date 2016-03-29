package edu.lbz207.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.lbz207.model.CommentData;
import edu.lbz207.model.HotCommentData;

public class JsonRead {

	public static void parseJson(String json) {
		try {
			JSONObject jsonObj = new JSONObject(json);
			System.out.println(jsonObj.getInt("tcount"));

			JSONArray data = jsonObj.getJSONArray("newPosts");
			System.out.println(data.length());// //输出数组长度
			for (int index = 0, length = data.length(); index < length; index++) {
				JSONObject jo = data.getJSONObject(index);
				JSONObject joo = jo.getJSONObject("1");
				// System.out.println(joo.getString("f"));//ok
				// System.out.println(joo.getString("b"));//ok
				// if(!joo.isNull("t")){
				// System.out.println(joo.getString("t"));//ok
				// }else{
				// System.out.println("2015-09-29 14:51:04");
				// }

				// System.out.println(joo.getString("ip"));//ok
				// if(joo.has("n")){
				// System.out.println(joo.getString("n"));
				// }else{
				// System.out.println("----匿名网友");
				// }

				// if (joo.has("d")) {
				// System.out.println(joo.getString("d"));
				// } else {
				// System.out.println("yiyanga ");
				// }
				// if(joo.has("v")){
				// System.out.println(joo.getString("v"));
				// }

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// {"a":"100","b":[{"b1":"b_value1","b2":"b_value2"},
		// {"b1":"b_value1","b2":"b_value2"}],"c":
		// {"c1":"c_value1","c2":"c_value2"}}
		String jsonStr = "{\"a\":\"100\",\"b\":[{\"b1\":\"b_value1\",\"b2\":\"b_value2\"}, {\"b1\":\"b_value1\",\"b2\":\"b_value2\"}],\"c\": {\"c1\":\"c_value1\",\"c2\":\"c_value2\"}}";
		String longJson = RWFile.getStrFromFile("temp/json.json");
		JsonRead.parseJson(longJson);
		// System.out.println(longJson);
		// ////老子这里少了一个斜线费劲了！
		// try {
		// JSONObject jsonObj = new JSONObject(longJson);
		// System.out.println(jsonObj.getInt("tcount"));
		//
		// JSONArray data = jsonObj.getJSONArray("newPosts");
		// System.out.println(data.length());////输出数组长度
		// for(int index = 0, length = data.length(); index < length; index++){
		// JSONObject jo = data.getJSONObject(index);
		// // if(jo.get)
		// //System.out.println(jo);
		// JSONObject joo =jo.getJSONObject("1");
		//
		// // System.out.println(joo.getString("f"));//ok
		// // System.out.println(joo.getString("b"));//ok
		// // if(!joo.isNull("t")){
		// // System.out.println(joo.getString("t"));//ok
		// // }else{
		// // System.out.println("2015-09-29 14:51:04");
		// // }
		//
		// // System.out.println(joo.getString("ip"));//ok
		// // if(joo.has("n")){
		// // System.out.println(joo.getString("n"));
		// // }else{
		// // System.out.println("----匿名网友");
		// // }
		//
		// if(joo.has("d")){
		// System.out.println(joo.getString("d"));
		// }else{
		// System.out.println("yiyanga ");
		// }
		// // System.out.println(joo.getString("d"));
		//
		//
		// }
		//
		//
		//
		//
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	// /普通评论的数据获取
	public static List<CommentData> parseJsonToObjectList(String json) {
		List<CommentData> cdList = new ArrayList<CommentData>();
		try {
			JSONObject jsonObj = new JSONObject(json);
			//System.out.println(jsonObj.getInt("tcount"));

			JSONArray data = jsonObj.getJSONArray("newPosts");
			String newsId = "";
			String dateTime = "000000 00:00:00";
			//System.out.println(data.length());// //输出数组长度
			for (int index = 0, length = data.length(); index < length; index++) {
				CommentData cd = new CommentData();
				JSONObject jo = data.getJSONObject(index);
				// //忽略多层回复的嵌套
				JSONObject joo = jo.getJSONObject("1");
				// 地址
				cd.setLocation(joo.getString("f"));
				// 内容
				cd.setContent(joo.getString("b"));
				// 时间
				if (!joo.isNull("t")) {
					dateTime = joo.getString("t");
					cd.setDateTime(dateTime);
				} else {
					cd.setDateTime(dateTime);
				}
				// 新闻id
				if (joo.has("d")) {
					newsId = joo.getString("d");
					cd.setNewsid(newsId);
				} else {
					cd.setNewsid(newsId);
				}
				//用户名称
				if (joo.has("n")) {
					cd.setUsername(joo.getString("n"));
					
				} else {
					cd.setUsername("Unknown Person");
				}
				///评论来源
				cd.setSource("网易新闻评论");
				cdList.add(cd);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cdList;
	}

	public static List<HotCommentData> parseJsonToHotComment(String json) {
		List<HotCommentData> cdList = new ArrayList<HotCommentData>();
		try {
			JSONObject jsonObj = new JSONObject(json);
			//System.out.println(jsonObj.getInt("tcount"));

			JSONArray data = jsonObj.getJSONArray("hotPosts");
			String newsId = "";
			String dateTime = "000000 00:00:00";
			System.out.println(data.length());
			//System.out.println(data.length());// //输出数组长度
			for (int index = 0, length = data.length(); index < length; index++) {
				HotCommentData cd = new HotCommentData();
				JSONObject jo = data.getJSONObject(index);
				
				for(int i=1;i<5;i++){
					if(jo.isNull(i+"")){
						continue;
					}
					JSONObject joo = jo.getJSONObject(i+"");
					// 地址
					cd.setLocation(joo.getString("f"));
					// 内容
					cd.setContent(joo.getString("b"));
					// 时间
					if (!joo.isNull("t")) {
						dateTime = joo.getString("t");
						cd.setDateTime(dateTime);
					} else {
						cd.setDateTime(dateTime);
					}
					// 新闻id
					if (joo.has("d")) {
						newsId = joo.getString("d");
						cd.setNewsid(newsId);
					} else {
						cd.setNewsid(newsId);
					}
					//用户名称
					if (joo.has("n")) {
						cd.setUsername(joo.getString("n"));
						
					} else {
						cd.setUsername("Unknown Person");
					}
					
					//支持度
					if (joo.has("v")) {
						cd.setSupport(joo.getString("v"));
						
					} else {
						cd.setSupport("0");
					}
					///评论来源
					cd.setSource("网易新闻评论");
					cdList.add(cd);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return cdList;
	}

}
