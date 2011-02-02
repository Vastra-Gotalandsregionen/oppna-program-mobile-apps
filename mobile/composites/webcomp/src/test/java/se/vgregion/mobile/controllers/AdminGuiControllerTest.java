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

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import se.vgregion.mobile.services.PrinterService;


public class AdminGuiControllerTest {

    private AdminGuiController controller = new AdminGuiController();
    
    @Test
    public void index() throws IOException {
        PrinterService printerService = mock(PrinterService.class);
        controller.setPrinterService(printerService);
        
        ModelAndView mav = controller.index();
    }
}
