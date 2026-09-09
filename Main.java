package package_00;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	
    	Vector<Sede> sedes = new Vector<Sede>();
    	MenuInteractivo menu = new MenuInteractivo(sedes, null);
    	menu.leerEntradaUsuario();
    }
}
