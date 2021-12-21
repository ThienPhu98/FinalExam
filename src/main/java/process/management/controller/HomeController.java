package process.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("")
    public String showIndex1() {
        return "redirect:/cities";
    }

    @GetMapping("/")
    public String showIndex2() {
        return "redirect:/cities";
    }
}
