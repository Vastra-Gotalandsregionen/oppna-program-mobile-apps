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

package se.vgregion.mobile.types;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


public class ErrorReportTest {

    @Test
    public void cstr() {
        PrinterQueue queue = new PrinterQueue("q");
        Set<PrinterQueue> queues = new HashSet<PrinterQueue>();
        queues.add(queue);
        
        Printer printer = new Printer("n", "h", "i", queues);
        ErrorReport report = new ErrorReport(printer, queue, "user", "desc");
        
        Assert.assertEquals(printer, report.getPrinter());
        Assert.assertEquals("desc", report.getDescription());
        Assert.assertEquals("user", report.getReporter());
        Assert.assertEquals(queue, report.getQueue());
        
    }
}
