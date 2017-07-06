package searchPW;

import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import logOnForm.DBConnection;
import logOnForm.Human;

import java.awt.*;
import net.miginfocom.swing.MigLayout;

//��й�ȣ ã�� ����(gui�� ���⼭ ��)
public class pwMain extends JFrame {
	// ���̵�
	private JTextField textField;
	// ���
	private JTextField textField_1;
	// �޺��ڽ�
	private JComboBox comboBox;
	// ���̵� Ȯ���� ���� �÷���
	private boolean flag = false;
	// ������ Ŀ�ؼ��� ����
	private Connection con;
	private pwManager pw;

	public pwMain() {
		pw = new pwManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("��й�ȣ ã��");
		setSize(382, 251);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[282px,grow]", "[][][][][][][]"));

		JLabel lblNewLabel_1 = new JLabel("\uCC3E\uACE0\uC790 \uD558\uB294 \uC544\uC774\uB514");
		panel_1.add(lblNewLabel_1, "flowx,cell 0 0");

		textField = new JTextField();
		panel_1.add(textField, "cell 0 0");
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\uC544\uC774\uB514 \uD655\uC778");
		btnNewButton.addActionListener(new listenerAction());
		panel_1.add(btnNewButton, "cell 0 1,alignx center");

		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30 \uC9C8\uBB38");
		panel_1.add(lblNewLabel_2, "flowx,cell 0 2");

		String[] questions = { "����", "����� ���� �����ϴ� �����?", "����� ���� �����ϴ� ��Ҵ�?", "���� ��￡ ���� ������?" };
		comboBox = new JComboBox(questions);
		comboBox.addActionListener(new listenerAction());
		panel_1.add(comboBox, "cell 0 2,alignx right");

		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30 \uB2F5\uBCC0");
		panel_1.add(label, "flowx,cell 0 3");

		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 0 3");
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("\uD655\uC778");
		btnNewButton_1.addActionListener(new listenerAction());
		panel_1.add(btnNewButton_1, "flowx,cell 0 5,alignx center");

		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pwMain();
	}

	class listenerAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = textField.getText();
			int choice = comboBox.getSelectedIndex();
			String answer = textField_1.getText();
			if (e.getActionCommand().equals("���̵� Ȯ��")) {
				if (pw.checkID(id) != null) {
					JOptionPane.showMessageDialog(null, id + " ���� ��� ��ȣ ã�Ⱑ �����մϴ�.");
					// ���̵� üũ�� ���������� �̷���� ��� �÷��״� Ʈ��
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null, "�ش� ���̵� �������� �ʽ��ϴ�.");
					textField.setText("");
					textField_1.setText("");
					comboBox.setSelectedIndex(0);
				}
			} else if (e.getActionCommand().equals("Ȯ��")) {
				// ���̵� üũ�� ���������� �̷���� ��쿡�� ã�� ����(���̵� �����ϴ� ���)
				if (flag) {
					Human h = new Human(id, choice, answer);
					String password = pw.findPW(h);
					if (password != null) {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� " + password + " �Դϴ�.");
					} else {
						JOptionPane.showMessageDialog(null, "��й�ȣ ������ �亯�� Ȯ�����ּ���.");
						textField_1.setText("");
						comboBox.setSelectedIndex(0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "���̵� Ȯ�κ��� ���ּ���.");
					textField.setText("");
					textField_1.setText("");
					comboBox.setSelectedIndex(0);
				}
			}
		}

	}

}
