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

package se.vgregion.mobile.services;

import java.util.Collection;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.vgregion.mobile.repository.PrinterRepository;
import se.vgregion.mobile.types.Printer;
import se.vgregion.mobile.types.PrinterQueue;

@Service
public class DefaultPrinterService implements PrinterService {

    private final Logger log = LoggerFactory.getLogger(DefaultPrinterService.class);

    @Resource
    private PrinterRepository printerRepository;
    
    @Override
    public Printer findPrinterById(UUID id) {
        return printerRepository.find(id);
    }

    @Override
    public Collection<Printer> findAllPrinters() {
        return printerRepository.findAll();
    }

    @Override
    public PrinterQueue findPrinterQueue(UUID printerId, UUID queueId) {
        Printer printer = findPrinterById(printerId);
        
        if(printer != null) {
            return printer.getQueue(queueId);
        } else {
            return null;
        }
    }
}
