package MorozIA.serviceMorozIA;

import MorozIA.modeMorozIA.Comment;
import MorozIA.repositoryMorozIA.CustomerRepository;
import MorozIA.repositoryMorozIA.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, CustomerRepository customerRepository){
        this.ratingRepository = ratingRepository;
        this.customerRepository = customerRepository;
    }

    public List<Comment> findAll(){
        return ratingRepository.findAll();
    }

    public void saveComment(String login, String comment){
        Comment comment1 = new Comment();
        comment1.setComments(comment);

        comment1.setId_customer(customerRepository.findTopByEmail(login));
        ratingRepository.save(comment1);
    }
}
