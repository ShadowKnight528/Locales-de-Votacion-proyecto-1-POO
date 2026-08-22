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
}
