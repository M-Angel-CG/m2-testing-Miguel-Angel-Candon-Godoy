package com.project.m2testingmiguelangelcandon;

import com.project.m2testingmiguelangelcandon.entities.Usuario;
import com.project.m2testingmiguelangelcandon.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
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
     				3- Modificar usuario
     				4- Borrar usuario
     				5- Borrar base de datos
     				0- Salir
					""");
			opcion = scanner.nextInt();

			if(opcion == 1) {
				List<Usuario> usuarios = usuarioRepository.findAll();

				if(usuarios.isEmpty()) {
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
				} else {
					System.out.println("Ya hay datos en la base de datos");
				}
			}
			if (opcion == 2) {
				break;
			}
			if (opcion == 3) {
				break;
			}
			if (opcion == 4) {
				break;
			}
			if (opcion == 5) {
				break;
			}
			if (opcion == 0) {
				break;
			}
		}
	}
}
