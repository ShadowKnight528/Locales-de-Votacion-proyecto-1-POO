package package_00;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        
        GestorDeColecciones gestor = ControlPersistenciaDeDatos.cargar();
        if (gestor == null) {
            gestor = new GestorDeColecciones();
            System.out.println("Sistema inicializado con un gestor vacio.");
        }
        
        Vector<Sede> sedes = gestor.getSedes(); 
        HashMap<String, Integer> plantillaConteoVotos = new HashMap<String, Integer>();
        MenuInteractivo menu = new MenuInteractivo(sedes, plantillaConteoVotos);
        try {
            menu.leerEntradaUsuario();
        } catch (IOException e) {
            System.err.println("Error en el menu: " + e.getMessage());
        }
       
        ControlPersistenciaDeDatos.guardar(gestor);
        System.out.println("Programa finalizado correctamente.");
    }
}
