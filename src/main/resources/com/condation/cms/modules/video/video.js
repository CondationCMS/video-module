const getQueryParameters = (element) => {
	var queryParameters = ""
	queryParameters += "autoplay="
	queryParameters += element.dataset.videoAutoplay === "true" ? 1 : 0;

	queryParameters += "&loop="
	queryParameters += element.dataset.videoLoop === "true" ? 1 : 0;

	queryParameters += "&controls="
	queryParameters += element.dataset.videoControls === "true" ? 1 : 0;

	var type = element.dataset.videoType;
	if (type === "youtube") {
		queryParameters += "&mute="
	} else if (type === "vimeo") {
		queryParameters += "&muted="
	}
	queryParameters += element.dataset.videoMuted === "true" ? 1 : 0;

	return queryParameters;
}

const loadYoutubeVideo = (element) => {
	var iframe = document.createElement("iframe");
	iframe.setAttribute("src", "https://www.youtube.com/embed/" + element.getAttribute("data-video-id") + "?" + getQueryParameters(element));
	iframe.setAttribute("frameborder", "0");
	iframe.setAttribute("style", "width: 100%; height: 100%");
	iframe.setAttribute("title", element.getAttribute("data-video-title"));
	//element.insertBefore(iframe, element.querySelector(".play"));
	element.querySelector(".play").replaceWith(iframe);
};

const loadVimeoVideo = (element) => {
	var iframe = document.createElement("iframe");
	iframe.setAttribute("src", "https://player.vimeo.com/video/" + element.getAttribute("data-video-id") + "?" + getQueryParameters(element));
	iframe.setAttribute("frameborder", "0");
	iframe.setAttribute("style", "width: 100%; height: 100%");
	iframe.setAttribute("title", element.getAttribute("data-video-title"));
	//element.insertBefore(iframe, element.querySelector(".play"));
	element.querySelector(".play").replaceWith(iframe);
};

document.addEventListener("DOMContentLoaded", () => {
	document.querySelectorAll("div[data-video-type]").forEach(function (element) {
		element.querySelector(".play").addEventListener("click", (event) => {
			event.preventDefault();
			var type = element.dataset.videoType;
			if (type === "youtube") {
				loadYoutubeVideo(element)
			} else if (type === "vimeo") {
				loadVimeoVideo(element)
			}
		});
	});
})