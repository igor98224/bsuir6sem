package MorozIA.modeMorozIA;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="access")
    private String access;
}