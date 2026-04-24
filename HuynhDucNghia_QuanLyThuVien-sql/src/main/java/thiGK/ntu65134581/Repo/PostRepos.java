package thiGK.ntu65134581.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thiGK.ntu65134581.Model.Post;

@Repository
public interface PostRepos extends JpaRepository<Post,Integer> {
	
}
