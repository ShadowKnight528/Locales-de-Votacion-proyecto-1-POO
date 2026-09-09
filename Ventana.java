package package_00;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Ventana extends JFrame {
    private JTextArea areaTexto;
    private JPanel panelSubOpciones;
    private GestorDeColecciones gestor;
    
    public Ventana(GestorDeColecciones gestor) {
    	this.gestor = gestor;
    	
        this.setSize(700, 600);
        this.setTitle("Ventana con Opciones");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        setLayout(new BorderLayout(10, 10));
 
        JPanel panelOpciones = crearPanelOpciones();
        add(panelOpciones, BorderLayout.NORTH);

        panelSubOpciones = new JPanel();
        panelSubOpciones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelSubOpciones.setBorder(BorderFactory.createTitledBorder("Subopciones"));
        panelSubOpciones.setBackground(new Color(240, 248, 255));
        add(panelSubOpciones, BorderLayout.CENTER);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new Dimension(680, 200));
        add(scroll, BorderLayout.SOUTH);

        areaTexto.append("Bienvenido! Selecciona una opción...\n");
    }
    
    private JPanel crearPanelOpciones() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton gestionVotantes = crearBoton("Gestion de Votantes", new Color(70, 130, 180));
        JButton gestionMesas = crearBoton("Gestion de Mesas", new Color(60, 179, 113));
        JButton gestionSedes = crearBoton("Gestion de Sedes", new Color(255, 140, 0));
        JButton btnLimpiar = crearBoton("Limpiar", Color.RED);
        
        gestionVotantes.addActionListener(e -> mostrarSubOpcionesVotantes());
        gestionMesas.addActionListener(e -> mostrarSubOpcionesMesas());
        gestionSedes.addActionListener(e -> mostrarSubOpcionesSedes());
        btnLimpiar.addActionListener(e -> {
            areaTexto.setText("");
            panelSubOpciones.removeAll();
            panelSubOpciones.revalidate();
            panelSubOpciones.repaint();
        });
        
        panel.add(gestionVotantes);
        panel.add(gestionMesas);
        panel.add(gestionSedes);
        panel.add(btnLimpiar);
        
        return panel;
    }

    private void mostrarSubOpcionesVotantes() {
        panelSubOpciones.removeAll();
        panelSubOpciones.setBorder(BorderFactory.createTitledBorder("Subopciones - Opción 1"));
        
        JButton agregarVotante = crearBotonSub("Agregar votante", new Color(70, 130, 180));
        JButton listarVotantesAsigMesa = crearBotonSub("Listar votantes asignados a una mesa", new Color(70, 130, 180));
        JButton buscarVotanteMesa = crearBotonSub("Buscar un votante en una mesa", new Color(70, 130, 180));
        JButton eliminarVotanteAsigMesa = crearBotonSub("Eliminar la asignacion de un votante a una mesa", new Color(70, 130, 180));
        JButton modificarVotanteNombre = crearBotonSub("Modificar el nombre de un votante", new Color(70, 130, 180));
        JButton modificarVotanteDomicilio = crearBotonSub("Modificar el domicilio de un votante", new Color(70, 130, 180));
        JButton btnVolver = crearBotonSub("⬅ Volver", Color.GRAY);

        agregarVotante.addActionListener(e -> {
            boolean esValidoID = false;
            int idSede = -1;
            while (!esValidoID) {
                String input = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el ID de la sede a la que pertenece la mesa a la que se le desea asignar un votante"
                );
                if (input == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                try {
                    idSede = Integer.parseInt(input.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    continue;
                }
                esValidoID = true;
            }

            Sede sede = gestor.buscarSede(idSede);
            if (sede == null) {
                areaTexto.append("El ID ingresado no corresponde una sede existente\n");
                return;
            } else {
                String inputMesa = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el numero de la mesa a la que desea agregar un votante"
                );
                if (inputMesa == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                int numMesa = -1;
                try {
                    numMesa = Integer.parseInt(inputMesa.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    return;
                }

                Mesa mesaAgregarVotante = gestor.buscarMesaEnSede(numMesa, sede);
                if (mesaAgregarVotante == null) {
                    areaTexto.append("El numero de mesa ingresado no se halla registrado dentro de la sede seleccionada\n");
                    return;
                } else {
                    areaTexto.append("Ingrese los datos del votante que desea agregar a la mesa\n");

                    String rut = JOptionPane.showInputDialog(this, "Ingrese el rut del votante");
                    if (rut == null) {
                        areaTexto.append("Operación cancelada\n");
                        return;
                    }

                    String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del votante");
                    if (nombre == null) {
                        areaTexto.append("Operación cancelada\n");
                        return;
                    }

                    String inputX = JOptionPane.showInputDialog(this, "Ingrese la componente x de la coordenada del votante");
                    if (inputX == null) {
                        areaTexto.append("Operación cancelada\n");
                        return;
                    }
                    double xComponent = 0.0;
                    try {
                        xComponent = Double.parseDouble(inputX.trim());
                    } catch (NumberFormatException ex) {
                        areaTexto.append("Error al leer la componente x, ingrese un double\n");
                        return;
                    }

                    String inputY = JOptionPane.showInputDialog(this, "Ingrese la componente y de la coordenada del votante");
                    if (inputY == null) {
                        areaTexto.append("Operación cancelada\n");
                        return;
                    }
                    double yComponent = 0.0;
                    try {
                        yComponent = Double.parseDouble(inputY.trim());
                    } catch (NumberFormatException ex) {
                        areaTexto.append("Error al leer la componente y, ingrese un double\n");
                        return;
                    }

                    Coordenadas residenciaVotanteAgregado = new Coordenadas(xComponent, yComponent);
                    Votante votanteAgregado = new Votante(rut, nombre, residenciaVotanteAgregado);
                    try {
                        mesaAgregarVotante.agregarVotante(votanteAgregado);
                        areaTexto.append("Votante agregado correctamente\n");
                    } catch (ExcedeCapacidadException ex) {
                        areaTexto.append(ex.getMessage() + "\n");
                        return;
                    }
                }
            }            
        });
        
        listarVotantesAsigMesa.addActionListener(e -> {
            boolean esValidoID = false;
            int idSede = -1;
            while (!esValidoID) {
                String input = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el ID de la sede a la que pertenece la mesa de la cual desea observar la lista de votantes"
                );
                if (input == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                try {
                    idSede = Integer.parseInt(input.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    continue;
                }
                esValidoID = true;
            }

            Sede sede = gestor.buscarSede(idSede);
            if (sede == null) {
                areaTexto.append("El ID ingresado no corresponde una sede existente\n");
                return;
            } else {
                String inputMesa = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el numero de la mesa que desea visualizar su lista de votantes"
                );
                if (inputMesa == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                int numMesa = -1;
                try {
                    numMesa = Integer.parseInt(inputMesa.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    return;
                }

                Mesa mesaAListarVotantes = gestor.buscarMesaEnSede(numMesa, sede);

                PrintStream originalOut = System.out;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(baos);
                System.setOut(printStream);

                gestor.listarVotantesMesa(mesaAListarVotantes);

                System.out.flush();
                System.setOut(originalOut);

                String salidaCapturada = baos.toString();
                if (!salidaCapturada.isEmpty()) {
                    areaTexto.append(salidaCapturada);
                } else {
                    areaTexto.append("No hay votantes en esta mesa\n");
                }
            }
        });
        
        buscarVotanteMesa.addActionListener(e -> {
            boolean esValidoID = false;
            int idSede = -1;
            while (!esValidoID) {
                String input = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el ID de la sede a la que pertenece la mesa en la que desea buscar un votante"
                );
                if (input == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                try {
                    idSede = Integer.parseInt(input.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    continue;
                }
                esValidoID = true;
            }

            Sede sede = gestor.buscarSede(idSede);
            if (sede == null) {
                areaTexto.append("El ID ingresado no corresponde una sede existente\n");
                return;
            } else {
                String inputMesa = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el numero de la mesa en la que desea buscar un votante"
                );
                if (inputMesa == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                int numMesa = -1;
                try {
                    numMesa = Integer.parseInt(inputMesa.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    return;
                }

                Mesa mesaBuscarVotante = gestor.buscarMesaEnSede(numMesa, sede);

                String rut = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el RUT del votante que desea buscar en la mesa"
                );
                if (rut == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }

                Votante votante = gestor.buscarVotanteEnMesa(rut, mesaBuscarVotante);
                if (votante == null) {
                    areaTexto.append("No se ha hallado el votante en la mesa solicitada\n");
                } else {
                    areaTexto.append("Votante encontrado: " + votante + "\n");
                }
            }
        });
        
        eliminarVotanteAsigMesa.addActionListener(e -> {
            boolean esValidoID = false;
            int idSede = -1;
            while (!esValidoID) {
                String input = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el ID de la sede a la que pertenece la mesa de la que desea eliminar un votante"
                );
                if (input == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                try {
                    idSede = Integer.parseInt(input.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    continue;
                }
                esValidoID = true;
            }

            Sede sede = gestor.buscarSede(idSede);
            if (sede == null) {
                areaTexto.append("El ID ingresado no corresponde una sede existente\n");
                return;
            } else {
                String inputMesa = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el numero de la mesa de la que desea eliminar un votante"
                );
                if (inputMesa == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                int numMesa = -1;
                try {
                    numMesa = Integer.parseInt(inputMesa.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    return;
                }

                Mesa mesaQuitarVotante = gestor.buscarMesaEnSede(numMesa, sede);

                String rut = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el RUT del votante que desea eliminar"
                );
                if (rut == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }

                gestor.eliminarVotanteDeMesa(rut, mesaQuitarVotante);
            }
        });
        
        modificarVotanteNombre.addActionListener(e -> {
            boolean esValidoID = false;
            int idSede = -1;
            while (!esValidoID) {
                String input = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el ID de la sede a la que pertenece la mesa del votante que desea modificar su nombre"
                );
                if (input == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                try {
                    idSede = Integer.parseInt(input.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    continue;
                }
                esValidoID = true;
            }

            Sede sede = gestor.buscarSede(idSede);
            if (sede == null) {
                areaTexto.append("El ID ingresado no corresponde una sede existente\n");
                return;
            } else {
                String inputMesa = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el numero de la mesa del votante que desea modificar su nombre"
                );
                if (inputMesa == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                int numMesa = -1;
                try {
                    numMesa = Integer.parseInt(inputMesa.trim());
                } catch (NumberFormatException ex) {
                    areaTexto.append("Valor invalido, ingrese un numero entero!\n");
                    return;
                }

                Mesa mesaModificarVotante = gestor.buscarMesaEnSede(numMesa, sede);

                String rut = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el RUT del votante que desea modificar su nombre"
                );
                if (rut == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }

                String nuevo = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el nuevo nombre del votante"
                );
                if (nuevo == null) {
                    areaTexto.append("Operación cancelada\n");
                    return;
                }
                boolean opcionValida = false;
                int opcionModificarNombreVotante = 0;
                while (!opcionValida) {
                    String[] opciones = {"1 - Modificar el nombre por RUT y mesa",
                        "2 - Modificar el nombre desde el votante",
                        "3 - Volver"};
                    String seleccion = (String) JOptionPane.showInputDialog(
                            this,
                            "Seleccione una opción:",
                            "Modificar nombre de votante",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opciones,
                            opciones[0]
                    );
                    if (seleccion == null) {
                        areaTexto.append("Operación cancelada\n");
                        return;
                    }
                    try {
                        opcionModificarNombreVotante = Integer.parseInt(seleccion.substring(0, 1));
                    } catch (NumberFormatException ex) {
                        areaTexto.append("Error al leer la opcion, ingrese un numero entero\n");
                        continue;
                    }
                    opcionValida = true;
                }

                switch (opcionModificarNombreVotante) {
                    case 1:
                        gestor.modificarNombreVotante(rut, mesaModificarVotante, nuevo);
                        break;
                    case 2:
                        Votante votanteModificarNombre = gestor.buscarVotanteEnMesa(rut, mesaModificarVotante);
                        if (votanteModificarNombre == null) {
                            areaTexto.append("El votante no se encuentra en la mesa solicitada\n");
                        } else {
                            gestor.modificarNombreVotante(votanteModificarNombre, nuevo);
                        }
                        break;
                    case 3:
                        areaTexto.append("Operación cancelada por el usuario\n");
                        return;
                    default:
                        areaTexto.append("Opción no válida\n");
                        break;
                }
            }
        });
        
        modificarVotanteDomicilio.addActionListener(e -> {
            areaTexto.append("Aquí va la accion de mover al votante de domicilio\n");
        });
        
        btnVolver.addActionListener(e -> {
            panelSubOpciones.removeAll();
            panelSubOpciones.revalidate();
            panelSubOpciones.repaint();
        });
        
        panelSubOpciones.add(agregarVotante);
        panelSubOpciones.add(listarVotantesAsigMesa);
        panelSubOpciones.add(buscarVotanteMesa);
        panelSubOpciones.add(eliminarVotanteAsigMesa);
        panelSubOpciones.add(modificarVotanteNombre);
        panelSubOpciones.add(modificarVotanteDomicilio);
        panelSubOpciones.add(btnVolver);
        
        panelSubOpciones.revalidate();
        panelSubOpciones.repaint();
    }
    
    private void mostrarSubOpcionesMesas() {
        panelSubOpciones.removeAll();
        panelSubOpciones.setBorder(BorderFactory.createTitledBorder("Subopciones - Opción 2"));
        
        JButton btnSub1 = crearBotonSub("Acción 1", new Color(60, 179, 113));
        JButton btnVolver = crearBotonSub("⬅ Volver", Color.GRAY);
        
        btnSub1.addActionListener(e -> {
            areaTexto.append("\n=== ACCIÓN 1 DE OPCIÓN 2 ===\n");
            areaTexto.append("Acción personalizada para Opción 2\n\n");
        });
        
        btnVolver.addActionListener(e -> {
            panelSubOpciones.removeAll();
            panelSubOpciones.revalidate();
            panelSubOpciones.repaint();
        });
        
        panelSubOpciones.add(btnSub1);
        panelSubOpciones.add(btnVolver);
        
        panelSubOpciones.revalidate();
        panelSubOpciones.repaint();
    }
    
    private void mostrarSubOpcionesSedes() {
        panelSubOpciones.removeAll();
        panelSubOpciones.setBorder(BorderFactory.createTitledBorder("Subopciones - Opción 3"));
        
        JButton btnSub1 = crearBotonSub("Accion 1", new Color(255, 140, 0));
        JButton btnVolver = crearBotonSub("⬅ Volver", Color.GRAY);
        
        btnSub1.addActionListener(e -> {
            areaTexto.append("Acción de herramienta 1\n\n");
        });
        
        btnVolver.addActionListener(e -> {
            panelSubOpciones.removeAll();
            panelSubOpciones.revalidate();
            panelSubOpciones.repaint();
        });
        
        panelSubOpciones.add(btnSub1);
        panelSubOpciones.add(btnVolver);
        
        panelSubOpciones.revalidate();
        panelSubOpciones.repaint();
    }
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(150, 45));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return boton;
    }
    
    private JButton crearBotonSub(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setPreferredSize(new Dimension(160, 40));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        return boton;
    }
}
