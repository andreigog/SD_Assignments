package ro.utcluj.student.twiss.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import ro.utcluj.student.twiss.configuration.WebMvcConfig;
import ro.utcluj.student.twiss.model.Driver;
import ro.utcluj.student.twiss.model.Passenger;
import ro.utcluj.student.twiss.service.DriverService;
import ro.utcluj.student.twiss.service.PassengerService;

@Controller
public class PassengerController {
    @Autowired
    private PassengerService passengerService;


    @ModelAttribute("passenger")
    public Passenger getPassenger(){return  new Passenger();}

    @RequestMapping(value = "/passengers", method = RequestMethod.GET)
    public String getPassengersPage(
            final Model model) {
        final Iterable<Passenger> passengers = this.passengerService.getAllPassengers();
        model.addAttribute("passengers", passengers);
        return "passengers";
    }

    @RequestMapping(value = "/passengers", method = RequestMethod.POST)
    public void addDriver(@ModelAttribute("passenger") Passenger passenger,
                          final HttpServletResponse response) throws IOException {

        this.passengerService.addPassenger(passenger);
        response.sendRedirect("/passengers");
    }

}