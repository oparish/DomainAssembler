package data;

public enum DomainField
{
	ZONING_OBJECT("Zoning Object"), ZONING_FIELD("Zoning Field"), 
	UP_EXIT("Up Exit"), DOWN_EXIT("Down Exit"), NORTH_EXIT("North Exit"), 
	SOUTH_EXIT("South Exit"), 	EAST_EXIT("East Exit"), WEST_EXIT("West Exit"), 
	ZONING_NAME("Zoning Name");
	
	private final String displayName;
	
	public String getDisplayName() {
		return displayName;
	}

	DomainField(String displayName)
	{
		this.displayName = displayName;
	}
}
