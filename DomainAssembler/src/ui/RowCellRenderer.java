package ui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import data.DomainRow;

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
		return super.getListCellRendererComponent(list, domainRow.getZoningName(), index, isSelected, cellHasFocus);
	}
}
