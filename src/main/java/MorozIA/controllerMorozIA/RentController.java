package MorozIA.controllerMorozIA;

import MorozIA.modeMorozIA.Car;
import MorozIA.modeMorozIA.Customer;
import MorozIA.modeMorozIA.Rent;
import MorozIA.otherMorozIA.StaticMorozIA;
import MorozIA.serviceMorozIA.CarService;
import MorozIA.serviceMorozIA.CustomerService;
import MorozIA.serviceMorozIA.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RentController {
    private final RentService rentService;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public RentController(RentService rentService, CarService carService, CustomerService customerService) {
        this.rentService = rentService;
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping("/current-car/{id}")
    public String getCurrentCar(@PathVariable("id") Integer id, Model model){
        Car car = carService.findById(id);
        model.addAttribute("car", car);


        return "/customer/rent-a-car";
    }

    @GetMapping("cancel-rent/{id}")
    public String deleteCity(@PathVariable("id") Integer id){
        rentService.deleteById(id);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String getOrders(){
        return "/customer/my-orders";
    }

    @PostMapping("/my-orders")
    public String getMyOrders(@RequestParam("login")String customer, Model model){
        List<Customer> customer1 = customerService.findByEmail2(customer);

        List<Rent> rents = rentService.findAllByCustomerId(customer1.get(0));
        model.addAttribute("rents", rents);
        return "/customer/my-orders";
    }

    @GetMapping("/rent")
    public String rent(Model model){
        model.addAttribute("message", "Сначала выберите автомобиль!");
        List<Car> cars = carService.findAllNotBusy();
        model.addAttribute("cars", cars);
        return "/customer/our-cars";
    }

    @PostMapping("/rent-car")
    public String addRent(@Param("login")String login,@Param("start")String start,@Param("end")String end,Car car, Model model) throws ParseException {
        if(start.compareTo(end)>=0){
            return "redirect:/cars";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(start);
        Date date2 = dateFormat.parse(end);

        List<Customer> customerList = customerService.findByEmail2(login);
        if(!customerList.get(0).getRating().equals("5")){
            int r = Integer.parseInt(customerList.get(0).getRating()) + 1;
            customerList.get(0).setRating(r+"");
            customerService.saveCustomer( customerList.get(0));
        }

        long milliseconds = date2.getTime() - date1.getTime();

        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));

        StaticMorozIA.Amount = days*Float.parseFloat(car.getPrice());
        StaticMorozIA.Days = days;
        StaticMorozIA.Name = car.getName();
        StaticMorozIA.Model = car.getModel();
        StaticMorozIA.Start = start;
        StaticMorozIA.End = end;


        Rent rent = new Rent();

        rent.setId_customer(customerList.get(0));
        rent.setId_car_rented(car);
        rent.setEndrent_date(end);
        rent.setStartrent_date(start);

        car.setIsBusy("busy");
        carService.saveCar(car);

        rentService.saveRent(rent);
        return "redirect:/checkout";
    }
}
