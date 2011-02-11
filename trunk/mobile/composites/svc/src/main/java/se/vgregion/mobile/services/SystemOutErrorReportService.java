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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import se.vgregion.mobile.types.ErrorReport;

@Service
public class SystemOutErrorReportService implements ErrorReportService {

    private final Logger log = LoggerFactory.getLogger(SystemOutErrorReportService.class);

    private String body = "Felrapport för skrivare %1s:\n%2s\n\nRapporterat av: %3s";
    
    @Override
    public void report(ErrorReport report) {
        String text = String.format(body, report.getPrinter().getName(), report.getDescription(), report.getReporter());
        System.out.println(text);
    }
}
