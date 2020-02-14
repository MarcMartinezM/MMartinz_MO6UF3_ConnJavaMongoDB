package Conn_Java_MongoDB;

import java.net.UnknownHostException;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Test {

	static void main(String[] args) throws UnknownHostException {
		System.out.println("Prueba conexión MongoDB");
		MongoClient mongo = crearConexion();

		if (mongo != null) {
			menu(mongo);
		} else {
			System.out.println("Error: Conexión no establecida");
		}
		disconnectMongo(mongo);
	}

	private static void menu(MongoClient mongo) throws UnknownHostException {

		boolean salir = false;
		int opcion; // Guardaremos la opcion del usuario
		System.out.println("Lista de bases de datos: ");
		//printDatabases(mongo);
		while (!salir) {

			System.out.println("1.alta de datos:");
			System.out.println("2.modificar datos:");
			System.out.println("3.Eliminacion datos:");
			System.out.println("4. buscar datos");
			System.out.println("5. Salir");

			try {

				System.out.println("Escribe una de las opciones");
				opcion = leerI();

				switch (opcion) {
				case 1:
					altaDeDatos(mongo);
					break;
				case 2:
					modificaDeDatos(mongo);

					break;
				case 3:
					deleteDeDatos(mongo);
					break;
				case 4:
					mostrarDatos(mongo);
					break;
				case 5:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 5");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				leerI();
			}
		}
	}

	private static MongoClient crearConexion() throws UnknownHostException {
		MongoClientURI mongoURI = new MongoClientURI(
				"mongodb+srv://MarcMartinez:superlocal@cluster0-idx6q.mongodb.net/test?retryWrites=true&w=majority");
		MongoClient conClient = new MongoClient(mongoURI);
		return conClient;
	}

	/*
	 * private static void printDatabases(MongoClient mongo) { MongoCursor<String>
	 * dbs = mongo.listDatabaseNames().iterator(); while (dbs.hasNext()) {
	 * System.out.println(dbs.next()); } }
	 */

	private static void altaDeDatos(MongoClient mongo) {
		MongoDatabase db = mongo.getDatabase("Entretenimiento");
		MongoCollection<Document> collection;

		boolean salir = false;
		while (!salir) {
			System.out.println("en que tabla quiere introducir:");
			System.out.println("1.Consolas");
			System.out.println("2.Juegos");
			System.out.println("3.salir");
			int opcion = leerI();
			try {
				switch (opcion) {
				case 1:
					collection = db.getCollection("Consolas");
					System.out.println("introduce nombre consola:");
					String nombre = leerS();
					System.out.println("introduce precio consola:");
					int precio = leerI();
					Document documentConsola1 = new Document("nombre", nombre).append("precio", precio);
					collection.insertOne(documentConsola1);
					System.out.println("consola introducia.");
					break;
				case 2:
					collection = db.getCollection("Juegos");

					System.out.println("introduce nombre juego:");
					String nombreJ = leerS();
					System.out.println("introduce precio juego");
					float precioJ = leerF();
					System.out.println("introduce plataforma del juego:");
					String plataforma = leerS();
					Document documentJuego1 = new Document("nombre", nombreJ).append("precio", precioJ)
							.append("plataforma", plataforma);
					collection.insertOne(documentJuego1);
					System.out.println("juego introducido.");
					break;
				case 3:
					salir = true;
					break;
				default:
					System.out.println("solo numeros entre 1 y 3.");
					break;
				}
			} catch (Exception e) {
				System.out.println("Debes insertar un número");
				leerI();
			}
		}

	}

	private static void modificaDeDatos(MongoClient mongo) {
		MongoDatabase db = mongo.getDatabase("Entretenimiento");
		MongoCollection<Document> collection;

		boolean salir = false;
		while (!salir) {
			System.out.println("en que tabla quiere modificar:");
			System.out.println("1.Consolas");
			System.out.println("2.Juegos");
			System.out.println("3.salir");
			int opcion = leerI();

			try {
				boolean salir2 = false;
				switch (opcion) {

				case 1:
					collection = db.getCollection("Consolas");
					while (!salir2) {
						System.out.println("que campo quires modificar de consola:");
						System.out.println("1.nombre de consola.");
						System.out.println("2.precio de consola.");
						System.out.println("3.salir");
						int opcion2 = leerI();
						switch (opcion2) {
						case 1:
							System.out.println("introduce nombre actual:");
							String nombreC = leerS();
							Document find = new Document("nombre", nombreC);
							System.out.println("introduce nombre nuevo:");
							String nombreCNuevo = leerS();
							Document update = new Document("$set", new Document("nombre", nombreCNuevo));
							collection.findOneAndUpdate(find, update);
							System.out.println("modificacion completada.");
							break;
						case 2:
							System.out.println("introduce precio actual:");
							float precioC = leerF();
							find = new Document("precio", precioC);
							System.out.println("introduce precio nuevo:");
							float precioCNuevo = leerF();
							update = new Document("$set", new Document("precio", precioCNuevo));
							collection.findOneAndUpdate(find, update);
							System.out.println("modificacion completada.");
							break;
						case 3:
							salir2 = true;
							break;
						default:
							System.out.println("solo numeros 1 y 3");
							break;
						}
					}
					break;
				case 2:
					collection = db.getCollection("Juegos");
					while (!salir2) {
						System.out.println("que campo quires modificar de juegos:");
						System.out.println("1.nombre de juego.");
						System.out.println("2.precio de juego.");
						System.out.println("3.plataforma de juego.");
						System.out.println("4.salir");
						int opcion2 = leerI();
						switch (opcion2) {
						case 1:
							System.out.println("introduce nombre actual:");
							String nombreJ = leerS();
							Document find = new Document("nombre", nombreJ);
							System.out.println("introduce nombre nuevo:");
							String nombreJNuevo = leerS();
							Document update = new Document("$set", new Document("nombre", nombreJNuevo));
							collection.findOneAndUpdate(find, update);
							System.out.println("modificacion completada.");
							break;
						case 2:
							System.out.println("introduce precio actual:");
							float precioJ = leerF();
							find = new Document("precio", precioJ);
							System.out.println("introduce precio nuevo:");
							float precioJNuevo = leerF();
							update = new Document("$set", new Document("precio", precioJNuevo));
							collection.findOneAndUpdate(find, update);
							System.out.println("modificacion completada.");
							break;
						case 3:
							System.out.println("introduce plataforma actual:");
							String plataformaJ = leerS();
							find = new Document("plataforma", plataformaJ);
							System.out.println("introduce plataforma nueva:");
							String plataformaJNuevo = leerS();
							update = new Document("$set", new Document("plataforma", plataformaJNuevo));
							collection.findOneAndUpdate(find, update);
							System.out.println("modificacion completada.");
							break;
						case 4:
							salir2 = true;
							break;
						default:
							System.out.println("solo numeros 1 y 3");
							break;
						}
					}
					break;
				case 3:
					salir = true;
					break;
				default:
					System.out.println("solo numeros entre 1 y 3.");
					break;
				}
			} catch (Exception e) {
				System.out.println("Debes insertar un número");
				leerI();
			}
		}

	}

	private static void deleteDeDatos(MongoClient mongo) {
		MongoDatabase db = mongo.getDatabase("Entretenimiento");
		MongoCollection<Document> collection;

		boolean salir = false;

		int opcion; // Guardaremos la opcion del usuario

		while (!salir) {

			System.out.println("1. eliminar una consola:");
			System.out.println("2. eliminar un juego:");
			System.out.println("3. Salir");

			try {

				System.out.println("Escribe una de las opciones");
				opcion = leerI();

				switch (opcion) {

				case 1:
					collection = db.getCollection("Consolas");
					System.out.println("introduce nombre de consola a eliminar:");
					String nombreC = leerS();
					Document find = new Document("nombre", nombreC);

					collection.findOneAndDelete(find);
					System.out.println("consola eliminada.");
					break;

				case 2:
					collection = db.getCollection("Juegos");
					System.out.println("introduce nombre de juego a eliminar:");
					String nombreJ = leerS();
					find = new Document("nombre", nombreJ);

					collection.findOneAndDelete(find);
					System.out.println("juego eliminado.");
					break;

				case 3:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 3");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				leerI();
			}
		}

	}

	private static void mostrarDatos(MongoClient mongo) {
		MongoDatabase db = mongo.getDatabase("Entretenimiento");
		MongoCollection<Document> collection;

		boolean salir = false;
		int opcion; // Guardaremos la opcion del usuario

		while (!salir) {

			System.out.println("1. Mostrar todo tabla consolas.");
			System.out.println("2. Mostrar todo tabla juegos.");
			System.out.println("3. buscar documento concreto.");
			System.out.println("4. Salir");

			try {

				System.out.println("Escribe una de las opciones");
				opcion = leerI();

				switch (opcion) {
				case 1:
					collection = db.getCollection("Consolas");
					FindIterable<Document> doc = collection.find();
					for (Document document : doc) {
						System.out.println(((Document) doc).toJson());
					}
					break;
				case 2:
					collection = db.getCollection("Juegos");
					FindIterable<Document> doc2 = collection.find();
					for (Document document : doc2) {
						System.out.println(((Document) doc2).toJson());
					}
					break;
				case 3:
					boolean salir2 = false;
					int opcion2;
					while (!salir2) {
						try {
							System.out.println("1.buscar en Consola.");
							System.out.println("2.buscar en juegos.");
							System.out.println("3.salir.");
							opcion2 = leerI();
							switch (opcion2) {
							case 1:
								collection = db.getCollection("Consolas");
								System.out.println("introduce en que quieres buscar (nombre o precio).");
								System.out.println(
										"si exite un document con los mismos parametros de busqueda tambien se mostrara.");
								String version = leerS();
								Document findDocument;
								if (version.equalsIgnoreCase("nombre")) {
									System.out.println("introduce nombre consola:");
									String nombreCBuscar = leerS();
									findDocument = new Document("nombre", nombreCBuscar);
									FindIterable<Document> resultado = (FindIterable<Document>) collection
											.find(findDocument).iterator();
									for (Document document : resultado) {
										System.out.println(((Document) resultado).toJson());
									}
								} else if (version.equalsIgnoreCase("precio")) {
									System.out.println("introduce precio consola:");
									String precioCBuscar = leerS();
									findDocument = new Document("precio", precioCBuscar);
									FindIterable<Document> resultado2 = (FindIterable<Document>) collection
											.find(findDocument).iterator();
									for (Document document : resultado2) {
										System.out.println(((Document) resultado2).toJson());
									}
								} else {
									System.out.println("no has introducido ninguna opcion correcta.");
								}

								break;
							case 2:
								collection = db.getCollection("Juegos");
								System.out.println("introduce en que quieres buscar (nombre , precio o plataforma).");
								System.out.println(
										"si exite un document con los mismos parametros de busqueda tambien se mostrara.");
								String version2 = leerS();
								Document findDocument2;
								if (version2.equalsIgnoreCase("nombre")) {
									System.out.println("introduce nombre juego:");
									String nombreJBuscar = leerS();
									findDocument2 = new Document("nombre", nombreJBuscar);
									FindIterable<Document> resultado = (FindIterable<Document>) collection
											.find(findDocument2).iterator();
									for (Document document : resultado) {
										System.out.println(((Document) resultado).toJson());
									}
								} else if (version2.equalsIgnoreCase("precio")) {
									System.out.println("introduce precio juego:");
									String precioJBuscar = leerS();
									findDocument2 = new Document("precio", precioJBuscar);
									FindIterable<Document> resultado2 = (FindIterable<Document>) collection
											.find(findDocument2).iterator();
									for (Document document : resultado2) {
										System.out.println(((Document) resultado2).toJson());
									}
								} else if(version2.equalsIgnoreCase("plataforma")){
									System.out.println("introduce plataforma juego:");
									String plataformaJbuscar = leerS();
									findDocument2 = new Document("plataforma",plataformaJbuscar);
									FindIterable<Document> resultado3 = (FindIterable<Document>) collection.find(findDocument2).iterator();
									for (Document document : resultado3) {
										System.out.println(((Document) resultado3).toJson());
										
									}
									
								}else {
								
									System.out.println("no has introducido ninguna opcion correcta.");
								}
								break;
							case 3:
								salir2 = true;
								break;
							default:
								System.out.println("Solo números entre 1 y 3");
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Debes insertar un número");
							leerI();
						}
					}
					break;
				case 4:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 4");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				leerI();
			}
		}

	}

	private static void disconnectMongo(MongoClient mongo) {
		mongo.close();

	}

	public static int leerI() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	public static float leerF() {
		Scanner sc = new Scanner(System.in);
		return sc.nextFloat();
	}

	public static String leerS() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	// ultima parte??

}