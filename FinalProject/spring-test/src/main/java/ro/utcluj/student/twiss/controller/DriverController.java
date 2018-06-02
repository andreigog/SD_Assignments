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
import ro.utcluj.student.twiss.service.DriverService;

@Controller
public class DriverController {
	@Autowired
	private DriverService driverService;


	@ModelAttribute("driver")
	public Driver getDriverObject() {
		return new Driver();
	}

	@RequestMapping(value = "/drivers", method = RequestMethod.GET)
	public String getDriversPage(
		final Model model) {
		final Iterable<Driver> drivers = this.driverService.getAllDrivers();
		model.addAttribute("drivers", drivers);
		return "drivers";
	}

	@RequestMapping(value = "/drivers", method = RequestMethod.POST)
	public void addDriver(@Valid @ModelAttribute("driver") Driver driver,
						  final HttpServletResponse response, BindingResult bindingResult) throws IOException {

			this.driverService.addDriver(driver);
			response.sendRedirect("/drivers");
	}

}