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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

import se.vgregion.mobile.types.Facility;
import se.vgregion.mobile.types.Position;

@Service
public class SambaFacilityService implements FacilityService {

    private URI sambaBaseUrl;
    private HttpClientFactory httpClientFactory = new DefaultHttpClientFactory();
    
    @Override
    public List<Facility> findNearbyFacilities(Position position) {
        if(sambaBaseUrl == null) {
        	throw new RuntimeException("Missing configuration for SAMBA base URL");
        }

        String urlTemplate = sambaBaseUrl.toString() + "&latitude=%f&longitude=%f&distance=10";
        String fullUrl = String.format(urlTemplate, position.getLatitude(), position.getLongitude()); 
        HttpGet get = new HttpGet(fullUrl);
        
        HttpResponse response = null;
        try {
        	HttpClient client = httpClientFactory.getClient();
			response = client.execute(get);
			
			if(HttpUtil.successStatus(response)) {
				Builder builder = new Builder();
				Document doc = builder.build(response.getEntity().getContent());
				
				List<Facility> facilities = new ArrayList<Facility>();
				Nodes resources = doc.query("/response/resource");
				for(int i = 0; i<resources.size(); i++) {
					Element resource = (Element) resources.get(i);
					String place = resource.getChildElements("place").get(0).getValue();
					String name = resource.getChildElements("name").get(0).getValue();
					String id = place + ":" + name;
					
					facilities.add(new Facility(id, name, position));
				}
				
				return facilities;
			} else {
				throw new RuntimeException("Failed to look up facilities, please try again later");
			}
			
		} catch (Exception e) {
			// TODO clean up
			throw new RuntimeException(e);
		} finally {
			HttpUtil.closeQuitely(response);
		}
        
    }
    
	public URI getSambaBaseUrl() {
		return sambaBaseUrl;
	}

	public void setSambaBaseUrl(URI sambaBaseUrl) {
		this.sambaBaseUrl = sambaBaseUrl;
	}

	public HttpClientFactory getHttpClientFactory() {
		return httpClientFactory;
	}

	public void setHttpClientFactory(HttpClientFactory httpClientFactory) {
		this.httpClientFactory = httpClientFactory;
	}
}
