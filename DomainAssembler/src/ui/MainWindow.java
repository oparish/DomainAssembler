package ui;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.HORIZONTAL;
import static java.awt.GridBagConstraints.VERTICAL;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JWindow;

import data.DomainRow;

public class MainWindow extends JFrame
{
	private static final String ZONING_FIELD = "Zoning Field";
	private static final String ZONE_OBJECT = "Zone Object";
	private static final String UP_EXIT = "Up Exit";
	private static final String DOWN_EXIT = "Down Exit";
	private static final String NORTH_EXIT = "North Exit";
	private static final String EAST_EXIT = "East Exit";
	private static final String SOUTH_EXIT = "South Exit";
	private static final String WEST_EXIT = "West Exit";
	private RowList<DomainRow> rowList;
	public RowList<DomainRow> getRowList() {
		return rowList;
	}

	private ArrayList<LabelledField> fields;
	private JToolBar toolbar;
	
	public MainWindow()
	{
		super();
		this.setSize(1000, 500);
		this.rowList = new RowList<DomainRow>();
		this.toolbar = new JToolBar();
		this.setupFields();
		this.setupToolbar();
		this.layoutComponents();
	}
	
	private void setupToolbar()
	{
		
	}
	
	private void setupFields()
	{
		this.fields = new ArrayList<LabelledField>();
		this.fields.add(new LabelledField(ZONING_FIELD));
		this.fields.add(new LabelledField(ZONE_OBJECT));
		this.fields.add(new LabelledField(UP_EXIT));
		this.fields.add(new LabelledField(DOWN_EXIT));
		this.fields.add(new LabelledField(NORTH_EXIT));
		this.fields.add(new LabelledField(EAST_EXIT));
		this.fields.add(new LabelledField(SOUTH_EXIT));
		this.fields.add(new LabelledField(WEST_EXIT));
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
		int i = 1;
		for (LabelledField field : fields)
		{
			fieldPanel.add(field, this.getFieldConstraints(i));
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
}
