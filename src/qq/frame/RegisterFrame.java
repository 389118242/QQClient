package qq.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import qq.entity.Message;
import qq.entity.User;
import qq.util.I_OUtil;

public class RegisterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userName;
	private JTextField userAge;
	private JTextField userEmail;
	private JPasswordField userPassword;
	private JFrame parentFrame;
	private Socket socket;

	public RegisterFrame(JFrame parentFrame, Socket socket) {
		getView();
		this.parentFrame = parentFrame;
		this.socket = socket;
	}

	public void getView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(-1, -1, -1, -1));
		setContentPane(contentPane);

		JLabel label = new JLabel("\u6635\u79F0\uFF1A");
		label.setFont(new Font("宋体", Font.BOLD, 13));

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.BOLD, 13));

		JLabel label_2 = new JLabel("\u5E74\u9F84\uFF1A");
		label_2.setFont(new Font("宋体", Font.BOLD, 13));

		JLabel lblNewLabel = new JLabel("\u90AE\u7BB1\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 13));

		userName = new JTextField();
		userName.setColumns(10);

		userAge = new JTextField();
		userAge.setColumns(10);

		userEmail = new JTextField();
		userEmail.setColumns(10);

		userPassword = new JPasswordField();

		JLabel label_3 = new JLabel("\u5934\u50CF\uFF1A");
		label_3.setFont(new Font("宋体", Font.BOLD, 13));

		JComboBox<String> selectAva = new JComboBox<String>();
		File avaPath = new File("img/icon");
		File[] avaFiles = avaPath.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith(".png")) {
					return true;
				}
				return false;
			}
		});
		JLabel avatarL = new JLabel("");
		for (File i : avaFiles) {
			selectAva.addItem(i.getName().substring(0, i.getName().length() - 4));
		}
		avatarL.setIcon(new ImageIcon(avaFiles[0].getPath()));
		selectAva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				avatarL.setIcon(new ImageIcon(avaFiles[selectAva.getSelectedIndex()].getPath()));
			}
		});

		JButton register = new JButton("\u6CE8\u518C");
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userNameT = userName.getText();
				if (userNameT.equals("") || userNameT.indexOf(" ") != -1) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "用户名不允许为空或包含空格");
					return;
				}
				String userPassT = new String(userPassword.getPassword());
				if (userPassT.length() <= 6) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "密码至少多于6位");
					return;
				}
				int userAgeT = 0;
				try {
					userAgeT = Integer.parseInt(userAge.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "年龄为整数");
					return;
				}
				String userEmailT = userEmail.getText();
				if (userEmailT.equals("") || userEmailT.indexOf(" ") != -1) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "邮箱不允许为空或包含空格");
					return;
				}
				User user = new User();
				user.setUserName(userNameT);
				user.setUserAge(userAgeT);
				user.setUserAvatar(selectAva.getSelectedItem().toString());
				user.setUserPassword(userPassT);
				user.setUserEmail(userEmailT);
				Message mess = new Message();
				mess.setType("register");
				mess.setContent(user);
				try {
					I_OUtil.sendNews(socket, mess);
					mess = (Message) I_OUtil.receiveNews(socket);
					if (mess.getContent() != null) {
						JOptionPane.showMessageDialog(RegisterFrame.this,
								"注册成功，您的账号为:" + mess.getContent().toString() + "，快去登录吧");
					} else {
						JOptionPane.showMessageDialog(RegisterFrame.this, "注册失败，请重试！");
						return;
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				parentFrame.setVisible(true);
				RegisterFrame.this.dispose();
			}
		});
		register.setFont(new Font("宋体", Font.BOLD, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(36).addComponent(label)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(userName))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(35)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_2)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(userAge))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_1)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(userPassword,
												GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(userEmail)))))
				.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addGap(27)
												.addComponent(label_3).addGap(3).addComponent(selectAva,
														GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(70).addComponent(avatarL,
										GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
								.addGap(32))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(register, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGap(31)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(29)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(label).addComponent(userName, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
										.addComponent(userPassword, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
										.addComponent(userAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel).addComponent(userEmail, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(32).addComponent(label_3))
								.addGroup(gl_contentPane.createSequentialGroup().addGap(28)
										.addComponent(selectAva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(15).addComponent(avatarL, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(13).addComponent(register)))
						.addContainerGap(22, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
