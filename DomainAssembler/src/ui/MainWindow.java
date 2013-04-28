package ui;

import static data.DomainField.DOWN_EXIT;
import static data.DomainField.EAST_EXIT;
import static data.DomainField.NORTH_EXIT;
import static data.DomainField.SOUTH_EXIT;
import static data.DomainField.UP_EXIT;
import static data.DomainField.WEST_EXIT;
import static data.DomainField.ZONING_FIELD;
import static data.DomainField.ZONING_NAME;
import static data.DomainField.ZONING_OBJECT;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static ui.ButtonType.ADD_ROW;
import static ui.ButtonType.DELETE_ROW;
import static ui.ButtonType.LOAD_TABLE;
import static ui.ButtonType.SAVE_SELECTED_ROW;
import static ui.ButtonType.SAVE_TABLE_AS;
import static ui.ButtonType.QUIT;
import static ui.ButtonType.SAVE_TABLE;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.DomainField;
import data.DomainRow;
import data.MainData;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener, 
	ListSelectionListener, DocumentListener
{	
	private final DomainField[] fieldOrder = {ZONING_NAME, ZONING_OBJECT, 
			ZONING_FIELD, UP_EXIT, DOWN_EXIT, NORTH_EXIT, SOUTH_EXIT, WEST_EXIT,
			EAST_EXIT};
	private RowList rowList;
	private HashMap<DomainField, LabelledField> fields;
	private JToolBar toolbar;
	private boolean rowSaved = true;
	private DomainRow rowSelected = null;
	private File saveFile;
	private boolean tableSaved;
	private JFileChooser myFileChooser;


	public MainWindow()
	{
		super();
		this.myFileChooser = new JFileChooser();
		this.setSize(1200, 500);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double mainWidth = screenSize.getWidth();
		double mainHeight = screenSize.getHeight();
		Double newX = (mainWidth/2) - 600;
		Double newY = (mainHeight/2) - 250;
		this.setLocation(newX.intValue(), newY.intValue());
		this.rowList = new RowList();
		this.setupFields();
		this.setupToolbar();
		this.noSelectedRow();
		this.layoutComponents();
	}
	
	public RowList getRowList() {
		return rowList;
	}
	
	private void noSelectedRow()
	{
		for (LabelledField field : fields.values())
		{
			field.setEnabled(false);
		}
		this.rowSelected = null;
	}
	
	private void setupToolbar()
	{
		this.toolbar = new JToolBar();
		new BoxLayout(this.toolbar, BoxLayout.X_AXIS);
		ButtonType[] toolbarButtons = {SAVE_SELECTED_ROW, ADD_ROW, DELETE_ROW, 
				LOAD_TABLE, SAVE_TABLE, SAVE_TABLE_AS, QUIT};
		for (ButtonType buttonType : toolbarButtons)
		{
			MyButton newButton = new MyButton(buttonType);
			this.toolbar.add(newButton);
			newButton.addActionListener(this);
		}
	}
	
	private void setupFields()
	{
		this.fields = new HashMap<DomainField, LabelledField>();
		for (DomainField field : DomainField.values())
		{
			this.fields.put(field, new LabelledField(field.getDisplayName(), this));
		}
	}
	
	private void layoutComponents()
	{
		this.setLayout(new GridBagLayout());
		this.add(this.toolbar, this.getToolbarConstraints());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		panel.add(this.rowList);
		this.rowList.addListSelectionListener(this);
		int i = 1;
		for (DomainField field : fieldOrder)
		{
			fieldPanel.add(fields.get(field), this.getFieldConstraints(i));
			i++;
		}
		panel.add(fieldPanel);
		this.add(panel, this.getPanelConstraints());
	}
	
	private GridBagConstraints getPanelConstraints()
	{
		GridBagConstraints panelConstraints = new GridBagConstraints();
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 1;
		panelConstraints.weightx = 1;
		panelConstraints.weighty = 1;
		panelConstraints.fill = BOTH;
		return panelConstraints;
	}
	
	private GridBagConstraints getFieldConstraints(int i)
	{
		GridBagConstraints panelConstraints = new GridBagConstraints();
		panelConstraints.gridx = 0;
		panelConstraints.gridy = i;
		panelConstraints.weightx = 1;
		panelConstraints.weighty = 1;
		panelConstraints.fill = HORIZONTAL;
		panelConstraints.insets = new Insets(4,4,4,4);
		return panelConstraints;
	}
	
	private GridBagConstraints getToolbarConstraints()
	{
		GridBagConstraints toolbarConstraints = new GridBagConstraints();
		toolbarConstraints.gridx = 0;
		toolbarConstraints.gridy = 0;
		toolbarConstraints.weightx = 0;
		toolbarConstraints.weighty = 0;
		toolbarConstraints.gridwidth = 2;
		toolbarConstraints.fill = HORIZONTAL;
		return toolbarConstraints;
	}
	
	public void deleteRow()
	{
		DefaultListModel model = this.rowList.getDefaultListModel();
		model.removeElement(this.rowSelected);
		if (model.getSize() != 0)
		{
			this.rowList.setSelectedIndex(0);
			this.loadRow((DomainRow) model.getElementAt(0));
		}
		else
		{
			this.disableFields();
			this.setRowSelected(null);
		}
	}
	
	private void quit()
	{
		new QuitDialog(this).setVisible(true);
	}
	
	private void suggestSaving(DomainRow newRow)
	{
		UnsavedChangesDialog unsavedChangesDialog = new UnsavedChangesDialog(this, newRow);
		unsavedChangesDialog.setVisible(true);
	}
	
	public void saveSelectedRow()
	{
		for (Entry<DomainField, LabelledField> fieldEntry : fields.entrySet())
		{
			this.rowSelected.setFieldValue(fieldEntry.getKey(), fieldEntry.getValue().getText());
		}
		this.rowSaved = true;
	}
	
	private void changeRow(DomainRow selectedRow)
	{	
		if (this.rowSelected != null && !this.rowSaved)
		{
			suggestSaving(selectedRow);
		}
		else 
		{
			if (this.rowSelected == null)
				enableFields();
			this.loadRow(selectedRow);
		}
	}
	
	private void enableFields()
	{
		for (LabelledField field : fields.values())
		{
			field.setEnabled(true);
		}
	}
	
	private void disableFields()
	{
		for (LabelledField field : fields.values())
		{
			field.setText("");
			field.setEnabled(false);
		}
	}
	
	public void loadRow(DomainRow row)
	{
		this.setRowSelected(row);
		for(Entry<DomainField, LabelledField> fieldEntry : fields.entrySet())
		{
			String fieldValue = row.getFieldValue(fieldEntry.getKey());
			fieldEntry.getValue().setText(fieldValue);
		}
		this.rowSaved = true;
	}
	
	public void addRow(String rowName)
	{	
		DefaultListModel model = this.rowList.getDefaultListModel();
		DomainRow newRow = new DomainRow(rowName);
		model.addElement(newRow);
		if (this.rowSelected == null)
		{
			this.enableFields();
		}
		this.rowList.setSelectedValue(newRow, true);
		if (!this.rowSaved)
			suggestSaving(newRow);
		else
			loadRow(newRow);
	}
	
	private void openAddDialog()
	{
		new AddRowDialog(this).setVisible(true);
	}
	
	private void openDeleteDialog()
	{
		new DeleteRowDialog(this).setVisible(true);
	}
	
	private void saveTable()
	{
		if (this.saveFile == null)
			this.saveTableAs();
		else
			this.startSaving();
	}
	
	private void startSaving()
	{
		if (!this.rowSaved)
		{
			this.saveSelectedRow();
		}
		this.saveToFile();
	}
	
	public void saveToFile()
	{
		StringBuilder sb = new StringBuilder();

		int i = 0;
		DomainField[] fields = DomainField.values();
		
		for (DomainField field : fields)
		{
			sb.append(field.toString());
			i++;
			if (i == fields.length)
				sb.append("\r\n");
			else
				sb.append('\t');

		}
		
		Object[] rows = this.rowList.getDefaultListModel().toArray();
		
		for (Object row : rows)
		{
			int j = 0;
			for (DomainField field: fields)
			{
				String columnString =  ((DomainRow)row).getFieldValue(field);
				sb.append(columnString);
				j++;
				if (j == fields.length)
					sb.append("\r\n");
				else
					sb.append('\t');
			}
		}
		
		FileWriter fileWriter;
		try
		{
			fileWriter = new FileWriter(this.saveFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(sb.toString());
			bufferedWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		
	}
	
	private void loadTable()
	{
		int returnValue = this.myFileChooser.showOpenDialog(this);
		if (returnValue == APPROVE_OPTION)
		{
			File loadFile = this.myFileChooser.getSelectedFile();
			try {
				this.loadFromFile(loadFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DefaultListModel model = this.rowList.getDefaultListModel();
		if (model.getSize() == 0)
		{
			this.disableFields();
		}
		else
		{
			this.enableFields();
			this.loadRow((DomainRow)model.toArray()[0]);
		}
	}
	
	private void loadFromFile(File loadFile) throws IOException
	{
		this.clearRows();
		FileReader fileReader = new FileReader(loadFile);
		skipFirstRow(fileReader);
		DomainField[] fields = DomainField.values();
		DomainRow currentRow = new DomainRow();
		DefaultListModel model = this.rowList.getDefaultListModel();
		StringBuilder sb = new StringBuilder();
		int i;
		int j = 0;
		i = fileReader.read();
		while ( i != -1)
		{
			char c = (char) i;
			if (c == '\t')
			{
				currentRow.setFieldValue(fields[j], sb.toString());
				sb = new StringBuilder();
				j++;
			}
			else if (c == '\r')
			{
				i = fileReader.read();
				currentRow.setFieldValue(fields[j], sb.toString());
				sb = new StringBuilder();
				model.addElement(currentRow);
				currentRow = new DomainRow();
				j = 0 ;
			}
			else
			{
				sb.append(c);
			}
				
			i = fileReader.read();
		}
	}
	
	private void skipFirstRow(FileReader reader) throws IOException
	{
		int i;
		char c;
		do 
		{
			i = reader.read();
			c = (char) i;
		}
		while ( c != '\r');
		reader.read();
	}
	
	private void clearRows()
	{
		this.rowList.getDefaultListModel().clear();
	}
	
	private void saveTableAs()
	{
		int returnValue = this.myFileChooser.showSaveDialog(this);
		if (returnValue == APPROVE_OPTION)
		{
			this.saveFile = this.myFileChooser.getSelectedFile();
			this.startSaving();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if (source instanceof MyButton)
		{
			MyButton sourceButton = (MyButton) source;
			switch(sourceButton.getType())
			{
			case SAVE_SELECTED_ROW:
				this.saveSelectedRow();
				break;
			case ADD_ROW:
				this.openAddDialog();
				break;
			case DELETE_ROW:
				this.openDeleteDialog();
				break;
			case SAVE_TABLE_AS:
				this.saveTableAs();
				break;
			case LOAD_TABLE:
				this.loadTable();
				break;
			case SAVE_TABLE:
				this.saveTable();
				break;
			case QUIT:
				this.quit();
				break;
			default:
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent event)
	{
		Object source = event.getSource();
		if (source instanceof RowList && event.getValueIsAdjusting())
		{
			RowList rowList = (RowList) source;
			DomainRow selectedRow = (DomainRow) rowList.getSelectedValue();
			if (selectedRow != this.rowSelected)
				this.changeRow(selectedRow);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent event) 
	{
		this.rowSaved = false;
	}

	@Override
	public void insertUpdate(DocumentEvent event) 
	{
		this.rowSaved = false;	
	}

	@Override
	public void removeUpdate(DocumentEvent event) 
	{
		this.rowSaved = false;
	}
	
	public DomainRow getRowSelected() {
		return rowSelected;
	}

	public void setRowSelected(DomainRow rowSelected) {
		this.rowSelected = rowSelected;
	}
}
