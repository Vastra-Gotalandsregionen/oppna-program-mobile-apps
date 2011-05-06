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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import se.vgregion.mobile.services.ErrorReportService;
import se.vgregion.mobile.services.PrinterService;
import se.vgregion.mobile.types.ErrorReport;
import se.vgregion.mobile.types.Printer;
import se.vgregion.mobile.types.PrinterQueue;

/**
 * Controller for showing a basic web GUI for shorting link.
 *
 */
@Controller
public class PrinterController {

    private final Logger log = LoggerFactory.getLogger(PrinterController.class);

    @Resource
    private PrinterService printerService;

    @Resource
    private ErrorReportService errorReportService;

    
    public PrinterController() {
        log.info("Created {}", PrinterController.class.getName());
    }

    @RequestMapping(value="/printer/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Printer printer(@PathVariable("id") UUID id) throws IOException {
        return printerService.findPrinterById(id);
    }

    
    @RequestMapping(value="/printer/{id}/report", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void report(@RequestParam("printer") UUID printerId, 
            @RequestParam(value="queue", required=false) UUID queueId,
            @RequestParam(value="reporter", required=false) String reporter,
            @RequestParam("error") String error
    ) throws IOException {
        Printer printer = printerService.findPrinterById(printerId);
        PrinterQueue queue = null;
        if(queueId != null) {
            queue = printerService.findPrinterQueue(printerId, queueId);
        }
        ErrorReport report = new ErrorReport(printer, queue, reporter, error);
        
        errorReportService.report(report);
    }


    public PrinterService getPrinterService() {
        return printerService;
    }

    public void setPrinterService(PrinterService printerService) {
        this.printerService = printerService;
    }

    public ErrorReportService getErrorReportService() {
        return errorReportService;
    }

    public void setErrorReportService(ErrorReportService errorReportService) {
        this.errorReportService = errorReportService;
    }
}
