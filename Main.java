package project;
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import javax.swing.JFrame;

public class Main {
	
	
	 public Main() {
		 
	        EventQueue.invokeLater(new Runnable() {
	            
	            public void run() {
	            	
	            	
	                try {
	                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Get the look and feel of the platform
	                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	                    ex.printStackTrace();
	                }
	                
	              
	                	
	                  
	                  
	                  try {
	                    Thread.sleep(15);
	                  } catch (InterruptedException ex) {
	                  }
	                
	                  ClockGUI g=new ClockGUI(150);
	                 JFrame frame=new JFrame("Clock");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setSize(430,700);
	                frame.add(g);
	                frame.setResizable(false);
	                new Thread(g).start();
	                frame.setVisible(true);
	                
	                
	                
	                
	               
	            }
	            
	        }
	        );
	    }

	public static void main(String[] args)
	{
	
		new Main();
		
	}}
	 

