package package_00;
import java.util.HashMap;

public class Sede {
	
	private int id;
	private int capMax;
	private int cuposDisponibles;
	private Coordenadas ubicacion;
	private HashMap<Integer, Mesa> mapaMesas;
	
	public Sede(int id, int capMax, Coordenadas ubicacion, HashMap<Integer, Mesa> mapaMesas) {
		setId(id);
		setCapMax(capMax);
		cuposDisponibles = this.capMax;
		setUbicacion(ubicacion);
		setMapaMesas(mapaMesas);
	}
	
	public void setId(int id) {
		if (id <= 0) {
			System.out.println("ID no valido"); // Despues manejar con excepciones
		} else {
			this.id = id;
		}
	}
	
	public void setCapMax(int capMax) {
		if (capMax <= 0) {
			System.out.println("Capacidad no valida");
		} else {
			this.capMax = capMax;
		}
	}
	
	public void setCuposDisponibles(int cuposDisponibles) {
		if (cuposDisponibles < 0 || cuposDisponibles > capMax) {
			System.out.println("Cantidad ingresada no valida");
		} else {
			this.cuposDisponibles = cuposDisponibles;
		}
	}
	
	public void setUbicacion(Coordenadas ubicacion) {
		if (ubicacion == null) {
			System.out.println("Ubicacion no valida");
		} else {
			this.ubicacion = ubicacion;
		}
	}
	
	public void setMapaMesas(HashMap<Integer, Mesa> mapaMesas) {
		if (mapaMesas == null) {
			System.out.println("Coleccion no valida");
		} else {
			this.mapaMesas = mapaMesas;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public int getCapMax() {
		return capMax;
	}
	
	public int getCuposDisponibles() {
		return cuposDisponibles;
	}
	
	public Coordenadas getUbicacion() {
		return ubicacion;
	}
	
	public HashMap<Integer, Mesa> getMapaMesas() {
		return mapaMesas;
	}
}
