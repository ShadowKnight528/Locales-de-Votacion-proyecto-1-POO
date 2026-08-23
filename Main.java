package package_00;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Vector;

public class Main {
	public static void main(String[] args) {
		
		HashMap<String, Integer> conteoVotosPorMesa = new HashMap<String, Integer>();
		Candidato candidato00 = new Candidato("42424141-1", "Rodolfo Higmer", "Partido de la Refundación");
		Candidato candidato01 = new Candidato("21424114-K", "Jose Stalin", "Coalición de Centro Unido");
		conteoVotosPorMesa.put(candidato00.getNombre(), 0);
		conteoVotosPorMesa.put(candidato01.getNombre(), 0);
		
		Coordenadas casaVotante00 = new Coordenadas(205.0, 10.0);
		Votante votante00 = new Votante("18234111-1", "Jose Velasquez", casaVotante00);

		Coordenadas casaVotante01 = new Coordenadas(190.5, -15.2);
		Votante votante01 = new Votante("19412998-3", "Manuel Rodriguez", casaVotante01);

		Coordenadas casaVotante02 = new Coordenadas(210.0, 5.5);
		Votante votante02 = new Votante("17523891-K", "Ramiro Sepulveda", casaVotante02);

		Coordenadas casaVotante03 = new Coordenadas(198.0, 12.0);
		Votante votante03 = new Votante("20123456-7", "Andrea Morales", casaVotante03);

		Coordenadas casaVotante04 = new Coordenadas(220.0, -8.0);
		Votante votante04 = new Votante("16892341-5", "Gonzalo Silva", casaVotante04);

		Coordenadas casaVotante05 = new Coordenadas(185.0, 20.0);
		Votante votante05 = new Votante("15982341-9", "Beatriz Arancibia", casaVotante05);

		Coordenadas casaVotante06 = new Coordenadas(202.0, -2.0);
		Votante votante06 = new Votante("21092384-2", "Claudio Fuentes", casaVotante06);

		Coordenadas casaVotante07 = new Coordenadas(215.0, 18.0);
		Votante votante07 = new Votante("14238911-8", "Daniela Gutierrez", casaVotante07);

		Coordenadas casaVotante08 = new Coordenadas(208.0, -12.0);
		Votante votante08 = new Votante("19823122-4", "Esteban Paredes", casaVotante08);

		Coordenadas casaVotante09 = new Coordenadas(-980.0, 1020.0);
		Votante votante09 = new Votante("13892311-6", "Fernanda Lopez", casaVotante09);

		Coordenadas casaVotante10 = new Coordenadas(-1015.0, 990.0);
		Votante votante10 = new Votante("12983411-0", "Gabriel Suazo", casaVotante10);
		
		Mesa mesa_1_sede_1 = new Mesa(1, 5, new HashMap<String, Integer>(conteoVotosPorMesa));
		Mesa mesa_2_sede_1 = new Mesa(2, 4, new HashMap<String, Integer>(conteoVotosPorMesa));
		Mesa mesa_1_sede_2 = new Mesa(1, 3, new HashMap<String, Integer>(conteoVotosPorMesa));
		Mesa mesa_2_sede_2 = new Mesa(2, 3, new HashMap<String, Integer>(conteoVotosPorMesa));
		Mesa mesa_3_sede_2 = new Mesa(3, 2, new HashMap<String, Integer>(conteoVotosPorMesa));
		Mesa mesa_1_sede_3 = new Mesa(1, 6, new HashMap<String, Integer>(conteoVotosPorMesa));
		
		HashMap<Integer, Mesa> mapaMesasSede1 = new HashMap<Integer, Mesa>();
		mapaMesasSede1.put(mesa_1_sede_1.getNumeroMesa(), mesa_1_sede_1);
		mapaMesasSede1.put(mesa_2_sede_1.getNumeroMesa(), mesa_2_sede_1);
		
		HashMap<Integer, Mesa> mapaMesasSede2 = new HashMap<Integer, Mesa>();
		mapaMesasSede2.put(mesa_1_sede_2.getNumeroMesa(), mesa_1_sede_2);
		mapaMesasSede2.put(mesa_2_sede_2.getNumeroMesa(), mesa_2_sede_2);
		mapaMesasSede2.put(mesa_3_sede_2.getNumeroMesa(), mesa_3_sede_2);
		
		HashMap<Integer, Mesa> mapaMesasSede3 = new HashMap<Integer, Mesa>();
		mapaMesasSede3.put(mesa_1_sede_3.getNumeroMesa(), mesa_1_sede_3);
		
		Coordenadas ubicacionSede1 = new Coordenadas(-1000, 1000);
		Coordenadas ubicacionSede2 = new Coordenadas(200, 0);
		Coordenadas ubicacionSede3 = new Coordenadas(0, 9000);
	
		Sede sede_1 = new Sede(1, 9, ubicacionSede1, mapaMesasSede1);
		Sede sede_2 = new Sede(2, 8, ubicacionSede2, mapaMesasSede2);
		Sede sede_3 = new Sede(3, 6, ubicacionSede3, mapaMesasSede3);
		
		Vector<Sede> sedes = new Vector<Sede>();
		sedes.add(sede_1);
		sedes.add(sede_2);
		sedes.add(sede_3);
		
		SistemaDeAsignacion gestor = new SistemaDeAsignacion();
		gestor.asignarSede(votante00, sedes);
		gestor.asignarSede(votante01, sedes);
		gestor.asignarSede(votante02, sedes);
		gestor.asignarSede(votante03, sedes);
		gestor.asignarSede(votante04, sedes);
		gestor.asignarSede(votante05, sedes);
		gestor.asignarSede(votante06, sedes);
		gestor.asignarSede(votante07, sedes);
		gestor.asignarSede(votante08, sedes);
		gestor.asignarSede(votante09, sedes);
		gestor.asignarSede(votante10, sedes);
		
		for (Sede sedeActual: sedes) {
			System.out.println("Sede " + sedeActual.getId() + " (" + sedeActual.getUbicacion().getX() + ", " + sedeActual.getUbicacion().getY() + ")");
			HashMap<Integer, Mesa> mapaMesas = sedeActual.getMapaMesas();
			for (Mesa mesaActual : mapaMesas.values()) {
				ArrayList<Votante> listaVotantesMesaActual = mesaActual.getListaVotantes();
				System.out.println(" ");
				System.out.println("Lista de votantes de la mesa " + mesaActual.getNumeroMesa() + " :" );
				System.out.println(" ");
				for (Votante votante : listaVotantesMesaActual) {
					System.out.println("RUT: " + votante.getRut() + " Nombre: " + votante.getNombre() + " (" + votante.getResidencia().getX() + ", " + votante.getResidencia().getY() + ")");
				}
			}
		}
	}
}
