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
import com.condation.cms.api.SiteProperties;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 *
 * @author t.marx
 */
public record Video(Map<String, Object> parameters, SiteProperties siteProperties) {

	private static final String DEFAULT_RATIO = "16:9";
	private static final Set<String> SUPPORTED_RATIOS = Set.of(DEFAULT_RATIO, "4:3", "1:1");

	public String type() {
		return (String) parameters.getOrDefault("type", "");
	}

	public String id() {
		return (String) parameters.getOrDefault("id", "");
	}

	public String title() {
		return (String) parameters.getOrDefault("title", "");
	}

	public String ratio() {
		Object value = parameters.get("ratio");
		if (value instanceof String ratio && SUPPORTED_RATIOS.contains(ratio)) {
			return ratio;
		}
		return DEFAULT_RATIO;
	}

	public String aspectRatio() {
		return ratio().replace(":", " / ");
	}

    public boolean dnt () {
        if (parameters.containsKey("dnt")) {
            return (boolean)parameters.get("dnt");
        }
        return (boolean)siteProperties.getOrDefault("video.dnt", false);
    }

    public boolean nocookie () {
        if (parameters.containsKey("nocookie")) {
            return (boolean)parameters.get("nocookie");
        }
        return (boolean)siteProperties.getOrDefault("video.nocookie", false);
    }
    
    public boolean consent() {
		if (parameters.containsKey("consent")) {
            return (boolean)parameters.get("consent");
        }
        return (boolean)siteProperties.getOrDefault("video.consent", false);
	}
    
	public boolean fullscreen() {
		return getValueOrDefault("fullscreen", false, Boolean::parseBoolean);
	}

	public boolean autoplay() {
		return getValueOrDefault("autoplay", false, Boolean::parseBoolean);
	}

	public boolean controls() {
		return getValueOrDefault("controls", true, Boolean::parseBoolean);
	}
	
	public boolean muted() {
		return getValueOrDefault("muted", true, Boolean::parseBoolean);
	}

	public boolean loop() {
		return getValueOrDefault("loop", false, Boolean::parseBoolean);
	}

	public String start() {
		Object value = parameters.getOrDefault("start", null);
		if (value instanceof String stringValue) {
			return stringValue;
		} else if (value instanceof Integer intValue) {
			return "%d".formatted(intValue);
		}
		
		return null;
	}
	
	

	public String thumbnail() {
		return (String) parameters.getOrDefault("thumbnail", "");
	}
	
	private <T> T getValueOrDefault (String name, T defaultValue, Function<String, T> parserFunction) {
		Object value = parameters.getOrDefault(name, defaultValue);
		if (value instanceof String stringValue) {
			return parserFunction.apply(stringValue);
		}
		return (T)value;
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
