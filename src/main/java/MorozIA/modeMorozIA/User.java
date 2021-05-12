package MorozIA.modeMorozIA;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @OneToOne
    @JoinColumn(name = "id_admin")
    private Admin id_admin;

    @OneToOne
    @JoinColumn(name = "id_customer")
    private Customer id_customer;


}
