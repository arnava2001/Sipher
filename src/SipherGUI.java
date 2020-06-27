import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;

public class SipherGUI {

	private JFrame MainFrame;
	
	//rotation variables
	private JTextArea rotMessage1;
	private JTextArea rotValue1;
	private String encodeRotMessage; 
	private int rotNum; 
	
	//vignere variables
	private String vignereKey; 
	private String vignereMessage; 
	
	//sha variables
	private String encodeSha; 	
	
	//PW GEN
	private int passwordLength; 
	
	Sipher sipherObj = new Sipher(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SipherGUI window = new SipherGUI();
					window.MainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SipherGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainFrame = new JFrame("Sipher");
		MainFrame.getContentPane().setBackground(new Color(75, 0, 130));
		MainFrame.getContentPane().setForeground(new Color(0, 0, 0));
		MainFrame.getContentPane().setLayout(new CardLayout(0, 0));
		MainFrame.setUndecorated(true);
		JPanel StartingPanel = new JPanel();
		StartingPanel.setBackground(Color.WHITE);
		MainFrame.getContentPane().add(StartingPanel, "startPan");
		StartingPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(182, 138, 718, 145);
		java.net.URL imgUrl = getClass().getResource("sipherlogo.png");
		ImageIcon icon = new ImageIcon(imgUrl);
		
		lblNewLabel_2.setIcon(new ImageIcon(icon.getImage())); 
		StartingPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		java.net.URL imgLogo = getClass().getResource("matrix.png"); 
		ImageIcon startIcon = new ImageIcon(imgLogo); 
		Image resizedLogo = getScaledImage(startIcon.getImage(), 100, 100); 
		lblNewLabel_3.setIcon(new ImageIcon(resizedLogo));
		lblNewLabel_3.setBounds(78, 38, 510, 351);
		StartingPanel.add(lblNewLabel_3);
		
		JLabel lblEncodedecodegenerate = new JLabel("ENCODE-DECODE-GENERATE");
		lblEncodedecodegenerate.setBounds(447, 400, 94, -40);
		StartingPanel.add(lblEncodedecodegenerate);
		
		JLabel lblEncodedecodegenerate_1 = new JLabel("ENCODE\u2022DECODE\u2022GENERATE");
		lblEncodedecodegenerate_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 21));
		lblEncodedecodegenerate_1.setBounds(377, 317, 281, 61);
		StartingPanel.add(lblEncodedecodegenerate_1);
		
		JPanel RotationPanel = new JPanel();
		RotationPanel.setBackground(Color.DARK_GRAY);
		MainFrame.getContentPane().add(RotationPanel, "rot");
		RotationPanel.setLayout(null);
		
		rotMessage1 = new JTextArea();
		rotMessage1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(rotMessage1.getText().equals("Message to Encode"))
				{
					rotMessage1.setText(""); 
				}
			}
		});
		rotMessage1.setLineWrap(true);
		rotMessage1.setText("Message to Encode");
		rotMessage1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		rotMessage1.setForeground(Color.WHITE);
		rotMessage1.setBackground(Color.DARK_GRAY);
		rotMessage1.setBounds(27, 99, 318, 112);
		RotationPanel.add(rotMessage1);
		rotMessage1.setWrapStyleWord(true);
		
		
		JButton rotEnter1 = new JButton("ENTER");
		rotEnter1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encodeRotMessage = rotMessage1.getText(); 
			}
		});
		
		rotEnter1.setBackground(SystemColor.controlText);
		rotEnter1.setForeground(Color.WHITE);
		rotEnter1.setBounds(355, 115, 89, 23);
		RotationPanel.add(rotEnter1);
		rotEnter1.setOpaque(true);
		rotEnter1.setFocusPainted(false);
		rotEnter1.setBorderPainted(false);
		
		JLabel lblNewLabel = new JLabel("ENCODE");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(153, 11, 224, 80);

		RotationPanel.add(lblNewLabel);
		
		rotValue1 = new JTextArea();
		rotValue1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(rotValue1.getText().equals("Enter rotation value"))
				{
					rotValue1.setText(""); 
				}
			}
		});
		rotValue1.setLineWrap(true);
		rotValue1.setWrapStyleWord(true);
		rotValue1.setText("Enter rotation value");
		rotValue1.setForeground(Color.WHITE);
		rotValue1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		rotValue1.setColumns(10);
		rotValue1.setBackground(Color.DARK_GRAY);
		rotValue1.setBounds(27, 222, 217, 42);
		RotationPanel.add(rotValue1);
		
		JButton rotEnter1_1 = new JButton("ENTER");
		rotEnter1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotNum = Integer.parseInt(rotValue1.getText()); 
			}
		});
		rotEnter1_1.setOpaque(true);
		rotEnter1_1.setForeground(Color.WHITE);
		rotEnter1_1.setFocusPainted(false);
		rotEnter1_1.setBorderPainted(false);
		rotEnter1_1.setBackground(SystemColor.textText);
		rotEnter1_1.setBounds(355, 226, 89, 23);
		RotationPanel.add(rotEnter1_1);
		
		JTextArea encodedRot = new JTextArea();
		encodedRot.setLineWrap(true);
		encodedRot.setBackground(Color.DARK_GRAY);
		encodedRot.setForeground(Color.WHITE);
		encodedRot.setText("Encoded Message");
		encodedRot.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		encodedRot.setBounds(27, 275, 330, 190);
		RotationPanel.add(encodedRot);
		encodedRot.setWrapStyleWord(true);
		
		JLabel lblDecode = new JLabel("DECODE");
		lblDecode.setForeground(Color.WHITE);
		lblDecode.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		lblDecode.setBackground(Color.DARK_GRAY);
		lblDecode.setBounds(720, 11, 224, 80);
		RotationPanel.add(lblDecode);
		
		JTextArea decodeBoxRot = new JTextArea();
		decodeBoxRot.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(decodeBoxRot.getText().equals("Message to Decode"))
				{
					decodeBoxRot.setText(""); 
				}
			}
		});
		decodeBoxRot.setWrapStyleWord(true);
		decodeBoxRot.setText("Message to Decode");
		decodeBoxRot.setLineWrap(true);
		decodeBoxRot.setForeground(Color.WHITE);
		decodeBoxRot.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		decodeBoxRot.setBackground(Color.DARK_GRAY);
		decodeBoxRot.setBounds(572, 99, 318, 112);
		RotationPanel.add(decodeBoxRot);
		
		JButton rotEnter1_2 = new JButton("ENTER");
		rotEnter1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encodeRotMessage = decodeBoxRot.getText(); 
			}
		});
		rotEnter1_2.setOpaque(true);
		rotEnter1_2.setForeground(Color.WHITE);
		rotEnter1_2.setFocusPainted(false);
		rotEnter1_2.setBorderPainted(false);
		rotEnter1_2.setBackground(Color.BLACK);
		rotEnter1_2.setBounds(956, 115, 89, 23);
		RotationPanel.add(rotEnter1_2);
		
		JTextArea rotValueDec = new JTextArea();
		rotValueDec.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(rotValueDec.getText().equals("Enter rotation value"))
				{
					rotValueDec.setText(""); 
				}
			}
		});
		rotValueDec.setWrapStyleWord(true);
		rotValueDec.setText("Enter rotation value");
		rotValueDec.setLineWrap(true);
		rotValueDec.setForeground(Color.WHITE);
		rotValueDec.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		rotValueDec.setColumns(10);
		rotValueDec.setBackground(Color.DARK_GRAY);
		rotValueDec.setBounds(572, 222, 217, 42);
		RotationPanel.add(rotValueDec);
		
		JButton rotEnter1_1_1 = new JButton("ENTER");
		rotEnter1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotNum = Integer.parseInt(rotValueDec.getText()); 
			}
		});
		rotEnter1_1_1.setOpaque(true);
		rotEnter1_1_1.setForeground(Color.WHITE);
		rotEnter1_1_1.setFocusPainted(false);
		rotEnter1_1_1.setBorderPainted(false);
		rotEnter1_1_1.setBackground(Color.BLACK);
		rotEnter1_1_1.setBounds(956, 226, 89, 23);
		RotationPanel.add(rotEnter1_1_1);
		
		JTextArea decodeBoxFinal = new JTextArea();
		decodeBoxFinal.setWrapStyleWord(true);
		decodeBoxFinal.setText("Decoded Message");
		decodeBoxFinal.setLineWrap(true);
		decodeBoxFinal.setForeground(Color.WHITE);
		decodeBoxFinal.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		decodeBoxFinal.setBackground(Color.DARK_GRAY);
		decodeBoxFinal.setBounds(572, 275, 372, 190);
		RotationPanel.add(decodeBoxFinal);
		
		JButton rotEnter1_1_2 = new JButton("SHOW");
		rotEnter1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encodedRot.setText(sipherObj.encodeROT(rotNum, encodeRotMessage));
			}
		});
		rotEnter1_1_2.setOpaque(true);
		rotEnter1_1_2.setForeground(Color.WHITE);
		rotEnter1_1_2.setFocusPainted(false);
		rotEnter1_1_2.setBorderPainted(false);
		rotEnter1_1_2.setBackground(Color.BLACK);
		rotEnter1_1_2.setBounds(355, 321, 89, 23);
		RotationPanel.add(rotEnter1_1_2);
		
		JButton rotShow2 = new JButton("SHOW");
		rotShow2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decodeBoxFinal.setText(sipherObj.decodeROT(rotNum, encodeRotMessage));
			}
		});
		rotShow2.setOpaque(true);
		rotShow2.setForeground(Color.WHITE);
		rotShow2.setFocusPainted(false);
		rotShow2.setBorderPainted(false);
		rotShow2.setBackground(Color.BLACK);
		rotShow2.setBounds(956, 321, 89, 23);
		RotationPanel.add(rotShow2);
		
		JPanel VignerePanel = new JPanel();
		VignerePanel.setBackground(Color.DARK_GRAY);
		MainFrame.getContentPane().add(VignerePanel, "vign");
		VignerePanel.setLayout(null);
		
		JTextArea vignereMessage1 = new JTextArea();
		vignereMessage1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(vignereMessage1.getText().equals("Message to Encode"))
				{
					vignereMessage1.setText(""); 
				}
			}
		});
		vignereMessage1.setWrapStyleWord(true);
		vignereMessage1.setText("Message to Encode");
		vignereMessage1.setLineWrap(true);
		vignereMessage1.setForeground(Color.WHITE);
		vignereMessage1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		vignereMessage1.setBackground(Color.DARK_GRAY);
		vignereMessage1.setBounds(27, 99, 318, 112);
		VignerePanel.add(vignereMessage1);
		
		JButton rotEnter1_3 = new JButton("ENTER");
		rotEnter1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vignereMessage = vignereMessage1.getText(); 
			}
		});
		rotEnter1_3.setOpaque(true);
		rotEnter1_3.setForeground(Color.WHITE);
		rotEnter1_3.setFocusPainted(false);
		rotEnter1_3.setBorderPainted(false);
		rotEnter1_3.setBackground(Color.BLACK);
		rotEnter1_3.setBounds(355, 115, 89, 23);
		VignerePanel.add(rotEnter1_3);
		
		JLabel lblNewLabel_1 = new JLabel("ENCODE");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(153, 11, 224, 80);
		VignerePanel.add(lblNewLabel_1);
		
		JTextArea vignereKeyOne = new JTextArea();
		vignereKeyOne.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(vignereKeyOne.getText().equals("Enter vignere key"))
				{
					vignereKeyOne.setText(""); 
				}
			}
		});
		vignereKeyOne.setWrapStyleWord(true);
		vignereKeyOne.setText("Enter vignere key");
		vignereKeyOne.setLineWrap(true);
		vignereKeyOne.setForeground(Color.WHITE);
		vignereKeyOne.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		vignereKeyOne.setColumns(10);
		vignereKeyOne.setBackground(Color.DARK_GRAY);
		vignereKeyOne.setBounds(27, 222, 217, 42);
		VignerePanel.add(vignereKeyOne);
		
		JButton rotEnter1_1_3 = new JButton("ENTER");
		rotEnter1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vignereKey = vignereKeyOne.getText(); 
			}
		});
		rotEnter1_1_3.setOpaque(true);
		rotEnter1_1_3.setForeground(Color.WHITE);
		rotEnter1_1_3.setFocusPainted(false);
		rotEnter1_1_3.setBorderPainted(false);
		rotEnter1_1_3.setBackground(Color.BLACK);
		rotEnter1_1_3.setBounds(355, 226, 89, 23);
		VignerePanel.add(rotEnter1_1_3);
		
		JTextArea VignereOutputOne = new JTextArea();
		VignereOutputOne.setWrapStyleWord(true);
		VignereOutputOne.setText("Encoded Message");
		VignereOutputOne.setLineWrap(true);
		VignereOutputOne.setForeground(Color.WHITE);
		VignereOutputOne.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		VignereOutputOne.setBackground(Color.DARK_GRAY);
		VignereOutputOne.setBounds(27, 275, 330, 190);
		VignerePanel.add(VignereOutputOne);
		
		JLabel lblDecode_1 = new JLabel("DECODE");
		lblDecode_1.setForeground(Color.WHITE);
		lblDecode_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		lblDecode_1.setBackground(Color.DARK_GRAY);
		lblDecode_1.setBounds(720, 11, 224, 80);
		VignerePanel.add(lblDecode_1);
		
		JTextArea VignereDecodeOne = new JTextArea();
		VignereDecodeOne.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(VignereDecodeOne.getText().equals("Message to Decode"))
				{
					VignereDecodeOne.setText(""); 
				}
			}
		});
		VignereDecodeOne.setWrapStyleWord(true);
		VignereDecodeOne.setText("Message to Decode");
		VignereDecodeOne.setLineWrap(true);
		VignereDecodeOne.setForeground(Color.WHITE);
		VignereDecodeOne.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		VignereDecodeOne.setBackground(Color.DARK_GRAY);
		VignereDecodeOne.setBounds(572, 99, 318, 112);
		VignerePanel.add(VignereDecodeOne);
		
		JButton rotEnter1_2_1 = new JButton("ENTER");
		rotEnter1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vignereMessage = VignereDecodeOne.getText(); 
			}
		});
		rotEnter1_2_1.setOpaque(true);
		rotEnter1_2_1.setForeground(Color.WHITE);
		rotEnter1_2_1.setFocusPainted(false);
		rotEnter1_2_1.setBorderPainted(false);
		rotEnter1_2_1.setBackground(Color.BLACK);
		rotEnter1_2_1.setBounds(956, 115, 89, 23);
		VignerePanel.add(rotEnter1_2_1);
		
		JTextArea vignereKeyTwo = new JTextArea();
		vignereKeyTwo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(vignereKeyTwo.getText().equals("Enter vignere key"))
				{
					vignereKeyTwo.setText(""); 
				}
			}
		});
		vignereKeyTwo.setWrapStyleWord(true);
		vignereKeyTwo.setText("Enter vignere key");
		vignereKeyTwo.setLineWrap(true);
		vignereKeyTwo.setForeground(Color.WHITE);
		vignereKeyTwo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		vignereKeyTwo.setColumns(10);
		vignereKeyTwo.setBackground(Color.DARK_GRAY);
		vignereKeyTwo.setBounds(572, 222, 217, 42);
		VignerePanel.add(vignereKeyTwo);
		
		JButton rotEnter1_1_1_1 = new JButton("ENTER");
		rotEnter1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vignereKey = vignereKeyTwo.getText(); 
			}
		});
		rotEnter1_1_1_1.setOpaque(true);
		rotEnter1_1_1_1.setForeground(Color.WHITE);
		rotEnter1_1_1_1.setFocusPainted(false);
		rotEnter1_1_1_1.setBorderPainted(false);
		rotEnter1_1_1_1.setBackground(Color.BLACK);
		rotEnter1_1_1_1.setBounds(956, 226, 89, 23);
		VignerePanel.add(rotEnter1_1_1_1);
		
		JTextArea VignereOutputTwo = new JTextArea();
		VignereOutputTwo.setWrapStyleWord(true);
		VignereOutputTwo.setText("Decoded Message");
		VignereOutputTwo.setLineWrap(true);
		VignereOutputTwo.setForeground(Color.WHITE);
		VignereOutputTwo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		VignereOutputTwo.setBackground(Color.DARK_GRAY);
		VignereOutputTwo.setBounds(572, 275, 372, 190);
		VignerePanel.add(VignereOutputTwo);
		
		JButton rotEnter1_1_2_1 = new JButton("SHOW");
		rotEnter1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VignereOutputOne.setText(sipherObj.vignTable(vignereKey, vignereMessage));
			}
		});
		rotEnter1_1_2_1.setOpaque(true);
		rotEnter1_1_2_1.setForeground(Color.WHITE);
		rotEnter1_1_2_1.setFocusPainted(false);
		rotEnter1_1_2_1.setBorderPainted(false);
		rotEnter1_1_2_1.setBackground(Color.BLACK);
		rotEnter1_1_2_1.setBounds(355, 321, 89, 23);
		VignerePanel.add(rotEnter1_1_2_1);
		
		JButton rotShow2_1 = new JButton("SHOW");
		rotShow2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VignereOutputTwo.setText(sipherObj.decodeVignTable(vignereKey, vignereMessage));
			}
		});
		rotShow2_1.setOpaque(true);
		rotShow2_1.setForeground(Color.WHITE);
		rotShow2_1.setFocusPainted(false);
		rotShow2_1.setBorderPainted(false);
		rotShow2_1.setBackground(Color.BLACK);
		rotShow2_1.setBounds(956, 321, 89, 23);
		VignerePanel.add(rotShow2_1);
		
		JPanel ShaPanel = new JPanel();
		ShaPanel.setBackground(Color.DARK_GRAY);
		MainFrame.getContentPane().add(ShaPanel, "sha");
		ShaPanel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("SHA-1");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		lblNewLabel_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1.setBounds(441, 11, 354, 80);
		ShaPanel.add(lblNewLabel_1_1);
		
		JTextArea shaEncode = new JTextArea();
		shaEncode.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(shaEncode.getText().equals("Message to Encode"))
				{
					shaEncode.setText(""); 
				}
			}
		});
		shaEncode.setWrapStyleWord(true);
		shaEncode.setText("Message to Encode");
		shaEncode.setLineWrap(true);
		shaEncode.setForeground(Color.WHITE);
		shaEncode.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		shaEncode.setBackground(Color.DARK_GRAY);
		shaEncode.setBounds(20, 102, 318, 339);
		ShaPanel.add(shaEncode);
		
		JButton rotEnter1_3_1 = new JButton("ENTER");
		rotEnter1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encodeSha = shaEncode.getText();
			}
		});
		rotEnter1_3_1.setOpaque(true);
		rotEnter1_3_1.setForeground(Color.WHITE);
		rotEnter1_3_1.setFocusPainted(false);
		rotEnter1_3_1.setBorderPainted(false);
		rotEnter1_3_1.setBackground(Color.BLACK);
		rotEnter1_3_1.setBounds(342, 102, 89, 23);
		ShaPanel.add(rotEnter1_3_1);
		
		JTextArea shaDecode = new JTextArea();
		shaDecode.setWrapStyleWord(true);
		shaDecode.setText("Encoded Message");
		shaDecode.setLineWrap(true);
		shaDecode.setForeground(Color.WHITE);
		shaDecode.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		shaDecode.setBackground(Color.DARK_GRAY);
		shaDecode.setBounds(465, 102, 330, 339);
		ShaPanel.add(shaDecode);
		
		JButton rotEnter1_1_2_1_1 = new JButton("SHOW");
		rotEnter1_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shaDecode.setText(sipherObj.SHA1(encodeSha));
			}
		});
		rotEnter1_1_2_1_1.setOpaque(true);
		rotEnter1_1_2_1_1.setForeground(Color.WHITE);
		rotEnter1_1_2_1_1.setFocusPainted(false);
		rotEnter1_1_2_1_1.setBorderPainted(false);
		rotEnter1_1_2_1_1.setBackground(Color.BLACK);
		rotEnter1_1_2_1_1.setBounds(840, 103, 89, 23);
		ShaPanel.add(rotEnter1_1_2_1_1);
		
		JPanel PWPanel = new JPanel();
		PWPanel.setBackground(Color.DARK_GRAY);
		MainFrame.getContentPane().add(PWPanel, "pw");
		PWPanel.setLayout(null);
		
		JTextArea txtrDesiredPasswordLength = new JTextArea();
		txtrDesiredPasswordLength.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtrDesiredPasswordLength.getText().equals("Desired Password Length"));
					txtrDesiredPasswordLength.setText("");
			}
		});
		txtrDesiredPasswordLength.setWrapStyleWord(true);
		txtrDesiredPasswordLength.setText("Desired Password Length");
		txtrDesiredPasswordLength.setLineWrap(true);
		txtrDesiredPasswordLength.setForeground(Color.WHITE);
		txtrDesiredPasswordLength.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		txtrDesiredPasswordLength.setBackground(Color.DARK_GRAY);
		txtrDesiredPasswordLength.setBounds(306, 127, 318, 112);
		PWPanel.add(txtrDesiredPasswordLength);
		
		JButton rotEnter1_3_2 = new JButton("ENTER");
		rotEnter1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordLength = Integer.parseInt(txtrDesiredPasswordLength.getText()); 
			}
		});
		rotEnter1_3_2.setOpaque(true);
		rotEnter1_3_2.setForeground(Color.WHITE);
		rotEnter1_3_2.setFocusPainted(false);
		rotEnter1_3_2.setBorderPainted(false);
		rotEnter1_3_2.setBackground(Color.BLACK);
		rotEnter1_3_2.setBounds(634, 128, 89, 23);
		PWPanel.add(rotEnter1_3_2);
		
		JTextArea VignereOutputOne_1 = new JTextArea();
		VignereOutputOne_1.setWrapStyleWord(true);
		VignereOutputOne_1.setText("Encoded Message");
		VignereOutputOne_1.setLineWrap(true);
		VignereOutputOne_1.setForeground(Color.WHITE);
		VignereOutputOne_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		VignereOutputOne_1.setBackground(Color.DARK_GRAY);
		VignereOutputOne_1.setBounds(306, 275, 330, 190);
		PWPanel.add(VignereOutputOne_1);
		
		JButton rotEnter1_1_2_1_2 = new JButton("SHOW");
		rotEnter1_1_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VignereOutputOne_1.setText(sipherObj.PWGen(passwordLength));
			}
		});
		rotEnter1_1_2_1_2.setOpaque(true);
		rotEnter1_1_2_1_2.setForeground(Color.WHITE);
		rotEnter1_1_2_1_2.setFocusPainted(false);
		rotEnter1_1_2_1_2.setBorderPainted(false);
		rotEnter1_1_2_1_2.setBackground(Color.BLACK);
		rotEnter1_1_2_1_2.setBounds(634, 288, 89, 23);
		PWPanel.add(rotEnter1_1_2_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("PASSWORD GENERATOR");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		lblNewLabel_1_1_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setBounds(309, 22, 492, 80);
		PWPanel.add(lblNewLabel_1_1_1);
		
		JPanel Sha256 = new JPanel();
		Sha256.setLayout(null);
		Sha256.setBackground(Color.DARK_GRAY);
		MainFrame.getContentPane().add(Sha256, "Sha2Panel");
		
		JLabel lblNewLabel_1_1_2 = new JLabel("SHA-256");
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 36));
		lblNewLabel_1_1_2.setBackground(Color.DARK_GRAY);
		lblNewLabel_1_1_2.setBounds(441, 11, 354, 80);
		Sha256.add(lblNewLabel_1_1_2);
		
		JTextArea shaEncode_1 = new JTextArea();
		shaEncode_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(shaEncode_1.getText().equals("Message to Encode"))
				{
					shaEncode_1.setText("");
				}
			}
		});
		shaEncode_1.setWrapStyleWord(true);
		shaEncode_1.setText("Message to Encode");
		shaEncode_1.setLineWrap(true);
		shaEncode_1.setForeground(Color.WHITE);
		shaEncode_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		shaEncode_1.setBackground(Color.DARK_GRAY);
		shaEncode_1.setBounds(20, 102, 318, 339);
		Sha256.add(shaEncode_1);
		
		JButton rotEnter1_3_1_1 = new JButton("ENTER");
		rotEnter1_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encodeSha = shaEncode_1.getText(); 
			}
		});
		rotEnter1_3_1_1.setOpaque(true);
		rotEnter1_3_1_1.setForeground(Color.WHITE);
		rotEnter1_3_1_1.setFocusPainted(false);
		rotEnter1_3_1_1.setBorderPainted(false);
		rotEnter1_3_1_1.setBackground(Color.BLACK);
		rotEnter1_3_1_1.setBounds(342, 102, 89, 23);
		Sha256.add(rotEnter1_3_1_1);
		
		JTextArea shaDecode_1 = new JTextArea();
		shaDecode_1.setWrapStyleWord(true);
		shaDecode_1.setText("Encoded Message");
		shaDecode_1.setLineWrap(true);
		shaDecode_1.setForeground(Color.WHITE);
		shaDecode_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		shaDecode_1.setBackground(Color.DARK_GRAY);
		shaDecode_1.setBounds(465, 102, 330, 339);
		Sha256.add(shaDecode_1);
		
		JButton rotEnter1_1_2_1_1_1 = new JButton("SHOW");
		rotEnter1_1_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shaDecode_1.setText(sipherObj.sha256(encodeSha));
			}
		});
		rotEnter1_1_2_1_1_1.setOpaque(true);
		rotEnter1_1_2_1_1_1.setForeground(Color.WHITE);
		rotEnter1_1_2_1_1_1.setFocusPainted(false);
		rotEnter1_1_2_1_1_1.setBorderPainted(false);
		rotEnter1_1_2_1_1_1.setBackground(Color.BLACK);
		rotEnter1_1_2_1_1_1.setBounds(840, 103, 89, 23);
		Sha256.add(rotEnter1_1_2_1_1_1);
		MainFrame.setBounds(100, 100, 1069, 537);
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		java.net.URL imgU = getClass().getResource("matrix.png");
		ImageIcon icu = new ImageIcon(imgU);
		MainFrame.setIconImage(icu.getImage());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(0, 0, 0));
		menuBar.setForeground(new Color(148, 0, 211));
		MainFrame.setJMenuBar(menuBar);
		
		JButton btnRotationCipher = new JButton("ROTATION CIPHER");
		btnRotationCipher.setFocusPainted(false);
		btnRotationCipher.setBorderPainted(false);
		btnRotationCipher.setOpaque(true);
		btnRotationCipher.setForeground(new Color(255, 255, 255));
		btnRotationCipher.setBackground(new Color(0, 0, 0));
		btnRotationCipher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRotationCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(MainFrame.getContentPane().getLayout()); 
				c.show(MainFrame.getContentPane(), "rot"); 
			}
		});
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(MainFrame.getContentPane().getLayout());
				c.show(MainFrame.getContentPane(), "startPan"); 
			}
		});
		btnHome.setOpaque(true);
		btnHome.setForeground(Color.WHITE);
		btnHome.setFocusPainted(false);
		btnHome.setBorderPainted(false);
		btnHome.setBackground(Color.BLACK);
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(btnHome);
		menuBar.add(btnRotationCipher);
		
		JButton btnVignereCipher = new JButton("VIGNERE CIPHER");
		btnVignereCipher.setFocusPainted(false);
		btnVignereCipher.setBorderPainted(false);
		btnVignereCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(MainFrame.getContentPane().getLayout()); 
				c.show(MainFrame.getContentPane(), "vign"); 
			}
		});
		btnVignereCipher.setForeground(new Color(255, 255, 255));
		btnVignereCipher.setBackground(new Color(0, 0, 0));
		btnVignereCipher.setOpaque(true);
		menuBar.add(btnVignereCipher);
		btnVignereCipher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JButton btnSha = new JButton("SHA-1");
		btnSha.setFocusPainted(false);
		btnSha.setBorderPainted(false);
		btnSha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(MainFrame.getContentPane().getLayout()); 
				c.show(MainFrame.getContentPane(), "sha"); 
			}
		});
		btnSha.setForeground(new Color(255, 255, 255));
		btnSha.setBackground(new Color(0, 0, 0));
		btnSha.setOpaque(true);
		menuBar.add(btnSha);
		btnSha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		
		JButton btnPasswordGenerator = new JButton("PASSWORD GENERATOR");
		btnPasswordGenerator.setFocusPainted(false);
		btnPasswordGenerator.setBorderPainted(false);
		btnPasswordGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(MainFrame.getContentPane().getLayout()); 
				c.show(MainFrame.getContentPane(), "pw"); 
			}
		});
		btnPasswordGenerator.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		
		JButton btnPasswordGenerator_1 = new JButton("SHA-256");
		btnPasswordGenerator_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout c = (CardLayout)(MainFrame.getContentPane().getLayout());
				c.show(MainFrame.getContentPane(), "Sha2Panel"); 
			}
		});
		btnPasswordGenerator_1.setOpaque(true);
		btnPasswordGenerator_1.setForeground(Color.WHITE);
		btnPasswordGenerator_1.setFocusPainted(false);
		btnPasswordGenerator_1.setBorderPainted(false);
		btnPasswordGenerator_1.setBackground(Color.BLACK);
		menuBar.add(btnPasswordGenerator_1);
		btnPasswordGenerator.setForeground(new Color(255, 255, 255));
		btnPasswordGenerator.setBackground(new Color(0, 0, 0));
		btnPasswordGenerator.setOpaque(true); 
		btnPasswordGenerator_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		menuBar.add(btnPasswordGenerator);
		
		JButton btnPasswordGenerator_2 = new JButton("\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E\u200F\u200F\u200E \u200E");
		btnPasswordGenerator_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPasswordGenerator_2.setOpaque(true);
		btnPasswordGenerator_2.setForeground(Color.WHITE);
		btnPasswordGenerator_2.setFocusPainted(false);
		btnPasswordGenerator_2.setBorderPainted(false);
		btnPasswordGenerator_2.setBackground(Color.BLACK);
		menuBar.add(btnPasswordGenerator_2);
		
		JButton btnX = new JButton("\u200E\u200EX");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnX.setOpaque(true);
		btnX.setForeground(Color.RED);
		btnX.setFocusPainted(false);
		btnX.setBorderPainted(false);
		btnX.setBackground(Color.BLACK);
		btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		menuBar.add(btnX);
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
