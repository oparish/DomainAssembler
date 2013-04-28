package ui;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static javax.swing.SwingConstants.CENTER;
import static ui.ButtonType.NO;
import static ui.ButtonType.QUIT;
import static ui.ButtonType.YES;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class QuitDialog extends MyDialog
{
	private static final String WORK_NOT_SAVED = "Work Not Saved";
	private static final String QUIT_WITHOUT_SAVING = "Quit Without Saving?";
	
	public QuitDialog(MainWindow mainWindow)
	{
		super(mainWindow, WORK_NOT_SAVED);
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(QUIT_WITHOUT_SAVING, CENTER), getLabelConstraints());
		MyButton yesButton = new MyButton(YES);
		this.add(yesButton, getButtonConstraints(0));
		yesButton.addActionListener(this);
		MyButton noButton = new MyButton(NO);
		this.add(noButton, getButtonConstraints(1));
		noButton.addActionListener(this);
	}
	
	private GridBagConstraints getLabelConstraints()
	{
		GridBagConstraints labelConstraints = new GridBagConstraints();
		labelConstraints.gridx = 0;
		labelConstraints.gridy = 0;
		labelConstraints.weightx = 1;
		labelConstraints.weighty = 1;
		labelConstraints.fill = HORIZONTAL;
		labelConstraints.insets = new Insets(0,5,0,5);
		labelConstraints.gridwidth = 2;
		return labelConstraints;
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
				mainWindow.setVisible(false);
				break;
			default:
			}
		}
		this.setVisible(false);
		
	}
}
