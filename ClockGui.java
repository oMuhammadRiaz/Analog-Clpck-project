package project;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.*;
import java.time.LocalTime;
import javax.swing.Timer;
public class ClockGUI extends JPanel implements Runnable
{
	private int seconds;
	private int minutes;
	private int hours;
	private double angle=Math.PI/2;
	 private int length;
	double x=1;
	LocalDate localDate = LocalDate.now();
    
	ClockGUI(int length)
	{
		this.length=length;
		this.startClock();
		
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics gui)
	{
		
		LocalTime n=LocalTime.now();
		//Current Seconds
		this.seconds=n.getSecond();
		
		//Current Minutes
		this.minutes=n.getMinute();
		
		//Current Hours
		this.hours=n.getHour();
		
		
		Graphics2D gui2D=(Graphics2D)gui;        //Casting gui to Graphics2D in order to use methods of 2D components
		
		 //Creates a grey Rectangle, with 430x430 width and height
		gui2D.setColor(Color.GRAY);
		gui2D.fillRect(0,0,430,430);
		
		//Creates a Dark Gray Circle with 415 radius
		
                gui2D.setColor(Color.BLACK);
		gui2D.fillOval(0, 0, 415, 415);
		
		//Creates the second hand component
	    gui2D.setColor(Color.DARK_GRAY);
		gui2D.fillOval(68,145, 110, 110);
		gui2D.translate(123, 200);
		gui2D.setColor(Color.WHITE);
		for(int i=0;i<60;i++)
		{
			gui2D.setStroke(new BasicStroke(1));
			gui2D.drawLine(0,-45,0,-50);
			gui2D.rotate(Math.PI/30);
		}
		//Rotates the second hand in the smaller component
		gui2D.setStroke(new BasicStroke(2));
		gui2D.setColor(Color.white);
		gui2D.rotate(seconds*Math.PI/30);
		gui2D.drawLine(0, 0, 0, -45);
		gui2D.rotate(2*Math.PI-(seconds*Math.PI/30));
		gui2D.translate(-123, -200);
		
		//Component to show the current date
		gui2D.drawRect(310, 190, 20, 20);
		 Font f2 = new Font("Onyx", Font.BOLD, 20);
	        gui2D.setFont(f2);
	       //Draws the current date
		gui2D.drawString(DateTimeFormatter.ofPattern("dd").format(localDate), 315, 207);
		
		gui2D.setColor(Color.WHITE);
		gui2D.translate(200,200);//Move the co-ordinates by 200
		
		//Create the markers for Hours
		for(int i=0;i<12;i++)
		{
			gui2D.setStroke(new BasicStroke(6));
			gui2D.drawLine(0, -160, 0, -190);//Draws a small line
			gui2D.rotate(Math.PI/6);//Rotates the translation so that a line is drawn after every 30 degrees
		}
		
		//Second Hand
		
		gui2D.setStroke(new BasicStroke(2));
		gui2D.setColor(Color.RED);
		gui2D.rotate(seconds*Math.PI/30);//Rotates the translation by 6 degrees
		gui2D.drawLine(0,0,0,-160);//Draws the second hand and rotates it each time this function is called
		
		//Minute Hand
		gui2D.setColor(Color.WHITE);
		gui2D.rotate(2*Math.PI-(seconds*Math.PI/30));//Rotates the circle back to initial position
		gui2D.rotate(minutes*Math.PI/30);//Then rotates it for exactly a minute mark
		gui2D.setStroke(new BasicStroke(4));//Increase the width of the stroke from pen for the minute hand
		gui2D.drawLine(0,0,0,-150);//draws the minute hand
		
		//Hour Hand
		gui2D.setColor(Color.WHITE);
		gui2D.rotate(2*Math.PI-minutes*Math.PI/30);
		gui2D.rotate(hours*Math.PI/6);
		gui2D.setStroke(new BasicStroke(6));
		gui2D.drawLine(0, 0, 0, -100);
		
		//Writing Casio as a brand
		gui2D.setColor(Color.gray);
		int fontSize = 30;
        Font f = new Font("Onyx", Font.BOLD, fontSize);
        gui2D.setFont(f);
		gui2D.rotate(2*Math.PI-hours*Math.PI/6);
		gui2D.drawString("TIMEX",-25,140);
		
		gui2D.translate(-200, -200);
		
		//Create Rectangle for the pendulum
		gui2D.setColor(Color.GRAY);
	    gui2D.fillRect(0, 415, 440, 430);
	    
	  //Creating the pendulum
	    gui2D.setColor(Color.BLACK); 
	    gui2D.setStroke(new BasicStroke(10));
	    int anchorX = 400 / 2, anchorY = 420;
	    int ballX =10+ anchorX + (int) (Math.sin(angle/2) * length);
	    int ballY = 10+anchorY + (int) (Math.cos(angle/2) * length);
	    gui2D.drawLine(anchorX, anchorY, ballX, ballY);
	    gui2D.fillOval(anchorX - 3, anchorY - 4, 7, 7);
	    gui2D.fillOval(ballX - 25, ballY - 10, 50,40);
		    
	
		  
            
		//Disposing to destroy all the componenets	
		gui2D.dispose();
		
	}
	public void run() {//Logic for the pendulum
	    double angleAccel, angleVelocity = 0, dt = 0.1;
	    while (true) {
	      angleAccel = -9.81 / length * Math.sin(angle);
	      angleVelocity += angleAccel * dt;
	      angle += angleVelocity * dt;
	      repaint();
	      try {
	        Thread.sleep(15);
	      } catch (InterruptedException ex) {
	      }
	    }
	  }//Starts the timer 
		 public void startClock() {
	            Timer timer = new Timer(500, new ActionListener() {
	            
	                public void actionPerformed(ActionEvent e) {
	                    repaint();
	                }
	            });
	            timer.start();
	        }

		
		
		
		
		
	}
