package ui;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static javax.swing.BoxLayout.X_AXIS;
import static ui.ButtonType.NO;
import static ui.ButtonType.YES;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DeleteRowDialog extends JDialog
{
	private static final String ARE_YOU_SURE = "Are you sure?";
	private static final String REALLY_DELETE_THIS_ROW = "Really delete this row?";
	
	public DeleteRowDialog(JFrame parentFrame)
	{
		super(parentFrame, ARE_YOU_SURE);
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(REALLY_DELETE_THIS_ROW), this.getLabelConstraints());
		this.add(new MyButton(YES), this.getButtonConstraints(0));
		this.add(new MyButton(NO), this.getButtonConstraints(1));
		this.setSize(300, 200);
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
}
