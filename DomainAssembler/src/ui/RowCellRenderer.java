package ui;

import static data.DomainField.ZONING_NAME;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import data.DomainField;
import data.DomainRow;

@SuppressWarnings("serial")
public class RowCellRenderer extends DefaultListCellRenderer
{
	private static RowCellRenderer rowCellRenderer = new RowCellRenderer();
	public static RowCellRenderer getInstance()
	{
		return RowCellRenderer.rowCellRenderer;
	}
	
	public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
	{
		DomainRow domainRow = (DomainRow) value;
		return super.getListCellRendererComponent(list, domainRow.getFieldValue(ZONING_NAME), index, isSelected, cellHasFocus);
	}
}
