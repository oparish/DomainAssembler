package ui;

import static java.awt.GridBagConstraints.HORIZONTAL;
import static ui.ButtonType.CANCEL;
import static ui.ButtonType.SUBMIT;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AddRowDialog extends MyDialog
{
	private final static String ADDING_A_NEW_ROW = "Adding a New Row.";
	private final static String NEW_ROW_NAME = "New Row Name:";
	
	private LabelledField nameField;
	
	public AddRowDialog(MainWindow mainWindow)
	{
		super(mainWindow, ADDING_A_NEW_ROW);
		this.setLayout(new GridBagLayout());
		this.nameField = new LabelledField(NEW_ROW_NAME);
		this.add(this.nameField, this.getNameFieldConstraints());
		MyButton submitButton = new MyButton(SUBMIT);
		this.add(submitButton, this.getButtonConstraints(0));
		submitButton.addActionListener(this);
		MyButton cancelButton = new MyButton(CANCEL);
		this.add(cancelButton, this.getButtonConstraints(1));
		cancelButton.addActionListener(this);
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
			case SUBMIT:
				mainWindow.addRow(nameField.getText());
				break;
			default:
			}
		}
		this.setVisible(false);
	}
}
