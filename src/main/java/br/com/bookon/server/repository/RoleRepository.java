package br.com.bookon.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bookon.server.enumerations.RoleEnum;
import br.com.bookon.server.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleEnum name);

    int countByName(RoleEnum name);
}
