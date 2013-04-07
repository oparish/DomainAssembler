package ui;

import javax.swing.JButton;

public class MyButton extends JButton
{
	private final ButtonType type;
	
	public ButtonType getType() {
		return type;
	}

	public MyButton(ButtonType buttonType)
	{
		super(buttonType.getName());
		this.type = buttonType;
	}
}
