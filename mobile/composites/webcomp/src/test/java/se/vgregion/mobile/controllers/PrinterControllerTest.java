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

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import se.vgregion.mobile.services.ErrorReportService;
import se.vgregion.mobile.services.PrinterService;
import se.vgregion.mobile.types.ErrorReport;
import se.vgregion.mobile.types.Printer;
import se.vgregion.mobile.types.PrinterQueue;


public class PrinterControllerTest {

    private PrinterController controller = new PrinterController();
    
    @Test
    public void printer() throws IOException {
        Printer p1 = new Printer("p1", "help", "info");
        List<Printer> printers = Arrays.asList(p1);
        
        PrinterService printerService = mock(PrinterService.class);
        
        Mockito.when(printerService.findAllPrinters()).thenReturn(printers);
        Mockito.when(printerService.findPrinterById(p1.getId())).thenReturn(p1);
        controller.setPrinterService(printerService);
        
        ModelAndView mav = controller.printer(p1.getId(), "note");
        Assert.assertEquals(p1, mav.getModel().get("printer"));
        Assert.assertEquals("note", mav.getModel().get("notice"));
    }

    @Test
    public void report() throws IOException {
        PrinterQueue q1 = new PrinterQueue("q1");
        Printer p1 = new Printer("p1", "help", "info", q1);
        
        PrinterService printerService = mock(PrinterService.class);
        ErrorReportService errorReportService = mock(ErrorReportService.class);
        
        Mockito.when(printerService.findPrinterById(p1.getId())).thenReturn(p1);
        controller.setPrinterService(printerService);
        controller.setErrorReportService(errorReportService);
        
        ModelAndView mav = controller.report(p1.getId(), "desc", "user", q1.getId());
        Assert.assertNotNull(mav.getModel().get("notice"));
        
        ArgumentCaptor<ErrorReport> reportCaptor = ArgumentCaptor.forClass(ErrorReport.class);
        Mockito.verify(errorReportService).report(reportCaptor.capture());
        
        Assert.assertEquals("desc", reportCaptor.getValue().getDescription());
        Assert.assertEquals("user", reportCaptor.getValue().getReporter());
        Assert.assertEquals(p1, reportCaptor.getValue().getPrinter());
        Assert.assertEquals(q1, reportCaptor.getValue().getQueue());
    }

}
