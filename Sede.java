package package_00;

public class Sede {
	
	private int id;
	private int capMax;
	private String estado;
	private Coordenadas ubicacion;
	private Mesa[] arregloMesas; // Despues decidir si se quedara como arreglo o como lista
	
	public Sede(int id, int capMax, Coordenadas ubicacion, Mesa[] arregloMesas) {
		setId(id);
		setCapMax(capMax);
		this.estado = "Capacidad Disponible";
		setUbicacion(ubicacion);
		setArregloMesas(arregloMesas);
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
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setUbicacion(Coordenadas ubicacion) {
		if (ubicacion == null) {
			System.out.println("Ubicacion no valida");
		} else {
			this.ubicacion = ubicacion;
		}
	}
	
	public void setArregloMesas(Mesa[] arregloMesas) {
		if (arregloMesas == null) {
			System.out.println("Arreglo no valido");
		} else {
			this.arregloMesas = arregloMesas;
		}
	}
	
	public int getId() {
		return id;
	}
	
	public int getCapMax() {
		return capMax;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Coordenadas getUbicacion() {
		return ubicacion;
	}
	
	public Mesa[] getArregloMesas() {
		return arregloMesas;
	}
}
