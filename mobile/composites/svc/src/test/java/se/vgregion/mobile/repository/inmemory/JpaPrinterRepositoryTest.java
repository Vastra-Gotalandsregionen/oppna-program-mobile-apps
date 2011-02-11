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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import se.vgregion.mobile.repository.PrinterRepository;
import se.vgregion.mobile.types.Printer;

@ContextConfiguration({"classpath:spring/services-common.xml", "classpath:test.xml"})
public class JpaPrinterRepositoryTest extends AbstractJUnit4SpringContextTests {

    private PrinterRepository dao;
    
    private Printer user1;
    
    @Before
    public void setup() {
        dao = applicationContext.getBean(PrinterRepository.class);
        user1 = new Printer("SE123-123", "help", "info");
        dao.persist(user1);
        dao.flush();
    }
    
    @Test
    public void find() {
        Printer loaded = dao.find(user1.getId());
        
        Assert.assertEquals(user1.getId(), loaded.getId());
    }
}
