package MorozIA.repositoryMorozIA;

import MorozIA.modeMorozIA.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT c FROM Car c where c.isBusy=?1")
    List<Car> findAllNotBusy(String isBusy);

    @Query("SELECT c FROM Car c where c.name like %?1%")
    List<Car> findNotBusyMatch(String name);

    @Query("SELECT c FROM Car c where c.isBusy=?1 order by c.price asc")
    List<Car> findAllNotBusyAsc(String isBusy);

    @Query("SELECT c FROM Car c where c.isBusy=?1 order by c.price desc")
    List<Car> findAllNotBusyDesc(String isBusy);

    @Query("SELECT c FROM Car c WHERE c.price BETWEEN ?1 AND ?2")
    List<Car> findAllNotBusyFiltered(String min, String max);

    @Query("UPDATE Car c SET c.isBusy='busy' WHERE c.id=?1")
    Car update(Integer id);
}
