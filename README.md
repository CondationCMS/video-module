# Video module

This modules offer youtube and vidmeo video integrations for the CondationCMS

## Integrate via shortcode

### In markdown 

This example integrates a youtube video in markdown.

```markdown
[[video type="youtube" id="y0sF5xhGreA" title="Everybody loves little cats" /]]
```

### In Template code

This example integrates a youtube video template code via calling the shortcode. 
For the example thymleaf template was uses.

```html
[(${shortCodes.call('video', #{'type' : 'youtube', 'id': 'y0sF5xhGreA', 'title' : 'Everybody loves little cats'})})]
```

### Use an thumbnail and click to play

This example integrates a youtube video template code with and thumbnail preview and click to play.
The youtube iframe is created on click via javascript.

```html
[(${shortCodes.call('video', #{'type' : 'youtube', 'id': 'y0sF5xhGreA', 'title' : 'Everybody loves little cats', 
'overlay' : true, 'thumbnail': '/assets/thumbnails/mountains.jpg', 'autoplay': 'true'})})]
```

## ShortCode parameters

| Parameter | Possible values | Default value |
| ------------- | ------------- | ------------- |
| type | youtube or vimeo  | "" |
| id | The id of the video  | "" |
| title | The video title | "" |
| fullscreen | allow fullscreen | false |
| autoplay | enable autoplay | false |
| controls | show controls | true |
| muted | play video muted | false |
| loop | play video in loop | false |
| start | the time to start the | for youtube number in seconds like '60', for vimeo a string like '1m2s' |
| overlay | enable the thumbnail and only integrate the video on click | false |
| thumbnail | the url of the thumbnail | "" |