package thick2.HuynhDucNghia.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thick2.HuynhDucNghia.Model.LoaiHang;
import thick2.HuynhDucNghia.Repo.LoaiHangRepository;

@Service
public class LoaiHangService {
	@Autowired
    private LoaiHangRepository repo;

    public List<LoaiHang> getAll() { return repo.findAll(); }
    public LoaiHang getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(LoaiHang lh) { repo.save(lh); }
    public void delete(Integer id) { repo.deleteById(id); }
}
