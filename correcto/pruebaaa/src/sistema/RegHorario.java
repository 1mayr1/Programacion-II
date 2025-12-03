package sistema;

import javax.swing.*;
import java.awt.*;

public class RegHorario extends JDialog {
    
    public RegHorario(Biblioteca biblioteca) {
        setTitle("Horario de Atención");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        
        crearInterfaz(biblioteca);
        
        setVisible(true);
    }
    
    private void crearInterfaz(Biblioteca biblioteca) {
    JPanel panelPrincipal = new JPanel(new BorderLayout());
    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    JLabel lblTitulo = new JLabel("HORARIO DE ATENCIÓN");
    lblTitulo.setFont(new Font("Serif", Font.BOLD, 20));
    lblTitulo.setForeground(new Color(230, 126, 34));
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    
    String[] columnas = {"Día", "Horario"};
    Object[][] datos = {
        {"Lunes", "08:00 - 20:00"},
        {"Martes", "08:00 - 20:00"},
        {"Miércoles", "08:00 - 20:00"},
        {"Jueves", "08:00 - 20:00"},
        {"Viernes", "08:00 - 20:00"},
        {"Sábado", "CERRADO"},
        {"Domingo", "CERRADO"}
    };
    
    JTable tablaHorario = new JTable(datos, columnas);
    tablaHorario.setRowHeight(30);
    tablaHorario.setFont(new Font("Serif", Font.PLAIN, 14));
    tablaHorario.getTableHeader().setFont(new Font("Serif", Font.BOLD, 14));
    tablaHorario.setEnabled(false);
    
    JScrollPane scrollPane = new JScrollPane(tablaHorario);
    scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    scrollPane.setPreferredSize(new Dimension(400, 200));
    
    JPanel panelTabla = new JPanel(new BorderLayout());
    panelTabla.add(scrollPane, BorderLayout.CENTER);
    
    JLabel lblInfo = new JLabel("Contacto: biblioteca@universidad.edu");
    lblInfo.setFont(new Font("Serif", Font.PLAIN, 12));
    lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
    lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

    JButton btnCerrar = new JButton("CERRAR");
    btnCerrar.setBackground(new Color(230, 126, 34));
    btnCerrar.setForeground(Color.WHITE);
    btnCerrar.setFont(new Font("Serif", Font.BOLD, 14));
    btnCerrar.setPreferredSize(new Dimension(120, 40));
    btnCerrar.addActionListener(e -> dispose());
    
    JPanel panelBoton = new JPanel();
    panelBoton.add(btnCerrar);
    
    panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
    panelPrincipal.add(panelTabla, BorderLayout.CENTER);
    
    JPanel panelSur = new JPanel(new BorderLayout());
    panelSur.add(lblInfo, BorderLayout.NORTH);
    panelSur.add(panelBoton, BorderLayout.CENTER);
    panelSur.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    
    panelPrincipal.add(panelSur, BorderLayout.SOUTH);
    
    add(panelPrincipal);
    
    setSize(500, 400);
    setLocationRelativeTo(null); 
}
}