package ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public abstract class MyDialog extends JDialog implements ActionListener
{
	public MyDialog(MainWindow mainWindow, String title)
	{
		super(mainWindow, title);
		this.setSize(500, 200);
		int mainX = mainWindow.getX();
		int mainY = mainWindow.getY();
		int mainWidth = mainWindow.getWidth();
		int mainHeight = mainWindow.getHeight();
		int newX = mainX + (mainWidth/2) - 250;
		int newY = mainY + (mainHeight/2) - 100;
		this.setLocation(newX, newY);
	}
	
	protected MainWindow getMainWindow()
	{
		return (MainWindow) this.getOwner();
	}
}
