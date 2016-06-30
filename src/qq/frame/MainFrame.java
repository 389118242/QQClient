package qq.frame;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import qq.entity.User;
import qq.util.I_OUtil;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("unused")
	private JFrame parentFrame;
	@SuppressWarnings("unused")
	private Socket socket;
	private List<User> userList;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public MainFrame(JFrame parentFrame, Socket socket) {
		this.parentFrame = parentFrame;
		this.socket = socket;
		getView();
		try {
			userList = (List<User>) I_OUtil.receiveNews(socket);
			System.out.println(userList.size());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(-1, -1, -1, -1));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainFrame.class.getResource("/main/1.PNG")));
		label.setBounds(0, 0, 225, 56);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MainFrame.class.getResource("/main/2.png")));
		label_1.setBounds(-1, 55, 31, 423);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(MainFrame.class.getResource("/main/3.png")));
		label_2.setBounds(0, 478, 225, 56);
		contentPane.add(label_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 57, 194, 423);
		contentPane.add(scrollPane);

		JList<User> list = new JList<User>();

		scrollPane.setViewportView(list);
		User ulu = new User();
		ulu.setUserName("Test");
		ulu.setUserAvatar("»¨");
		User ulu1 = new User();
		ulu1.setUserName("Test1");
		ulu1.setUserAvatar("ÔÆÐ¦Á³");
		User ulu2 = new User();
		ulu2.setUserName("Test2");
		ulu2.setUserAvatar("±ÊÄ«");
		DefaultListModel<User> dlm = new DefaultListModel<>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		dlm.addElement(ulu);
		dlm.addElement(ulu1);
		dlm.addElement(ulu2);
		list.setModel(dlm);
		list.setCellRenderer(new MyCellRenderer());

		JLabel search = new JLabel("");
		search.setBounds(92, 514, 71, 20);
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("cha");
			}
		});
		contentPane.add(search);
		// dlm.addElement(ulu.getUserList());
		// list.add(ulu.getUserList());
		this.setResizable(false);
	}

	private class MyCellRenderer extends JLabel implements ListCellRenderer<User> {

		public MyCellRenderer() {
			this.setOpaque(true);
		}

		private static final long serialVersionUID = 1L;

		@Override
		public Component getListCellRendererComponent(JList<? extends User> list, User value, int index,
				boolean isSelected, boolean cellHasFocus) {
			this.setBorder(new EmptyBorder(1, 0, 1, 0));
			setIcon(new ImageIcon("img/icon/" + value.getUserAvatar() + ".png"));
			setText(value.getUserName());
			JList.DropLocation dropLocation = list.getDropLocation();
			if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {

				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			;

			return this;
		}

	}
}
