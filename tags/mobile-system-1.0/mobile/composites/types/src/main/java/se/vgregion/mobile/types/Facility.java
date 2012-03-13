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

import org.springframework.util.Assert;

import se.vgregion.dao.domain.patterns.entity.AbstractEntity;

public class Facility extends AbstractEntity<String> {

    private String id;
    
    private String name;
    
    private Position position;
    
    public Facility(String id, String name, Position position) {
    	Assert.hasText(id);
        Assert.hasText(name);
        Assert.notNull(position);

        this.id = id;
        this.name = name;
        this.position = position;
    }

    @Override
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
