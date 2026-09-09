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
    													Sede sede = buscarSede(idSede);
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
    														Mesa mesaAgregarVotante = buscarMesaEnSede(numMesa, sede);
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
    													sede = buscarSede(idSede);
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
    														Mesa mesaAListarVotantes = buscarMesaEnSede(numMesa, sede);
    														listarVotantesMesa(mesaAListarVotantes);
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
    													sede = buscarSede(idSede);
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
    														Mesa mesaBuscarVotante = buscarMesaEnSede(numMesa, sede);
    														System.out.println("Ingrese el RUT del votante que desea buscar en la mesa");
    														String rut = null;
    														try {
    															rut = lector.readLine();
    														} catch (IOException e) {
    															System.out.println("Error al leer el rut, ingrese un String");
    															continue;
    														}
    														if (buscarVotanteEnMesa(rut, mesaBuscarVotante) == null) {
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
    													sede = buscarSede(idSede);
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
    														Mesa mesaQuitarVotante = buscarMesaEnSede(numMesa, sede);
    														System.out.println("Ingrese el RUT del votante que desea eliminar");
    														String rut = null;
    														try {
    															rut = lector.readLine();
    														} catch (IOException e) {
    															System.out.println("Error al leer el rut, ingrese un String");
    															continue;
    														}
    														eliminarVotanteDeMesa(rut, mesaQuitarVotante);
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
    													sede = buscarSede(idSede);
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
    														Mesa mesaModificarVotante = buscarMesaEnSede(numMesa, sede);
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
    																modificarNombreVotante(rut, mesaModificarVotante, nuevo);
    																break;
    															case 2:
    																Votante votanteModificarNombre = buscarVotanteEnMesa(rut, mesaModificarVotante);
    																if (votanteModificarNombre == null) {
    																	System.out.println("El votante no se encuentra en la mesa solicitada");
    																} else {
    																	modificarNombreVotante(votanteModificarNombre, nuevo);
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
	
	public void agregarVotanteAMesa(Votante votante, Mesa mesa) {
		if (votante == null || mesa == null)	{
			System.out.println("La mesa y/o el votante ingresados no existen");
			return;
		} else {
			try {
				mesa.agregarVotante(votante);
				System.out.println("El votante ha sido agregado con exito");
			} catch (ExcedeCapacidadException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void agregarMesaASede(Mesa mesa, Sede sede) {
		if (sede == null) {
			System.out.println("La sede no existe");
			return;
		} else {
			try {
				sede.agregarMesa(mesa);
			    System.out.println("La mesa ha sido agregada con exito");
			} catch (ExcedeCapacidadException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void listarVotantesMesa(Mesa mesa) {
		if (mesa == null) {
			System.out.println("Esta mesa no existe");
		} else {
			ArrayList<Votante> listaVotantes = mesa.getListaVotantes();
			if (listaVotantes == null) {
				System.out.println("Esta mesa no tiene votantes");
			} else {
				for (Votante votante : listaVotantes) {
					System.out.println(votante.obtenerDatos());
				}
			}
		}
	}
	
	public void listarMesasSede(Sede sede) {
		if (sede == null) {
			System.out.println("La sede no existe");
		} else {
			HashMap<Integer, Mesa> mapaMesas = sede.getMapaMesas();
			if (mapaMesas == null) {
				System.out.println("La sede no cuenta con mesas disponibles");
			} else {
				for (Mesa mesa : mapaMesas.values()) {
					System.out.println("Numero de mesa: " + mesa.getNumeroMesa() + " Capacidad maxima: " + mesa.getCapMax());
				}
			}
		}
	}
	
	public Votante buscarVotanteEnMesa(String rut, Mesa mesa) {
		
		if (mesa == null) {
			System.out.println("La mesa ingresada no existe");
			return null;
		} else {
			try {
				Votante v = mesa.buscarVotante(rut);
				System.out.println("Votante hallado con exito");
				return v;
			} catch (BusquedaFallidaException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
	}
	
	public Mesa buscarMesaEnSede(int numeroMesa, Sede sede) {
		if (sede == null) {
			System.out.println("La sede ingresada no existe");
			return null;
		} else {
			try { 
				Mesa encontrada = sede.buscarMesa(numeroMesa);
				System.out.println("La mesa ha sido encontrada con exito");
				return encontrada;
			} catch (BusquedaFallidaException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
	}
	
	public Votante eliminarVotanteDeMesa(String rut, Mesa mesa) {
		
		if (mesa == null) {
			System.out.println("La mesa ingresada no existe");
			return null;
		} else {
			try {
				Votante eliminado = mesa.eliminarVotante(rut);
				System.out.println("Votante eliminado con exito");
				return eliminado;
			} catch (BusquedaFallidaException e) {
				
				System.out.println(e.getMessage());
				return null;
			}
		}
	}
	
	public Mesa eliminarMesaDeSede(int numeroMesa, Sede sede) {
		if (sede == null) {
			System.out.println("La sede ingresada no existe");
			return null;
		} else {
			try {
				Mesa eliminada = sede.retirarMesa(numeroMesa);
				System.out.println("Mesa eliminada con exito");
				return eliminada;
			} catch (BusquedaFallidaException e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
	}
	
	public void modificarNombreVotante(String rut, Mesa mesa, String nuevoNombre) {
		if (mesa == null || mesa.getListaVotantes() == null || rut == null || nuevoNombre == null) {
			System.out.println("La mesa ingresada no existe o bien no tiene votantes asignados, el rut ingresado no es valido, o el nuevo nombre tampoco");
		} else {
			Votante votanteAModificarNombre = buscarVotanteEnMesa(rut, mesa);
			if (votanteAModificarNombre == null) {
				System.out.println("El rut ingresado no corresponde a un votante asignado a esta mesa");
				return;
			} else {
				votanteAModificarNombre.setNombre(nuevoNombre);
				System.out.println("Nombre actualizado con exito");
				return;
			}
		}
	}
	
	public void modificarNombreVotante(Votante votante, String nuevoNombre) {
		if (votante == null || nuevoNombre == null) {
			System.out.println("El votante no existe o bien el nuevo nombre no es valido");
			return;
		} else {
			votante.setNombre(nuevoNombre);
			System.out.println("Nombre actualizado con exito");
			return;
		}
	}
	
	public void modificarResidenciaVotante(String rut, Mesa mesa, Coordenadas nuevoDomicilio) {
		if (mesa == null || mesa.getListaVotantes() == null || rut == null || nuevoDomicilio == null) {
			System.out.println("La mesa no existe o no cuenta con votantes asignados, el rut ingresado no es valido, o el domicilio ingresado no existe");
			return;
		} else {
			Votante votanteAModificarDomicilio = buscarVotanteEnMesa(rut, mesa);
			if (votanteAModificarDomicilio == null) {
				System.out.println("El rut ingresado no corresponde a un votante asignado a esta mesa");
				return;
			} else {
				votanteAModificarDomicilio.setResidencia(nuevoDomicilio);
				return;
			}
		}
	}
	
	public void modificarResidenciaVotante(String rut, Mesa mesa, double xNuevoDomicilio, double yNuevoDomicilio) {
		if (mesa == null || mesa.getListaVotantes() == null || rut == null) {
			System.out.println("La mesa no existe o no cuenta con votantes asignados, o bien el rut ingresado no es valido");
			return;
		} else {
			Votante votanteAModificarDomicilio = buscarVotanteEnMesa(rut, mesa);
			if (votanteAModificarDomicilio == null) {
				System.out.println("El rut ingresado no corresponde a un votante asignado a esta mesa");
				return;
			} else {
				votanteAModificarDomicilio.setResidencia(xNuevoDomicilio, yNuevoDomicilio);
				return;
			}
		}
	}
	
	public void modificarResidenciaVotante(Votante votante, Coordenadas nuevoDomicilio) {
		if (votante == null || nuevoDomicilio == null) {
			System.out.println("El votante o el nuevo domicilio ingresados no existen");
			return;
		} else {
			votante.setResidencia(nuevoDomicilio);
			return;
		}
	}
	
	public void modificarResidenciaVotante(Votante votante, double xNuevoDomicilio, double yNuevoDomicilio) {
		if (votante == null) {
			System.out.println("El votante ingresado no existe");
			return;
		} else {
			votante.setResidencia(xNuevoDomicilio, yNuevoDomicilio);
			return;
		}
	}
	
	public void modificarCapMaxMesa(int numeroMesa, Sede sede, int nuevaCapMax) {
		
		if (sede == null) {
			System.out.println("La sede ingresada no existe");
			return;
		} else {
			
			Mesa mesaAModificarCapMax = buscarMesaEnSede(numeroMesa, sede);
			if (mesaAModificarCapMax == null) {
				return;
			}
			if (mesaAModificarCapMax.getListaVotantes() == null) {
				mesaAModificarCapMax.setListaVotantes(new ArrayList<Votante>());
			}
				
			int aux = mesaAModificarCapMax.getCapMax();
			if (!mesaAModificarCapMax.setCapMax(nuevaCapMax)) {
				return;	
			} else {
				try {
					sede.agregarMesa(mesaAModificarCapMax);
					System.out.println("La mesa ha sido agregada con exito");
					return;
				} catch (ExcedeCapacidadException e) {
					mesaAModificarCapMax.setCapMax(aux);
					System.out.println(e.getMessage());
					return;
				}
			}
		}
	}
	public Sede buscarSede(int id) {
		if (sedes == null) {
			return null;
		} else {
			for (Sede sede : sedes) {
				if (sede != null && sede.getId() == id) {
					return sede;
				}
			}
			return null;
		}
	}
}	
