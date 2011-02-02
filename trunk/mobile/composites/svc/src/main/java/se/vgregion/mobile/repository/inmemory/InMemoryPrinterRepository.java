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

package se.vgregion.mobile.repository.inmemory;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import se.vgregion.dao.domain.patterns.repository.inmemory.AbstractInMemoryRepository;
import se.vgregion.mobile.repository.PrinterRepository;
import se.vgregion.mobile.types.Printer;
    
@Repository
public class InMemoryPrinterRepository extends AbstractInMemoryRepository<Printer, UUID> implements PrinterRepository {


    @Override
    public Printer findByName(String name) {
        Collection<Printer> all = findAll();
        for(Printer printer : all) {
            if(name.equals(printer.getName())) return printer;
        }
        
        return null;
    }
    

}