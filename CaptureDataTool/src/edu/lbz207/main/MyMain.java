package edu.lbz207.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import edu.lbz207.help.CoCommentDown163File;
import edu.lbz207.help.HotCommentDown163File;
import edu.lbz207.help.PageDown163File;
import edu.lbz207.help.StringTool;
import edu.lbz207.model.CommentData;
import edu.lbz207.model.HotCommentData;
import edu.lbz207.model.News;


public class MyMain {
	
	//// 线程的构建
	private static PageDown163FileInner pD163FI = null;
	private static HotCommentDownAnalyze hcda = null;
	private static CoCommentDownAnalyze ccda = null;
	
	
	// //基础组件的设计加载s
	/**
	 * 控制panel的组件
	 */
	final static JTextField JTFurlConn = new JTextField("请将新闻链接复制到此处……");
	final static JButton JBSolve = new JButton("新闻抓取");
	final static JButton JBClearJFT = new JButton("清空链接");

	final static JComboBox<String> combox = new JComboBox();
	/**
	 * 显示panel的组件
	 */
	final static JTextField newTitle = new JTextField();
	final static JTextField newId = new JTextField();
	final static JTextField newClass = new JTextField();
	final static JTextField someInfo = new JTextField();
	final static JTextField hotURL = new JTextField();
	final static JTextField comURL = new JTextField();
	final static JTextField pageCount = new JTextField("0");

	final static JButton btnHot = new JButton("热门评论获取");
	final static JButton btnCom = new JButton("普通评论获取");

	final static JLabel jLShow = new JLabel("状态");

	/**
	 * 内容panel的组件
	 */

	final static JButton BtnClear = new JButton("清空内容");
	final static JTextArea results = new JTextArea(25, 45);

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("新闻评论数据抓取工具");
		frame.setLocation(150, 50);// /显示位置的设置
		frame.setSize(1000, 600);
		Image imageLogo = new ImageIcon("image/logo.png").getImage();
		frame.setIconImage(imageLogo);// //工具的logo
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());
		addListenersTopane(frame.getContentPane());
		frame.setResizable(false);
		// frame.pack();//根据窗口里面的布局及组件的preferedSize来确定frame的最佳大小。
		frame.setVisible(true);
	}
	
	// //添加监听事件
	private static void addListenersTopane(Container contentPane) {
		// /清楚链接
		JBClearJFT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JTFurlConn.setText("");

					}
				});

			}
		});
		// /清除内容
		BtnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						results.setText("");

					}
				});

			}
		});

		// /网页下载保存解析
		JBSolve.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				
				String judge = JTFurlConn.getText().trim();
				if(!judge.contains("news.163.com")){
					JOptionPane.showMessageDialog(null,
							"对不起， 您当前的链接有问题！",
							"提示!", JOptionPane.ERROR_MESSAGE);
				}else{
					pD163FI = new PageDown163FileInner();	////初始化线程				
					pD163FI.start();
				}
				
				
				

			}
		});
		
		////热门评论的获取
		btnHot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hcda = new HotCommentDownAnalyze();
				hcda.start();
				
			}
		});
		////普通评论的获取
		btnCom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ccda = new CoCommentDownAnalyze();
				ccda.start();
				
			}
		});
	}

	// /给当前的容器添加响应的组件
	private static void addComponentsToPane(Container container) {
		// 布局设置
		container.setLayout(new BorderLayout());
		// 内部组件的初始化
		// //设置布局的排列和组件之间的间距
		JPanel controlPanel = new JPanel(
				new FlowLayout(FlowLayout.LEFT, 20, 20));
		controlPanel.setPreferredSize(new Dimension(1000, 100));

		controlPanel.setBorder(new TitledBorder("ControlPanel"));
		// JTFurlConn.setSize(300, 40);
		JTFurlConn.setPreferredSize(new Dimension(600, 30));

		String[] source = { "网易新闻", "新浪新闻", "腾讯新闻" };
		// /添加栏目
		for (int i = 0; i < 3; i++)
			combox.addItem(source[i]);

		// //为下拉框添加事件监听
		combox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"你选择了" + ((JComboBox) e.getSource()).getSelectedItem(),
						"Cozy  Tips!", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		controlPanel.add(JTFurlConn);
		// controlPanel.add(tshow);
		controlPanel.add(combox);
		controlPanel.add(JBSolve);
		controlPanel.add(JBClearJFT);

		// controlPanel.add(subControlPanel1);
		// controlPanel.add(subControlPanel2);

		// /信息显示区域
		JPanel showPanel = new JPanel();
		showPanel.setBorder(new TitledBorder("ShowPanel"));
		showPanel.setPreferredSize(new Dimension(430, 500));
		addJTextField(showPanel);

		// ////下面是关于内容的展示区域
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(540, 500));
		contentPanel.setBorder(new TitledBorder("ContentPanel"));
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("请单击按钮清空内容:"));
		bottom.add(BtnClear);

		results.setText("消息内容显示");
		//results.setLineWrap(true);
		contentPanel.add(new JScrollPane(results));
		contentPanel.add(BorderLayout.SOUTH, bottom);
		container.add(controlPanel, BorderLayout.NORTH);
		container.add(showPanel, BorderLayout.WEST);
		container.add(contentPanel, BorderLayout.EAST);

	}

	private static void addJTextField(JPanel showPanel) {
		JPanel jpTop = new JPanel();
		jpTop.setPreferredSize(new Dimension(420, 300));
		GroupLayout groupLayout = new GroupLayout(jpTop);
		jpTop.setLayout(groupLayout);
		JLabel label1 = new JLabel("新闻标题");
		JLabel label2 = new JLabel("新闻ID");
		JLabel label3 = new JLabel("新闻类型");
		JLabel label4 = new JLabel("相关信息");
		JLabel label5 = new JLabel("热门评论");
		JLabel label6 = new JLabel("所有评论");
		JLabel label7 = new JLabel("评论页数");

		GroupLayout.SequentialGroup hGroup = groupLayout
				.createSequentialGroup();
		hGroup.addGap(5);// 添加间隔
		hGroup.addGroup(groupLayout.createParallelGroup().addComponent(label1)
				.addComponent(label2).addComponent(label3).addComponent(label4)
				.addComponent(label5).addComponent(label6).addComponent(label7));

		hGroup.addGap(5);
		hGroup.addGroup(groupLayout.createParallelGroup()
				.addComponent(newTitle).addComponent(newId)
				.addComponent(newClass).addComponent(someInfo)
				.addComponent(hotURL).addComponent(comURL)
				.addComponent(pageCount));

		hGroup.addGap(5);
		// //水平组
		groupLayout.setHorizontalGroup(hGroup);

		// 创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
		GroupLayout.SequentialGroup vGroup = groupLayout
				.createSequentialGroup();
		vGroup.addGap(30);
		vGroup.addGroup(groupLayout.createParallelGroup().addComponent(label1)
				.addComponent(newTitle));
		vGroup.addGap(10);
		vGroup.addGroup(groupLayout.createParallelGroup().addComponent(label2)
				.addComponent(newId));
		vGroup.addGap(10);
		vGroup.addGroup(groupLayout.createParallelGroup().addComponent(label3)
				.addComponent(newClass));
		vGroup.addGap(10);
		vGroup.addGroup(groupLayout.createParallelGroup().addComponent(label4)
				.addComponent(someInfo));
		vGroup.addGap(10);
		vGroup.addGroup(groupLayout.createParallelGroup().addComponent(label5)
				.addComponent(hotURL));
		vGroup.addGap(10);
		vGroup.addGroup(groupLayout.createParallelGroup().addComponent(label6)
				.addComponent(comURL));

		vGroup.addGap(10);
		vGroup.addGroup(groupLayout.createParallelGroup().addComponent(label7)
				.addComponent(pageCount));

		vGroup.addGap(20);
		// 设置垂直组
		groupLayout.setVerticalGroup(vGroup);

		JPanel jpBottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		jpBottom.setPreferredSize(new Dimension(420, 200));
		jpBottom.add(btnHot);

		jpBottom.add(jLShow);

		jpBottom.add(btnCom);

		showPanel.add(jpTop);
		showPanel.add(jpBottom);

	}

	/**
	 * 核心启动类的项目
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}

		});
	}

	
	/////启动其他的线程类 用于执行后台任务，防止页面卡死
	/**
	 * 新闻网页的下载
	 * @author MrLBZ
	 *
	 */
	static class PageDown163FileInner extends Thread{  
        public void run() {  
        	String judge = JTFurlConn.getText().trim();
        	final News news = new PageDown163File().run(judge);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					newTitle.setText(news.getContents().getTitle());
					newId.setText(news.getNewid());
					newClass.setText(news.getClas());
					someInfo.setText(news.getComments().getfCount()+news.getComments().getJoinCount());
					hotURL.setText(news.getComments().getHotjsonurl());
					comURL.setText(news.getComments().getCommonjsonurl());
					pageCount.setText(news.getComments().getCommonjsonpage());
					String strContent = StringTool.toMultiLine(news.getContents().getPassage());
					results.setText(strContent);
					jLShow.setText("数据处理完毕");

				}
			});
           
        }  
    }
	/**
	 * 热门评论的加载
	 * @author MrLBZ
	 *
	 */
	static class HotCommentDownAnalyze extends Thread{
		@Override
		public void run() {
			HotCommentDown163File hcdf = new HotCommentDown163File();
			List<HotCommentData> hcdList = new ArrayList<HotCommentData>();
			String hoturl = hotURL.getText().trim();
			hcdList = hcdf.HotComDownAnalyze(hoturl);
			results.setText("数据正在后台获取……");
			for(HotCommentData hcd:hcdList){
				results.append(hcd+"\n");
			}
			
			
		}
	}
	/**
	 * 普通评论的加载
	 * @author MrLBZ
	 *
	 */
	static class CoCommentDownAnalyze extends Thread{
		@Override
		public void run() {
			CoCommentDown163File ccdf = new CoCommentDown163File();				
			List<CommentData> ccdList = new ArrayList<CommentData>();
			String comturl = comURL.getText().trim();
			int pages = Integer.parseInt(pageCount.getText().trim());
			
			ccdList = ccdf.CoComDownAnalyze(comturl,pages);
			results.setText("");
			results.setText("数据正在后台获取…………………\n");
			for(CommentData ccd:ccdList){
				results.append(ccd+"\n");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}
	}
}
