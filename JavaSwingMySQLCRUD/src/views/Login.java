package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Conexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login  {

	private JFrame frame;
	private JTextField Usuario_text;
	private JTextField Password_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(83, 61, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		Usuario_text = new JTextField();
		Usuario_text.setBounds(210, 58, 179, 20);
		frame.getContentPane().add(Usuario_text);
		Usuario_text.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(83, 117, 76, 14);
		frame.getContentPane().add(lblContrasea);
		
		Password_text = new JTextField();
		Password_text.setBounds(210, 114, 179, 20);
		frame.getContentPane().add(Password_text);
		Password_text.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("botón reset pulsado");
				Usuario_text.setText("");
				Password_text.setText("");
			}
		});
		btnReset.setBounds(210, 169, 76, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("botón login pulsado");
				acceder();
			}
		});
		btnLogin.setBounds(318, 169, 71, 23);
		frame.getContentPane().add(btnLogin);
		
		
	}
	
	private void acceder(){
		System.out.println("método acceder");
		Connection miconexion = new Conexion().conectar();
		
		String consulta = "SELECT * FROM user where username=? AND password=?";
		try {
			PreparedStatement ps = miconexion.prepareStatement(consulta);
			ps.setString(1, Usuario_text.getText());
			ps.setString(2, Password_text.getText());
			
			ResultSet rs = ps.executeQuery();
			
			//System.out.println(rs.next()); // comprueba si elusuario existe
			
			if (rs.next()) {
				Principal p = new Principal();
				p.setVisible(true); //mejor que show() que esta depreciado
			
				
			}else {
				JOptionPane.showMessageDialog(null, "Login no valido");
			}
			
			ps.close();
			rs.close();
			miconexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
