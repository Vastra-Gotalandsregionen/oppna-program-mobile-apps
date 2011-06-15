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

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import se.vgregion.mobile.services.FacilityService;
import se.vgregion.mobile.types.Facility;
import se.vgregion.mobile.types.Position;

/**
 * Controller for showing a basic web GUI for shorting link.
 *
 */
@Controller
public class FacilitiesController {

    private final Logger log = LoggerFactory.getLogger(FacilitiesController.class);

    @Resource
    private FacilityService facilityService;

    public FacilitiesController() {
        log.info("Created {}", FacilitiesController.class.getName());
    }

    @RequestMapping(value="/facilities", method=RequestMethod.GET)
    public ModelAndView index() throws IOException {
        ModelAndView mav = new ModelAndView("facilities");
        
        return mav;
    }

    @RequestMapping(value="/facilities/near/{latitude}/{longitude}", method=RequestMethod.GET)
    @ResponseBody
    public List<Facility> near(@PathVariable("latitude") Double latitude, 
            @PathVariable("longitude") Double longitude) throws IOException {
        Position position = new Position(latitude, longitude);
        
        List<Facility> facilities = facilityService.findNearbyFacilities(position);
        
        return facilities;
    }

    public FacilityService getFacilityService() {
        return facilityService;
    }

    public void setFacilityService(FacilityService facilityService) {
        this.facilityService = facilityService;
    }
}
