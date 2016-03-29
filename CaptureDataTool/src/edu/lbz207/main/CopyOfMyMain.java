package edu.lbz207.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class CopyOfMyMain {
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
////添加监听事件
	private static void addListenersTopane(Container contentPane) {
		//////清楚操作
		JBClearJFT.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTFurlConn.setText("");
				
			}
		});
		BtnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				results.setText("");
				
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

		///信息显示区域
		JPanel showPanel = new JPanel();
		showPanel.setBorder(new TitledBorder("ShowPanel"));
		showPanel.setPreferredSize(new Dimension(430, 500));		
		addJTextField(showPanel);
		
		
		//////下面是关于内容的展示区域
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(540, 500));
		contentPanel.setBorder(new TitledBorder("ContentPanel"));
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("请单击按钮清空内容:"));
		bottom.add(BtnClear);

		results.setText("消息内容显示");
		contentPanel.add(new JScrollPane(results));
		contentPanel.add(BorderLayout.SOUTH, bottom);
		container.add(controlPanel, BorderLayout.NORTH);
		container.add(showPanel, BorderLayout.WEST);
		container.add(contentPanel, BorderLayout.EAST);

	}

	private static void addJTextField(JPanel showPanel) {
		JPanel jpTop = new JPanel();
		jpTop.setPreferredSize(new Dimension(420,300));
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
		hGroup.addGroup(groupLayout.createParallelGroup().addComponent(newTitle)
				.addComponent(newId).addComponent(newClass).addComponent(someInfo)
				.addComponent(hotURL).addComponent(comURL).addComponent(pageCount));

		hGroup.addGap(5);
		////水平组
		groupLayout.setHorizontalGroup(hGroup);
		
		
		//创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。  
        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();  
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
        //设置垂直组  
        groupLayout.setVerticalGroup(vGroup); 
		
        
        JPanel jpBottom = new JPanel();
        jpBottom.setPreferredSize(new Dimension(420,200));
        jpBottom.add(btnHot);
        jpBottom.add(btnCom);    
        showPanel.add(jpTop);
        showPanel.add(jpBottom);
		
		

	}
/**
 * 核心启动类的项目
 * @param args
 */

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}

		});
	}

}
