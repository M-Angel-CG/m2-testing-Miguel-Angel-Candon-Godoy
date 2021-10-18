package com.project.m2testingmiguelangelcandon;

import com.project.m2testingmiguelangelcandon.entities.Usuario;
import com.project.m2testingmiguelangelcandon.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class M2TestingMiguelAngelCandonApplication implements CommandLineRunner {

	/**
	 * Inicialización de la base de datos
	 */
	@Autowired
	UsuarioRepository usuarioRepository;


	public static void main(String[] args) {
		SpringApplication.run(M2TestingMiguelAngelCandonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcion = -10;
		while(true) {
			System.out.println("""
Elige una opción:
1- Inicializar base de datos
2- Crear usuario
3- Listar usuarios
4- Buscar usuario
5- Modificar usuario
6- Borrar usuario
7- Borrar base de datos
8- Nº de usuarios
9- Comprobar si existe usuario por id
0- Salir
					""");

			try {
				opcion = scanner.nextInt();
			} catch (InputMismatchException e) {
				//e.printStackTrace();
				System.out.println("El input no es válido");
			}

			if(opcion == 1) {
				initRepo();
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 2) {
				crear();
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 3) {
				listar();
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 4) {
				try {
					System.out.println("Escribe el codigo del usuario:");
					int id = scanner.nextInt();
					buscar(id);
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("El input no es válido");
				}
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 5) {
				try {
					System.out.println("Escribe el id:");
					int id = scanner.nextInt();
					modificar(id);
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("El input no es válido");
				}
				scanner.nextLine();
				scanner.nextLine();
			}

			if (opcion == 6) {
				try {
					System.out.println("Escribe el id:");
					int id = scanner.nextInt();
					borrar(id);
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("El input no es válido");
				}
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 7) {
				try {
					System.out.println("¿Está seguro? (y/n)");
					String conf = scanner.next();
					if (conf.equalsIgnoreCase("Y")) {
						borrarTodo();
					}
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("El input no es válido");
				}
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 8) {
				contarUsuarios();
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 9) {
				try {
					System.out.println("Escribe el id:");
					int id = scanner.nextInt();
					if(existeUsuario(id).isPresent()) {
						System.out.println("El usuario con id " +
								id + " existe.");
					} else {
						System.out.println("No existe el usuario con id " + id + ".");
					}
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("El input no es válido");
				}
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 0) {
				break;
			}
		}
	}

	/**
	 * Función que imprime por pantalla el número de usuarios en la base de datos
	 */
	public void contarUsuarios() {
		System.out.println("Nº de usuarios: " + usuarioRepository.count());
	}

	/**
	 * Función que devuelve todos los usuarios de la base de datos
	 * @return Lista de todos los usuarios
	 */
	public List<Usuario> listaUsuarios() {
		return usuarioRepository.findAll();
	}

	/**
	 * Lista todos los usuarios dados
	 */
	public void listar() {
		List<Usuario> usuarios = listaUsuarios();
		if(!usuarios.isEmpty()) {
			for (Usuario usuario : usuarios) {
				System.out.println(usuario.toString());
			}
		} else {
			System.out.println("No hay usuarios en la base de datos");
		}
	}

	/**
	 * Añade a la base de datos nuevos usuarios
	 */
	public void initRepo() {
		if(listaUsuarios().isEmpty()) {
			usuarioRepository.save(new Usuario(
					null, "Pedro Pérez García",
					"pperez", "aere5rfugiyh8",
					"pedroperez@hotmail.com",null,
					"pedro_perez84",37,959888818
			));

			usuarioRepository.save(new Usuario(
					null, "José Fernández García",
					"josefer", "herhw56tesyrt",
					"josefer62@hotmail.com","joseFG",
					"jose_fer_gar",59,672401452
			));
			System.out.println("Base de datos inicializada correctamente");
		} else {
			System.out.println("Ya hay datos en la base de datos");
		}
	}

	/**
	 * Comprueba si existe un usuario por su id
	 * @param id codigo que identifica a cada usuario
	 * @return List<Usuario>
	 */
	public Optional<Usuario> existeUsuario(long id) {
		return usuarioRepository.findById(id);
	}

	/**
	 * Imprime el usuario con esa id por consola
	 * @param id codigo que identifica a cada usuario
	 */
	public void buscar(long id) {
		Optional<Usuario> usuario = existeUsuario(id);
		if(usuario.isPresent()) {
			System.out.println(usuario.get());
		} else {
			System.out.println("No existe el usuario");
		}
	}

	/**
	 * Modifica al usuario con una id determinada dejando elegir los parámetros a modificar
	 * @param id codigo que identifica a cada usuario
	 */
	public void modificar(long id) {
		Optional<Usuario> optUsuario = existeUsuario(id);
		if(optUsuario.isPresent()) {
			Usuario usuario = optUsuario.get();

			String name = usuario.getName();
			String username = usuario.getUsername();
			String password = usuario.getPassword();
			String email = usuario.getEmail();
			String github = usuario.getGithub();
			String twitter = usuario.getTwitter();
			int age = usuario.getAge();
			int phone = usuario.getPhone();

			Scanner scanner = new Scanner(System.in);
			//(System.in,"UTF-8") sigue sin permitir tildes

			int confirmar = 0;
			while(true) {
				System.out.println("""
Elige un parámetro para modificar: (sin tildes)
1- Nombre
2- Usuario
3- Contraseña
4- Mail
5- Github
6- Twitter
7- Edad
8- Teléfono
9- Confirmar
0- Salir
						""");
				int eleccion = -1;

				try {
					eleccion = scanner.nextInt();
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("El input no es válido");
				}

				if(eleccion == 0) {
					System.out.println("Saliendo");
					scanner.nextLine();
					scanner.nextLine();
					break;
				}

				if(eleccion == 1) {
					System.out.println("Escribe un nombre nuevo");
					name = scanner.next();
				}

				if(eleccion == 2) {
					System.out.println("Escribe un nuevo nombre de usuario");
					username = scanner.next();
				}

				if(eleccion == 3) {
					System.out.println("Escribe la nueva contraseña");
					password = scanner.next();
				}

				if(eleccion == 4) {
					System.out.println("Escribe un mail");
					email = scanner.next();
				}

				if(eleccion == 5) {
					System.out.println("Escribe una cuenta de github");
					github = scanner.next();
				}

				if(eleccion == 6) {
					System.out.println("Escribe una cuenta de twitter");
					twitter = scanner.next();
				}

				if(eleccion == 7) {
					System.out.println("Escribe una edad");
					age = scanner.nextInt();
				}

				if(eleccion == 8) {
					System.out.println("Escribe una cuenta de twitter");
					phone = scanner.nextInt();
				}

				if(eleccion == 9) {
					confirmar = 1;
					break;
				}
			}

			if(confirmar == 1) {
				usuario.setName(name);
				usuario.setUsername(username);
				usuario.setPassword(password);
				usuario.setEmail(email);
				usuario.setGithub(github);
				usuario.setTwitter(twitter);
				usuario.setAge(age);
				usuario.setPhone(phone);

				usuarioRepository.save(usuario);

				System.out.println("Usuario modificado");
			}

		} else {
			System.out.println("El usuario no existe");
		}
	}

	/**
	 * Crea un nuevo usuario
	 */
	public void crear() {
		System.out.println("Introduce tus datos: ");
		Scanner scanner = new Scanner(System.in);

		System.out.println("Nombre:");
		String name = scanner.nextLine();


		System.out.println("Nombre de usuario:");
		String username = scanner.nextLine();


		System.out.println("Contraseña");
		String password = scanner.next();


		System.out.println("Correo electrónico:");
		String email = scanner.next();


		System.out.println("Github:");
		String github = scanner.next();


		System.out.println("Twitter:");
		String twitter = scanner.next();


		System.out.println("Edad:");
		int age = scanner.nextInt();


		System.out.println("Teléfono:");
		int phone = scanner.nextInt();



		usuarioRepository.save(new Usuario(
				null,name,username,
				password,email,github,
				twitter,age,phone
		));
		System.out.println("Usuario creado");
	}

	/**
	 * Borra al usuario de la id correspondiente
	 * @param id codigo que identifica a cada usuario
	 */
	public void borrar(long id) {
		usuarioRepository.deleteById(id);
		System.out.println("Usuario borrado con éxito");
	}

	/**
	 * Borra todos los usuarios de la base de datos
	 */
	public void borrarTodo() {
		usuarioRepository.deleteAllInBatch();
		System.out.println("Base de datos borrada con éxito");
	}
}
