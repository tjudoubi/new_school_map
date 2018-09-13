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
	private static String[] building_name = {"�����ʦ��Ԣ-��ʦ��Ԣʳ��","�ۺ�������-УҽԺ","������������","����-��ְ����������","31��ѧ¥",
			"32��ѧ¥","33��ѧ¥","����㳡","����","������԰","34��ѧ¥","51��52��53��54��ѧ����","50��ѧ¥","�ڶ�ѧ��ʳ��","49��ѧ¥","48��ѧ¥",
			"47��ѧ¥","37��ѧ¥","35��36��ѧ����","��԰","��԰-��ѧ��ʳ��","֪԰-����ѧ��ʳ��-��һѧ��ʳ��","��԰","��԰","46��ѧ¥","45��ѧ¥","��԰","��԰",
			"����ѧ��ʳ��","39��38��ѧ����","55��ѧ¥","��������-��Դվ","֣��ͼ���","44��ѧ¥","43��ѧ¥","42��41��40��ѧ����","����","ƽ԰","����ѧ��ʳ��","����ѧ��ʳ��",
			"��԰","����","ѧ������-̫�׹㳡","���������","���Ʊ�·-���ı�·","��°�·-���ı�·","���ƶ���-��°�·","���񱱵�-���ı�·","���±���-���ı�·","���񱱵�-��ҵ·","��ҵ·-���±���","���±���-��ѧ·",
			"�����ϵ�-��ѧ·","���Ǵ��-������·","�����ϵ�-������·","�����ϵ�-������·","����͢��-������·","����͢��-���ƶ���","������·-�����ϵ�","���Ʊ���-������·","��°�·-������·","���񱱵�-������·",
			"���±���-������·","���Ǵ��-������·","�����ϵ�-������·","�����ϵ�-������·","�����ϵ�-������·","���Ʊ���-���Ǳ�·",
			"��°�·-���Ǳ�·","���񱱵�-���Ǳ�·","���±���-���Ǳ�·","�����ϵ�-������·","�����ϵ�-������·","�����ϵ�-������·","���Ʊ���-��Ԫ��·","���񱱵�-��Ԫ��·","˫̨��·-���񱱵�","���±���-��Ԫ��·","���±���-˫̨��·",
			"�����ϵ�-˫̨��·","�����ϵ�-��Ԫ��·","�����ϵ�-��Ԫ��·","�����ϵ�-˫̨��·","�����ϵ�-��Ԫ��· ","�����ϵ�-˫̨��·","Ӣ����-������·",
			"���Ǳ���-������·","Ӣ����-������·","���Ǳ���-������·","���ﱱ��-˫̨��·","���ﱱ��-��Ԫ��·","���ﱱ��-˫̨��·","�����ϵ�-��Ԫ��·","�����ϵ�-������·","�����ϵ�-������·","�����ϵ�-������·","���̵�-������·","���̵�-������·","���̵�-˫̨��·","���̵�-��Ԫ��·","���Ǵ��-������·"
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

		// JSplitPane ��ִ��񣬴�ֱ��ַ�ʽ
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(755, 60, 89, 21);
		comboBox_1.addItem("�����ʦ��Ԣ-��ʦ��Ԣʳ��");
		comboBox_1.addItem("�ۺ�������-УҽԺ");
		comboBox_1.addItem("������������");
		comboBox_1.addItem("����-��ְ����������");
		comboBox_1.addItem("31��ѧ¥");
		comboBox_1.addItem("32��ѧ¥");
		comboBox_1.addItem("33��ѧ¥");
		comboBox_1.addItem("����㳡");
		comboBox_1.addItem("����");
		comboBox_1.addItem("������԰");
		comboBox_1.addItem("34��ѧ¥");
		comboBox_1.addItem("51��52��53��54��ѧ����");
		comboBox_1.addItem("50��ѧ¥");
		comboBox_1.addItem("�ڶ�ѧ��ʳ��");
		comboBox_1.addItem("49��ѧ¥");
		comboBox_1.addItem("48��ѧ¥");
		comboBox_1.addItem("47��ѧ¥");
		comboBox_1.addItem("37��ѧ¥");
		comboBox_1.addItem("35��36��ѧ����");
		comboBox_1.addItem("��԰");
		comboBox_1.addItem("��԰-��ѧ��ʳ��");
		comboBox_1.addItem("֪԰-����ѧ��ʳ��-��һѧ��ʳ��");
		comboBox_1.addItem("��԰");
		comboBox_1.addItem("��԰");
		comboBox_1.addItem("46��ѧ¥");
		comboBox_1.addItem("45��ѧ¥");
		comboBox_1.addItem("��԰");
		comboBox_1.addItem("��԰");
		comboBox_1.addItem("����ѧ��ʳ��");
		comboBox_1.addItem("39��38��ѧ����");
		comboBox_1.addItem("55��ѧ¥");
		comboBox_1.addItem("�������ġ���Դվ");
		comboBox_1.addItem("֣��ͼ���");
		comboBox_1.addItem("44��ѧ¥");
		comboBox_1.addItem("43��ѧ¥");
		comboBox_1.addItem("42��41��40��ѧ����");
		comboBox_1.addItem("����");
		comboBox_1.addItem("ƽ԰");
		comboBox_1.addItem("����ѧ��ʳ��");
		comboBox_1.addItem("����ѧ��ʳ��");
		comboBox_1.addItem("��԰");
		comboBox_1.addItem("����");
		comboBox_1.addItem("ѧ������-̫�׹㳡");
		comboBox_1.addItem("���������");
		
		
		contentPane.add(comboBox_1);
		
		contentPane.add(jLabel);
		
		car = new JRadioButton("����");
		car.setBounds(38, 6, 121, 23);
		contentPane.add(car);
		
		walk = new JRadioButton("����");
		walk.setBounds(181, 6, 121, 23);
		contentPane.add(walk);
		
		bicycle = new JRadioButton("���г�/�綯��");
		bicycle.setBounds(304, 6, 121, 23);
		contentPane.add(bicycle);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(875, 60, 93, 21);
		contentPane.add(comboBox);
		comboBox.addItem("�����ʦ��Ԣ-��ʦ��Ԣʳ��");
		comboBox.addItem("�ۺ�������-УҽԺ");
		comboBox.addItem("������������");
		comboBox.addItem("����-��ְ����������");
		comboBox.addItem("31��ѧ¥");
		comboBox.addItem("32��ѧ¥");
		comboBox.addItem("33��ѧ¥");
		comboBox.addItem("����㳡");
		comboBox.addItem("����");
		comboBox.addItem("������԰");
		comboBox.addItem("34��ѧ¥");
		comboBox.addItem("51��52��53��54��ѧ����");
		comboBox.addItem("50��ѧ¥");
		comboBox.addItem("�ڶ�ѧ��ʳ��");
		comboBox.addItem("49��ѧ¥");
		comboBox.addItem("48��ѧ¥");
		comboBox.addItem("47��ѧ¥");
		comboBox.addItem("37��ѧ¥");
		comboBox.addItem("35��36��ѧ����");
		comboBox.addItem("��԰");
		comboBox.addItem("��԰-��ѧ��ʳ��");
		comboBox.addItem("֪԰-����ѧ��ʳ��-��һѧ��ʳ��");
		comboBox.addItem("��԰");
		comboBox.addItem("��԰");
		comboBox.addItem("46��ѧ¥");
		comboBox.addItem("45��ѧ¥");
		comboBox.addItem("��԰");
		comboBox.addItem("��԰");
		comboBox.addItem("����ѧ��ʳ��");
		comboBox.addItem("39��38��ѧ����");
		comboBox.addItem("55��ѧ¥");
		comboBox.addItem("�������ġ���Դվ");
		comboBox.addItem("֣��ͼ���");
		comboBox.addItem("44��ѧ¥");
		comboBox.addItem("43��ѧ¥");
		comboBox.addItem("42��41��40��ѧ����");
		comboBox.addItem("����");
		comboBox.addItem("ƽ԰");
		comboBox.addItem("����ѧ��ʳ��");
		comboBox.addItem("����ѧ��ʳ��");
		comboBox.addItem("��԰");
		comboBox.addItem("����");
		comboBox.addItem("ѧ������-̫�׹㳡");
		comboBox.addItem("���������");
		
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
				while ( pre != -1&&pre != begin_int) {  //��P[v][w]Ϊtrue,��w�Ǵ�v0��v��ǰ������·���Ķ���
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
//        this.setTitle("�������ʹ��");
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
