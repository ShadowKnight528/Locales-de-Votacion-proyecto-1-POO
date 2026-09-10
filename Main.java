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
            try {
                Coordenadas coordSede = new Coordenadas(-67.7676, -67.67);
                Sede sedeInicial = new Sede(1, 67, coordSede); 
                
                HashMap<String, Integer> votosMesaInicial = new HashMap<String, Integer>();
                votosMesaInicial.put("Candidato A", 0);
                votosMesaInicial.put("Candidato B", 0);
                
                Mesa mesaInicial = new Mesa(101, 50, votosMesaInicial);
                sedeInicial.agregarMesa(mesaInicial);
                
                Coordenadas coordVotante = new Coordenadas(-60.677, -70.67);
                Votante votanteInicial = new Votante("12345678-9", "Juan Perez", coordVotante);
                mesaInicial.agregarVotante(votanteInicial);
                
                gestor.getSedes().add(sedeInicial);
                System.out.println("Datos iniciales de prueba cargados exitosamente.");
            } catch (Exception e) {
                System.err.println("Advertencia al cargar datos iniciales: " + e.getMessage());
            }
        }
        
        Vector<Sede> sedes = gestor.getSedes(); 
        HashMap<String, Integer> plantillaConteoVotos = new HashMap<String, Integer>();
        plantillaConteoVotos.put("Candidato A", 0);
        plantillaConteoVotos.put("Candidato B", 0);
        
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
