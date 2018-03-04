package com.holytrinityorthodox.prayerbookservice.rest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index**"})
    public String index() {
        try{
            return "index";
        }
        catch(Exception ex){
            //logger.log(Level.ERROR, ex);
            throw ex;
        }
    }
}