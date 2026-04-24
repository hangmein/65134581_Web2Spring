package thiGK.ntu65134581.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thiGK.ntu65134581.Model.Page;
import thiGK.ntu65134581.Repo.PageRepos;
import thiGK.ntu65134581.Repo.PostRepos;

@Service
public class HomeService {
	@Autowired
	PageRepos pageRepos;
	
	@Autowired
	PostRepos postRepos;
	
	public List<Page> getAllPage(){
		return pageRepos.findAll();
	}
	public void SavePage(Page p) {
		pageRepos.save(p);
	}
	public Page ViewPage(int id) {
		return pageRepos.getReferenceById(id);
	}
	public void DeletePage(int id)
	{
		pageRepos.deleteById(id);
	}
	
}
