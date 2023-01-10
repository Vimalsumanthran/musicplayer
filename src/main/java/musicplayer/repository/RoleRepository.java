package musicplayer.repository;

import musicplayer.entity.Role;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Set<Role> findByName(String name);

	Optional<Role> findById(Long id);

}