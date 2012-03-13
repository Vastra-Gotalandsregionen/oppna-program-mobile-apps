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
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import se.vgregion.mobile.services.FacilityService;
import se.vgregion.mobile.types.Facility;
import se.vgregion.mobile.types.Position;


public class FacilitiesControllerTest {

    private FacilitiesController controller = new FacilitiesController();
    private List<Facility> facilities;
    private FacilityService facilityService;

    @Before
    public void before() {
        Facility f1 = new Facility("id1", "fac1", new Position(1, 2));
        Facility f2 = new Facility("id2", "fac2", new Position(3, 4));
        
        facilities = Arrays.asList(f1, f2);
        
        facilityService = mock(FacilityService.class);
        
        Mockito.when(facilityService.findNearbyFacilities(Mockito.any(Position.class))).thenReturn(facilities);
        controller.setFacilityService(facilityService);
    }
    
    @Test
    public void index() throws IOException {
        ModelAndView mav = controller.index();
        Assert.assertEquals("facilities", mav.getViewName());
    }

    @Test
    public void near() throws IOException {
        List<Facility> actualFacilities = controller.near(1.0, 2.0);

        Assert.assertEquals(facilities, actualFacilities);
    }

}
