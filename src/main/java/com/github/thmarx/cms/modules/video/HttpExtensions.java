package com.github.thmarx.cms.modules.video;

/*-
 * #%L
 * video-module
 * %%
 * Copyright (C) 2024 Marx-Software
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.github.thmarx.cms.api.extensions.HttpHandler;
import com.github.thmarx.cms.api.extensions.HttpHandlerExtensionPoint;
import com.github.thmarx.cms.api.extensions.Mapping;
import com.github.thmarx.modules.api.annotation.Extension;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.pathmap.PathSpec;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

/**
 *
 * @author t.marx
 */
@Extension(HttpHandlerExtensionPoint.class)
public class HttpExtensions extends HttpHandlerExtensionPoint {

	@Override
	public Mapping getMapping() {
		try {
			Mapping mapping = new Mapping();
			
			mapping.add(PathSpec.from("/video.js"), httpHandler("video.js", "application/javascript"));
			mapping.add(PathSpec.from("/video.css"), httpHandler("video.css", "text/css"));
			mapping.add(PathSpec.from("/arrow.svg"), httpHandler("arrow.svg", "image/svg+xml"));
			
			return mapping;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private HttpHandler httpHandler (String resource, String contentType) throws IOException {
		String content = CharStreams.toString( new InputStreamReader(HttpExtensions.class.getResourceAsStream(resource), StandardCharsets.UTF_8 ) );
		return new StringContentHandler(contentType, content);
	}
	
	@RequiredArgsConstructor
	public static class StringContentHandler implements HttpHandler {

		private final String contentType;
		private final String content;
		
		
		@Override
		public boolean handle(Request request, Response response, Callback callback) throws Exception {
			response.getHeaders().add(HttpHeader.CONTENT_TYPE, contentType);
			response.write(true, ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8)), callback);
			return true;
		}
		
	}
}
