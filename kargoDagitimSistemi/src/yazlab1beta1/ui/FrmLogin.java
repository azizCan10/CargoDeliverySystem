package yazlab1beta1.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import yazlab1beta1.Utils;
import yazlab1beta1.dataAccess.abstracts.DbHelperDao;
import yazlab1beta1.dataAccess.concretes.HibernateDbHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private DbHelperDao dbHelperDao;
	public static int userId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	
	private JPasswordField txtPassword;
	private JTextField txtUserName;
	JLabel lblWrongInfo;
	
	public FrmLogin() {
		setTitle("KARGO DA\u011EITIM S\u0130STEM\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-11, -1, 1941, 1049);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dbHelperDao = new HibernateDbHelper();
		
		txtUserName = new JTextField();
		txtUserName.setForeground(Color.WHITE);
		txtUserName.setBackground(Color.BLACK);
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtUserName.setBounds(904, 279, 264, 90);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JButton btnLogin = new JButton("G\u0130R\u0130\u015E");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				ResultSet resultSet;
				try {
					String sql = "select * from Company where CompanyName = ? and Password = ?";
					connection = dbHelperDao.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, txtUserName.getText());
					preparedStatement.setString(2, txtPassword.getText());
					resultSet = preparedStatement.executeQuery();
					
					int count = 0;
					
					while (resultSet.next()) {
						count++;
						userId = resultSet.getInt("CompanyId");
					}
					
					if (count == 1) {
						new FrmCargos().setVisible(true);
						dispose();
					}
					
					else {
						lblWrongInfo.setVisible(true);
					}
				}
				catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(btnLogin);
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBounds(761, 658, 278, 79);
		btnLogin.setBorderPainted(false);
		contentPane.add(btnLogin);
		
		JLabel lblUserName = new JLabel("Kullanýcý Adý:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(691, 277, 176, 91);
		contentPane.add(lblUserName);
		
		txtPassword = new JPasswordField();
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBackground(Color.BLACK);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtPassword.setBounds(904, 451, 264, 90);
		contentPane.add(txtPassword);
		
		JLabel lblPassword = new JLabel("\u015Eifre:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblPassword.setBounds(691, 449, 128, 91);
		contentPane.add(lblPassword);
		
		JLabel lblLoginText = new JLabel("Hesab\u0131n\u0131z yok mu?");
		lblLoginText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoginText.setForeground(Color.LIGHT_GRAY);
		lblLoginText.setBounds(761, 773, 148, 71);
		contentPane.add(lblLoginText);
		
		JLabel lblLogin = new JLabel("KAYDOLUN");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(lblLogin);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrmRegister().setVisible(true);
				dispose();
			}
		});
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBounds(919, 773, 116, 71);
		contentPane.add(lblLogin);
		
		lblWrongInfo = new JLabel("E-Mail veya \u015Fifre yanl\u0131\u015F. L\u00FCtfen tekrar deneyin.");
		lblWrongInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWrongInfo.setForeground(Color.RED);
		lblWrongInfo.setBounds(708, 189, 505, 53);
		lblWrongInfo.setVisible(false);
		contentPane.add(lblWrongInfo);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(FrmLogin.class.getResource("/images/background.jpg")));
		lblBackground.setBounds(0, 0, 1924, 1010);
		contentPane.add(lblBackground);
	}
}