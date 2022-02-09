package yazlab1beta1.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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
import yazlab1beta1.dataAccess.concretes.HibernateCompany;
import yazlab1beta1.dataAccess.concretes.HibernateDbHelper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmChangePassword extends JFrame {

	private JPanel contentPane;
	private CompanyService companyService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChangePassword frame = new FrmChangePassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static JTextField txtPassword;
	
	public FrmChangePassword() {
		setTitle("KARGO DA\u011EITIM S\u0130STEM\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-11, -1, 1941, 1049);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		companyService = new CompanyManager(new HibernateCompany(new HibernateDbHelper()));
		
		JLabel lblPassword = new JLabel("\u015Eifre:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblPassword.setBounds(671, 298, 194, 91);
		contentPane.add(lblPassword);
		
		JButton btnChangePassword = new JButton("ÞÝFREYÝ DEÐÝÞTÝR");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyService.update();
				new FrmLogin().setVisible(true);
				dispose();
			}
		});
		btnChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(btnChangePassword);
			}
		});
		
		btnChangePassword.setForeground(Color.WHITE);
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnChangePassword.setBorderPainted(false);
		btnChangePassword.setBackground(Color.DARK_GRAY);
		btnChangePassword.setBounds(681, 541, 476, 91);
		contentPane.add(btnChangePassword);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setBackground(Color.BLACK);
		txtPassword.setBounds(915, 300, 264, 90);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblBack = new JLabel("");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(lblBack);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new FrmCargos().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		lblBack.setIcon(new ImageIcon(FrmChangePassword.class.getResource("/images/back.PNG")));
		lblBack.setBounds(0, 1, 54, 41);
		contentPane.add(lblBack);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(FrmChangePassword.class.getResource("/images/background.jpg")));
		lblBackground.setBounds(0, 0, 1924, 1010);
		contentPane.add(lblBackground);
	}
}
