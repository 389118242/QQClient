package qq.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import qq.entity.Message;
import qq.util.I_OUtil;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userId;
	private JPasswordField userPassword;
	private Socket socket = null;

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();

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
	public LoginFrame() {
		try {
			this.setSocket(new Socket("localhost", 3927));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		this.setResizable(false);
		setBounds(100, 100, 321, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel loginTitle = new JLabel("");
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitle.setIcon(new ImageIcon(LoginFrame.class.getResource("/login/qq.PNG")));
		contentPane.add(loginTitle, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel id = new JLabel("\u8D26\u53F7:");
		id.setFont(new Font("ø¨ÃÂ", Font.BOLD, 19));

		JLabel pw = new JLabel("\u5BC6\u7801:");
		pw.setFont(new Font("ø¨ÃÂ", Font.BOLD, 19));

		userId = new JTextField();
		userId.setFont(new Font("ÀŒÃÂ", Font.PLAIN, 16));
		userId.setColumns(10);

		userPassword = new JPasswordField();
		userPassword.setFont(new Font("ÀŒÃÂ", Font.PLAIN, 16));

		JLabel reg = new JLabel("");
		reg.setIcon(new ImageIcon(LoginFrame.class.getResource("/login/reg.png")));
		JLabel qh = new JLabel("");
		qh.setIcon(new ImageIcon(LoginFrame.class.getResource("/login/qh.png")));
		reg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterFrame(LoginFrame.this, socket).setVisible(true);
				LoginFrame.this.setVisible(false);
			}
		});

		JLabel label = new JLabel("");

		JLabel lab2_2 = new JLabel("");
		lab2_2.setIcon(new ImageIcon(LoginFrame.class.getResource("/login/2-2.png")));

		JButton login = new JButton();
		login.setPressedIcon(new ImageIcon("img/login/sz.pgn"));
		login.setMargin(new Insets(-3, -3, -3, -3));
		login.setIcon(new ImageIcon(LoginFrame.class.getResource("/login/login.png")));
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String uid = userId.getText();
				String upass = new String(userPassword.getPassword());
				if (uid.equals("") || upass.equals("")) {
					JOptionPane.showMessageDialog(LoginFrame.this, "’À∫≈ªÚ√‹¬Î≤ª‘ –ÌŒ™ø’");
					return;
				}
				Message mess = new Message();
				mess.setType("login");
				mess.setContent(uid + ":" + upass);
				try {
					I_OUtil.sendNews(socket, mess);
					mess = (Message) I_OUtil.receiveNews(socket);
					System.out.println(mess.getContent());
					if ((boolean) mess.getContent()) {
						new MainFrame(LoginFrame.this, socket).setVisible(true);
						LoginFrame.this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(LoginFrame.this, "’À∫≈ªÚ√‹¬Î¥ÌŒÛ");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton setting = new JButton("");
		setting.setMargin(new Insets(-3, -3, -3, -3));
		setting.setIcon(new ImageIcon(LoginFrame.class.getResource("/login/sz.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(14)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup().addComponent(id)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(userId,
														GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup().addComponent(pw)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(label)
												.addComponent(userPassword, 150, 150, 150))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(qh).addComponent(reg)))
						.addGroup(gl_panel.createSequentialGroup().addGap(39)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false).addComponent(lab2_2)
										.addGroup(gl_panel.createSequentialGroup().addGap(13).addComponent(login)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(setting)))))
						.addContainerGap(25, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(33)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(reg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(id).addComponent(userId,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(30)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false).addComponent(pw)
						.addComponent(userPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(qh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE).addComponent(label)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lab2_2).addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(login).addComponent(setting))
				.addGap(25)));
		panel.setLayout(gl_panel);
	}
}
