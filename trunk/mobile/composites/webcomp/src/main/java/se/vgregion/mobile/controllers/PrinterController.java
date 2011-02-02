/**
 * Copyright 2010 Västra Götalandsregionen
 *
 *   This library is free software; you can redistribute it and/or modify
 *   it under the terms of version 2.1 of the GNU Lesser General Public
 *   License as published by the Free Software Foundation.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the
 *   Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 *   Boston, MA 02111-1307  USA
 *
 */

package se.vgregion.mobile.controllers;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.vgregion.mobile.services.PrinterService;

/**
 * Controller for showing a basic web GUI for shorting link.
 *
 */
@Controller
public class PrinterController {

    private final Logger log = LoggerFactory.getLogger(PrinterController.class);

    @Resource
    private PrinterService printerService;

    
    public PrinterController() {
        log.info("Created {}", PrinterController.class.getName());
    }

    @RequestMapping(value="/printer/{id}", method=RequestMethod.GET)
    public ModelAndView printer(@PathVariable("id") UUID id) throws IOException {
        ModelAndView mav = new ModelAndView("printer");
        
        mav.addObject("printer", printerService.findPrinterById(id));
        
        return mav;
    }


    public PrinterService getPrinterService() {
        return printerService;
    }

    public void setPrinterService(PrinterService printerService) {
        this.printerService = printerService;
    }
}
