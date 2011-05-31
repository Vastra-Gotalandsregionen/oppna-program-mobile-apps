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

package se.vgregion.urlservice.services;

import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicStatusLine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import se.vgregion.mobile.services.HttpClientFactory;
import se.vgregion.mobile.services.SambaFacilityService;
import se.vgregion.mobile.types.Facility;
import se.vgregion.mobile.types.Position;


public class SambaFacilitiesServiceTest {

	@Mock private HttpClient httpClient;
	@Mock private HttpResponse httpResponse;
	@Mock private HttpEntity httpEntity;
	@Mock private HttpClientFactory httpClientFactory;
	private Position pos = new Position(1, 2);
	
	private SambaFacilityService facilityService = new SambaFacilityService();
	
	@Before
	public void before() throws IOException {
        MockitoAnnotations.initMocks(this);
		
		facilityService.setSambaBaseUrl(URI.create("http://example.com/"));
	
		when(httpClientFactory.getClient()).thenReturn(httpClient);
		when(httpClient.execute(Mockito.any(HttpUriRequest.class))).thenReturn(httpResponse);
		when(httpResponse.getEntity()).thenReturn(httpEntity);
		when(httpResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 0), 200, "OK"));
		
		facilityService.setHttpClientFactory(httpClientFactory);
	}
	
	@Test
	public void testSuccessful() throws Exception {
		when(httpEntity.getContent()).thenReturn(new FileInputStream("src/test/resources/samba1.xml"));
		
		List<Facility> actual = facilityService.findNearbyFacilities(pos);
		List<Facility> expected = Arrays.asList(
				new Facility("RH-Gbg-Lillhagen:Fullriggaren 28", "Fullriggaren 28", pos),
				new Facility("RH-Gbg-Lillhagen:Barken 22", "Barken 22", pos)
		);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testSuccessfulNoResources() throws Exception {
		when(httpEntity.getContent()).thenReturn(new FileInputStream("src/test/resources/samba2.xml"));
		
		List<Facility> actual = facilityService.findNearbyFacilities(pos);
		
		Assert.assertTrue(actual.isEmpty());
	}

	
	@Test(expected=RuntimeException.class)
	public void testHttpError() throws Exception {
		when(httpResponse.getStatusLine()).thenReturn(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 0), 500, "ERROR"));
		
		facilityService.findNearbyFacilities(pos);
	}

	@Test(expected=RuntimeException.class)
	public void testIoException() throws Exception {
		when(httpClient.execute(Mockito.any(HttpUriRequest.class))).thenThrow(new IOException());
		
		facilityService.findNearbyFacilities(pos);
	}

}
