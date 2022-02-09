package yazlab1beta1.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import yazlab1beta1.Utils;
import yazlab1beta1.business.abstracts.CompanyService;
import yazlab1beta1.business.concretes.CompanyManager;
import yazlab1beta1.dataAccess.abstracts.CompanyDao;
import yazlab1beta1.dataAccess.concretes.HibernateCompany;
import yazlab1beta1.dataAccess.concretes.HibernateDbHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegister extends JFrame {

	private JPanel contentPane;
	private CompanyService companyService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegister frame = new FrmRegister();
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
	
	static public JTextField txtUserName;
	static public JTextField txtPassword;
	
	public FrmRegister() {
		setTitle("KARGO DA\u011EITIM S\u0130STEM\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-11, -1, 1941, 1049);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		companyService = new CompanyManager(new HibernateCompany(new HibernateDbHelper()));
		
		JLabel lblUserName = new JLabel("Kullan\u0131c\u0131 Ad\u0131: ");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(691, 277, 206, 91);
		contentPane.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtUserName.setForeground(Color.WHITE);
		txtUserName.setBackground(Color.BLACK);
		txtUserName.setBounds(904, 279, 264, 90);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("\u015Eifre:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblPassword.setBounds(691, 449, 194, 91);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBackground(Color.BLACK);
		txtPassword.setBounds(904, 451, 264, 90);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnRegister = new JButton("KAYDOL");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyService.add();
				new FrmLogin().setVisible(true);
				dispose();
			}
		});
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(btnRegister);
			}
		});
		
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRegister.setBorderPainted(false);
		btnRegister.setBackground(Color.DARK_GRAY);
		btnRegister.setBounds(761, 658, 278, 79);
		contentPane.add(btnRegister);
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(lblBack);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrmLogin().setVisible(true);
				dispose();
			}
		});
		lblBack.setIcon(new ImageIcon(FrmChangePassword.class.getResource("/images/back.PNG")));
		lblBack.setBounds(0, 1, 54, 41);
		contentPane.add(lblBack);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(FrmRegister.class.getResource("/images/background.jpg")));
		lblBackground.setBounds(0, 0, 1924, 1010);
		contentPane.add(lblBackground);
	}

}
