package package_00;

import java.io.Serializable;

public class Ciudadano implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rut;
	private String nombre;
	
	public Ciudadano(String rut, String nombre) {
		setRut(rut);
		setNombre(nombre);
	}
	
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getRut() {
		return rut;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String obtenerDatos() {
		return "RUT: " + rut + " " + "Nombre: " + nombre;
	}
}
