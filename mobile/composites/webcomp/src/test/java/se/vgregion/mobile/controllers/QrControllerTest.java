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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class QrControllerTest {

    private QrController controller = new QrController();
    
    @Test
    public void image() throws IOException, ServletException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        final ByteArrayOutputStream out = new ByteArrayOutputStream(); 
        ServletOutputStream servletOut = new ServletOutputStream() {
            @Override
            public void write(int arg0) throws IOException {
                out.write(arg0);
            }
        };
        Mockito.when(response.getOutputStream()).thenReturn(servletOut);
        
        String url = "http://example.com";
        controller.image(url, 100, 200, response);
        
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(out.toByteArray()));
        
        Assert.assertEquals(100, img.getWidth());
        Assert.assertEquals(200, img.getHeight());
    }
}
