package com.gemserk.tools.cantunethis.editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import com.gemserk.properties.Property;
import com.gemserk.tools.cantunethis.PropertyManager;
import com.gemserk.tools.cantunethis.properties.TunableProperty;

public class PropertiesEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private PropertyManager propertyManager;

	private Set<String> properties;
	private JPanel panel;

	public void setPropertyManager(PropertyManager propertyManager) {
		this.propertyManager = propertyManager;
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Runnable runnable = new Runnable() {
			public void run() {
				try {
					while (true) {
						Set<String> listProperties = propertyManager.listProperties();
						if (properties == null)
							properties = new HashSet<String>();

						if (!listProperties.equals(properties)) {
							properties = new HashSet<String>(listProperties);
							regenerateProperties(properties);
						}

						Thread.sleep(1000);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};
		new Thread(runnable).start();

		// EventQueue.invokeLater(runnable);
	}

	private void regenerateProperties(Set<String> properties) {
		// final String[] propertiesArray = new String[properties.size()];
		// properties.toArray(propertiesArray);

		panel.removeAll();

		// on the desktop part of the application I created a dynamic editor to create the modifiable fields for those registered properties

		for (final String id : properties) {

			final TunableProperty tunableProperty = propertyManager.get(id);
			final Object propertyValue = tunableProperty.getProperty().get();

			if (propertyValue == null)
				return;

			if (propertyValue instanceof Float) {
				addFloatPropertyComponent(id, tunableProperty, (Float) propertyValue);
			} else if (propertyValue instanceof Boolean) {
				addBooleanPropertyComponent(id, (Boolean) propertyValue);
			}

		}

		panel.validate();

	}

	private void addBooleanPropertyComponent(final String id, final Boolean value) {
		JPanel propertyPanel = new JPanel();
		propertyPanel.add(new JLabel(id));

		JCheckBox checkBox = new JCheckBox() {
			{
				setSelected(value.booleanValue());
				addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						JCheckBox checkBox = (JCheckBox) e.getItem();
						TunableProperty tunableProperty = propertyManager.get(id);
						Property<Boolean> property = tunableProperty.getProperty();
						property.set(Boolean.valueOf(checkBox.isSelected()));
					}
				});
			}
		};

		propertyPanel.add(checkBox);
		panel.add(propertyPanel);
	}

	private void addFloatPropertyComponent(final String id, final TunableProperty tunableProperty, final Float value) {
		JPanel propertyPanel = new JPanel();

		// final JTextField field = new JTextField(value.toString());
		// final JFormattedTextField field = new JFormattedTextField(NumberFormat.getNumberInstance());
		final JFormattedTextField field = new JFormattedTextField(new DefaultFormatter()) {
			{
				addPropertyChangeListener("value", new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if (!"value".equals(evt.getPropertyName()))
							return;
						Property<Object> property = propertyManager.get(id).getProperty();
						property.set(evt.getNewValue());
					}
				});
			}
		};
		field.setColumns(5);
		field.setValue(value);

		propertyPanel.add(new JLabel(id));
		propertyPanel.add(field);

		Float min = (Float) tunableProperty.getConstraint("min");
		Float max = (Float) tunableProperty.getConstraint("max");

		// if I found those restrictions, then I create a jslider with them
		// the idea is to register whatever constraints you want and then create the counterpart on the editor to modify those values

		if (min != null && max != null) {
			propertyPanel.add(new JSlider(min.intValue(), max.intValue(), (int) value.floatValue()) {
				{
					setMinorTickSpacing(1);
					setMajorTickSpacing((getMaximum() - getMinimum()) / 10);
					setPaintTicks(true);

					Hashtable labelTable = new Hashtable();
					labelTable.put(getMinimum(), new JLabel(String.valueOf(getMinimum())));
					labelTable.put(getMaximum(), new JLabel(String.valueOf(getMaximum())));
					labelTable.put(value.intValue(), new JLabel("*"));
					setLabelTable(labelTable);
					setPaintLabels(true);

					addChangeListener(new ChangeListener() {

						@Override
						public void stateChanged(ChangeEvent e) {
							JSlider source = (JSlider) e.getSource();
							if (source.getValueIsAdjusting())
								return;
							field.setValue(Float.valueOf(source.getValue()));
						}
					});
				}
			});
		}

		panel.add(propertyPanel);
	}

	/**
	 * Create the frame.
	 */
	public PropertiesEditor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 2));
	}

}
