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
import java.util.Map;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author t.marx
 */
@RequiredArgsConstructor
public class Video {

	private final Map<String, Object> parameters;

	public String type() {
		return (String) parameters.getOrDefault("type", "");
	}

	public String id() {
		return (String) parameters.getOrDefault("id", "");
	}

	public String title() {
		return (String) parameters.getOrDefault("title", "");
	}
	
	public boolean fullscreen() {
		return (Boolean) parameters.getOrDefault("fullscreen", false);
	}

	public boolean autostart() {
		return (Boolean) parameters.getOrDefault("autostart", false);
	}
	public boolean controls() {
		return (Boolean) parameters.getOrDefault("controls", true);
	}

	public boolean overlay() {
		return (Boolean) parameters.getOrDefault("overlay", false);
	}
	
	public String thumbnail() {
		return (String) parameters.getOrDefault("thumbnail", "");
	}
	
	public String href () {
		if ("youtube".equals(type())) {
			return "https://youtube.com/watch?v=" + id();
		} else if ("vimeo".equals(type())) {
			return "https://vimeo.com/" + id();
		}
		return "";
	}
	
	public String queryParameters() {
		StringBuilder query = new StringBuilder();
		if ("youtube".equals(type())) {
			if (autostart()) {
				query.append("autoplay=1;");
			} else {
				query.append("autoplay=0;");
			}
			if (controls()) {
				query.append("controls=1;");
			} else {
				query.append("controls=0;");
			}
		} 

		if ("vimeo".equals(type())) {
			return "badge=0&amp;autopause=0&amp;player_id=0";
		}

		return query.toString();
	}

	public String allowAttribute() {
		if ("youtube".equals(type())) {
			if (autostart()) {
				return "autoplay=1";
			} else {
				return "autoplay=0";
			}
		}

		if ("vimeo".equals(type())) {
			return "badge=0&amp;autopause=0&amp;player_id=0";
		}

		return "";
	}
}
