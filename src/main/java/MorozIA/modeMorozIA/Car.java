package MorozIA.modeMorozIA;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_car")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="model")
    private String model;

    @Column(name="passengers")
    private String passengers;

    @Column(name="rashodtopliva")
    private String rashodtopliva;

    @Column(name="typedvigatelya")
    private String typedvigatelya;

    @Column(name="maxspeed")
    private String maxspeed;

    @Column(name="price")
    private String price;

    @Column(name="vremyarazgona")
    private String vremyarazgona;

    @Column(name="volumeofbak")
    private String volumeofbak;

@Column(name="busy")
private String isBusy;

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getRashodtopliva() {
        return rashodtopliva;
    }

    public void setRashodtopliva(String rashodtopliva) {
        this.rashodtopliva = rashodtopliva;
    }

    public String getTypedvigatelya() {
        return typedvigatelya;
    }

    public void setTypedvigatelya(String typedvigatelya) {
        this.typedvigatelya = typedvigatelya;
    }

    public String getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(String maxspeed) {
        this.maxspeed = maxspeed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVremyarazgona() {
        return vremyarazgona;
    }

    public void setVremyarazgona(String vremyarazgona) {
        this.vremyarazgona = vremyarazgona;
    }

    public String getVolumeofbak() {
        return volumeofbak;
    }

    public void setVolumeofbak(String volumeofbak) {
        this.volumeofbak = volumeofbak;
    }

    public String getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(String isBusy) {
        this.isBusy = isBusy;
    }
}
