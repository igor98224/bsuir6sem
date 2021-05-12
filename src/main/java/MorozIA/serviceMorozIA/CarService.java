package MorozIA.serviceMorozIA;

import MorozIA.modeMorozIA.Car;
import MorozIA.repositoryMorozIA.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> findAllNotBusy(){
        return carRepository.findAllNotBusy("free");
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public List<Car> findAllNotBusySorted(String order){
        if(order.equals("asc")) return carRepository.findAllNotBusyAsc("free");
        return carRepository.findAllNotBusyDesc("free");
    }

    public List<Car> findNotBusyMatch(String name){
        return carRepository.findNotBusyMatch(name);
    }

    public List<Car> findAllNotBusyFiltered(String min, String max){

        return carRepository.findAllNotBusyFiltered(min, max);
    }

    public Car findById(Integer id){
        return carRepository.getOne(id);
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public void deleteById(Integer id){
        carRepository.deleteById(id);
    }

    public Car update(Integer id) {
        return carRepository.update(id);
    }

    public List<Car> findAllBusy(){
        return carRepository.findAllNotBusy("busy");
    }
}
