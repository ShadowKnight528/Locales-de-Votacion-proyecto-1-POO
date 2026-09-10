package package_00;

import java.io.*;

public class ControlPersistenciaDeDatos {

    private static final String ARCHIVO = "sistema_votacion.dat";

    public static void guardar(GestorDeColecciones gestor) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(gestor);
            System.out.println("Datos guardados correctamente en el archivo batch!");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public static GestorDeColecciones cargar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("No se encontro archivo previo. Se iniciara con datos nuevos.");
            return null; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            GestorDeColecciones gestorCargado = (GestorDeColecciones) ois.readObject();
            System.out.println("Datos cargados exitosamente desde el archivo!");
            return gestorCargado;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }
}
