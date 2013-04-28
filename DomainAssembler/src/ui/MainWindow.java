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
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
	


	public MainWindow()
	{
		super();
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
