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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.vgregion.mobile.types.Facility;
import se.vgregion.mobile.types.Position;

@Service
public class MockFacilityService implements FacilityService {

    private final Logger log = LoggerFactory.getLogger(MockFacilityService.class);

    private static List<Facility> MOCK_FACILITIES = new ArrayList<Facility>();
    static {
        MOCK_FACILITIES.add(new Facility("id1", "Vipan", new Position(57.6095426, 12.07527)));
        MOCK_FACILITIES.add(new Facility("id2", "Ärlan", new Position(57.6095424, 12.07529)));
        MOCK_FACILITIES.add(new Facility("id3", "Trasten", new Position(56.6095426, 13.07527)));
        MOCK_FACILITIES.add(new Facility("id4", "Göken", new Position(58.6095426, 11.07527)));
    }

    @Override
    public List<Facility> findNearbyFacilities(Position position) {
        return MOCK_FACILITIES;
    }
    
}
