package data;

import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import ui.MainWindow;
import ui.RowList;

public class MainData
{
	private static MainData instance;
	private MainWindow mainWindow;

	public MainWindow getMainWindow() {
		return this.mainWindow;
	}

	private ArrayList<DomainRow> domainRows;
	
	public MainData()
	{
		this.mainWindow = new MainWindow();
		this.mainWindow.setVisible(true);
	}
	
	public static void main(String args[])
	{
		MainData.instance = new MainData();
		//MainData.test();
	}
	
	public static MainData getMainDataInstance()
	{
		return MainData.instance;
	}
	
	private static void test()
	{
		DomainRow testRow = new DomainRow("zoningName");
		testRow.setFieldValue(DomainField.ZONING_FIELD, "zoningField");
		testRow.setFieldValue(DomainField.ZONING_OBJECT, "zoningObject");
		testRow.setFieldValue(DomainField.DOWN_EXIT, "downExit");
		testRow.setFieldValue(DomainField.EAST_EXIT, "eastExit");
		testRow.setFieldValue(DomainField.NORTH_EXIT, "northExit");
		testRow.setFieldValue(DomainField.UP_EXIT, "upExit");
		testRow.setFieldValue(DomainField.WEST_EXIT, "westExit");
		testRow.setFieldValue(DomainField.SOUTH_EXIT, "southExit");
		
		DomainRow testRow2 = new DomainRow("zoningName2");
		testRow2.setFieldValue(DomainField.ZONING_FIELD, "zoningField2");
		testRow2.setFieldValue(DomainField.ZONING_OBJECT, "zoningObject2");
		testRow2.setFieldValue(DomainField.DOWN_EXIT, "downExit2");
		testRow2.setFieldValue(DomainField.EAST_EXIT, "eastExit2");
		testRow2.setFieldValue(DomainField.NORTH_EXIT, "northExit2");
		testRow2.setFieldValue(DomainField.UP_EXIT, "upExit2");
		testRow2.setFieldValue(DomainField.WEST_EXIT, "westExit2");
		testRow2.setFieldValue(DomainField.SOUTH_EXIT, "southExit2");
		
		MainData mainData = MainData.getMainDataInstance();
		MainWindow mainWindow = mainData.getMainWindow();
		RowList rowList = mainWindow.getRowList();
		DefaultListModel defaultListModel = rowList.getDefaultListModel();
		defaultListModel.addElement(testRow);
		defaultListModel.addElement(testRow2);
	}
}
