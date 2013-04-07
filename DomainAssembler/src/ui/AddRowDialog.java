package ui;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static ui.ButtonType.CANCEL;
import static ui.ButtonType.SUBMIT;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AddRowDialog extends JDialog
{
	private final static String ADDING_A_NEW_ROW = "Adding a New Row.";
	private final static String NEW_ROW_NAME = "New Row Name:";
	
	private LabelledField nameField;
	
	public AddRowDialog(JFrame parentFrame)
	{
		super(parentFrame, ADDING_A_NEW_ROW);
		this.setLayout(new GridBagLayout());
		this.nameField = new LabelledField(NEW_ROW_NAME);
		this.add(this.nameField, this.getNameFieldConstraints());
		this.add(new MyButton(SUBMIT), this.getButtonConstraints(0));
		this.add(new MyButton(CANCEL), this.getButtonConstraints(1));
		this.setSize(300, 200);
	}
	
	private GridBagConstraints getNameFieldConstraints()
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
