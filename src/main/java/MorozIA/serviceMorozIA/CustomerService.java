package MorozIA.serviceMorozIA;

import MorozIA.modeMorozIA.Customer;
import MorozIA.repositoryMorozIA.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer findById(Integer id){
        return customerRepository.getOne(id);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteById(Integer id){
        customerRepository.deleteById(id);
    }

    public List<Customer> findByEmail(String email, String access){
        return customerRepository.findByEmailAndAccess(email, access);
    }

    public String how(){
        String how="";
        BufferedReader objReader = null;
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader("C:\\Users\\Moroz\\Downloads\\moroz\\moroz\\src\\main\\resources\\recommendations\\how.txt"));
            while ((strCurrentLine = objReader.readLine())!=null) {
                how+=strCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return how;
    }

    public List<Customer> findByEmail2(String email){
        return customerRepository.findByEmail(email);
    }
}
