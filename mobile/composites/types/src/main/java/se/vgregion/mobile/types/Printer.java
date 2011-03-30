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
import java.util.UUID;

import org.springframework.util.Assert;

import se.vgregion.dao.domain.patterns.entity.AbstractEntity;

public class Printer extends AbstractEntity<UUID> {

    private UUID id;
    
    private String name;
    
    private String help;
    
    private String information;

    private Set<PrinterQueue> queues;
    
    public Printer(String name, String help, String information, PrinterQueue... queues) {
        this(name, help, information, (Set<PrinterQueue>)null);
        
        this.queues = new HashSet<PrinterQueue>();
        for(PrinterQueue queue : queues) {
            this.queues.add(queue);
        }
    }
    
    public Printer(String name, String help, String information, Set<PrinterQueue> queues) {
        Assert.hasText(name);
        Assert.hasText(help);
        Assert.hasText(information);

        this.id = UUID.randomUUID();
        this.name = name;
        this.help = help;
        this.information = information;
        this.queues = queues;
    }

    @Override
    public UUID getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getHelp() {
        return help;
    }

    public String getInformation() {
        return information;
    }
    
    public Set<PrinterQueue> getQueues() {
        return queues;
    }

    public PrinterQueue getQueue(UUID id) {
        for(PrinterQueue queue : queues) {
            if(queue.getId().equals(id)) return queue;
        }
        
        return null;
    }
}
