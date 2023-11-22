/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea_rsa;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author SFAR
 */
public class Interfaz_RSA extends JFrame implements ActionListener{
    private JButton btnCifrar;
    private JLabel jblInfo, jlbTitulo, jlbCifrado, jlbDescifrado;
    private JTextField txtMensaje;
    private JTextArea txtCifrado, txtDescifrado;
    private JScrollPane scl;
    
    private String mensaje = "";
    
    public Interfaz_RSA(){
        configurarventana();
        iniciarComponentes();
    }
    
    private void configurarventana(){
        this.setLayout(null);
        this.setBounds(100, 100, 700, 600);
        this.setTitle("Cifrado RSA");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    private void iniciarComponentes(){
        btnCifrar = new JButton("Cifrar");
        btnCifrar.setBounds(450, 90, 130, 45);
        this.add(btnCifrar);
        btnCifrar.addActionListener(this);
        
        jblInfo = new JLabel("Mensaje a cifrar:");
        jblInfo.setBounds(40, 100, 200, 30);
        this.add(jblInfo);
        
        jlbTitulo = new JLabel("Cifrado RSA");
        jlbTitulo.setBounds(200, 20, 300, 50);
        jlbTitulo.setFont(new Font("Serif", Font.PLAIN, 50));
        this.add(jlbTitulo);
        
        jlbCifrado = new JLabel("Mensaje cifrado: ");
        jlbCifrado.setBounds(40, 150, 200, 30);
        this.add(jlbCifrado);
        
        jlbDescifrado = new JLabel("Mensaje descifrado: ");
        jlbDescifrado.setBounds(450, 150, 200, 30);
        this.add(jlbDescifrado);
        
        txtMensaje = new JTextField();
        txtMensaje.setBounds(150, 100, 280, 30);
        this.add(txtMensaje);
        
        txtCifrado = new JTextArea();
        txtCifrado.setLineWrap(true);

        scl = new JScrollPane(txtCifrado, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scl.setBounds(40, 190, 370, 350);

        this.add(scl);
        
        txtDescifrado = new JTextArea();
        txtDescifrado.setBounds(450, 190, 220, 100);
        txtDescifrado.setLineWrap(true);
        this.add(txtDescifrado);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("".equals(txtMensaje.getText())){
            JOptionPane.showMessageDialog(null,"Campo vacío");
        }else{
            String mensajeCompleto = "";
            mensaje = txtMensaje.getText();
            Cifrado_RSA rsa = new Cifrado_RSA(512);
            rsa.generarPrimos();

            // Generar claves pública y privada
            rsa.generarClaves();

            // Cifrar el mensaje
            BigInteger[] mensajeCifrado = rsa.cifrar(mensaje);

            // Mostrar el mensaje cifrado
            for (BigInteger cifrado : mensajeCifrado) {
                mensajeCompleto += cifrado + " ";
            }
            txtCifrado.setText(mensajeCompleto);

            // Descifrar el mensaje
            String mensajeDescifrado = rsa.descifrar(mensajeCifrado);

            // Mostrar el mensaje descifrado
            txtDescifrado.setText(mensajeDescifrado);
            
        }
    }
    
}
