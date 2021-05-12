package MorozIA.controllerMorozIA;

import MorozIA.modeMorozIA.Car;
import MorozIA.serviceMorozIA.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String findAll(Model model){
        List<Car> cars = carService.findAllNotBusy();
        model.addAttribute("cars", cars);
        return "/customer/our-cars";
    }

    @PostMapping("/search")
    public String foundForCustomer(@Param("name")String name,Model model){
        List<Car> cars = carService.findNotBusyMatch(name);
        model.addAttribute("cars", cars);
        return "/customer/our-cars";
    }



    @GetMapping("/asc")
    public String findAllASC(Model model){
        List<Car> cars = carService.findAllNotBusySorted("asc");
        model.addAttribute("cars", cars);
        return "/customer/our-cars";
    }

    @GetMapping("/desc")
    public String findAllDESC(Model model){
        List<Car> cars = carService.findAllNotBusySorted("desc");
        model.addAttribute("cars", cars);
        return "/customer/our-cars";
    }

    @PostMapping("/filter")
    public String findAllFilterInput(@Param("min")String min, @Param("max")String max, Model model){
        List<Car> cars = carService.findAllNotBusyFiltered(min, max);
        model.addAttribute("cars", cars);
        return "/customer/our-cars";
    }

    @GetMapping("/rent-current-car/{id}")
    public String rentACarFrom(@PathVariable("id") Integer id, Model model){
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "/customer/rent-a-car";
    }

    @GetMapping("/cars-list")
    public String carList(Model model){
        List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        return "/admin/car-list";

    }

    @GetMapping("/add-car")
    public String createCityForm(Car car){
        return "/admin/create-car";
    }

    @GetMapping("car-delete/{id}")
    public String deleteCar(@PathVariable("id") Integer id){
        carService.deleteById(id);

        return "redirect:/cars-list";
    }

    @GetMapping("/update-car/{id}")
    public String updateCarForm(@PathVariable("id") Integer id, Model model){
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "/admin/update-car";
    }

    @PostMapping("/car-update")
    public String updateCar(Car car){
        carService.saveCar(car);
        return "redirect:/cars-list";
    }


    @PostMapping("/car-create")
    public String createCity(@RequestParam("isBusy")String busy, Car car){

        try {
            car.setIsBusy(busy);
            carService.saveCar(car);
        }catch (Exception e){
            System.out.println("error");
        }

        return "redirect:/cars-list";
    }

}
