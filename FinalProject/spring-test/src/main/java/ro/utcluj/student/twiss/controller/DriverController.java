package ro.utcluj.student.twiss.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.utcluj.student.twiss.model.Driver;
import ro.utcluj.student.twiss.service.DriverService;

@Controller
public class DriverController {
	@Autowired
	private DriverService driverService;

	@RequestMapping(value = "/drivers", method = RequestMethod.GET)
	public String getDriversPage(
		final Model model) {
		final Iterable<Driver> drivers = this.driverService.getAllDrivers();
		model.addAttribute("drivers", drivers);
		return "drivers";
	}

	@RequestMapping(value = "/drivers", method = RequestMethod.POST)
	public void addDriver(
		final Model model,
		final HttpServletResponse response) throws IOException {
		this.driverService.addDriver();
		response.sendRedirect("/drivers");
	}

}