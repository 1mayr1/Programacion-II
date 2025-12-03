package sistema;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegistroPrestamo extends JDialog {
    
    private Biblioteca biblioteca;
    private Libro libro;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    
    public RegistroPrestamo(JFrame parent, Biblioteca biblioteca, Libro libro) {
        super(parent, "Registro de Préstamo", true);
        this.biblioteca = biblioteca;
        this.libro = libro;
        
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        
        crearInterfaz();
        
        setVisible(true);
    }
    private void crearInterfaz() {
    JPanel panelPrincipal = new JPanel(new BorderLayout());
    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panelPrincipal.setBackground(Color.WHITE);
    
    JLabel lblTitulo = new JLabel(" REGISTRO DE PRÉSTAMO");
    lblTitulo.setFont(new Font("Serif", Font.BOLD, 20));
    lblTitulo.setForeground(new Color(36, 59, 73));
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
    
    // **PANEL CENTRAL - Simple y ordenado**
    JPanel panelCentral = new JPanel();
    panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
    panelCentral.setBackground(Color.WHITE);
    
    JPanel panelFormulario = new JPanel();
    panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
    panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    panelFormulario.setBackground(Color.WHITE);
    
    JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
    panelNombre.setBackground(Color.WHITE);
    JLabel lblNombre = new JLabel("Nombre del Estudiante:");
    lblNombre.setFont(new Font("Serif", Font.BOLD, 14));
    lblNombre.setPreferredSize(new Dimension(200, 25));
    
    txtNombre = new JTextField();
    txtNombre.setFont(new Font("Serif", Font.PLAIN, 14));
    txtNombre.setPreferredSize(new Dimension(250, 35));
    txtNombre.setMaximumSize(new Dimension(250, 35));
    
    panelNombre.add(lblNombre);
    panelNombre.add(Box.createRigidArea(new Dimension(10, 0)));
    panelNombre.add(txtNombre);
    
    JPanel panelCodigo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
    panelCodigo.setBackground(Color.WHITE);
    JLabel lblCodigo = new JLabel("Código de Estudiante:");
    lblCodigo.setFont(new Font("Serif", Font.BOLD, 14));
    lblCodigo.setPreferredSize(new Dimension(200, 25));
    
    txtCodigo = new JTextField();
    txtCodigo.setFont(new Font("Serif", Font.PLAIN, 14));
    txtCodigo.setPreferredSize(new Dimension(250, 35));
    txtCodigo.setMaximumSize(new Dimension(250, 35));
    
    panelCodigo.add(lblCodigo);
    panelCodigo.add(Box.createRigidArea(new Dimension(10, 0)));
    panelCodigo.add(txtCodigo);
    
    panelFormulario.add(panelNombre);
    panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)));
    panelFormulario.add(panelCodigo);
    
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    panelBotones.setBackground(Color.WHITE);
    
    JButton btnGuardar = new JButton("GUARDAR");
    btnGuardar.setFont(new Font("Serif", Font.BOLD, 14));
    btnGuardar.setBackground(new Color(52, 152, 219));
    btnGuardar.setForeground(Color.WHITE);
    btnGuardar.setPreferredSize(new Dimension(150, 30));
    btnGuardar.addActionListener(e -> registrarPrestamo());
    
    JButton btnCancelar = new JButton("CANCELAR");
    btnCancelar.setFont(new Font("Serif", Font.BOLD, 14));
    btnCancelar.setBackground(new Color(231, 76, 60));
    btnCancelar.setForeground(Color.WHITE);
    btnCancelar.setPreferredSize(new Dimension(150, 30));
    btnCancelar.addActionListener(e -> dispose());
    
    panelBotones.add(btnGuardar);
    panelBotones.add(btnCancelar);
    
    panelCentral.add(panelFormulario);
    panelCentral.add(panelBotones);
    
    panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
    panelPrincipal.add(panelCentral, BorderLayout.CENTER);
    
    add(panelPrincipal);
    
    setSize(500, 400);
    setLocationRelativeTo(getParent());
    
    SwingUtilities.invokeLater(() -> txtNombre.requestFocus());
}
    private void registrarPrestamo() {
    String nombre = txtNombre.getText();
    String codigo = txtCodigo.getText();
    
    if (nombre == null || nombre.trim().isEmpty() || 
        codigo == null || codigo.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Por favor ingrese nombre y código",
            "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(this,
        "¿Confirmar préstamo?\n" +
        "Estudiante: " + nombre + "\n" +
        "Código: " + codigo + "\n" +
        "Libro: " + libro.getTitulo(),
        "Confirmar Préstamo", JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        JOptionPane.showMessageDialog(this, "Préstamo registrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
   
}