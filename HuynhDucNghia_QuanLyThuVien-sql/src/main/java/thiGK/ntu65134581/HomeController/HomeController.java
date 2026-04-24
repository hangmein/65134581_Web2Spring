package thiGK.ntu65134581.HomeController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import thiGK.ntu65134581.Model.Page;
import thiGK.ntu65134581.Service.HomeService;

@Controller
public class HomeController {
	@Autowired
	HomeService homeService;
	@GetMapping("/dashboard")
	public String getDashboard() {
		return "dashboard";
	}
	
    // a. Page - List (Url: /page/all)
    @GetMapping("/page/list")
    public String listAll(ModelMap model) {
        List<Page> pageList = new ArrayList<Page>();
        pageList = homeService.getAllPage();
    	model.addAttribute("pages", pageList);
        return "page-list"; 
    }

    @GetMapping("/page/new")
    public String showAddForm(ModelMap model) {
        model.addAttribute("page", new Page());
        return "page-add";
    }

    @PostMapping("/page/new")
    public String savePage(@ModelAttribute("page") Page page) {
    	homeService.SavePage(page);
        return "redirect:/page/list";
    }
//
//    // c. Page - View (Url: /page/view/id)
    @GetMapping("/page/view/{id}")
    public String viewPage(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("page", homeService.ViewPage(id));
        return "page-view";
    }
//
//    // d. Page - Delete (Url: /page/delete/id)
    @GetMapping("/page/delete/{id}")
    public String deletePage(@PathVariable("id") int id) {
        homeService.DeletePage(id);
        return "redirect:/page/list";
    }
}
