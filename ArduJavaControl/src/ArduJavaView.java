import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.text.*;

public class ArduJavaView extends JFrame implements ActionListener{
    //  *** Declaração dos Atributos ***
    
    ControlePorta arduino = new ControlePorta("COM3", 9600);//instanciando o java
    
    public static Container ctnPrincipal;
    
    public static String stLegenda[] = {"Hora","Onda Verde","Modo Normal","Manual", "Administrador"};
    
    public static JLabel lblimg,lblFarol[],lblTexto;
    
    public static JButton btnAbout,btnModo[];
    
    public static JDesktopPane dksImg;
    
    public static ImageIcon FarolVerde,FarolVermelho,FotoAbout;
    
    
    public ArduJavaView(){
    
        // *** Metodo Construtor ***
       super("ArduJava - Onda Verde");
       //Container
       ctnPrincipal = new Container();
       ctnPrincipal.setLayout(null);
       this.add(ctnPrincipal);
       
       
       FarolVerde = new ImageIcon("img/FarolVerde.png");
       FarolVermelho = new ImageIcon("img/FarolVermelho.png");
       FotoAbout = new ImageIcon("img/ImagemAbout.jpg"); 
       
       //Botões
       btnAbout = new JButton("About");
       btnAbout.setBounds(515,5,70,30);
       btnAbout.setBorder(null);
       btnAbout.addActionListener(this);
       ctnPrincipal.add(btnAbout);
       
       //instanciando os botões que ativa os modos dentro do FOR
       btnModo = new JButton[3];
       for(int i=0; i<3; i++){
       btnModo[i] = new JButton("Ativar");
       btnModo[i].setBounds(75+(i*200),490,70,15);
       btnModo[i].addActionListener(this);
       btnModo[i].setBorder(null);
       ctnPrincipal.add(btnModo[i]);
       }
       
       //Labels
       lblFarol = new JLabel[3];
       for(int i=0; i<3; i++){
       lblFarol[i]= new JLabel(FarolVermelho);
       lblFarol[i].setBounds(60+(i*200),360,100,100);
       ctnPrincipal.add(lblFarol[i]);
       }
       
        String urlimg = "img/ImagemDeFundoArduJava.jpg";
        try {
            JImagePanel imgFundo = new JImagePanel(urlimg);
            imgFundo.setBounds(0,0,600,600);
            ctnPrincipal.add(imgFundo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inciar a imagem de fundo do programa");
        }
       
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Comando para encerrar o programa ao fecha-lo
       this.setSize(600,625);
       this.setResizable(false);//tirando o maximizar do programa
       this.show();
       this.setLocationRelativeTo(null);//iniciando o programa no centro da tela
    }//fechando Construtor
    
    
    public void actionPerformed (ActionEvent evt){
       
        if(evt.getSource()==btnAbout){
           
            lblTexto = new JLabel();
            lblTexto.setText("<html><center>--> Projeto Onda Verde <--<br><br>"
                   + "Sistema desenvolvido para controlar a sicronização dos farois<br>"
                   + "Desenvolvido pelo grupo 'Onda Verde' do 4°Semestre do curso de Ciência da Computação - Universidade Guarulhos (UNG)<br>"
                   + "o grupo esta formado pelos seguintes integrantes:<br><br>"
                   + "Gabriel Pereira Santos<br>"
                   + "João Paulo Amaral Melo<br>"
                   + "José Islan Rodrigues Galvão<br>"
                   + "Cainã Sena da Silva<br>"
                   + "José Abrain Alves Nascimento<br><br>"
                   + "Ao útilizar esse projeto, é nescessário citar que a fonte veio através destes nomes.<br>"
                   + " -- Juntos faremos um trânsito mais Tecnológico, Eficiente e Seguro --</center><br><br></html>");
            lblTexto.setHorizontalAlignment(JLabel.CENTER);
            JOptionPane.showMessageDialog(null,lblTexto,"About",JOptionPane.PLAIN_MESSAGE);
       
          
        }
        else if(evt.getSource()==btnModo[0]){
            if(lblFarol[0].getIcon()==FarolVermelho){
                ativarModos(1);
            }else{
                desativarModos(1);
            }
        }//fechando ação do btnNormal
        else if(evt.getSource()==btnModo[1]){
            if(lblFarol[1].getIcon()==FarolVermelho){
               ativarModos(2);
            }else{
                desativarModos(2);
            }
        }//fechando ação do btnOndaVerde
        else if(evt.getSource()==btnModo[2]){
            if(lblFarol[2].getIcon()==FarolVermelho){
                ativarModos(3);
            }else{
                desativarModos(3);
            }
        }//fechando ação do btnPiscante
    }//fechando ActionPerformed
    
    public void ativarModos(int modo){
        
        if (modo ==1){//Ativar modo Onda Verde
            lblFarol[0].setIcon(FarolVerde);
            lblFarol[1].setIcon(FarolVermelho);
            lblFarol[2].setIcon(FarolVermelho);
            btnModo[0].setText("Desativar");
            btnModo[1].setText("Ativar");
            btnModo[2].setText("Ativar");
            arduino.enviaDados(1);
        }
        else if (modo == 2){//Ativar modo Normal
            lblFarol[0].setIcon(FarolVermelho);
            lblFarol[1].setIcon(FarolVerde);
            lblFarol[2].setIcon(FarolVermelho);
            btnModo[0].setText("Ativar");
            btnModo[1].setText("Desativar");
            btnModo[2].setText("Ativar");
            arduino.enviaDados(2);
        }
         else if (modo == 3){//Ativar modo Piscante
            lblFarol[0].setIcon(FarolVermelho);
            lblFarol[1].setIcon(FarolVermelho);
            lblFarol[2].setIcon(FarolVerde);
            btnModo[0].setText("Ativar");
            btnModo[1].setText("Ativar");
            btnModo[2].setText("Desativar");
            arduino.enviaDados(3);
        }
    }
    
    public void desativarModos(int modo){
        
        if (modo ==1){//desativar modo Onda Verde
            lblFarol[0].setIcon(FarolVermelho);
            lblFarol[1].setIcon(FarolVermelho);
            lblFarol[2].setIcon(FarolVermelho);
            btnModo[0].setText("Ativar");
            btnModo[1].setText("Ativar");
            btnModo[2].setText("Ativar");
            arduino.enviaDados(4);
        }
        else if (modo == 2){//desativar modo Normal
            lblFarol[0].setIcon(FarolVermelho);
            lblFarol[1].setIcon(FarolVermelho);
            lblFarol[2].setIcon(FarolVermelho);
            btnModo[0].setText("Ativar");
            btnModo[1].setText("Ativar");
            btnModo[2].setText("Ativar");
            arduino.enviaDados(4);
        }
        else if (modo == 3){//desativar modo Piscante
            lblFarol[0].setIcon(FarolVermelho);
            lblFarol[1].setIcon(FarolVermelho);
            lblFarol[2].setIcon(FarolVermelho);
            btnModo[0].setText("Ativar");
            btnModo[1].setText("Ativar");
            btnModo[2].setText("Ativar");
            arduino.enviaDados(4);
        }
    }
    
    public class JImagePanel extends JPanel{
        BufferedImage background = null;

        public JImagePanel(BufferedImage img){

            if (img == null)
                throw new NullPointerException("Buffered image cannot be null!");
            this.background = img;
        }

        public JImagePanel(File imgSrc) throws IOException{

            this(ImageIO.read(imgSrc));
        }

        public JImagePanel(String fileName) throws IOException{
            this(new File(fileName));
        }
        protected void paintComponent(Graphics g){        
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
            g2d.dispose();        
        }    
        
        public class xxw implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }
        public void keyPressed(KeyEvent e) {
        }
        public void keyReleased(KeyEvent e) {
            if (((JTextField)e.getComponent()).getText().length() == 10)
                e.getComponent().transferFocus();
        }
    }
    }//fechando Class Image Panel
}//fechando Class Principal
