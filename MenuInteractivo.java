package package_00;

import java.util.HashMap;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuInteractivo {
	
	private Vector<Sede> sedes;
	
	public MenuInteractivo(Vector<Sede> sedes) {
		this.sedes = sedes;
	}
	
    public void leerEntradaUsuario() throws IOException {
    	
        GestorDeColecciones gestor = new GestorDeColecciones(sedes);
    	BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    	int opcion = 0;
    	while(true) {
    		
    		System.out.println("Seleccione un numero");
        	System.out.println("1 - Entrar en modo de consola");
        	System.out.println("2 - Entrar en modo de ventana");
        	
    		try {
    			opcion = Integer.parseInt(lector.readLine());
    		} catch (IllegalArgumentException e) {
    			System.out.println("Debe ingresar un numero entero!");
    			continue;
    		}
    		
    		switch(opcion) {
    			case 1:
    				boolean modoConsolaActivado = true;
    				while (modoConsolaActivado) {
    					System.out.println("Seleccione un numero");
    					System.out.println("1 - Entrar como gestor");
    					System.out.println("2 - Entrar como votante");
    					System.out.println("3 - Volver");
    					int opcionConsola = 0;
    					try {
    						opcionConsola = Integer.parseInt(lector.readLine());
    					} catch (IllegalArgumentException e) {
    						System.out.println("Debe ingresar un numero entero!");
    						continue;
    					}
    				
    					switch(opcionConsola) {
    						case 1:
    							boolean modoGestorActivado = true;
    							while (modoGestorActivado) {
    								System.out.println("Seleccione un numero");
    								System.out.println("1 - Gestionar votantes");
    								System.out.println("2 - Gestionar mesas");
    								System.out.println("3 - Gestionar sedes");
    								System.out.println("4 - Volver");
    								int opcionGestion = 0;
    								try {
    									opcionGestion = Integer.parseInt(lector.readLine());
    								} catch (IllegalArgumentException e) {
    									System.out.println("Debe ingresar un numero entero!");
    									continue;
    								}
    								
    								switch(opcionGestion) {
    									case 1:
    										boolean modoGestorVotantes = true;
    										while (modoGestorVotantes) {
    											System.out.println("Seleccione un numero");
    											System.out.println("1 - Agregar votante a mesa");
    											System.out.println("2 - Listar votantes asignados a una mesa");
    											System.out.println("3 - Buscar un votante en una mesa");
    											System.out.println("4 - Eliminar la asignacion de un votante a una mesa");
    											System.out.println("5 - Modificar el nombre de un votante");
    											System.out.println("6 - Modificar el domicilio de un votante");
    											System.out.println("7 - Volver");
    											int opcionGestionVotantes = 0;
    											try {
    												opcionGestionVotantes = Integer.parseInt(lector.readLine());
    											} catch (IllegalArgumentException e) {
    												System.out.println("Debe ingresar un numero entero!");
    												continue;
    											}
    											switch(opcionGestionVotantes) {
    												case 1:
    													
    													boolean esValidoID = false;
    													int idSede = -1;
    													while(!esValidoID) {
    														System.out.println("Ingrese el ID de la sede a la que pertenece la mesa a la que se le desea asginar un votante");
    														try {
    															idSede = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														esValidoID = true;
    													}
    													Sede sede = gestor.buscarSede(idSede);
    													if (sede == null) {
    														System.out.println("El ID ingresado no corresponde una sede existente");
    														break;
    													} else {
    														System.out.println("Ingrese el numero de la mesa a la que desea agregar un votante");
    														int numMesa = -1;
    														try {
    															numMesa = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														Mesa mesaAgregarVotante = gestor.buscarMesaEnSede(numMesa, sede);
    														if (mesaAgregarVotante == null) {
    															System.out.println("El numero de mesa ingresado no se halla registrado dentro de la sede seleccionada");
    															break;
    														} else {
    															System.out.println("Ingrese los datos del votante que desea agregar a la mesa");
    															System.out.println("Ingrese el rut del votante");
    															String rut = null;
    															try {
    																rut = lector.readLine();
    															} catch (IOException e) {
    																System.out.println("Error al leer el rut, ingrese un String");
    																continue;
    															}
    															System.out.println("Ingrese el nombre del votante");
    															String nombre = null;
    															try {
    																nombre = lector.readLine();
    															} catch (IOException e) {
    																System.out.println("Error al leer el nombre, ingrese un String");
    																continue;
    															}
    															System.out.println("Ingrese la componenete x de la coordenada del votante");
    															double xComponent = 0.0;
    															try {
    																xComponent = Double.parseDouble(lector.readLine());
    															} catch (IllegalArgumentException e) {
    																System.out.println("Error al leer la componente x, ingrese un double");
    																continue;
    															}
    															System.out.println("Ingrese la componenete y de la coordenada del votante");
    															double yComponent = 0.0;
    															try {
    																yComponent = Double.parseDouble(lector.readLine());
    															} catch (IllegalArgumentException e) {
    																System.out.println("Error al leer la componente y, ingrese un double");
    																continue;
    															}
    															Coordenadas residenciaVotanteAgregado = new Coordenadas(xComponent, yComponent);
    															Votante votanteAgregado = new Votante(rut, nombre, residenciaVotanteAgregado);
    															try {
    																mesaAgregarVotante.agregarVotante(votanteAgregado);
    															} catch (ExcedeCapacidadException e) {
    																System.out.println(e.getMessage());
    																break;
    															}
    														}
    													}
    													break;
    												case 2:
    													esValidoID = false;
    													idSede = -1;
    													while(!esValidoID) {
    														System.out.println("Ingrese el ID de la sede a la que pertenece la mesa de la cual desea observar la lista de votantes");
    														try {
    															idSede = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														esValidoID = true;
    													}
    													sede = gestor.buscarSede(idSede);
    													if (sede == null) {
    														System.out.println("El ID ingresado no corresponde una sede existente");
    														break;
    													} else {
    														System.out.println("Ingrese el numero de la mesa que desea visualizar su lista de votantes");
    														int numMesa = -1;
    														try {
    															numMesa = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														Mesa mesaAListarVotantes = gestor.buscarMesaEnSede(numMesa, sede);
    														gestor.listarVotantesMesa(mesaAListarVotantes);
    													}
    														
    													break;
    												case 3:
    													esValidoID = false;
    													idSede = -1;
    													while(!esValidoID) {
    														System.out.println("Ingrese el ID de la sede a la que pertenece la mesa en la que desea buscar un votante");
    														try {
    															idSede = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														esValidoID = true;
    													}
    													sede = gestor.buscarSede(idSede);
    													if (sede == null) {
    														System.out.println("El ID ingresado no corresponde una sede existente");
    														break;
    													} else {
    														System.out.println("Ingrese el numero de la mesa en la que desea buscar un votante");
    														int numMesa = -1;
    														try {
    															numMesa = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														Mesa mesaBuscarVotante = gestor.buscarMesaEnSede(numMesa, sede);
    														System.out.println("Ingrese el RUT del votante que desea buscar en la mesa");
    														String rut = null;
    														try {
    															rut = lector.readLine();
    														} catch (IOException e) {
    															System.out.println("Error al leer el rut, ingrese un String");
    															continue;
    														}
    														if (gestor.buscarVotanteEnMesa(rut, mesaBuscarVotante) == null) {
    															System.out.println("No se ha hallado el votante en la mesa solicitada");
    														}
    													}
    													break;
    												case 4:
    													esValidoID = false;
    													idSede = -1;
    													while(!esValidoID) {
    														System.out.println("Ingrese el ID de la sede a la que pertenece la mesa de la que desea eliminar un votante");
    														try {
    															idSede = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														esValidoID = true;
    													}
    													sede = gestor.buscarSede(idSede);
    													if (sede == null) {
    														System.out.println("El ID ingresado no corresponde una sede existente");
    														break;
    													} else {
    														System.out.println("Ingrese el numero de la mesa de la que desea eliminar un votante");
    														int numMesa = -1;
    														try {
    															numMesa = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														Mesa mesaQuitarVotante = gestor.buscarMesaEnSede(numMesa, sede);
    														System.out.println("Ingrese el RUT del votante que desea eliminar");
    														String rut = null;
    														try {
    															rut = lector.readLine();
    														} catch (IOException e) {
    															System.out.println("Error al leer el rut, ingrese un String");
    															continue;
    														}
    														gestor.eliminarVotanteDeMesa(rut, mesaQuitarVotante);
    													}
    													break;
    												case 5:
    													esValidoID = false;
    													idSede = -1;
    													while(!esValidoID) {
    														System.out.println("Ingrese el ID de la sede a la que pertenece la mesa del votante que desea modificar su nombre");
    														try {
    															idSede = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														esValidoID = true;
    													}
    													sede = gestor.buscarSede(idSede);
    													if (sede == null) {
    														System.out.println("El ID ingresado no corresponde una sede existente");
    														break;
    													} else {
    														System.out.println("Ingrese el numero de la mesa del votante que desea modificar su nombre");
    														int numMesa = -1;
    														try {
    															numMesa = Integer.parseInt(lector.readLine());
    														} catch (IllegalArgumentException e) {
    															System.out.println("Valor invalido, ingrese un numero entero!");
    															continue;
    														}
    														Mesa mesaModificarVotante = gestor.buscarMesaEnSede(numMesa, sede);
    														System.out.println("Ingrese el RUT del votante que desea modificar su nombre");
    														String rut = null;
    														try {
    															rut = lector.readLine();
    														} catch (IOException e) {
    															System.out.println("Error al leer el rut, ingrese un String");
    															continue;
    														}
    														System.out.println("Ingrese el nuevo nombre del votante");
    														String nuevo = null;
    														try {
    															nuevo = lector.readLine();
    														} catch (IOException e) {
    															System.out.println("Error al leer el nuevo nombre, ingrese un String");
    															continue;
    														}
    														boolean opcionValida = false;
    														int opcionModificarNombreVotante = 0;
    														while (!opcionValida) {
    															System.out.println("Ingrese un numero");
    															System.out.println("1 - Modificar el nombre del votante por medio de su rut y numero de mesa");
    															System.out.println("2 - Modificar el nombre del votante desde el mismo votante");
    															System.out.println("3 - Volver");
    															try {
    																opcionModificarNombreVotante = Integer.parseInt(lector.readLine());
    															} catch (IllegalArgumentException e) {
    																System.out.println("Error al leer la opcion, ingrese un numero entero");
    																continue;
    															}
    															opcionValida = true;
    														}
    														switch (opcionModificarNombreVotante) {
    															case 1:
    																gestor.modificarNombreVotante(rut, mesaModificarVotante, nuevo);
    																break;
    															case 2:
    																Votante votanteModificarNombre = gestor.buscarVotanteEnMesa(rut, mesaModificarVotante);
    																if (votanteModificarNombre == null) {
    																	System.out.println("El votante no se encuentra en la mesa solicitada");
    																} else {
    																	gestor.modificarNombreVotante(votanteModificarNombre, nuevo);
    																}
    																break;
    															case 3:
    																modoGestorVotantes = false;
    																break;
    														}
    													}
    													
    													break;
    												case 6:
    													break;
    												case 7:
    													modoGestorVotantes = false;
    													break;
    												default: 
    													System.out.println("La opcion ingresada no es valida");	
    											}
    										}
    										break;
    									case 2:
    										break;
    									case 3:
    										break;
    									case 4:
    										modoGestorActivado = false;
    										break;
    									default:
    										System.out.println("La opcion ingresada no es valida");	
    								}
    							}
    							
    							break;
    						case 2:
    							break;
    						case 3:
    							modoConsolaActivado = false;
    							break;
    						default:
    							System.out.println("La opcion ingresada no es valida");	
    				
    					}
    				}
    				
    				break;
    			case 2:
    				Ventana ventanaPrincipal = new Ventana();
    				break;
    			default:
    				System.out.println("La opción ingresada no es valida, intente ingresando 1 o 2");
    				continue;
    		}
    	}
    }
}
