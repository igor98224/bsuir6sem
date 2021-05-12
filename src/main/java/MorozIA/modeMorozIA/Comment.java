package MorozIA.modeMorozIA;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer id_customer;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Customer getId_customer() {
        return id_customer;
    }

    public void setId_customer(Customer id_customer) {
        this.id_customer = id_customer;
    }
}

