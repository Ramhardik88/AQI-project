package io.javabrains.AQITracker.controller;

import io.javabrains.AQITracker.model.ComponentStats;
import io.javabrains.AQITracker.services.AqiDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.OptionalInt;

@Controller
public class HomeController
{
    @Autowired
    AqiDataServices aqiDataServices;
    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("componentStats",aqiDataServices.getAllStats());
        return "home";
    }
}
