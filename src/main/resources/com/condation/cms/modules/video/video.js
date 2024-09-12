const loadYoutubeVideo = (element) => {
	var iframe = document.createElement("iframe");
	iframe.setAttribute("src", "https://www.youtube.com/embed/" + element.getAttribute("data-video-id") + "?autoplay=1");
	iframe.setAttribute("frameborder", "0");
	iframe.setAttribute("style", "width: 100%; height: 100%");
	iframe.setAttribute("title", element.getAttribute("data-video-title"));
	//element.insertBefore(iframe, element.querySelector(".play"));
	element.querySelector(".play").replaceWith(iframe);
};

const loadVimeoVideo = (element) => {
	var iframe = document.createElement("iframe");
	iframe.setAttribute("src", `https://player.vimeo.com/video/${element.getAttribute("data-video-id")}?badge=0&amp;autopause=0&amp;player_id=0`);
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
			console.log("clicked", type);
			if (type === "youtube") {
				loadYoutubeVideo(element)
			} else if (type === "vimeo") {
				loadVimeoVideo(element)
			}
		});
	});
})