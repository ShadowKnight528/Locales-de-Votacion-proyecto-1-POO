package package_00;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        
        // Se declaran las estructuras base por si no hay archivo previo o si hay que inicializar datos
        Vector<Sede> sedesIniciales = new Vector<>();
        HashMap<String, Integer> plantillaConteoVotos = new HashMap<>();
        plantillaConteoVotos.put("Candidato A", 0);
        plantillaConteoVotos.put("Candidato B", 0);

        // Se prueba cargar los datos desde el archivo CSV
        GestorDeColecciones gestor = ControlPersistenciaDeDatos.cargar(sedesIniciales, plantillaConteoVotos);
        
        // Si no existe el archivo, se crea el gestor y se inyectan los datos de prueba
        if (gestor == null) {
            gestor = new GestorDeColecciones(sedesIniciales);
            System.out.println("Sistema inicializado con un gestor vacio.");
            try {
                Coordenadas coordSede = new Coordenadas(-67.7676, -67.67);
                Sede sedeInicial = new Sede(1, 67, coordSede); 
                
                Mesa mesaInicial = new Mesa(101, 50, new HashMap<>(plantillaConteoVotos));
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
        
        // Obtenemos las sedes actuales (ya sea cargadas del CSV o los que estan en el main por defecto)
        Vector<Sede> sedes = gestor.getSedes(); 
        
        // Lanzamos el menu interactivo
        MenuInteractivo menu = new MenuInteractivo(sedes, plantillaConteoVotos);
        try {
            menu.leerEntradaUsuario();
        } catch (IOException e) {
            System.err.println("Error en el menu: " + e.getMessage());
        }
        
        // Al salir del menu, guardamos automaticamente en el archivo CSV (sistema batch)
        ControlPersistenciaDeDatos.guardar(gestor);
        System.out.println("Programa finalizado correctamente.");
    }
}
