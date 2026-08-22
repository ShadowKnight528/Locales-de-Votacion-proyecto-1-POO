package package_00;

public class Votante extends Ciudadano {
	
	private boolean estadoVoto;
	private Coordenadas residencia;
	
	public Votante(String rut, String nombre, Coordenadas residencia) {
		super(rut, nombre);
		setEstadoVoto(false);
		setResidencia(residencia);
	}
	
	public void setEstadoVoto(boolean estadoVoto) {
		this.estadoVoto = estadoVoto;
	}
	
	public void setResidencia(Coordenadas residencia) {
		if (residencia == null) {
			System.out.println("Residencia invalida");
		} else {
			this.residencia = residencia;
		}
	}
	
	
	public boolean getEstadoVoto() {
		return estadoVoto;
	}
	
	public Coordenadas getResidencia() {
		return residencia;
	}
	
	public double calcularDistanciaASede(Sede sede) {
		if (sede == null || sede.getUbicacion() == null || this.residencia == null) {
			return -1;
		}
		Coordenadas ubicacionSede = sede.getUbicacion();
		double componenteXSede = ubicacionSede.getX();
		double componenteYSede = ubicacionSede.getY();
		double componenteXVotante = residencia.getX();
		double componenteYVotante = residencia.getY();
		double aux = Math.pow(componenteXVotante -  componenteXSede, 2) + Math.pow(componenteYVotante -  componenteYSede, 2);
		double distancia = Math.sqrt(aux);
		return distancia;
	}
}
