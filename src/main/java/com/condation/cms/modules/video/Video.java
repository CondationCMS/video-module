package com.condation.cms.modules.video;

/*-
 * #%L
 * video-module
 * %%
 * Copyright (C) 2024 CondationCMS
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
		return getBooleanValueOrDefault("fullscreen", false);
	}

	public boolean autoplay() {
		return getBooleanValueOrDefault("autoplay", false);
	}

	public boolean controls() {
		return getBooleanValueOrDefault("controls", true);
	}
	
	public boolean muted() {
		return getBooleanValueOrDefault("muted", true);
	}

	public boolean loop() {
		return getBooleanValueOrDefault("loop", false);
	}

	public String start() {
		return (String) parameters.getOrDefault("start", null);
	}
	
	public boolean overlay() {
		return getBooleanValueOrDefault("overlay", false);
	}

	public String thumbnail() {
		return (String) parameters.getOrDefault("thumbnail", "");
	}

	private boolean getBooleanValueOrDefault (String name, boolean defaultValue) {
		Object value = parameters.getOrDefault(name, defaultValue);
		if (value instanceof String) {
			return Boolean.parseBoolean((String)value);
		}
		return (boolean)value;
	}
	
	public String href() {
		if ("youtube".equals(type())) {
			return "https://youtube.com/watch?v=" + id();
		} else if ("vimeo".equals(type())) {
			return "https://vimeo.com/" + id();
		}
		return "";
	}

	public String queryParameters() {
		StringBuilder query = new StringBuilder();

		if (autoplay()) {
			query.append("autoplay=1");
		} else {
			query.append("autoplay=0");
		}
		if (controls()) {
			query.append("&controls=1");
		} else {
			query.append("&controls=0");
		}
		if (loop()) {
			query.append("&loop=1");
		} else {
			query.append("&loop=0");
		}
		if (muted()) {
			query.append("&muted=1");
		} else {
			query.append("&muted=0");
		}
		
		if (muted()) {
			if ("youtube".equals(type())) {
				query.append("&mute=1");
			} else if ("vimeo".equals(type())) {
				query.append("&muted=1");
			}
		}
		
		if (start() != null) {
			if ("youtube".equals(type())) {
				query.append("&start=").append(start());
			} else if ("vimeo".equals(type())) {
				query.append("#t=").append(start());
			}
		}

		return query.toString();
	}

	public String allowAttribute() {
		StringBuilder allowAttribute = new StringBuilder();

		if (fullscreen()) {
			allowAttribute.append("fullscreen;");
		}
		if (autoplay()) {
			allowAttribute.append("autoplay;");
		}

		return allowAttribute.toString();
	}

	public String extras() {
		StringBuilder extras = new StringBuilder();

		if (fullscreen()) {
			extras.append(" allowfullscreen=\"allowfullscreen\" ");
		}

		return extras.toString();
	}
}
