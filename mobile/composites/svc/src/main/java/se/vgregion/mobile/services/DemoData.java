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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import se.vgregion.mobile.repository.PrinterRepository;
import se.vgregion.mobile.types.Printer;

@Service
public class DemoData {

    @Resource
    private PrinterRepository printerRepository;
    
    @PostConstruct
    public void init() {
        printerRepository.persist(new Printer("HP 700A"));
        printerRepository.persist(new Printer("HP 700B"));
        printerRepository.persist(new Printer("HP 700C"));
    }
    
}
