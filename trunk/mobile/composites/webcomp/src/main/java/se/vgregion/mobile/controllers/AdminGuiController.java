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
import java.net.URI;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.vgregion.mobile.services.PrinterService;

/**
 * Controller for showing a basic web GUI for shorting link.
 *
 */
@Controller
public class AdminGuiController {

    private final Logger log = LoggerFactory.getLogger(AdminGuiController.class);

    @Resource
    private PrinterService printerService;
    
    private URI applicationUrl;

    
    public AdminGuiController() {
        log.info("Created {}", AdminGuiController.class.getName());
    }

    @RequestMapping(value="/admin", method=RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView("admin/index");
        
        mav.addObject("appurl", getApplicationUrl(request));
        mav.addObject("printers", printerService.findAllPrinters());
        
        return mav;
    }
    
    private URI getApplicationUrl(HttpServletRequest request) {
        if(applicationUrl != null) {
            return applicationUrl;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(request.getScheme());
            sb.append("://");
            sb.append(request.getServerName());
            if(request.getServerPort() > 0) {
                sb.append(":");
                sb.append(request.getServerPort());
            }
            sb.append(request.getContextPath());
            
            return URI.create(sb.toString());
        }
    }


    public PrinterService getPrinterService() {
        return printerService;
    }

    public void setPrinterService(PrinterService printerService) {
        this.printerService = printerService;
    }

    public URI getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(URI applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
