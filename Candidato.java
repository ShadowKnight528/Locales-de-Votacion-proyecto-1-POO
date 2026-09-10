package package_00;

import java.io.Serializable;

public class Candidato extends Ciudadano implements Serializable {
	
	private String partido;
	
	public Candidato(String rut, String nombre, String partido) {
		super(rut, nombre);
		setPartido(partido);
	}
	
	public void setPartido(String partido) {
		this.partido = partido;
	}
	
	public String getPartido() {
		return partido;
	}
	
	@Override
	public String obtenerDatos() {
		return super.obtenerDatos() + " Partido: " + partido;
	}
}
