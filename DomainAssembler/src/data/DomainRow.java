package data;

import static data.DomainField.ZONING_NAME;

import java.util.HashMap;

public class DomainRow
{
	private HashMap<DomainField, String> fieldMap;
	
	public DomainRow(String name)
	{
		this.fieldMap = new HashMap<DomainField, String>();
		for (DomainField field: DomainField.values())
		{
			if (field == ZONING_NAME)
				this.fieldMap.put(field, name);
			else
				this.fieldMap.put(field, "");
		}
	}
	
	public void setFieldValue(DomainField key, String value)
	{
		this.fieldMap.put(key, value);
	}
	
	public String getFieldValue(DomainField key)
	{
		return this.fieldMap.get(key);
	}
}
