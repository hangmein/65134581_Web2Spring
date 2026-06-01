package thick2.HuynhDucNghia.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thick2.HuynhDucNghia.Model.HangHoa;
import thick2.HuynhDucNghia.Repo.HangHoaRepository;

public class HangHoaService {
	 @Autowired
	 private HangHoaRepository repo;
	 public List<HangHoa> getAll() { return repo.findAll(); }
	 public HangHoa getById(Integer id) { return repo.findById(id).orElse(null); }
	 public void save(HangHoa hh) { repo.save(hh); }
	    public void delete(Integer id) { repo.deleteById(id); }

	    public List<HangHoa> search(String keyword) {
	        if (keyword == null || keyword.trim().isEmpty()) return getAll();
	        return repo.findByTenHangHoaContainingIgnoreCase(keyword.trim());
	    }
}
