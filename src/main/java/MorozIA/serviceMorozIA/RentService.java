package MorozIA.serviceMorozIA;

import MorozIA.modeMorozIA.Customer;
import MorozIA.modeMorozIA.Rent;
import MorozIA.repositoryMorozIA.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;

    @Autowired
    public RentService(RentRepository rentRepository){
        this.rentRepository = rentRepository;
    }
    public Rent saveRent(Rent rent){
        return rentRepository.save(rent);
    }

    public List<Rent> findAllByCustomerId(Customer id){
        return rentRepository.findRentByCustomerId(id);
    }

    public void deleteById(Integer id){
        rentRepository.deleteById(id);
    }

    public List<Integer> findTopFiveNumbers(){
        return rentRepository.findTopFiveNumbers(PageRequest.of(0,5));
    }

    public List<Rent> findTopFive(){

        return rentRepository.findTopFive(PageRequest.of(0,5));
    }

}
