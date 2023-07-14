package iff.poo.projetos.emprestimo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EmprestimoAddView {
	
	public static void main(String[] args) {
		EmprestimoController ec = new EmprestimoController();
		
		JFrame frame = new JFrame("Inserir livro");
		frame.setLayout(null);
		frame.setSize(400, 300);
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(10, 10, 100, 30);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 50, 100, 30);
		
		JTextField tfTitulo = new JTextField();
		tfTitulo.setBounds(100, 10, 100, 30);
		
		JTextField tfIsbn = new JTextField();
		tfIsbn.setBounds(100, 50, 100, 30);
		
		JButton btn = new JButton("Salvar");
		btn.setBounds(100, 100, 80, 30);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage());
				}
			}
		});
		
		frame.add(lblTitulo);
		frame.add(lblIsbn);
		frame.add(tfTitulo);
		frame.add(tfIsbn);
		frame.add(btn);
		
		frame.setVisible(true);
		

	}

}