package com.server.codewarriors.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
        private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);
        @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
        public void handleOptionsRequests() {
            logger.info("Handling preflight request");
        }

}
