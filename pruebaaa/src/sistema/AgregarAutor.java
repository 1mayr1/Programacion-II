package sistema;

import javax.swing.*;
import java.awt.*;

public class AgregarAutor extends JDialog {
    
    private Biblioteca biblioteca;
    private JTextField txtNombre;
    private JTextField txtNacionalidad;
    
    public AgregarAutor(JFrame parent, Biblioteca biblioteca) {
        super(parent, "Agregar Nuevo Autor", true);
        this.biblioteca = biblioteca;
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        crearInterfaz();
        
        setVisible(true);
    }
    
    private void crearInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblTitulo = new JLabel("AGREGAR NUEVO AUTOR");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(128, 99, 117));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        
        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nacionalidad:"), gbc);
        
        txtNacionalidad = new JTextField(20);
        gbc.gridx = 1;
        panelFormulario.add(txtNacionalidad, gbc);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBackground(new Color(128, 99, 117));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardarAutor());
        
        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> dispose());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private void guardarAutor() {
        String nombre = txtNombre.getText().trim();
        String nacionalidad = txtNacionalidad.getText().trim();
        
        if (nombre.isEmpty() || nacionalidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor complete todos los campos.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Autor nuevoAutor = new Autor(nombre, nacionalidad);
        biblioteca.agregarAutor(nuevoAutor);
        
        JSONManager.guardarLista("autores.json", biblioteca.getAutorRegistrados());
        
        JOptionPane.showMessageDialog(this, 
            "✅ Autor agregado exitosamente:\n" +
            "Nombre: " + nombre + "\n" +
            "Nacionalidad: " + nacionalidad,
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }
}