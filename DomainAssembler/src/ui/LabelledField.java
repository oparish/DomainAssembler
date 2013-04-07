package ui;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class LabelledField extends Panel
{
	private JTextField textField;
	private JLabel label;
	
	public LabelledField(String labelText)
	{
		super();
		this.label = new JLabel(labelText);
		this.textField = new JTextField();
		this.layoutComponents();
	}
	
	private void layoutComponents()
	{
		this.setLayout(new GridLayout(1,2));
		this.add(this.label);
		this.add(this.textField);
	}
	
	public void setText(String text)
	{
		this.textField.setText(text);
	}
	
	public String getText()
	{
		return this.textField.getText();
	}
}
