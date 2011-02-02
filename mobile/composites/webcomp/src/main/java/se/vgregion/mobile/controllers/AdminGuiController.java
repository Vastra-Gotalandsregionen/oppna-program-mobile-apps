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

    
    public AdminGuiController() {
        log.info("Created {}", AdminGuiController.class.getName());
    }

    @RequestMapping(value="/admin", method=RequestMethod.GET)
    public ModelAndView index() throws IOException {
        ModelAndView mav = new ModelAndView("admin/index");
        
        mav.addObject("printers", printerService.findAllPrinters());
//        mav.addObject("staticRedirects", printerService.findAllStaticRedirects());
//        mav.addObject("applications", printerService.findAllApplications());
        
        return mav;
    }

    @RequestMapping(value="/admin/redirectrules", method=RequestMethod.POST)
    public ModelAndView updateRedirectRules(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView("redirect:../admin");
        
//        if(request.getParameter("add") != null) {
//            // adding a new rule
//            String domain = request.getParameter("domain");
//            if(StringUtils.isEmpty(domain)) {
//                domain = null;
//            }
//            String pattern = request.getParameter("pattern");
//            URI url = URI.create(request.getParameter("url"));
//
//            if(StringUtils.isNotEmpty(pattern)) {
//                log.debug("Adding redirect rule with pattern \"{}\" and URL \"{}\"", pattern, url);
//                try { 
//                    printerService.createRedirectRule(new RedirectRule(domain, pattern, url));
//                } catch(RuntimeException e) {
//                    throw e;
//                }
//            } else {
//                // handle validation error
//                System.out.println("Validation error");
//            }
//        } else {
//            UUID deletedId = findDeletedId(request);
//            if(deletedId != null) {
//                log.debug("Deleting redirect rule {}", deletedId);
//                printerService.removeRedirectRule(deletedId);
//            }
//        }
        
        return mav;
    }

    public PrinterService getPrinterService() {
        return printerService;
    }

    public void setPrinterService(PrinterService printerService) {
        this.printerService = printerService;
    }


    
//    private UUID findDeletedId(HttpServletRequest request) {
//        Enumeration names = request.getParameterNames();
//        while(names.hasMoreElements()) {
//            String name = (String) names.nextElement();
//            if(name.startsWith("delete-")) {
//                return UUID.fromString(name.substring(7));
//            }
//        }
//        
//        return null;
//    }

    
}
