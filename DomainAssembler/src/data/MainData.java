package data;

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
		MainData.test();
	}
	
	public static MainData getMainDataInstance()
	{
		return MainData.instance;
	}
	
	private static void test()
	{
		DomainRow testRow = new DomainRow();
		testRow.setZoningName("zoningName");
		testRow.setZoningField("zoningField");
		testRow.setZoningObject("zoningObject");
		testRow.setDownExit("downExit");
		testRow.setEastExit("eastExit");
		testRow.setNorthExit("northExit");
		testRow.setUpExit("upExit");
		testRow.setWestExit("westExit");
		testRow.setSouthExit("southExit");
		MainData mainData = MainData.getMainDataInstance();
		MainWindow mainWindow = mainData.getMainWindow();
		RowList<DomainRow> rowList = mainWindow.getRowList();
		DefaultListModel defaultListModel = rowList.getDefaultListModel();
		defaultListModel.addElement(testRow);
	}
}
