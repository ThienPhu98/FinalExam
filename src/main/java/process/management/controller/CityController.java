package process.management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import process.management.model.City;
import process.management.service.ICityService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping("")
    public ModelAndView showCityList() {
        ModelAndView modelAndView = new ModelAndView("/cityList");
        Iterable<City> cityList = cityService.findAll();
        modelAndView.addObject("cityList", cityList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCity(@Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        ModelAndView modelAndView = null;
        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("/create");
            modelAndView.addObject("message", "create city fail! please follow instructor!");
        } else {
            cityService.save(city);
            modelAndView = new ModelAndView("/cityList");
            Iterable<City> cityList = cityService.findAll();
            modelAndView.addObject("cityList", cityList);
            modelAndView.addObject("message", "create city success!");
        }
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable long id) {
        ModelAndView modelAndView = null;
        Optional<City> cityEdit = cityService.findById(id);
        if (cityEdit.isPresent()) {
            modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("cityEdit", cityEdit.get());
            modelAndView.addObject("city", new City());
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editCustomerInformation(@PathVariable long id, @Validated @ModelAttribute("city") City city, BindingResult bindingResult) {
        ModelAndView modelAndView = null;
        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("message", "Edit city fail! please follow instructor!");
        } else {
            Optional<City> cityOptional = cityService.findById(id);
            if (cityOptional.isPresent()) {
                City cityEdit = cityOptional.get();
                cityEdit.setName(city.getName());
                cityEdit.setArea(city.getArea());
                cityEdit.setCountry(city.getCountry());
                cityEdit.setDescription(city.getDescription());
                cityEdit.setGDP(city.getGDP());
                cityEdit.setPopulation(city.getPopulation());
                cityService.save(cityEdit);
                modelAndView = new ModelAndView("/cityList");
                Iterable<City> cityIterable = cityService.findAll();
                modelAndView.addObject("cityList", cityIterable);
                modelAndView.addObject("message", "Edit city success!");
            } else {
                modelAndView = new ModelAndView("/error.404");
            }
        }
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDelete(@PathVariable long id) {
        ModelAndView modelAndView = null;
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("city", city.get());
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable long id) {
        ModelAndView modelAndView = null;
        Optional<City> cityOptional = cityService.findById(id);
        if (cityOptional.isPresent()) {
            cityService.remove(id);
            modelAndView = new ModelAndView("/cityList");
            Iterable<City> cityIterable = cityService.findAll();
            modelAndView.addObject("cityList", cityIterable);
            modelAndView.addObject("message", "delete city success");
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @GetMapping("/{id}/info")
    public ModelAndView showInfo(@PathVariable long id) {
        ModelAndView modelAndView = null;
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            modelAndView = new ModelAndView("/info");
            modelAndView.addObject("city", city.get());
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }
}
