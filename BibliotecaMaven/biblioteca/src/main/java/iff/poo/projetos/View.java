package iff.poo.projetos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;

import iff.poo.projetos.controller.EmprestimoController;
import iff.poo.projetos.controller.LivroController;
import iff.poo.projetos.controller.UsuarioController;

public class View {
	public static void main(String[] args) {
		LivroController lc = new LivroController();
		UsuarioController uc = new UsuarioController();
        EmprestimoController ec = new EmprestimoController();
		
		JFrame frame = new JFrame("Tela Inicial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());

        ImageIcon logo = new ImageIcon("BibliotecaMaven/biblioteca/logo.png");
        Image imagem = logo.getImage();
        Image imagemRedimensionada = imagem.getScaledInstance(-1, 100, Image.SCALE_SMOOTH);
        logo = new ImageIcon(imagemRedimensionada);
        JLabel imagemLabel = new JLabel();
        imagemLabel.setIcon(logo);

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(250,250));

        JButton b_usuarios = new JButton("Usuários", null);
        JButton b_livros = new JButton("Livros", null);
        JButton b_emprestimos = new JButton("Empréstimos", null);
        b_usuarios.setAlignmentX(Component.CENTER_ALIGNMENT);
        b_livros.setAlignmentX(Component.CENTER_ALIGNMENT);
        b_emprestimos.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        panel.add(imagemLabel);
        panel.add(b_usuarios);
        panel.add(b_emprestimos);
        panel.add(b_livros);
        panel.add(Box.createVerticalGlue());

        frame.add(panel, BorderLayout.WEST);
        
		frame.setVisible(true);
	}

}

