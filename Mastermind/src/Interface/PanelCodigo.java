/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;



import javax.swing.*;

/**
 *
 * @author 2im3q
 */
//panel para anidar a los botones de selecccion de cada digito del codigo
public class PanelCodigo extends JPanel{
    
    private  ButtonGroup grupo;
    
    public PanelCodigo(String titulo) {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        //generacion del grupo de botones
        grupo= new ButtonGroup();
        int seleccionar= (int)(Math.random()*8+1);
        for (int i = 0; i < 8; i++) {
            JRadioButton boton=new JRadioButton(String.valueOf(i+1));
            boton.setActionCommand(String.valueOf(i+1));
            add(boton);
            grupo.add(boton);
            boton.setSelected((i+1)==seleccionar);
        }
        
        
    }
    
    //regresa el numero seleccionado del grupo
    public int getSeleccion(){
        ButtonModel miboton=grupo.getSelection();
        String num=miboton.getActionCommand();
        int n=Integer.parseInt(num);
        return n;
    }
    
}
