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

import java.util.UUID;

import org.springframework.util.Assert;

import se.vgregion.dao.domain.patterns.entity.AbstractEntity;

public class ErrorReport extends AbstractEntity<UUID> {

    private UUID id;
    
    private Printer printer;
    
    private PrinterQueue queue;
    
    private String reporter = "Okänd";
    
    private String description;
    
    public ErrorReport(Printer printer, PrinterQueue queue, String reporter, String description) {
        Assert.notNull(printer);
        Assert.hasText(description);

        this.id = UUID.randomUUID();
        this.printer = printer;
        this.queue = queue;
        this.reporter = reporter;
        this.description = description;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Printer getPrinter() {
        return printer;
    }

    public PrinterQueue getQueue() {
        return queue;
    }
    
    public String getReporter() {
        return reporter;
    }

    public String getDescription() {
        return description;
    }
}
