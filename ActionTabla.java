package actionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridLayout;

public class ActionTabla implements ActionListener {

	JTextField nombre, apepat, apemat, edad;
	JTable tabla;
	JButton agregar, eliminar, seleccionar, modificar, limpiar, salir;
	DefaultTableModel dtm;

	public void tabla() {
		JFrame ventana = new JFrame("Tabla");
		ventana.setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Campos"));

		JLabel eNombre = new JLabel("Nombre");
		JLabel eApePat = new JLabel("Apellido Paterno");
		JLabel eApeMat = new JLabel("Apellido Materno");
		JLabel eEdad = new JLabel("Edad");

		nombre = new JTextField();
		nombre.setColumns(20);
		apepat = new JTextField();
		apepat.setColumns(20);
		apemat = new JTextField();
		apemat.setColumns(20);
		edad = new JTextField();
		edad.setColumns(20);

		agregar = new JButton("Agregar");
		eliminar = new JButton("Eliminar");
		seleccionar = new JButton("Seleccionar");
		modificar = new JButton("Modificar");
		limpiar = new JButton("Limpiar");
		salir = new JButton("Salir");

		agregar.addActionListener(this);
		eliminar.addActionListener(this);
		seleccionar.addActionListener(this);
		modificar.addActionListener(this);
		limpiar.addActionListener(this);
		salir.addActionListener(this);

		panel.setLayout(new GridLayout(7,2));
		panel.add(eNombre);
		panel.add(nombre);
		panel.add(eApePat);
		panel.add(apepat);
		panel.add(eApeMat);
		panel.add(apemat);
		panel.add(eEdad);
		panel.add(edad);
		panel.add(agregar);
		panel.add(eliminar);
		panel.add(seleccionar);
		panel.add(modificar);
		panel.add(limpiar);
		panel.add(salir);

		Object [][] filascolumnas = {
									{"Rodolfo","Morales","Cortes","23"},
									{"Alejandro","Morales","Cortes","31"},
									{"Isabel","Zavala","Lira","24"},
									{"Daniel","Perez","Raya","26"},
									};

		Object [] titulo = {"Nombre","Apellido Paterno","Apellido Materno","Edad"};

		//Creo variable de tipo DTM y le paso los arreglos
		dtm = new DefaultTableModel(filascolumnas,titulo);
		tabla = new JTable(dtm);

		//Damos tamaño a la tabla
		tabla.setPreferredScrollableViewportSize(new Dimension(500,70));
		JScrollPane contenedor = new JScrollPane(tabla);
		JPanel panelTabla = new JPanel();
		panelTabla.add(contenedor);

		ventana.add(panel);
		ventana.add(panelTabla);

		ventana.pack();
		ventana.setLocation(200,200);
		ventana.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();

		//Boton Agregar
		if(source == agregar){
			String [] fila = new String[4];
			fila[0] = nombre.getText();
			fila[1] = apepat.getText();
			fila[2] = apemat.getText();
			fila[3] = edad.getText();
			dtm.addRow(fila);
		//Boton Eliminar
		}else if(source == eliminar){
			dtm = (DefaultTableModel)tabla.getModel();
			int filaEliminar = tabla.getSelectedRow();
			
			if(filaEliminar > -1){
				dtm.removeRow(filaEliminar);
				tabla.setModel(dtm);
			}else{
				new JOptionPane();
				JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar un registro a eliminar");
			}
		//Boton Seleccionar
		}else if(source == seleccionar){
			dtm = (DefaultTableModel)tabla.getModel();
			int filaSeleccionada = tabla.getSelectedRow();

			String nomb = (String)dtm.getValueAt(filaSeleccionada, 0);
			String apep = (String)dtm.getValueAt(filaSeleccionada, 1);
			String apem = (String)dtm.getValueAt(filaSeleccionada, 2);
			int eda = Integer.parseInt((String)dtm.getValueAt(filaSeleccionada, 3));

			nombre.setText(nomb);
			apepat.setText(apep);
			apemat.setText(apem);
			edad.setText(String.valueOf(eda));
		//Boton Modificar
		}else if(source == modificar){
			dtm = (DefaultTableModel)tabla.getModel();
			int filaSelec = tabla.getSelectedRow();

			dtm.setValueAt(nombre.getText(), filaSelec, 0);
			dtm.setValueAt(apepat.getText(), filaSelec, 1);
			dtm.setValueAt(apemat.getText(), filaSelec, 2);
			dtm.setValueAt(edad.getText(), filaSelec, 3);
		//Boton Limpiar
		}else if(source == limpiar){
			nombre.setText(null);
			apepat.setText(null);
			apemat.setText(null);
			edad.setText(null);
		//Boton Salir
		}else if(source == salir){
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new ActionTabla().tabla();
	}
}