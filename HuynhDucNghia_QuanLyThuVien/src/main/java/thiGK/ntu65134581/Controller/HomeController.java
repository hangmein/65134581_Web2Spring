package thiGK.ntu65134581.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import thiGK.ntu65134581.model.Page;

@Controller
public class HomeController {
	@GetMapping("/dashboard")
	public String getDashboard() {
		return "dashboard";
	}
	
	private static List<Page> pageList = new ArrayList<>();

    static {
        // Khởi tạo một vài dữ liệu mẫu khớp với Constructor của bạn
        pageList.add(new Page(1, "Trang chủ", "home, ntu", "Nội dung trang chủ", "0"));
        pageList.add(new Page(2, "Giới thiệu", "about", "Nội dung giới thiệu", "1"));
    }

    // a. Page - List (Url: /page/all)
    @GetMapping("/page/list")
    public String listAll(ModelMap model) {
        model.addAttribute("pages", pageList);
        return "page-list"; 
    }

    // b. Page - Addnew (Url: /page/new)
    @GetMapping("/page/new")
    public String showAddForm(ModelMap model) {
        model.addAttribute("page", new Page());
        return "page-add";
    }

    @PostMapping("/page/new")
    public String savePage(@ModelAttribute("page") Page page) {
        // Tự động tăng ID dựa trên size của list
        page.setId(pageList.size() + 1);
        pageList.add(page);
        return "redirect:/page/list";
    }

    // c. Page - View (Url: /page/view/id)
    @GetMapping("/page/view/{id}")
    public String viewPage(@PathVariable("id") int id, ModelMap model) {
        Page foundPage = pageList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("page", foundPage);
        return "page-view";
    }

    // d. Page - Delete (Url: /page/delete/id)
    @GetMapping("/page/delete/{id}")
    public String deletePage(@PathVariable("id") int id) {
        pageList.removeIf(p -> p.getId() == id);
        return "redirect:/page/list";
    }
}
