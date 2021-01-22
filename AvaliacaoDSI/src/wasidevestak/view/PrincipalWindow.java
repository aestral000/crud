package wasidevestak.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import wasidevestak.dao.PersonDAOImp;
import wasidevestak.entity.Person;

public class PrincipalWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private PersonDAOImp dao;

	private JPanel panelPrincipal;
	private JPanel panelMenu;
	private JPanel panelWindows;
	private JButton buttonInsert;
	private JButton buttonDelete;
	private JButton buttonSelect;
	private JButton buttonUpdate;

	private JButton buttonInsertWindow;
	private JTextField fieldInsertWindow;
	private JLabel labelInsertWindow;

	private JButton buttonDeleteWindow;
	private JComboBox comboDeleteWindow;

	private JButton buttonUpdateWindow;
	private JComboBox comboUpdateWindow;
	private JTextField fieldUpdateWindow;
	private JLabel labelUpdateWindow;

	private JScrollPane scrollSelectWindow;
	private JTextArea areaSelectWindow;
	private JButton buttonSelectWindow;

	public PrincipalWindow(PersonDAOImp dao) {
		this.dao = dao;
		this.setTitle("CRUD");
		createComponents();
	}

	private void revalidatePanelWindow() {
		if (panelWindows.getComponents() != null) {
			panelWindows.removeAll();
			panelWindows.revalidate();
			panelWindows.setVisible(false);
		}
	}

	private void createComponents() {
		panelPrincipal = new JPanel();
		panelMenu = new JPanel();
		panelWindows = new JPanel();
		buttonInsert = new JButton("INSERT");
		buttonDelete = new JButton("DELETE");
		buttonSelect = new JButton("SELECT");
		buttonUpdate = new JButton("UPDATE");

		buttonInsert.addActionListener(e -> createComponentsInsert());
		buttonDelete.addActionListener(e -> createComponentsDelete());
		buttonSelect.addActionListener(e -> createComponentsSelect());
		buttonUpdate.addActionListener(e -> createComponentsUpdate());
		panelWindows.setVisible(false);

		panelMenu.add(buttonInsert);
		panelMenu.add(buttonDelete);
		panelMenu.add(buttonSelect);
		panelMenu.add(buttonUpdate);
		panelPrincipal.add(panelMenu);
		panelPrincipal.add(panelWindows);
		this.add(panelPrincipal);

		pack();
		setVisible(true);

	}

	private void createComponentsSelect() {
		revalidatePanelWindow();
		areaSelectWindow = new JTextArea(30, 30);
		scrollSelectWindow = new JScrollPane(areaSelectWindow);
		areaSelectWindow.setText(dao.selectPerson().toString());
		buttonSelectWindow = new JButton("FINALIZAR");
		panelMenu.add(scrollSelectWindow);
		panelWindows.setVisible(true);
		pack();
		select();

	}

	private void createComponentsDelete() {
		revalidatePanelWindow();
		buttonDeleteWindow = new JButton("REMOVER");
		DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(dao.getAllId().toArray());
		comboDeleteWindow = new JComboBox<Integer>(defaultComboBox);
		Integer selected;
		if (dao.getAllId().size() > 1) {
			selected = (Integer) comboDeleteWindow.getItemAt(comboDeleteWindow.getSelectedIndex());
		} else {
			selected = (Integer) comboDeleteWindow.getItemAt(0);
		}
		buttonDeleteWindow.addActionListener(e -> delete(selected));
		panelWindows.add(comboDeleteWindow);
		panelWindows.add(buttonDeleteWindow);
		panelWindows.setVisible(true);
		pack();
	}

	private void createComponentsInsert() {
		revalidatePanelWindow();

		fieldInsertWindow = new JTextField(6);
		buttonInsertWindow = new JButton("INSERIR");
		buttonInsertWindow.addActionListener(e -> insert(fieldInsertWindow.getText().trim()));
		labelInsertWindow = new JLabel("NAME: ");
		panelWindows.add(labelInsertWindow);
		panelWindows.add(fieldInsertWindow);
		panelWindows.add(buttonInsertWindow);
		panelWindows.setVisible(true);
		pack();
	}

	private void createComponentsUpdate() {
		revalidatePanelWindow();
		DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(dao.getAllId().toArray());
		comboUpdateWindow = new JComboBox<Integer>(defaultComboBox);
		fieldUpdateWindow = new JTextField(6);
		buttonUpdateWindow = new JButton("ATUALIZAR");
		labelUpdateWindow = new JLabel("Novo nome: ");
		Integer selected;
		if (dao.getAllId().size() > 1) {
			selected = (Integer) comboUpdateWindow.getItemAt(comboUpdateWindow.getSelectedIndex());
		} else {
			selected = (Integer) comboUpdateWindow.getItemAt(0);
		}
		buttonUpdateWindow.addActionListener(e -> update(fieldUpdateWindow.getText().trim(), selected));
		panelWindows.add(comboUpdateWindow);
		panelWindows.add(labelUpdateWindow);
		panelWindows.add(fieldUpdateWindow);
		panelWindows.add(buttonUpdateWindow);
		panelWindows.setVisible(true);
		pack();
	}

	private void insert(String input) {
		if (!input.isEmpty()) {
			dao.insertPerson(new Person(input));
			JOptionPane.showMessageDialog(panelPrincipal, "INSERTED");
			revalidatePanelWindow();
		} else {
			JOptionPane.showMessageDialog(panelPrincipal, "NOT INSERTED");
			revalidatePanelWindow();
		}
	}

	private void select() {
		JOptionPane.showMessageDialog(null, "SELECTED");
		revalidatePanelWindow();
	}

	private void delete(Integer id) {
		if (id != null) {
			if (dao.deletePerson(id)) {
				JOptionPane.showMessageDialog(panelPrincipal, "DELETED");
				revalidatePanelWindow();
			} else {
				JOptionPane.showMessageDialog(panelPrincipal, "NOT DELETED");
				revalidatePanelWindow();
			}
		}
	}

	private void update(String name, Integer id) {
		if (!name.isEmpty() && id != null) {
			dao.editPerson(id, name);
			JOptionPane.showMessageDialog(panelPrincipal, "UPDATED");
			revalidatePanelWindow();
		} else {
			JOptionPane.showMessageDialog(panelPrincipal, "NOT UPDATED");
			revalidatePanelWindow();
		}
	}

}
