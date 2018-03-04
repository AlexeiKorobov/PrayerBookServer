package com.holytrinityorthodox.prayerbookservice.rest;
import com.holytrinityorthodox.prayerbookservice.util.ILoggerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.logging.log4j.Level;


@Controller
public class IndexController {

    @Autowired
    ILoggerProxy logger;

    @RequestMapping(value = {"/", "/index**"})
    public String index() {
        try{
            return "index";
        }
        catch(Exception ex){
            logger.log(Level.ERROR, ex);
            throw ex;
        }
    }
}