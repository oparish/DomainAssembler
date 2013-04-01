package ui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import data.DomainRow;

public class RowList<DomainRow> extends JList
{
	RowList(ArrayList<DomainRow> rows)
	{
		this();
		this.setListData(rows.toArray());
	}
	
	RowList()
	{
		super();
		this.setCellRenderer(RowCellRenderer.getInstance());
		this.setModel(new DefaultListModel());
	}
	
	public DefaultListModel getDefaultListModel()
	{
		return (DefaultListModel) this.getModel();
	}
	
}
