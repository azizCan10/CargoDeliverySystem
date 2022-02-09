package yazlab1beta1.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import yazlab1beta1.Utils;
import yazlab1beta1.business.abstracts.CargoService;
import yazlab1beta1.business.abstracts.CompanyService;
import yazlab1beta1.business.abstracts.UserCargoCompanyService;
import yazlab1beta1.business.abstracts.UserService;
import yazlab1beta1.business.concretes.CargoManager;
import yazlab1beta1.business.concretes.CompanyManager;
import yazlab1beta1.business.concretes.UserCargoCompanyManager;
import yazlab1beta1.business.concretes.UserManager;
import yazlab1beta1.dataAccess.abstracts.DbHelperDao;
import yazlab1beta1.dataAccess.concretes.HibernateCargo;
import yazlab1beta1.dataAccess.concretes.HibernateCompany;
import yazlab1beta1.dataAccess.concretes.HibernateDbHelper;
import yazlab1beta1.dataAccess.concretes.HibernateUser;
import yazlab1beta1.dataAccess.concretes.HibernateUserCargoCompany;
import yazlab1beta1.entities.concretes.Cargo;
import yazlab1beta1.entities.concretes.Company;
import yazlab1beta1.entities.concretes.User;
import yazlab1beta1.entities.concretes.UserCargoCompany;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class FrmCargos extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model;
	public static JTextField txtCargoName;
	public static JTextField txtUserName;
	public static JTextField txtAdress;
	public static int userId;
	public static JTextField txtCargoId;
	
	private CargoService cargoService;
	private UserService userService;
	private CompanyService companyService;
	private UserCargoCompanyService userCargoCompanyService;
	private DbHelperDao dbHelperDao;
	private Connection connection;
	private JTable tblCargos;
	private JComboBox cmbBoxUser;
	DefaultComboBoxModel dm = new DefaultComboBoxModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCargos frame = new FrmCargos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	
	
	public FrmCargos() throws SQLException {
		
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		setTitle("KARGO DA\u011EITIM S\u0130STEM\u0130");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-11, -1, 1941, 1049);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dbHelperDao = new HibernateDbHelper();
		cargoService = new CargoManager(new HibernateCargo(new HibernateDbHelper()));
		userService = new UserManager(new HibernateUser(new HibernateDbHelper()));
		companyService = new CompanyManager(new HibernateCompany(new HibernateDbHelper()));
		userCargoCompanyService = new UserCargoCompanyManager(new HibernateUserCargoCompany(new HibernateDbHelper()));
		connection = dbHelperDao.getConnection();
		
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
		lblBack.setIcon(new ImageIcon(FrmCargos.class.getResource("/images/back.PNG")));
		lblBack.setBounds(0, 1, 54, 41);
		contentPane.add(lblBack);
		
		JLabel lblChangePassword = new JLabel("\u015E\u0130FRE DE\u011E\u0130\u015ET\u0130R");
		lblChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(lblChangePassword);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				new FrmChangePassword().setVisible(true);
				dispose();
			}
		});
		lblChangePassword.setForeground(Color.WHITE);
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChangePassword.setBounds(101, 899, 176, 71);
		contentPane.add(lblChangePassword);
		
		JLabel lblCargoName = new JLabel("Kargo Ad\u0131:");
		lblCargoName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCargoName.setForeground(Color.WHITE);
		lblCargoName.setBounds(20, 95, 105, 50);
		contentPane.add(lblCargoName);
		
		txtCargoName = new JTextField();
		txtCargoName.setForeground(Color.WHITE);
		txtCargoName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCargoName.setBackground(Color.BLACK);
		txtCargoName.setBounds(135, 96, 183, 50);
		contentPane.add(txtCargoName);
		txtCargoName.setColumns(10);
		
		JLabel lblUser = new JLabel("M\u00FC\u015Fteri:");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUser.setBounds(20, 216, 105, 50);
		contentPane.add(lblUser);
		
		JButton btnAddCargo = new JButton("Kargo Ekle");
		btnAddCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargoService.add();
				
				try {
					userCargoCompanyService.add();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				populateTable();
				
			}
		});
		btnAddCargo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(btnAddCargo);
			}
		});
		btnAddCargo.setForeground(Color.WHITE);
		btnAddCargo.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnAddCargo.setBorderPainted(false);
		btnAddCargo.setBackground(Color.DARK_GRAY);
		btnAddCargo.setBounds(20, 348, 298, 61);
		contentPane.add(btnAddCargo);
		
		JLabel lblUserName = new JLabel("M\u00FC\u015Fteri Ad\u0131:");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUserName.setBounds(20, 515, 105, 50);
		contentPane.add(lblUserName);
		
		JLabel lblAdress = new JLabel("Adres:");
		lblAdress.setForeground(Color.WHITE);
		lblAdress.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAdress.setBounds(20, 632, 105, 50);
		contentPane.add(lblAdress);
		
		txtUserName = new JTextField();
		txtUserName.setForeground(Color.WHITE);
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtUserName.setColumns(10);
		txtUserName.setBackground(Color.BLACK);
		txtUserName.setBounds(135, 516, 183, 50);
		contentPane.add(txtUserName);
		
		txtAdress = new JTextField();
		txtAdress.setForeground(Color.WHITE);
		txtAdress.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtAdress.setColumns(10);
		txtAdress.setBackground(Color.BLACK);
		txtAdress.setBounds(135, 633, 183, 50);
		contentPane.add(txtAdress);
		
		JButton btnAddUser = new JButton("M\u00FC\u015Fteri Ekle");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userService.add();
				fillComboBox("select * from User", "UserName", cmbBoxUser);
			}
		});
		btnAddUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(btnAddUser);
			}
		});
		btnAddUser.setForeground(Color.WHITE);
		btnAddUser.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnAddUser.setBorderPainted(false);
		btnAddUser.setBackground(Color.DARK_GRAY);
		btnAddUser.setBounds(20, 758, 298, 61);
		contentPane.add(btnAddUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setFocusable(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(480, 95, 1318, 724);
		contentPane.add(scrollPane);
		
		tblCargos = new JTable();
		tblCargos.setRowMargin(0);
		tblCargos.setIntercellSpacing(new Dimension(0, 0));
		tblCargos.setFocusable(false);
		tblCargos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Kargo Ad\u0131", "\u015Eirket", "M\u00FC\u015Fteri Ad\u0131", "Adres", "Teslimat Durumu"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCargos.setShowVerticalLines(false);
		tblCargos.setBorder(null);
		tblCargos.setForeground(Color.WHITE);
		tblCargos.setBackground(Color.BLACK);
		
		tblCargos.setForeground(Color.WHITE);
		tblCargos.setBackground(Color.BLACK);
		tblCargos.getTableHeader().setOpaque(false);
		tblCargos.getTableHeader().setBackground(Color.BLACK);
		tblCargos.getTableHeader().setForeground(Color.WHITE);
		tblCargos.getTableHeader().setPreferredSize(new Dimension(0, 40));
		tblCargos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		tblCargos.setRowHeight(40);
		UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
		
		scrollPane.setViewportView(tblCargos);
		
		populateTable();
				
		cmbBoxUser = new JComboBox();
		cmbBoxUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query = "select UserId from User where Username = ?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String)cmbBoxUser.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while (rs.next()) {
						userId = rs.getInt("UserId"); 
					}
					
					pst.close();
				} 
				
				catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		cmbBoxUser.setToolTipText("kjhgfdhgkjfsjdghkj");
		cmbBoxUser.setForeground(Color.BLACK);
		cmbBoxUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbBoxUser.setBackground(Color.WHITE);
		cmbBoxUser.setBounds(135, 216, 183, 50);
		contentPane.add(cmbBoxUser);
		fillComboBox("select * from User", "UserName", cmbBoxUser);
		
		JLabel lblCargoId = new JLabel("Kargo Id:");
		lblCargoId.setForeground(Color.WHITE);
		lblCargoId.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCargoId.setBounds(480, 884, 131, 61);
		contentPane.add(lblCargoId);
		
		JButton btnDeleteCargo = new JButton("S\u0130L");
		btnDeleteCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userCargoCompanyService.delete();
				cargoService.delete();
				populateTable();
			}
		});
		btnDeleteCargo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Utils.setHandCursor(btnDeleteCargo);
			}
		});
		btnDeleteCargo.setForeground(Color.WHITE);
		btnDeleteCargo.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDeleteCargo.setBorderPainted(false);
		btnDeleteCargo.setBackground(Color.DARK_GRAY);
		btnDeleteCargo.setBounds(948, 883, 201, 61);
		contentPane.add(btnDeleteCargo);
		
		txtCargoId = new JTextField();
		txtCargoId.setForeground(Color.WHITE);
		txtCargoId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCargoId.setColumns(10);
		txtCargoId.setBackground(Color.BLACK);
		txtCargoId.setBounds(621, 884, 194, 61);
		contentPane.add(txtCargoId);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBackground(Color.BLACK);
		lblBackground.setIcon(new ImageIcon(FrmLogin.class.getResource("/images/background.jpg")));
		lblBackground.setBounds(0, 0, 1924, 1010);
		contentPane.add(lblBackground);
	}
	
	public void fillComboBox(String queryy, String db, JComboBox box) {
		
		dm = new DefaultComboBoxModel();
		box.setModel(dm);
		try {
			String query = queryy;
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				box.addItem(rs.getString(db));
			}
		} 
		
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void populateTable() {
		model = (DefaultTableModel)tblCargos.getModel();
		model.setRowCount(0);
		
		try {
			ArrayList<Cargo> cargos = cargoService.getAll();
			ArrayList<User> users = userService.getAll();
			ArrayList<Company> companies = companyService.getAll();
			ArrayList<UserCargoCompany> userCargoCompanies = userCargoCompanyService.getAll();
			
			for (UserCargoCompany userCargoCompany: userCargoCompanies) {
				
				int cargoNo = 0;
				int userNo = 0;
				int companyNo = 0;
				
				int tempCargo = 0;
				int tempUser = 0;
				int tempCompany = 0;
				
				for (Cargo cargo: cargos) {
					if (cargo.getCargoId() == userCargoCompany.getCargoId()) {
						cargoNo = tempCargo;
					}
					tempCargo++;
				}
				
				for (User user: users) {
					if (user.getUserId() == userCargoCompany.getUserId()) {
						userNo = tempUser;
					}
					tempUser++;
				}
				
				for (Company company: companies) {
					if (company.getCompanyId() == userCargoCompany.getCompanyId()) {
						companyNo = tempCompany;
					}
					tempCompany++;
				}
				
				Object[] row = {cargos.get(cargoNo).getCargoId(), cargos.get(cargoNo).getCargoName(), companies.get(companyNo).getCompanyName(), users.get(userNo).getUserName(), users.get(userNo).getAdress(), cargos.get(cargoNo).isTeslimat()};
				model.addRow(row);
				
			}
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
