package package_00;
import java.util.HashMap;
import java.util.Vector;

public class SistemaDeAsignacion {
	
	private boolean asignarMesa(Votante votante, Sede sede) {
		HashMap<Integer, Mesa> mapaMesas = sede.getMapaMesas();
		for (Mesa mesa : mapaMesas.values()) {
			if (mesa.agregarVotante(votante) == true) {
				return true;
			}
		}
		return false;
	}
	
	public void asignarSede(Votante votante, Vector<Sede> vectorSedes) {
		double menorDistancia = 0;
		Sede sedeMenorDistancia = null;
		int aux = 0;
		for (Sede sede : vectorSedes) {
			if (sede.getCuposDisponibles() > 0) {
				if (aux == 0) {
					menorDistancia = votante.calcularDistanciaASede(sede);
					sedeMenorDistancia = sede;
					aux = 1;
				} else {
					double distanciaActual = votante.calcularDistanciaASede(sede);
					if (distanciaActual < menorDistancia) {
						menorDistancia = distanciaActual;
						sedeMenorDistancia = sede;
					}
				}
			}
		}
		if (sedeMenorDistancia != null) {
			if (asignarMesa(votante, sedeMenorDistancia) == true) {
				sedeMenorDistancia.decrementarCuposDisponibles();
			}
		}
	}
}
