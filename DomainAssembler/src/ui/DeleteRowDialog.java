package ui;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static javax.swing.SwingConstants.CENTER;
import static ui.ButtonType.NO;
import static ui.ButtonType.YES;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DeleteRowDialog extends MyDialog
{
	private static final String ARE_YOU_SURE = "Are you sure?";
	private static final String REALLY_DELETE_THIS_ROW = "Really delete this row?";
	
	public DeleteRowDialog(MainWindow mainWindow)
	{
		super(mainWindow, ARE_YOU_SURE);
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(REALLY_DELETE_THIS_ROW, CENTER), this.getLabelConstraints());
		MyButton yesButton = new MyButton(YES);
		this.add(yesButton, this.getButtonConstraints(0));
		yesButton.addActionListener(this);
		MyButton noButton = new MyButton(NO);
		this.add(noButton, this.getButtonConstraints(1));
		noButton.addActionListener(this);
	}
	
	private GridBagConstraints getLabelConstraints()
	{
		GridBagConstraints nameFieldConstraints = new GridBagConstraints();
		nameFieldConstraints.gridx = 0;
		nameFieldConstraints.gridy = 0;
		nameFieldConstraints.weightx = 1;
		nameFieldConstraints.weighty = 1;
		nameFieldConstraints.fill = HORIZONTAL;
		nameFieldConstraints.insets = new Insets(0,5,0,5);
		nameFieldConstraints.gridwidth = 2;
		return nameFieldConstraints;
	}
	
	private GridBagConstraints getButtonConstraints(int xpos)
	{
		GridBagConstraints buttonFieldConstraints = new GridBagConstraints();
		buttonFieldConstraints.gridx = xpos;
		buttonFieldConstraints.gridy = 1;
		buttonFieldConstraints.weightx = 1;
		buttonFieldConstraints.weighty = 1;
		return buttonFieldConstraints;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		MainWindow mainWindow = this.getMainWindow();
		if (source instanceof MyButton)
		{
			MyButton sourceButton = (MyButton) source;

			switch(sourceButton.getType())
			{
			case YES:
				mainWindow.deleteRow();
				break;
			default:
			}
		}
		this.setVisible(false);
	}
}
