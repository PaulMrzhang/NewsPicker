package edu.lbz207.help;

import edu.lbz207.model.News;
import edu.lbz207.util.NewsAnalyze;
import edu.lbz207.util.NewsDownload;
/**
 * 普通的下载类的设计
 * @author MrLBZ
 *
 */
public class PageDown163File{
	private String url = "http://news.163.com/15/0909/21/B33PF55S00014SEH.html";
	
	public PageDown163File() {
		super();
	}

	public PageDown163File(String url) {
		super();
		this.url = url;
	}

	public static void main(String[] args) {
		PageDown163File pd = new PageDown163File();
		
		

	}


	public News run(String url) {
		String newsFileName = "news163file.html";		
		String fileEncodeGBK = "gbk";		
		String sourceOfNews = "网易新闻";
		
		System.out.println("---------------step1下载网页----------------");
		NewsDownload.download(url, fileEncodeGBK, newsFileName);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("---------------step2 解析----------------");
		/** 根据解析的内容来获取新闻对象 */
		News news = new News();
		news.setLink(url);
		news.setSource(sourceOfNews);
		news = NewsAnalyze.FileToNews(news, fileEncodeGBK, newsFileName);
//		System.out.println("---------------main----------------");
//		System.out.println(news);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("++++++++++++++++++++++方法类中调用测试+++++++++++++++++++++++++");
		System.out.println(news);
		return news;
		
		
	}

}
