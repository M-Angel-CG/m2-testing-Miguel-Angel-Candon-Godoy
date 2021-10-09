package com.project.m2testingmiguelangelcandon.repositories;

import com.project.m2testingmiguelangelcandon.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { }
