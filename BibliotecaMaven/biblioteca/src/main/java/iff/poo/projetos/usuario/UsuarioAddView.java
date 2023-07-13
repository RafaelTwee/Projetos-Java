package iff.poo.projetos.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UsuarioAddView {
	
	public static void main(String[] args) {
		
		UsuarioController uc = new UsuarioController();
		UsuarioDAO udao = new UsuarioDAO();
		
		JFrame frame = new JFrame("Inserir usuario");
		frame.setLayout(null);
		frame.setSize(400, 300);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 10, 100, 30);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 50, 100, 30);
		
		JTextField tfNome = new JTextField();
		tfNome.setBounds(100, 10, 100, 30);
		
		JTextField tfCpf = new JTextField();
		tfCpf.setBounds(100, 50, 100, 30);
		
		JButton btn = new JButton("Salvar");
		btn.setBounds(100, 100, 80, 30);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario(tfNome.getText(), tfCpf.getText());
				try {
					uc.inserir(usuario);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			}
		});
		
		frame.add(lblNome);
		frame.add(lblCpf);
		frame.add(tfNome);
		frame.add(tfCpf);
		frame.add(btn);
		
		frame.setVisible(true);

		System.out.println(udao.selecionarPorCpf("15805444798").getNome());
	}

}