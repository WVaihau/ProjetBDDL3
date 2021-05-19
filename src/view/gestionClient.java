package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class gestionClient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestionClient frame = new gestionClient();
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
	public gestionClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("R\u00E9ceptionniste");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(480, 23, 139, 25);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 64, 981, 2);
		contentPane.add(separator);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(116, 176, 843, 397);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_523663826086600");
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Nom : ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(10, 60, 51, 21);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 10, 74, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Mail : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(10, 113, 62, 21);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("T\u00E9l\u00E9phone : ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_5.setBounds(10, 158, 94, 26);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Rue :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_6.setBounds(10, 210, 45, 13);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Ville : ");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_7.setBounds(10, 250, 74, 26);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Code Postal : ");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_8.setBounds(10, 301, 145, 26);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Date Abonnement : ");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_9.setBounds(10, 357, 166, 30);
		panel.add(lblNewLabel_9);
		
		textField = new JTextField();
		textField.setBounds(92, 14, 275, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(71, 61, 296, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(71, 111, 296, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(107, 162, 260, 26);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(59, 205, 308, 30);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(59, 254, 308, 26);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(121, 305, 246, 26);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(166, 359, 201, 33);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton = new JButton("Inscription ");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(533, 90, 201, 64);
		panel.add(btnNewButton);
		
		JButton btnMiseJour = new JButton("Mise \u00E0 Jour ");
		btnMiseJour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMiseJour.setBounds(533, 230, 201, 64);
		panel.add(btnMiseJour);
		
		JPanel panel_1 = new JPanel();
		layeredPane.add(panel_1, "name_523969697263900");
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des veicules ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(lblNewLabel_2);
		
		JButton client = new JButton("Gestion des Clients ");
		client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				layeredPane.add(client);
				layeredPane.repaint();
				layeredPane.revalidate();	
			}
		});
		client.setFont(new Font("Tahoma", Font.PLAIN, 17));
		client.setBounds(219, 86, 227, 58);
		contentPane.add(client);
		
		JButton vehicule = new JButton("Gestion des V\u00E9hicules ");
		vehicule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				layeredPane.add(vehicule);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		vehicule.setFont(new Font("Tahoma", Font.PLAIN, 17));
		vehicule.setBounds(588, 76, 232, 58);
		contentPane.add(vehicule);
	}
}
