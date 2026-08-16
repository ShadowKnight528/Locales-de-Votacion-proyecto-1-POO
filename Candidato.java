package package_00;

public class Candidato extends Ciudadano {
	
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
}
