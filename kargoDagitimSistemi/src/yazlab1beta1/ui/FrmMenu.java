package yazlab1beta1.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kingaspx.util.BrowserUtil;
import com.kingaspx.version.Version;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.events.TitleEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class FrmMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
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
	
	static JPanel mapPanel;
	

	public FrmMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-11, -1, 1941, 1049);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mapPanel = new JPanel();
		mapPanel.setBounds(0, 0, 1915, 1000);
		contentPane.add(mapPanel);
		mapPanel.setLayout(new BorderLayout(0, 0));
		
		openSite();
	}
	
	Browser browser;
    BrowserView view;
	
	private void openSite() {
        BrowserUtil.setVersion(Version.V6_22);

        browser = new Browser();
        view = new BrowserView(browser);

        mapPanel.add(view, BorderLayout.CENTER);

        browser.addTitleListener((TitleEvent evt) -> {
            //jLabel1.setText("   " + evt.getTitle());
        });

        browser.addConsoleListener((ConsoleEvent evt) -> {
            System.out.println("LOG: " + evt.getMessage());
        });

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent evt) {
                evt.getBrowser().setZoomLevel(-2);
            }
        });

        browser.loadURL("C:\\HTMLGMaps\\simpleMap.html");
    }
}