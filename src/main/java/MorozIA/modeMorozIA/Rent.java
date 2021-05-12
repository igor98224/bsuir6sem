package MorozIA.modeMorozIA;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="startrent_date")
    private String startrent_date;

    @Column(name="endrent_date")
    private String endrent_date;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer id_customer;

    @ManyToOne
    @JoinColumn(name = "id_car_rented")
    private Car id_car_rented;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartrent_date() {
        return startrent_date;
    }

    public void setStartrent_date(String startrent_date) {
        this.startrent_date = startrent_date;
    }

    public String getEndrent_date() {
        return endrent_date;
    }

    public void setEndrent_date(String endrent_date) {
        this.endrent_date = endrent_date;
    }

    public Customer getId_customer() {
        return id_customer;
    }

    public void setId_customer(Customer id_customer) {
        this.id_customer = id_customer;
    }

    public Car getId_car_rented() {
        return id_car_rented;
    }

    public void setId_car_rented(Car id_car_rented) {
        this.id_car_rented = id_car_rented;
    }
}
