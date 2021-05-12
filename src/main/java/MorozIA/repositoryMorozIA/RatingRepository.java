package MorozIA.repositoryMorozIA;

import MorozIA.modeMorozIA.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Comment, Integer> {
}
