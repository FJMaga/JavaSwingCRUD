package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Conexion;
import model.User;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Principal extends JFrame{
	private JFrame frame;
	private JTable table;
	private JTextField nombretxt;
	private JTextField passwordtxt;
	private JTextField estadotxt;
	
	Connection miConexion = new Conexion().conectar();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() {

		inicializar();
		verUsuarios(); // showUser()
		
	}
	
	public ArrayList<User> listaUsuarios(){
		ArrayList<User> usuarios = new ArrayList<User>();
		
		try {
			Statement st = miConexion.createStatement();
			ResultSet rs= st.executeQuery("SELECT * FROM user");
			
			User usuario;
			
			while(rs.next()) { // next() devuelve un true o un false, entonces mientras true haz.
				usuario = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				usuarios.add(usuario);
			}// cierra while
			
			st.close();
			rs.close();
			miConexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;
		
	}//cierra listaUsuarios()
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void inicializar() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Acceso OK");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(240, 347, 77, 67);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBounds(39, 60, 196, 267);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "Usuario", "Pass", "Esatdo"
			}
		));
		table.setBounds(26, 11, 146, 233);
		panel.add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(127, 255, 212));
		panel_1.setBounds(315, 60, 196, 267);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(10, 11, 46, 14);
		panel_1.add(nombre);
		
		nombretxt = new JTextField();
		nombretxt.setBounds(85, 8, 67, 14);
		panel_1.add(nombretxt);
		nombretxt.setColumns(10);
		
		JLabel password = new JLabel("Contrase\u00F1a");
		password.setBounds(10, 36, 56, 14);
		panel_1.add(password);
		
		passwordtxt = new JTextField();
		passwordtxt.setBounds(85, 33, 67, 14);
		panel_1.add(passwordtxt);
		passwordtxt.setColumns(10);
		
		JLabel estado = new JLabel("Estado");
		estado.setBounds(10, 61, 46, 14);
		panel_1.add(estado);
		
		estadotxt = new JTextField();
		estadotxt.setBounds(85, 58, 67, 14);
		panel_1.add(estadotxt);
		estadotxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Nuevo Usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(39, 7, 103, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(228, 7, 89, 23);
		getContentPane().add(btnEliminar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnActualizar.setBounds(422, 7, 89, 23);
		getContentPane().add(btnActualizar);
		
	}//cierra inicialize
	
	private void verUsuarios() { // carga un modelo con los ususarios del arrayList,pero falta rellenar la tabla con el modelo
		System.out.println("llamada a ver usuarios");
		ArrayList<User> usuariosParaTabla = listaUsuarios();
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		Object fila[] = new Object[4];
		
		for (int i = 0; i < usuariosParaTabla.size(); i++) {
			fila[0]=usuariosParaTabla.get(i).getid();
			fila[1]=usuariosParaTabla.get(i).getusername();
			fila[2]=usuariosParaTabla.get(i).getpassword();
			fila[3]=usuariosParaTabla.get(i).getuser_status();
			
			modelo.addRow(fila);
		}
		
	}
}//cierra el class
