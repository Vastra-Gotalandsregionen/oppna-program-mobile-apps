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
import se.vgregion.mobile.types.PrinterQueue;

@Service
public class DemoData {

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur condimentum lacinia faucibus. Donec scelerisque nulla at turpis sagittis facilisis sed nec mi. Donec iaculis tincidunt varius. Vestibulum placerat lacus quis urna iaculis vitae scelerisque arcu fermentum. Integer consectetur enim sed purus laoreet sollicitudin sit amet vel libero. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec vitae ipsum a diam eleifend interdum a ac justo. In in erat in neque semper cursus sit amet non sem.";
    
    @Resource
    private PrinterRepository printerRepository;
    
    @PostConstruct
    public void init() {
        printerRepository.persist(new Printer("HP 700A", LOREM_IPSUM, LOREM_IPSUM, new PrinterQueue("A4"), new PrinterQueue("A3")));
        printerRepository.persist(new Printer("HP 700B", LOREM_IPSUM, LOREM_IPSUM, new PrinterQueue("A4"), new PrinterQueue("Kuvert")));
        printerRepository.persist(new Printer("HP 700C", LOREM_IPSUM, LOREM_IPSUM, new PrinterQueue("A4"), new PrinterQueue("Dubbelsidigt")));
    }
    
}
