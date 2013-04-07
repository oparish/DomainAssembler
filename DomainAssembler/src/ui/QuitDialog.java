package ui;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static ui.ButtonType.NO;
import static ui.ButtonType.YES;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class QuitDialog extends JDialog
{
	private static final String WORK_NOT_SAVED = "Work Not Saved";
	private static final String QUIT_WITHOUT_SAVING = "Quit Without Saving?";
	
	public QuitDialog(JFrame parentFrame)
	{
		super(parentFrame, WORK_NOT_SAVED);
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(QUIT_WITHOUT_SAVING), getLabelConstraints());
		this.add(new MyButton(YES), getButtonConstraints(0));
		this.add(new MyButton(NO), getButtonConstraints(1));
		this.setSize(300, 200);
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
}
