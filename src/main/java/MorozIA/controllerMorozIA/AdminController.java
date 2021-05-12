package MorozIA.controllerMorozIA;

import MorozIA.modeMorozIA.Admin;
import MorozIA.modeMorozIA.Car;
import MorozIA.modeMorozIA.Rent;
import MorozIA.serviceMorozIA.AdminService;
import MorozIA.serviceMorozIA.CarService;
import MorozIA.serviceMorozIA.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final RentService rentService;
    private final CarService carService;


    @Autowired
    public AdminController(AdminService adminService, RentService rentService, CarService carService) {
        this.adminService = adminService;
        this.rentService = rentService;
        this.carService = carService;
    }

    @GetMapping("/admins")
    public String findAll(Model model){
        List<Admin> admins = adminService.findAll();
        model.addAttribute("admins", admins);
        return "/admin/admin-list";
    }

    @GetMapping("/admin-create")
    public String createAdminForm(Admin admin){
        return "/admin/admin-create";
    }

    @PostMapping("/admin-create")
    public String createAdmin(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    @GetMapping("/admin-profile")
    public String showAdminProfile(){
        createReports();
        return "/admin/admin-profile";
    }

    @GetMapping("admin-delete/{id}")
    public String deleteCity(@PathVariable("id") Integer id){
        adminService.deleteById(id);

        return "redirect:/admins";
    }

    @GetMapping("/admin-update/{id}")
    public String updateCityForm(@PathVariable("id") Integer id, Model model){
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin/admin-update";
    }

    @PostMapping("/admin-update")
    public String updateCity(Admin admin){
        adminService.saveAdmin(admin);
        return "redirect:/admins";
    }

    @GetMapping("/reports")
    public String reports(){

        return "/admin/report";
    }

    public void createReports(){
        try(FileWriter writer = new FileWriter("C:\\Users\\Moroz\\Downloads\\moroz\\moroz\\src\\main\\resources\\static\\reports\\first-report.txt", false))
        {
            // запись всей строки
            String text = "DriveAve\n";
            text += "Отчет о популярности автомобилей\n\n";
            text+="Наиболее популярные автомобили:\n";
            text+="==================================\n";
            List<Rent> rents = rentService.findTopFive();
            List<Integer> numbers = rentService.findTopFiveNumbers();
            for(int i=0;i<rents.size();i++)
                text+="\t"+rents.get(i).getId_car_rented().getName()+"\t"+rents.get(i).getId_car_rented().getModel()+"\t"+numbers.get(i)+"\n";
            text+="==================================\n";
            writer.write(text);

            writer.flush();


        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


        try(FileWriter writer2 = new FileWriter("C:\\Users\\Moroz\\Downloads\\moroz\\moroz\\src\\main\\resources\\static\\reports\\second-report.txt", false))
        {
            String text = "DriveAve\n";
            text += "Занятые автомобили\n\n";
            text+="Автомобили:\n";
            text+="==================================\n";
            List<Car> cars= carService.findAllBusy();
            for(int i=0;i<cars.size();i++){
                text+=cars.get(i).getName()+"\t"+cars.get(i).getModel()+"\t\n";
            }
            text+="==================================\n";
            writer2.write(text);

            writer2.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

}
