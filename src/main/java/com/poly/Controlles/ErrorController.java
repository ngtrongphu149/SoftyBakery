package com.poly.Controlles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(Exception ex) {
//        ModelAndView modelAndView = new ModelAndView("404");
//        modelAndView.addObject("errorMessage", ex.getMessage());
//        return modelAndView;
//    }
//
//    @GetMapping("/error")
//    public void triggerError() throws Exception {
//        throw new Exception("This is a custom error message!");
//    }
}
