package ui;

public enum ButtonType
{
	ADD_ROW("Add Row"),
	DELETE_ROW("Delete Row"),
	SAVE_TABLE_AS("Save Table As..."),
	LOAD_TABLE("Load Table"),
	SAVE_TABLE("Save Table"),
	QUIT("Quit"),
	CANCEL("Cancel"),
	SUBMIT("Submit"),
	YES("Yes"),
	NO("No");
	
	private String name;
	ButtonType(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
