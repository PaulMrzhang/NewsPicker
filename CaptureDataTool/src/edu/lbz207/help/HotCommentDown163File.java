package edu.lbz207.help;

import java.util.ArrayList;
import java.util.List;

import edu.lbz207.model.HotCommentData;
import edu.lbz207.util.CommentDownload;
import edu.lbz207.util.JsonComFileProcess;
import edu.lbz207.util.JsonRead;

/**
 * 热门评论的下载解析
 * 热门评论一般只有一页 所以不用传递页码数据
 * @author MrLBZ
 * 
 */
public class HotCommentDown163File {
	int totalPages = 1;////默认值
	String hotUrl = "http://news.163.com/15/0909/21/B33PF55S00014SEH.html";	
	
	
	public void downandsave() {
		////测试使用的链接
		String url = "http://comment.news.163.com/data/news3_bbs/df/B33PF55S00014SEH_1.html";
		CommentDownload.download(url, "utf-8", "hot163.html");
		String file="temp/hot163.html";/////下载文件的路径
		String str = JsonComFileProcess.getJsonStrFromFile(file);
		System.out.println(str);
		List<HotCommentData> hcdList = new ArrayList<HotCommentData>();
		hcdList=JsonRead.parseJsonToHotComment(str);		
		for(HotCommentData hcd:hcdList){
			System.out.println(hcd.getContent());
		}
		
		System.out.println(hcdList.size());
				
	}
	public List<HotCommentData> HotComDownAnalyze(String url){
		///需要设置编码等各项参数
		CommentDownload.download(url, "utf-8", "hot163.html");
		String file="temp/hot163.html";/////下载文件的路径
		String str = JsonComFileProcess.getJsonStrFromFile(file);
		System.out.println(str);
		List<HotCommentData> hcdList = new ArrayList<HotCommentData>();
		hcdList=JsonRead.parseJsonToHotComment(str);		
		for(HotCommentData hcd:hcdList){
			System.out.println(hcd.getContent());
		}
		System.out.println(hcdList.size());
		return hcdList;
	}
	
	
	public static void main(String[] args) {
		HotCommentDown163File hcdf = new HotCommentDown163File();
		hcdf.downandsave();
	}

}
