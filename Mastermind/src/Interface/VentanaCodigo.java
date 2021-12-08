/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author 2im3q
 */
public class VentanaCodigo extends JFrame{
    
    private int[] codigo=new int[4];
    private PanelCodigo numero1,numero2,numero3,numero4;
    
    //el constructor requiere un codigo por si se cancela nos devuelva el codigo oiginal
    public VentanaCodigo(int[] codigo) {
        setTitle("Ingrese un codigo");
        setBounds(500, 200, 350, 350);
        setVisible(true);
        this.codigo=codigo;
        
        JPanel panelPrin=new JPanel();
        panelPrin.setLayout(new GridLayout(1,4));
        
        //inicializamos y agregamos los paneles que almacenaran las opciones de
        //seleccion de nuestro codigo
        numero1 = new PanelCodigo("Numero 1");
        panelPrin.add(numero1);
        numero2 = new PanelCodigo("Numero 2");
        panelPrin.add(numero2);
        numero3 = new PanelCodigo("Numero 3");
        panelPrin.add(numero3);
        numero4 = new PanelCodigo("Numero 4");
        panelPrin.add(numero4);
        
        // creamos el panel inferior donde iran los botones
        JPanel panelInf=new JPanel();
        JButton cancelar=new JButton("Cancelar");
        JButton ingresar=new JButton("Ingresar");
        cancelar.setSize(70,20);
        ingresar.setSize(70,20);
        
        //agregamos las acciones a los botones
        ingresar.addActionListener(new Ingresa());
        cancelar.addActionListener(new Cancela());
        
        //agregamos los elementos
        panelInf.add(cancelar);
        panelInf.add(ingresar);
        add(panelInf,BorderLayout.SOUTH);
        add(panelPrin,BorderLayout.CENTER);
    }

    public int[] getCodigo() {
        return codigo;
    }

    public void setCodigo(int[] codigo) {
        this.codigo = codigo;
    }
    
    //metods para las acciones de los botones
    private class Ingresa implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            getCodigo()[0]=numero1.getSeleccion();
            getCodigo()[1]=numero2.getSeleccion();
            getCodigo()[2]=numero3.getSeleccion();
            getCodigo()[3]=numero4.getSeleccion();
            dispose();
        }
        
    }
    public class Cancela implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    
    }
    
    
}
