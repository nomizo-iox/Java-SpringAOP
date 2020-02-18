package com.nomizo.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class Logging {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void logs(){
        logger.info("This is INFO");
        logger.debug("This is DEBUG");
        logger.warn("This is WARNING");
        logger.error("This is ERROR");
    }
}
