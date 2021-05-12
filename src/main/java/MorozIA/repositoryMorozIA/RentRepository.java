package MorozIA.repositoryMorozIA;

import MorozIA.modeMorozIA.Customer;
import MorozIA.modeMorozIA.Rent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
    @Query("SELECT c FROM Rent c WHERE c.id_customer=?1")
    List<Rent> findRentByCustomerId(Customer id);

    @Query("SELECT c from Rent c GROUP BY c.id_car_rented  order by count(c.id_car_rented) desc ")
    List<Rent> findTopFive(Pageable pageable);

    @Query("SELECT count(c.id_car_rented) FROM Rent c GROUP BY c.id_car_rented order by count(c.id_car_rented) desc")
    List<Integer> findTopFiveNumbers(Pageable pageable);
}
