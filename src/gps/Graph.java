package gps;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Graph extends JFrame {

	boolean [] flag;
	private static String[] building_name = {"青年教师公寓-教师公寓食堂","综合体育馆-校医院","行政服务中心","工会-教职工服务中心","31教学楼",
			"32教学楼","33教学楼","北洋广场","东门","体育公园","34教学楼","51、52、53、54教学组团","50教学楼","第二学生食堂","49教学楼","48教学楼",
			"47教学楼","37教学楼","35、36教学组团","留园","格园-留学生食堂","知园-第六学生食堂-第一学生食堂","诚园","正园","46教学楼","45教学楼","修园","齐园",
			"第四学生食堂","39、38教学组团","55教学楼","信网中心-能源站","郑东图书馆","44教学楼","43教学楼","42、41、40教学组团","北门","平园","第三学生食堂","第五学生食堂",
			"治园","南门","学生中心-太雷广场","青年湖西岸","至善北路-博文北路","侯德榜路-博文北路","至善东道-侯德榜路","亲民北道-博文北路","明德北道-博文北路","亲民北道-敬业路","敬业路-明德北道","明德北道-兴学路",
			"明德南道-兴学路","求是大道-博文中路","明德南道-博文中路","亲民南道-博文南路","王正廷道-博文南路","王正廷道-至善东道","博文南路-至善南道","至善北道-西沽北路","侯德榜路-西沽北路","亲民北道-西沽北路",
			"明德北道-西沽北路","求是大道-西沽中路","明德南道-西沽南路","亲民南道-西沽南路","至善南道-西沽南路","至善北道-七星北路",
			"侯德榜路-七星北路","亲民北道-七星北路","明德北道-七星北路","明德南道-七星南路","亲民南道-七星南路","至善南道-七星南路","至善北道-新元北路","亲民北道-新元北路","双台北路-亲民北道","明德北道-新元中路","明德北道-双台中路",
			"明德南道-双台南路","明德南道-新元南路","亲民南道-新元南路","亲民南道-双台南路","至善南道-新元南路 ","至善南道-双台南路","英华道-西沽中路",
			"求是北道-西沽中路","英华道-西沽中路","求是北道-七星中路","书田北道-双台中路","书田北道-新元中路","书田北道-双台中路","书田南道-新元中路","书田南道-七星中路","求是南道-七星中路","求是南道-西沽中路","花堤道-七星中路","花堤道-西沽中路","花堤道-双台中路","花堤道-新元中路","求是大道-七星中路"
	};
	private int inf = (int) 1e20;
	private int[][] distance ;
	private HashMap<String,Integer> match = new HashMap<String,Integer>();
	private JPanel contentPane;
	private JSplitPane jSplitPane;
	private JLabel jLabel;
	private JList jList;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JRadioButton walk;
	private JRadioButton bicycle;
	private JTextField textField;
	private JTextArea textArea_1;
	private JRadioButton car;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Graph frame = new Graph();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		});
		
	}
	
	

	/**
	 * Create the frame.
	 */
	public Graph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 1019, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		scrollPane=new JScrollPane();
		
		
		String [] words = {"Java", "Python", "Golang"};
		jLabel = new JLabel(new ImageIcon("map_1.png"));
		jLabel.setBackground(Color.DARK_GRAY);
		jLabel.setBounds(38, 35, 649, 468);

		// JSplitPane 拆分窗格，垂直拆分方式
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(755, 60, 89, 21);
		comboBox_1.addItem("青年教师公寓-教师公寓食堂");
		comboBox_1.addItem("综合体育馆-校医院");
		comboBox_1.addItem("行政服务中心");
		comboBox_1.addItem("工会-教职工服务中心");
		comboBox_1.addItem("31教学楼");
		comboBox_1.addItem("32教学楼");
		comboBox_1.addItem("33教学楼");
		comboBox_1.addItem("北洋广场");
		comboBox_1.addItem("东门");
		comboBox_1.addItem("体育公园");
		comboBox_1.addItem("34教学楼");
		comboBox_1.addItem("51、52、53、54教学组团");
		comboBox_1.addItem("50教学楼");
		comboBox_1.addItem("第二学生食堂");
		comboBox_1.addItem("49教学楼");
		comboBox_1.addItem("48教学楼");
		comboBox_1.addItem("47教学楼");
		comboBox_1.addItem("37教学楼");
		comboBox_1.addItem("35、36教学组团");
		comboBox_1.addItem("留园");
		comboBox_1.addItem("格园-留学生食堂");
		comboBox_1.addItem("知园-第六学生食堂-第一学生食堂");
		comboBox_1.addItem("诚园");
		comboBox_1.addItem("正园");
		comboBox_1.addItem("46教学楼");
		comboBox_1.addItem("45教学楼");
		comboBox_1.addItem("修园");
		comboBox_1.addItem("齐园");
		comboBox_1.addItem("第四学生食堂");
		comboBox_1.addItem("39、38教学组团");
		comboBox_1.addItem("55教学楼");
		comboBox_1.addItem("信网中心、能源站");
		comboBox_1.addItem("郑东图书馆");
		comboBox_1.addItem("44教学楼");
		comboBox_1.addItem("43教学楼");
		comboBox_1.addItem("42、41、40教学组团");
		comboBox_1.addItem("北门");
		comboBox_1.addItem("平园");
		comboBox_1.addItem("第三学生食堂");
		comboBox_1.addItem("第五学生食堂");
		comboBox_1.addItem("治园");
		comboBox_1.addItem("南门");
		comboBox_1.addItem("学生中心-太雷广场");
		comboBox_1.addItem("青年湖西岸");
		
		
		contentPane.add(comboBox_1);
		
		contentPane.add(jLabel);
		
		car = new JRadioButton("汽车");
		car.setBounds(38, 6, 121, 23);
		contentPane.add(car);
		
		walk = new JRadioButton("步行");
		walk.setBounds(181, 6, 121, 23);
		contentPane.add(walk);
		
		bicycle = new JRadioButton("自行车/电动车");
		bicycle.setBounds(304, 6, 121, 23);
		contentPane.add(bicycle);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(875, 60, 93, 21);
		contentPane.add(comboBox);
		comboBox.addItem("青年教师公寓-教师公寓食堂");
		comboBox.addItem("综合体育馆-校医院");
		comboBox.addItem("行政服务中心");
		comboBox.addItem("工会-教职工服务中心");
		comboBox.addItem("31教学楼");
		comboBox.addItem("32教学楼");
		comboBox.addItem("33教学楼");
		comboBox.addItem("北洋广场");
		comboBox.addItem("东门");
		comboBox.addItem("体育公园");
		comboBox.addItem("34教学楼");
		comboBox.addItem("51、52、53、54教学组团");
		comboBox.addItem("50教学楼");
		comboBox.addItem("第二学生食堂");
		comboBox.addItem("49教学楼");
		comboBox.addItem("48教学楼");
		comboBox.addItem("47教学楼");
		comboBox.addItem("37教学楼");
		comboBox.addItem("35、36教学组团");
		comboBox.addItem("留园");
		comboBox.addItem("格园-留学生食堂");
		comboBox.addItem("知园-第六学生食堂-第一学生食堂");
		comboBox.addItem("诚园");
		comboBox.addItem("正园");
		comboBox.addItem("46教学楼");
		comboBox.addItem("45教学楼");
		comboBox.addItem("修园");
		comboBox.addItem("齐园");
		comboBox.addItem("第四学生食堂");
		comboBox.addItem("39、38教学组团");
		comboBox.addItem("55教学楼");
		comboBox.addItem("信网中心、能源站");
		comboBox.addItem("郑东图书馆");
		comboBox.addItem("44教学楼");
		comboBox.addItem("43教学楼");
		comboBox.addItem("42、41、40教学组团");
		comboBox.addItem("北门");
		comboBox.addItem("平园");
		comboBox.addItem("第三学生食堂");
		comboBox.addItem("第五学生食堂");
		comboBox.addItem("治园");
		comboBox.addItem("南门");
		comboBox.addItem("学生中心-太雷广场");
		comboBox.addItem("青年湖西岸");
		
		JLabel label = new JLabel("\u59CB\u53D1\u5730");
		label.setBounds(755, 35, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u76EE\u7684\u5730");
		label_1.setBounds(875, 35, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u603B\u91CC\u7A0B");
		label_2.setBounds(755, 509, 54, 15);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(819, 506, 110, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u641C\u7D22");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
				String begin = comboBox_1.getSelectedItem().toString();
				String end = comboBox.getSelectedItem().toString();
				int begin_int = match.get(begin);
				int end_int = match.get(end);
				dijkstra(begin_int,end_int);
				
				flag = new boolean[101];
				for(int i = 0;i < flag.length;i++){
					flag[i] = false;
				}
				
			}
			
			
			public void dijkstra(int begin_int, int end_int) {
				// TODO Auto-generated method stub
				int[] map = new int[101];
				int[] p = new int[101];
				p[begin_int] = begin_int;
				for(int i = 0;i < map.length;i++){
					p[i] = -1;
					map[i] = distance[begin_int][i];
				}
				p[begin_int] = begin_int;
				flag[begin_int] = true;
				for(int i = 0;i < map.length;i++){
					int minn = inf;int k = 0;
					for(int j = 0; j < map.length;j++){
						if(!flag[j]&&map[j] < minn){
							minn = map[j];
							k = j;
						}
					}

					flag[k] = true;
					for(int j = 0;j < map.length;j++){
						if(!flag[j]&&map[j] > map[k]+distance[k][j]){
							map[j] = map[k]+distance[k][j];
							p[j] = k;
						}
					}
				}
				ArrayList<String> s = new ArrayList<String>();
				int pre = end_int;
				while ( pre != -1&&pre != begin_int) {  //若P[v][w]为true,则w是从v0到v当前求得最短路径的顶点
					s.add(building_name[pre]);
					pre = p[pre];
				}
				String si = "";
				int i = 0;
				while(s.size() != 0){
					si+= s.remove(s.size()-1);
					si += '\n';
					i++;
				}
				textField.setText(""+map[end_int]);
				textArea_1.setText(si);
				
			}
		});
		button.setBounds(819, 91, 93, 23);
		contentPane.add(button);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(755, 144, 216, 332);
		contentPane.add(textArea_1);
		
		
//		
//        scrollPane.setViewportView(jSplitPane);
//        this.setTitle("滚动面板使用");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setBounds(100, 100, 250, 200);
//        this.setVisible(true);
	}
	
	private void init() {
		distance = new int[101][101];
		flag = new boolean[101];
		for(int i = 0;i < flag.length;i++){
			flag[i] = false;
		}
		try {
			init_distance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		for(int i = 0;i < building_name.length;i++){
			match.put(building_name[i], i);
//			System.out.println(i+"     "+building_name[i]);
		}
	}



	private void init_distance() throws IOException {
		File file = null;
		
		if(car.isSelected()){
			file = new File("mapp_1.txt");
		}else{
			file=new File("mapp.txt");
		}
		BufferedReader reader=null;
		String temp=null;
		int line=0;
		
		try{
			reader=new BufferedReader(new FileReader(file));
			while((temp=reader.readLine())!=null){
				String [] s = temp.split(",");
				add_in_distance(line,s);
				line++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(reader!=null){
				try{
					reader.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		// TODO Auto-generated method stub
		
	}



	private void add_in_distance(int line,String[] s) {
		// TODO Auto-generated method stub
		for(int i = 0;i < s.length;i++){
			if(s[i].equals("inf")){
				distance[line][i] = 100000;
			}else{
				distance[line][i] = Integer.valueOf(s[i]);
			}
		}
	}
}
