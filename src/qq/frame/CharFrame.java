package qq.frame;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class CharFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharFrame frame = new CharFrame();
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
	public CharFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(-1, -1, -1, -1));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JLabel label = new JLabel("");
		ImageIcon image = new ImageIcon(CharFrame.class.getResource("/chat/female.png"));
		image.setImage(image.getImage().getScaledInstance(136, 290, Image.SCALE_DEFAULT));
		label.setIcon(image);

		JLabel label_1 = new JLabel("");
		image = new ImageIcon(CharFrame.class.getResource("/chat/1.png"));
		image.setImage(image.getImage().getScaledInstance(556, image.getIconHeight(), Image.SCALE_DEFAULT));
		label_1.setIcon(image);
		
		JTextArea textContent = new JTextArea();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CharFrame.class.getResource("/chat/male.png")));
		
		JLabel sendFile = new JLabel("");
		sendFile.setIcon(new ImageIcon(CharFrame.class.getResource("/chat/folder.png")));
		
		JButton sendText = new JButton("\u53D1\u9001");
		sendText.setFont(new Font("ËÎÌו", Font.BOLD, 13));
		
		JButton close = new JButton("\u5173\u95ED");
		close.setFont(new Font("ËÎÌו", Font.BOLD, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textContent, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(sendFile)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(close)
									.addGap(18)
									.addComponent(sendText)
									.addGap(12)))
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
						.addComponent(label_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textContent, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(sendText)
									.addComponent(close))
								.addComponent(sendFile))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}
}
