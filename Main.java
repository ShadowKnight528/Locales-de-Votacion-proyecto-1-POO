package package_00;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
    public static void main(String[] args) throws IOException {
         
    	BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Seleccione 1 para consola y 2 para ventana");
    	
    	int opcion = 0;
    	while(true) {
    		
    		try {
    			opcion = Integer.parseInt(lector.readLine());
    		} catch (IllegalArgumentException e) {
    			System.out.println("Debe ingresar un numero entero!");
    			continue;
    		}
    		
    		switch(opcion) {
    			case 1:
    				System.out.println("En progreso, vuelva pronto...");
    				break;
    			case 2:
    				Ventana ventanaPrincipal = new Ventana();
    				break;
    			default:
    				System.out.println("La opción ingresada no es valida, intente ingresando 1 o 2");
    				continue;
    		}
    		break;
    	}
    }
}
