package com.project.m2testingmiguelangelcandon;

import com.project.m2testingmiguelangelcandon.entities.Usuario;
import com.project.m2testingmiguelangelcandon.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class M2TestingMiguelAngelCandonApplication implements CommandLineRunner {

	@Autowired
	UsuarioRepository usuarioRepository;

	int opcion;

	public static void main(String[] args) {
		SpringApplication.run(M2TestingMiguelAngelCandonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
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

			//TODO: borrar uno y todos los usuarios
			if (opcion == 6) {
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 7) {
				scanner.nextLine();
				scanner.nextLine();
			}
			if (opcion == 0) {
				break;
			}
		}
	}

	public List<Usuario> listaUsuarios() {
		return usuarioRepository.findAll();
	}

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

	public Optional<Usuario> existeUsuario(int id) {
		return usuarioRepository.findById((long)id);
	}

	public void buscar(int id) {
		Optional<Usuario> usuario = existeUsuario(id);
		if(usuario.isPresent()) {
			System.out.println(usuario.get());
		} else {
			System.out.println("No existe el usuario");
		}
	}

	public void modificar(int id) {
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
}
