package edu.lbz207.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.lbz207.model.Comments;
import edu.lbz207.model.Contents;
import edu.lbz207.model.News;

public class NewsAnalyze {

	public static void main(String[] args) {
		// //根据传入的文件进行解析内容
		String clas = NewsAnalyze.getClassOfNews("temp/newsfile.html");
		System.out.println(clas);
		// /threadId = "
		String newsId = NewsAnalyze.getNewsId("temp/newsfile.html");
		System.out.println(newsId);
		String commentLink = "http://comment.news.163.com/";
		commentLink += clas.substring(11, clas.length() - 2) + "/";
		commentLink += newsId.substring(12, newsId.length() - 2);
		commentLink += ".html";
		System.out.println(commentLink);
		//NewsDownload.download(commentLink, "utf-8", "commentsfile.html");
		// NewsAnalyze.jsoupNewsFile("temp/newsfile.html");
	}

	private static String getNewsId(String filePath) {
		String newsId = "";
		/* 文件的读取 */
		StringBuffer sb = null;
		BufferedReader br = null;
		File file = new File(filePath);
		// /判断文件是否存在
		if (!file.exists()) {
			System.out.println(filePath + "文件没有找到 ");
			return "";
		}
		// /boardId = "
		try {
			br = new BufferedReader(new FileReader(filePath));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = br.readLine()) != null) {
				if (tempString.contains("threadId = \"")) {
					newsId = tempString.trim();

				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return newsId;
	}

	private static String getClassOfNews(String filePath) {
		String boardId = "";
		/* 文件的读取 */
		StringBuffer sb = null;
		BufferedReader br = null;
		File file = new File(filePath);
		// /判断文件是否存在
		if (!file.exists()) {
			System.out.println(filePath + "文件没有找到 ");
			return "";
		}
		// /boardId = "
		try {
			br = new BufferedReader(new FileReader(filePath));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = br.readLine()) != null) {
				if (tempString.contains("boardId = \"")) {
					boardId = tempString.trim();

				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return boardId;

	}

	private static void jsoupNewsFile(String filePath) {
		// //判断文件是否存在
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println(filePath + "文件没有成功下载 ");
			return;
		}
		File input = new File(filePath);
		Document doc;
		try {

			doc = Jsoup.parse(input, "gbk");
			// doc
			// =Jsoup.connect("http://news.163.com/14/0114/19/9IISVTCG0001124J.html").get();

			Element epContent = doc.getElementById("epContentLeft");
			Elements epdivs = epContent.getElementsByClass("left");
			String time = epdivs.get(0).text().substring(0, 19);

			Element epa = epdivs.get(0).select("a").first();
			String currUrl = epa.absUrl("href");
			String currdata = epa.text();

			String linkHref = epa.attr("abs:href");

			Element eleDiv = doc.getElementById("endText");
			String content = eleDiv.text();
			Element eleTitle = doc.getElementById("h1title");
			String title = eleTitle.text();

			Elements eleScripts = doc.select("script");
			String test = eleScripts.get(1).text();

			System.out.println("title:" + title);
			System.out.println("time:" + time);
			System.out.println("content:" + content);
			System.out.println("currUrl:" + currUrl + currdata);
			System.out.println(test);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void analyzeNewsFile(String filePath) {
		/* 文件的读取 */
		StringBuffer sb = null;
		BufferedReader br = null;
		File file = new File(filePath);
		// /判断文件是否存在
		if (!file.exists()) {
			System.out.println(filePath + "文件没有成功下载 ");
			return;
		}
		try {
			br = new BufferedReader(new FileReader(filePath));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = br.readLine()) != null) {
				// 显示行号
				sb.append(tempString);
				// System.out.println("line " + line + ": " + tempString);
				line++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * //解析新闻文件返回一个新闻类的对象实例
	 * 
	 * @param news
	 * @param fileEncodeGBK
	 * @param newsFileName
	 * @return
	 */
	public static News FileToNews(News news, String fileEncodeGBK,
			String newsFileName) {
		// /临时新闻对象的构建
		Comments comments = new Comments();
		Contents contentts = new Contents();
		String filePath = "temp/" + newsFileName;
		// //判断文件是否存在
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println(filePath + "文件没有成功下载 ");
			return null;
		}
		File input = new File(filePath);

		// 第一步：进行 jsoup 的 html解析
		Document doc;
		try {

			doc = Jsoup.parse(input, fileEncodeGBK);
			Element epContent = doc.getElementById("epContentLeft");
			/***
			 * !!!!注意：这个地方需要注意2014年和2015页面之间存在区别
			 */
			Elements epdivs = epContent.getElementsByClass("ep-time-soure");
			
			String time = epdivs.get(0).text();
			System.out.println(time);
			// 时间的设置
			news.setDate(time);
			//新闻内容的设置
			contentts.setLink(news.getLink());
			Element eleDiv = doc.getElementById("endText");
			String contentstr = eleDiv.text();
			contentts.setPassage(contentstr);
			Element eleTitle = doc.getElementById("h1title");
			String title = eleTitle.text();
			contentts.setTitle(title);
			news.setContents(contentts);// //设置新闻内容的数据

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// /第二步：文本解析的获取
		String replyCount="";
		String totalCount="";
		String fCount = "";
		String joinCount="";
		//url主要是拼接
		String hotjsonurl="";
		String commonjsonurl="";
		String commonjsonpage="";
		String linkComment = "http://comment.news.163.com/";
		
		
		//用于作为 newsid 和 clas的数据获取
		String boardId = "";
		String threadId = "";

		/* 文件的读取 */
		StringBuffer sb = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filePath));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = br.readLine()) != null) {
				if (tempString.contains("tcount:")) {
					fCount = tempString.trim();
				} else if (tempString.contains("joincount:")) {
					joinCount = tempString.trim();
				} else if (tempString.contains("threadId =")) {
					threadId = tempString.trim();
				} else if (tempString.contains("boardId =")) {
					boardId = tempString.trim();
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		
		///文本的处理
		fCount = fCount.substring(8);
		joinCount = joinCount.substring(11,joinCount.length()-1);
		System.out.println(fCount);
		System.out.println(joinCount);
		
		boardId=boardId.substring(11, boardId.length() - 2);
		threadId=threadId.substring(12, threadId.length() - 2);
		//新闻id 和 新闻类型的设计v
		news.setNewid(threadId);
		news.setClas(boardId);
		
		linkComment+=boardId+"/"+threadId+".html";
		System.out.println(linkComment);
		hotjsonurl="http://comment.news.163.com/data/"+boardId+"/df/"+threadId+"_1.html";
		int i=1;
		commonjsonurl="http://comment.news.163.com/cache/newlist/"+boardId+"/"+threadId+".html";
		
		int temp = Integer.parseInt(fCount);
		int pages = temp/30+1;
		commonjsonpage=""+pages;
		System.out.println(hotjsonurl);
		System.out.println(commonjsonurl);
		System.out.println(commonjsonpage);
	    
		comments.setLink(linkComment);
		comments.setfCount(fCount);
		comments.setJoinCount(joinCount);
		comments.setHotjsonurl(hotjsonurl);
		comments.setCommonjsonurl(commonjsonurl);
		
		comments.setCommonjsonpage(commonjsonpage);
		
		news.setComments(comments);
		return news;
	}

}
