package MorozIA.repositoryMorozIA;

import MorozIA.modeMorozIA.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    List<Admin> findByNameAndAccess(String name, String access);
}
