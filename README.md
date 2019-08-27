## Templates to tests

#### API Documentation

1. Change pom version
2. Run deploy-and-push.sh
3. Run push.sh
4. Update Portainer

Configuration based in: `https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api`

Dev access at: 
    `http://localhost:8090/v2/api-docs`, 
    `http://localhost:8090/swagger-ui.html`

1. `http://[app-host]:[port]/v2/api-docs`       
2. `http://[app-host]:[port]/swagger-ui.html`
3. `http://[app-host]:[port]/api/swagger-ui.html`

Homologation access at: 
1. `http://terrabrasilis2.dpi.inpe.br:13003/swagger-ui.html`, 
2. `http://terrabrasilis2.dpi.inpe.br:13003/v2/api-docs`, 
3. `http://terrabrasilis2.dpi.inpe.br:13003/api/v1/vision/name/desforestation/all`

#### Relationship

Using NoSQL database, we can use save the collection we all information is need from that collection or use an
annotation `@DBRef` to refer to one other collection.

These API use both of them. 

#### Cache

The API is using Cache to improve the performance when consume some data.
The resources configured to manage the cache are:

1. `@CachePut("deforestation")` - `POST /api/v1/vision-to-vision` - When save new data, the cache will be updated
2. `@Cacheable("deforestation")` - `GET /api/v1/vision/name/{name}/all` - When request a vision by name, the data will be cached

Based in: `https://www.baeldung.com/spring-boot-evict-cache`

#### Application monitor

This application is using the SpringBoot Actuator module. It allow monitoring and managing the app in production mode.

Path: `[host]/management`

Health: `[path]/health`

Info: `[path]/info`

Metrics: `[path]/metrics` (this endpoint delivery the names of `metrics` you want to see, so, after, you can use 
with this way: `[host]/metrics/{name}`)

#### Subdomain

##### Create

`POST  /batch : register the subdomain from list of Subdomains.`

```json
[
    {
        "name": "mt0",
        "description": "Google MT0 subdomain",
        "domain": "mt0",
        "enabled": true
    },
    {
        "name": "mt1",
        "description": "Google MT1 subdomain",
        "domain": "mt1",
        "enabled": true
    },
    {
        "name": "mt2",
        "description": "Google MT2 subdomain",
        "domain": "mt2",
        "enabled": true
    },
    {
        "name": "mt3",
        "description": "Google MT3 subdomain",
        "domain": "mt3",
        "enabled": true
    }
]
```

##### Select

`GET  /all : retrieve all subdomains.`

```json
[
    {
        "id": "5c40b5760e9b2a0e7553750a",
        "name": "name",
        "description": "description",
        "domain": "domain",
        "enabled": true,
        "created": "2019-01-11 22:48:15"
    }
]
```

#### Tool

##### Create 

```json
{
    "name": "Transparency",
    "description": "This tool allow increase and decrease the layer transparency",
    "tag": "<dfn attr.data-info=\"{{ 'tools.transparency' | translate }}\"><button class=\"btn\" type=\"button\" data-toggle=\"collapse\" attr.href=\"#{{(layer.name | cleanWhiteSpace) + '_opacity'}}\" role=\"button\" aria-expanded=\"false\" attr.aria-controls=\"{{(layer.name | cleanWhiteSpace) + '_opacity'}}\"><i class=\"material-icons md-dark\">brightness_6</i></button></dfn>",
    "target": "fe-camadas",
    "enabled": true
}
```
`POST: /batch`

```json
[
	{
	    "name": "Basic Layer Info",
	    "description": "This tool allow to see the basic info about layer",
	    "tag": "<dfn attr.data-info=\"{{ 'tools.basicInfo' | translate }}\"><button type=\"button\" class=\"btn\" (click)=\"getBasicLayerInfo(layer)\"><i class=\"material-icons md-dark\">info</i></button></dfn>",
	    "target": "fe-camadas",
	    "enabled": true
	},
	{
	    "name": "Remove Layer",
	    "description": "This tool allow to remove layer",
	    "tag": "<dfn attr.data-info=\"{{ 'tools.removeLayer' | translate }}\"><button type=\"button\" class=\"btn\" *ngIf=\"layer.isRemovable\" (click)=\"removeLayer(layer, project)\"><i class=\"material-icons md-dark\">delete</i></button></dfn>",
	    "target": "fe-camadas",
	    "enabled": true
	},
	{
	    "name": "Download Data",
	    "description": "This tool allow perform download data",
	    "tag": "<dfn attr.data-info=\"{{ 'tools.download' | translate }}\"><button type=\"button\" class=\"btn\" *ngIf=\"layer.download != null\" (click)=\"download(layer)\"><i class=\"material-icons md-dark\">cloud_download</i></button></dfn>",
	    "target": "fe-camadas",
	    "enabled": true
	},
	{
	    "name": "Metadata to Layer",
	    "description": "This tool allow perform got to the metadata of layer",
	    "tag": "<dfn attr.data-info=\"{{ 'tools.metadata' | translate }}\"><button type=\"button\" class=\"btn\" *ngIf=\"layer.metadata != null\" (click)=\"goTo(layer.metadata)\"><i class=\"material-icons md-dark\">assignment</i></button></dfn>",
	    "target": "fe-camadas",
	    "enabled": true
	},
	{
	    "name": "Data Dashboard of the Layer",
	    "description": "This tool allow to see the data dashboard",
	    "tag": "<dfn attr.data-info=\"{{ 'tools.dashboard' | translate }}\"><button type=\"button\" class=\"btn\" *ngIf=\"layer.dashboard != null\" (click)=\"goTo(layer.dashboard)\"><i class=\"material-icons md-dark\">assessment</i></button></dfn>",
	    "target": "fe-camadas",
	    "enabled": true
	},
	{
	    "name": "Legend of the Layer",
	    "description": "This tool allow to see the legend of layer",
	    "tag": "<dfn attr.data-info=\"{{ 'tools.legend' | translate }}\"><button type=\"button\" class=\"btn\" (click)=\"showDialog(getLegend(layer, true))\"><i class=\"material-icons md-dark\">image</i></button></dfn>",
	    "target": "fe-camadas",
	    "enabled": false
	}
]
``` 

##### Select

```json
[
    {
	    "id": "5c5476e9e16438142572c47f",
	    "name": "Transparency",
	    "description": "This tool allow increase and decrease the layer transparency",
	    "tag": "<dfn attr.data-info=\"{{ 'tools.transparency' | translate }}\"><button class=\"btn\" type=\"button\" data-toggle=\"collapse\" attr.href=\"#{{(layer.name | cleanWhiteSpace) + '_opacity'}}\" role=\"button\" aria-expanded=\"false\" attr.aria-controls=\"{{(layer.name | cleanWhiteSpace) + '_opacity'}}\"><i class=\"material-icons md-dark\">brightness_6</i></button></dfn>",
	    "target": "fe-camadas",
	    "enabled": true,
	    "created": "2019-02-01 14:42:16"
	}
]
```

#### Vision

##### Create

`POST / : insert vision.`

```json
{
    "name": "desforestation",
    "description": "The main vision to desforestation monitoring projects like Proces AMZ and Cerrado",
    "stackOrder": 1,
    "enabled": true
}
```

```json
{
    "name": "Prodes AMZ",
    "description": "Prodes AMZ Vision",
    "stackOrder": 2,
    "enabled": true,
    "layers": [
		{
    		"id" : "5c40b6930e9b2a0e7553750d"
		},
		{
    		"id" : "5c40b7510e9b2a0e7553750e"
		}
	],
	"tools": [
		{
			"id": "5c40b5a70e9b2a0e7553750b"
		}
	]
}

```
`PUT /{Vision} : update vision.`

```json
{
	"id": "5c40fda10e9b2a14264e03a5",
	"layers": [
		{
    		"id" : "5c489de6bbfeb44c9df6aa60"
		},
		{
    		"id" : "5c489f56bbfeb44c9df6aa61"
		},
		{
    		"id" : "5c489f8dbbfeb44c9df6aa62"
		},
		{
    		"id" : "5c489fa1bbfeb44c9df6aa63"
		},
		{
    		"id" : "5c48a2c8bbfeb44c9df6aa67"
		},
		{
    		"id" : "5c48a316bbfeb44c9df6aa68"
		},
		{
    		"id" : "5c48a346bbfeb44c9df6aa69"
		}
	]
}
```

```
`PUT /{Vision} : update vision.`

```json
{
	"id": "5c40fe3a0e9b2a14264e03a7",
	"downloads": [
		{
    		"id" : "5c76c940169e6c00010cf005"
		},
		{
    		"id" : "5c76c940169e6c00010cf006"
		},
		{
    		"id" : "5c76c940169e6c00010cf007"
		}
	]
}
```

##### Select 

```json
[
    {
        "id": "5c40b8900e9b2a0e75537511",
        "name": "desforestation",
        "description": "The main vision to desforestation monitoring projects like Proces AMZ and Cerrado",
        "stackOrder": 1,
        "enabled": true,
        "created": "2019-01-17 15:17:04",
        "root": null,
        "children": [],
        "tools": [],
        "layers": []
    }
]
```

```json
[
    {
        "id": "5c40fda10e9b2a14264e03a5",
        "name": "desforestation",
        "description": "The main vision to desforestation monitoring projects like Proces AMZ and Cerrado",
        "stackOrder": 1,
        "enabled": true,
        "created": "2019-01-17 20:11:45",
        "tools": [],
        "layers": []
    },
    {
        "id": "5c40fdce0e9b2a14264e03a6",
        "name": "Prodes AMZ",
        "description": "Prodes AMZ Vision",
        "stackOrder": 2,
        "enabled": true,
        "created": "2019-01-17 20:12:30",
        "tools": [
            {
                "id": "5c40b5a70e9b2a0e7553750b",
                "name": "Fullscreen",
                "description": "This tool, after used, make the screen fullscreen",
                "tag": "html to the tool",
                "target": "where this tag will be built",
                "enabled": true,
                "created": "2019-01-17 15:04:39"
            }
        ],
        "layers": [
            {
                "id": "5c40b6930e9b2a0e7553750d",
                "name": "layer",
                "title": "Layer 1",
                "description": "description",
                "attribution": "attribution",
                "workspace": "workspace",
                "capabilitiesUrl": "capabilitiesUrl",
                "stackOrder": 0,
                "opacity": 0.9,
                "baselayer": false,
                "active": true,
                "enabled": true,
                "created": "2019-01-17 15:08:35",
                "datasource": {
                    "id": "5c409e920e9b2a0b8424ef1b",
                    "name": "Prodes Amazonia",
                    "description": "Prodes Amazonia",
                    "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
                    "metadata": "",
                    "enabled": true,
                    "created": "2019-01-17 13:26:10",
                    "downloads": [],
                    "tools": []
                },
                "tools": [],
                "subdomains": []
            },
            {
                "id": "5c40b7510e9b2a0e7553750e",
                "name": "layer_2",
                "title": "Layer 2",
                "description": "description",
                "attribution": "attribution",
                "workspace": "workspace",
                "capabilitiesUrl": "capabilitiesUrl",
                "stackOrder": 0,
                "opacity": 0.9,
                "baselayer": false,
                "active": true,
                "enabled": true,
                "created": "2019-01-17 15:11:44",
                "datasource": {
                    "id": "5c409e920e9b2a0b8424ef1b",
                    "name": "Prodes Amazonia",
                    "description": "Prodes Amazonia",
                    "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
                    "metadata": "",
                    "enabled": true,
                    "created": "2019-01-17 13:26:10",
                    "downloads": [],
                    "tools": []
                },
                "tools": [
                    {
                        "id": "5c40b5a70e9b2a0e7553750b",
                        "name": "Fullscreen",
                        "description": "This tool, after used, make the screen fullscreen",
                        "tag": "html to the tool",
                        "target": "where this tag will be built",
                        "enabled": true,
                        "created": "2019-01-17 15:04:39"
                    },
                    {
                        "id": "5c40b5d40e9b2a0e7553750c",
                        "name": "Transparency",
                        "description": "This tool allow increase and decrease the layer transparency",
                        "tag": "html to the tool",
                        "target": "where this tag will be built",
                        "enabled": true,
                        "created": "2019-01-17 15:05:24"
                    }
                ],
                "subdomains": [
                    {
                        "id": "5c40b5760e9b2a0e7553750a",
                        "name": "subdominio1",
                        "description": "subdominio1",
                        "domain": "domain",
                        "enabled": true,
                        "created": "2019-01-17 15:03:50"
                    }
                ]
            }
        ]
    }
]
```

#### VisionDTO

##### Create

```json
{
    "root": {
        "id": "5c40fda10e9b2a14264e03a5"
    },
    "visions": [
        {
            "id": "5c40fdce0e9b2a14264e03a6"
        },
        {
            "id": "5c40fe3a0e9b2a14264e03a7"
        }
    ]
}
```

##### Select

```json
[
    {
        "visions": [
            {
                "id": "5c40fdce0e9b2a14264e03a6",
                "name": "Prodes AMZ",
                "description": "Prodes AMZ Vision",
                "stackOrder": 2,
                "enabled": true,
                "created": "2019-01-17 22:12:30",
                "tools": [
                    {
                        "id": "5c40b5a70e9b2a0e7553750b",
                        "name": "Fullscreen",
                        "description": "This tool, after used, make the screen fullscreen",
                        "tag": "html to the tool",
                        "target": "where this tag will be built",
                        "enabled": true,
                        "created": "2019-01-17 17:04:39"
                    }
                ],
                "layers": [
                    {
                        "id": "5c40b6930e9b2a0e7553750d",
                        "name": "layer",
                        "title": "Layer 1",
                        "description": "description",
                        "attribution": "attribution",
                        "workspace": "workspace",
                        "capabilitiesUrl": "capabilitiesUrl",
                        "stackOrder": 0,
                        "opacity": 0.9,
                        "baselayer": false,
                        "active": true,
                        "enabled": true,
                        "created": "2019-01-17 17:08:35",
                        "datasource": {
                            "id": "5c409e920e9b2a0b8424ef1b",
                            "name": "Prodes Amazonia",
                            "description": "Prodes Amazonia",
                            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
                            "metadata": "",
                            "enabled": true,
                            "created": "2019-01-17 15:26:10",
                            "downloads": [],
                            "tools": []
                        },
                        "tools": [],
                        "subdomains": []
                    },
                    {
                        "id": "5c40b7510e9b2a0e7553750e",
                        "name": "layer_2",
                        "title": "Layer 2",
                        "description": "description",
                        "attribution": "attribution",
                        "workspace": "workspace",
                        "capabilitiesUrl": "capabilitiesUrl",
                        "stackOrder": 0,
                        "opacity": 0.9,
                        "baselayer": false,
                        "active": true,
                        "enabled": true,
                        "created": "2019-01-17 17:11:44",
                        "datasource": {
                            "id": "5c409e920e9b2a0b8424ef1b",
                            "name": "Prodes Amazonia",
                            "description": "Prodes Amazonia",
                            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
                            "metadata": "",
                            "enabled": true,
                            "created": "2019-01-17 15:26:10",
                            "downloads": [],
                            "tools": []
                        },
                        "tools": [
                            {
                                "id": "5c40b5a70e9b2a0e7553750b",
                                "name": "Fullscreen",
                                "description": "This tool, after used, make the screen fullscreen",
                                "tag": "html to the tool",
                                "target": "where this tag will be built",
                                "enabled": true,
                                "created": "2019-01-17 17:04:39"
                            },
                            {
                                "id": "5c40b5d40e9b2a0e7553750c",
                                "name": "Transparency",
                                "description": "This tool allow increase and decrease the layer transparency",
                                "tag": "html to the tool",
                                "target": "where this tag will be built",
                                "enabled": true,
                                "created": "2019-01-17 17:05:24"
                            }
                        ],
                        "subdomains": [
                            {
                                "id": "5c40b5760e9b2a0e7553750a",
                                "name": "subdominio1",
                                "description": "subdominio1",
                                "domain": "domain",
                                "enabled": true,
                                "created": "2019-01-17 17:03:50"
                            }
                        ]
                    }
                ]
            },
            {
                "id": "5c40fe3a0e9b2a14264e03a7",
                "name": "Prodes Cerrado",
                "description": "Prodes Cerrado Vision",
                "stackOrder": 2,
                "enabled": true,
                "created": "2019-01-17 22:14:18",
                "tools": [
                    {
                        "id": "5c40b5a70e9b2a0e7553750b",
                        "name": "Fullscreen",
                        "description": "This tool, after used, make the screen fullscreen",
                        "tag": "html to the tool",
                        "target": "where this tag will be built",
                        "enabled": true,
                        "created": "2019-01-17 17:04:39"
                    }
                ],
                "layers": [
                    {
                        "id": "5c40b6930e9b2a0e7553750d",
                        "name": "layer",
                        "title": "Layer 1",
                        "description": "description",
                        "attribution": "attribution",
                        "workspace": "workspace",
                        "capabilitiesUrl": "capabilitiesUrl",
                        "stackOrder": 0,
                        "opacity": 0.9,
                        "baselayer": false,
                        "active": true,
                        "enabled": true,
                        "created": "2019-01-17 17:08:35",
                        "datasource": {
                            "id": "5c409e920e9b2a0b8424ef1b",
                            "name": "Prodes Amazonia",
                            "description": "Prodes Amazonia",
                            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
                            "metadata": "",
                            "enabled": true,
                            "created": "2019-01-17 15:26:10",
                            "downloads": [],
                            "tools": []
                        },
                        "tools": [],
                        "subdomains": []
                    },
                    {
                        "id": "5c40b7510e9b2a0e7553750e",
                        "name": "layer_2",
                        "title": "Layer 2",
                        "description": "description",
                        "attribution": "attribution",
                        "workspace": "workspace",
                        "capabilitiesUrl": "capabilitiesUrl",
                        "stackOrder": 0,
                        "opacity": 0.9,
                        "baselayer": false,
                        "active": true,
                        "enabled": true,
                        "created": "2019-01-17 17:11:44",
                        "datasource": {
                            "id": "5c409e920e9b2a0b8424ef1b",
                            "name": "Prodes Amazonia",
                            "description": "Prodes Amazonia",
                            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
                            "metadata": "",
                            "enabled": true,
                            "created": "2019-01-17 15:26:10",
                            "downloads": [],
                            "tools": []
                        },
                        "tools": [
                            {
                                "id": "5c40b5a70e9b2a0e7553750b",
                                "name": "Fullscreen",
                                "description": "This tool, after used, make the screen fullscreen",
                                "tag": "html to the tool",
                                "target": "where this tag will be built",
                                "enabled": true,
                                "created": "2019-01-17 17:04:39"
                            },
                            {
                                "id": "5c40b5d40e9b2a0e7553750c",
                                "name": "Transparency",
                                "description": "This tool allow increase and decrease the layer transparency",
                                "tag": "html to the tool",
                                "target": "where this tag will be built",
                                "enabled": true,
                                "created": "2019-01-17 17:05:24"
                            }
                        ],
                        "subdomains": [
                            {
                                "id": "5c40b5760e9b2a0e7553750a",
                                "name": "subdominio1",
                                "description": "subdominio1",
                                "domain": "domain",
                                "enabled": true,
                                "created": "2019-01-17 17:03:50"
                            }
                        ]
                    }
                ]
            }
        ],
        "vision": {
            "id": "5c40fda10e9b2a14264e03a5",
            "name": "desforestation",
            "description": "The main vision to desforestation monitoring projects like Proces AMZ and Cerrado",
            "stackOrder": 1,
            "enabled": true,
            "created": "2019-01-17 22:11:45",
            "tools": [],
            "layers": []
        }
    }
]
```

#### Datasource

##### Create


`POST  / : register the datasource`

```json
{
    "name": "Deter Amazonia",
    "description": "Deter Amazonia",
    "host": "http://terrabrasilis.info/deterb/ows",
    "metadata": "",
    "enabled": true,
    "downloads": [
    		{
    			"id": "download_id"
    		},
    		{
    			"id": "download_id"
    		}
    ]
}
```

`POST  /batch : register the datasource from list of Datasource` 

```json
[
    {
	    "name": "Google Satellite",
	    "description": "Google Satellite",
	    "host": "http://{s}.google.com/vt/lyrs=s&x={x}&y={y}&z={z}",
	    "metadata": "",
	    "enabled": true
    },
    {
	    "name": "Google Hybrid",
	    "description": "Google Hybrid",
	    "host": "http://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}",
	    "metadata": "",
	    "enabled": true
    },
    {
	    "name": "Google Streets",
	    "description": "Google Streets",
	    "host": "http://{s}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}",
	    "metadata": "",
	    "enabled": true
    },
    {
	    "name": "Google Terrain",
	    "description": "Google Terrain",
	    "host": "http://{s}.google.com/vt/lyrs=p&x={x}&y={y}&z={z}",
	    "metadata": "",
	    "enabled": true
    }
]

[
    {
	    "name": "OSM",
	    "description": "Open Street Map",
	    "host": "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
	    "metadata": "",
	    "enabled": true
    },
    {
	    "name": "OSM Black",
	    "description": "Open Street Map Black Style",
	    "host": "http://{s}.tiles.wmflabs.org/bw-mapnik/{z}/{x}/{y}.png",
	    "metadata": "",
	    "enabled": true
    },
    {
	    "name": "OSM Topo",
	    "description": "Open Street Map Topo",
	    "host": "https://{s}.tile.opentopomap.org/{z}/{x}/{y}.png",
	    "metadata": "",
	    "enabled": true
    }
]
``` 

```json
[
    {
        "name": "Deter/Prodes Cerrado",
        "description": "Deter/Prodes Cerrado",
        "host": "http://terrabrasilis.info/fip-service/ows",
        "metadata": "",
        "enabled": true
    },
    {
        "name": "Prodes Amazonia",
        "description": "Prodes Amazonia",
        "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
        "metadata": "",
        "enabled": true
    },
    {
        "name": "TerraAmazon",
        "description": "TerraAmazon",
        "host": "http://terrabrasilis.info/terraamazon/ows",
        "metadata": "",
        "enabled": true
    },
    {
        "name": "IBAMA",
        "description": "IBAMA",
        "host": "http://siscom.ibama.gov.br/geoserver/ows",
        "metadata": "",
        "enabled": true
    },
    {
        "name": "IBGE",
        "description": "IBGE",
        "host": "http://www.geoservicos.ibge.gov.br/geoserver/ows",
        "metadata": "",
        "enabled": true
    }
]
```

##### Select 

`GET /all : retrieve all datasources` 

```json
[
    {
        "id": "5c4097e20e9b2a088356bd95",
        "name": "Deter Amazonia",
        "description": "Deter Amazonia",
        "host": "http://terrabrasilis.info/deterb/ows",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-17 12:57:38",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c409e1e0e9b2a0b8424ef1a",
        "name": "Deter/Prodes Cerrado",
        "description": "Deter/Prodes Cerrado",
        "host": "http://terrabrasilis.info/fip-service/ows",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-17 13:24:14",
        "downloads": [
            null
        ],
        "tools": []
    },
    {
        "id": "5c409e920e9b2a0b8424ef1b",
        "name": "Prodes Amazonia",
        "description": "Prodes Amazonia",
        "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-17 13:26:10",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c409f870e9b2a0b8424ef1e",
        "name": "TerraAmazon",
        "description": "TerraAmazon",
        "host": "http://terrabrasilis.info/terraamazon/ows",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-17 13:30:15",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c409fb50e9b2a0b8424ef1f",
        "name": "IBAMA",
        "description": "IBAMA",
        "host": "http://siscom.ibama.gov.br/geoserver/ows",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-17 13:31:01",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c409fcb0e9b2a0b8424ef20",
        "name": "IBGE",
        "description": "IBGE",
        "host": "http://www.geoservicos.ibge.gov.br/geoserver/ows",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-17 13:31:23",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c489b1ebbfeb44c9df6aa5c",
        "name": "Google Satellite",
        "description": "Google Satellite",
        "host": "http://{s}.google.com/vt/lyrs=s&x={x}&y={y}&z={z}",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-23 14:49:34",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c489b1ebbfeb44c9df6aa5d",
        "name": "Google Hybrid",
        "description": "Google Hybrid",
        "host": "http://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-23 14:49:34",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c489b1ebbfeb44c9df6aa5e",
        "name": "Google Streets",
        "description": "Google Streets",
        "host": "http://{s}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-23 14:49:34",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c489b1ebbfeb44c9df6aa5f",
        "name": "Google Terrain",
        "description": "Google Terrain",
        "host": "http://{s}.google.com/vt/lyrs=p&x={x}&y={y}&z={z}",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-23 14:49:34",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c48a23cbbfeb44c9df6aa64",
        "name": "OSM",
        "description": "Open Street Map",
        "host": "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-23 15:19:56",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c48a23cbbfeb44c9df6aa65",
        "name": "OSM Black",
        "description": "Open Street Map Black Style",
        "host": "http://{s}.tiles.wmflabs.org/bw-mapnik/{z}/{x}/{y}.png",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-23 15:19:56",
        "downloads": [],
        "tools": []
    },
    {
        "id": "5c48a23cbbfeb44c9df6aa66",
        "name": "OSM Topo",
        "description": "Open Street Map Topo",
        "host": "https://{s}.tile.opentopomap.org/{z}/{x}/{y}.png",
        "metadata": "",
        "enabled": true,
        "created": "2019-01-23 15:19:56",
        "downloads": [],
        "tools": []
    }
]
```

#### Download

##### Create

```json
[
	{
	    "name": "Prodes Cerrado 2000_2017_v20180625",
	    "description": "Arquivo completo em formato shapefile para o Bioma Cerrado.",
	    "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/vector/prodes_cerrado_2000_2017_v20180625.zip",
	    "enabled": true
	},
	{
	    "name": "Prodes Cerrado Orbita Ponto Landsat 2000_2017_v201806226",
	    "description": "Arquivo com a rela&ccedil;&atilde;o de &Oacute;rbita/Ponto Landsat utilizada na interpreta&ccedil;&atilde;o.",
	    "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/prodes_cerrado_orbita_ponto_landsat_2000_2017_v201806226.zip",
	    "enabled": true
	},
	{
	    "name": "Prodes Cerrado 2000_2018_v20181211",
	    "description": "Arquivo completo em formato raster para o Bioma Cerrado.",
	    "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/raster/prodes_cerrado_2000_2018_v20181211.zip",
	    "enabled": true
	},
	{
	    "name": "Estados Cerrado",
	    "description": "Arquivo completo em formato shapefile com estados do Cerrado.",
	    "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/estados_cerrado.zip",
	    "enabled": true
	},
	{
	    "name": "Limite Cerrado",
	    "description": "Arquivo completo em formato shapefile do limite do Cerrado.",
	    "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/limite_cerrado.zip",
	    "enabled": true
	},
	{
	    "name": "Deter Cerrado",
	    "description": "Arquivo completo em formato shapefile de alertas no Cerrado.",
	    "link": "http://terrabrasilis.dpi.inpe.br/download/deter-cerrado/deter-cerrado_all.zip",
	    "enabled": true
	},
	{
	    "name": "Municípios Cerrado",
	    "description": "Arquivo completo em formato shapefile dos municípios do Cerrado.",
	    "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/municipio_cerrado.zip",
	    "enabled": true
	}
]
```

##### Select 

```json
[
    {
        "id": "5c76c940169e6c00010cf005",
        "name": "Prodes Cerrado 2000_2017_v20180625",
        "description": "Arquivo completo em formato shapefile para o Bioma Cerrado.",
        "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/vector/prodes_cerrado_2000_2017_v20180625.zip",
        "enabled": true,
        "created": "2019-02-27 17:30:40"
    },
    {
        "id": "5c76c940169e6c00010cf006",
        "name": "Prodes Cerrado Orbita Ponto Landsat 2000_2017_v201806226",
        "description": "Arquivo com a rela&ccedil;&atilde;o de &Oacute;rbita/Ponto Landsat utilizada na interpreta&ccedil;&atilde;o.",
        "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/prodes_cerrado_orbita_ponto_landsat_2000_2017_v201806226.zip",
        "enabled": true,
        "created": "2019-02-27 17:30:40"
    },
    {
        "id": "5c76c940169e6c00010cf007",
        "name": "Prodes Cerrado 2000_2018_v20181211",
        "description": "Arquivo completo em formato raster para o Bioma Cerrado.",
        "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/raster/prodes_cerrado_2000_2018_v20181211.zip",
        "enabled": true,
        "created": "2019-02-27 17:30:40"
    },
    {
        "id": "5c76c940169e6c00010cf008",
        "name": "Estados Cerrado",
        "description": "Arquivo completo em formato shapefile com estados do Cerrado.",
        "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/estados_cerrado.zip",
        "enabled": true,
        "created": "2019-02-27 17:30:40"
    },
    {
        "id": "5c76ca0f169e6c00010cf00d",
        "name": "Limite Cerrado",
        "description": "Arquivo completo em formato shapefile do limite do Cerrado.",
        "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/limite_cerrado.zip",
        "enabled": true,
        "created": "2019-02-27 17:34:07"
    },
    {
        "id": "5c76ca0f169e6c00010cf00e",
        "name": "Deter Cerrado",
        "description": "Arquivo completo em formato shapefile de alertas no Cerrado.",
        "link": "http://terrabrasilis.dpi.inpe.br/download/deter-cerrado/deter-cerrado_all.zip",
        "enabled": true,
        "created": "2019-02-27 17:34:07"
    },
    {
        "id": "5c76ca0f169e6c00010cf00f",
        "name": "Municípios Cerrado",
        "description": "Arquivo completo em formato shapefile dos municípios do Cerrado.",
        "link": "http://terrabrasilis.dpi.inpe.br/download/prodes-cerrado/municipio_cerrado.zip",
        "enabled": true,
        "created": "2019-02-27 17:34:07"
    }
]
```

#### Layer

##### Create
```json
{
    "name": "Google Satellite",
    "title": "Google Satellite",
    "description": "",
    "attribution": "",
    "workspace": "",
    "capabilitiesUrl": "",
    "stackOrder": 0,
    "opacity": 1,
    "baselayer": true,
    "active": true,
    "enabled": true,
    "datasource": {
    	"id": "5c489b1ebbfeb44c9df6aa5c"
    },
    "subdomains": [
    	{
    		"id": "5c488594bbfeb4108d34da19"
    	},
    	{
    		"id": "5c488594bbfeb4108d34da1a"
    	},
    	{
    		"id": "5c488594bbfeb4108d34da1b"
    	},
    	{
    		"id": "5c488594bbfeb4108d34da1c"
    	}
    ]
}
```

```json
{
    "name": "name",
    "title": "title",
    "description": "description",
    "attribution": "attribution",
    "workspace": "workspace",
    "capabilitiesUrl": "capabilitiesUrl",
    "stackOrder": 0,
    "opacity": 0.9,
    "baselayer": false,
    "active": true,
    "enabled": true,
    "datasource": {
    		"id": "5c409e920e9b2a0b8424ef1b"
    }, 
    "subdomains": [
    		{
    			"id": "5c40b5760e9b2a0e7553750a"
    		},
    ],
    "tools": [
    		{
    			"id": "5c40b5a70e9b2a0e7553750b"
    		},
    		{
    			"id": "5c40b5d40e9b2a0e7553750c"
    		}
    ]
}
```

##### Select

```json
[
    {
        "id": "5c489de6bbfeb44c9df6aa60",
        "name": "Google Satellite",
        "title": "Google Satellite",
        "description": "",
        "attribution": "",
        "workspace": "",
        "capabilitiesUrl": "",
        "stackOrder": 0,
        "opacity": 1,
        "baselayer": true,
        "active": false,
        "enabled": true,
        "created": "2019-01-23 17:01:25",
        "datasource": {
            "id": "5c489b1ebbfeb44c9df6aa5c",
            "name": "Google Satellite",
            "description": "Google Satellite",
            "host": "https://{s}.google.com/vt/lyrs=s&x={x}&y={y}&z={z}",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-23 16:49:34",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": [
            {
                "id": "5c488594bbfeb4108d34da19",
                "name": "mt0",
                "description": "Google MT0 subdomain",
                "domain": "mt0",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1a",
                "name": "mt1",
                "description": "Google MT1 subdomain",
                "domain": "mt1",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1b",
                "name": "mt2",
                "description": "Google MT2 subdomain",
                "domain": "mt2",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1c",
                "name": "mt3",
                "description": "Google MT3 subdomain",
                "domain": "mt3",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            }
        ]
    },
    {
        "id": "5c489f56bbfeb44c9df6aa61",
        "name": "Google Hybrid",
        "title": "Google Hybrid",
        "description": "",
        "attribution": "",
        "workspace": "",
        "capabilitiesUrl": "",
        "stackOrder": 0,
        "opacity": 1,
        "baselayer": true,
        "active": true,
        "enabled": true,
        "created": "2019-01-23 17:07:33",
        "datasource": {
            "id": "5c489b1ebbfeb44c9df6aa5d",
            "name": "Google Hybrid",
            "description": "Google Hybrid",
            "host": "https://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-23 16:49:34",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": [
            {
                "id": "5c488594bbfeb4108d34da19",
                "name": "mt0",
                "description": "Google MT0 subdomain",
                "domain": "mt0",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1a",
                "name": "mt1",
                "description": "Google MT1 subdomain",
                "domain": "mt1",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1b",
                "name": "mt2",
                "description": "Google MT2 subdomain",
                "domain": "mt2",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1c",
                "name": "mt3",
                "description": "Google MT3 subdomain",
                "domain": "mt3",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            }
        ]
    },
    {
        "id": "5c489f8dbbfeb44c9df6aa62",
        "name": "Google Streets",
        "title": "Google Streets",
        "description": "",
        "attribution": "",
        "workspace": "",
        "capabilitiesUrl": "",
        "stackOrder": 0,
        "opacity": 1,
        "baselayer": true,
        "active": false,
        "enabled": true,
        "created": "2019-01-23 17:08:28",
        "datasource": {
            "id": "5c489b1ebbfeb44c9df6aa5e",
            "name": "Google Streets",
            "description": "Google Streets",
            "host": "https://{s}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-23 16:49:34",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": [
            {
                "id": "5c488594bbfeb4108d34da19",
                "name": "mt0",
                "description": "Google MT0 subdomain",
                "domain": "mt0",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1a",
                "name": "mt1",
                "description": "Google MT1 subdomain",
                "domain": "mt1",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1b",
                "name": "mt2",
                "description": "Google MT2 subdomain",
                "domain": "mt2",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1c",
                "name": "mt3",
                "description": "Google MT3 subdomain",
                "domain": "mt3",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            }
        ]
    },
    {
        "id": "5c489fa1bbfeb44c9df6aa63",
        "name": "Google Terrain",
        "title": "Google Terrain",
        "description": "",
        "attribution": "",
        "workspace": "",
        "capabilitiesUrl": "",
        "stackOrder": 0,
        "opacity": 1,
        "baselayer": true,
        "active": false,
        "enabled": true,
        "created": "2019-01-23 17:08:48",
        "datasource": {
            "id": "5c489b1ebbfeb44c9df6aa5f",
            "name": "Google Terrain",
            "description": "Google Terrain",
            "host": "https://{s}.google.com/vt/lyrs=p&x={x}&y={y}&z={z}",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-23 16:49:34",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": [
            {
                "id": "5c488594bbfeb4108d34da19",
                "name": "mt0",
                "description": "Google MT0 subdomain",
                "domain": "mt0",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1a",
                "name": "mt1",
                "description": "Google MT1 subdomain",
                "domain": "mt1",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1b",
                "name": "mt2",
                "description": "Google MT2 subdomain",
                "domain": "mt2",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            },
            {
                "id": "5c488594bbfeb4108d34da1c",
                "name": "mt3",
                "description": "Google MT3 subdomain",
                "domain": "mt3",
                "enabled": true,
                "created": "2019-01-23 15:17:40"
            }
        ]
    },
    {
        "id": "5c48a2c8bbfeb44c9df6aa67",
        "name": "OSM",
        "title": "OSM",
        "description": "Open Street Map",
        "attribution": "Map data &copy; <a href=\"http://openstreetmap.org\">OpenStreetMap</a> contributors, <a href=\"http://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>",
        "workspace": "",
        "capabilitiesUrl": "",
        "stackOrder": 0,
        "opacity": 1,
        "baselayer": true,
        "active": false,
        "enabled": true,
        "created": "2019-01-23 17:22:16",
        "datasource": {
            "id": "5c48a23cbbfeb44c9df6aa64",
            "name": "OSM",
            "description": "Open Street Map",
            "host": "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-23 17:19:56",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c48a316bbfeb44c9df6aa68",
        "name": "OSM Black",
        "title": "OSM Black",
        "description": "Open Street Map Black Style",
        "attribution": "Map data &copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a>",
        "workspace": "",
        "capabilitiesUrl": "",
        "stackOrder": 0,
        "opacity": 1,
        "baselayer": true,
        "active": false,
        "enabled": true,
        "created": "2019-01-23 17:23:34",
        "datasource": {
            "id": "5c48a23cbbfeb44c9df6aa65",
            "name": "OSM Black",
            "description": "Open Street Map Black Style",
            "host": "http://{s}.tiles.wmflabs.org/bw-mapnik/{z}/{x}/{y}.png",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-23 17:19:56",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c48a346bbfeb44c9df6aa69",
        "name": "OSM Topo",
        "title": "OSM Topo",
        "description": "Open Street Map Topo",
        "attribution": "Map data: &copy; <a href=\"http://www.openstreetmap.org/copyright\">OpenStreetMap</a>, <a href=\"http://viewfinderpanoramas.org\">SRTM</a> | Map style: &copy; <a href=\"https://opentopomap.org\">OpenTopoMap</a> (<a href=\"https://creativecommons.org/licenses/by-sa/3.0/\">CC-BY-SA</a>",
        "workspace": "",
        "capabilitiesUrl": "",
        "stackOrder": 0,
        "opacity": 1,
        "baselayer": true,
        "active": false,
        "enabled": true,
        "created": "2019-01-23 17:24:22",
        "datasource": {
            "id": "5c48a23cbbfeb44c9df6aa66",
            "name": "OSM Topo",
            "description": "Open Street Map Topo",
            "host": "https://{s}.tile.opentopomap.org/{z}/{x}/{y}.png",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-23 17:19:56",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f5901a21020001cd6637",
        "name": "forest",
        "title": "Forest 2016/2017",
        "description": "Forest 2016/2017",
        "attribution": "",
        "workspace": "prodes-amz",
        "capabilitiesUrl": "",
        "stackOrder": 1,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:27:43",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f5bc1a21020001cd6638",
        "name": "yearly_deforestation_2013_2018",
        "title": "AMZ Yearly Deforestation",
        "description": "AMZ Yearly Deforestation",
        "attribution": "",
        "workspace": "prodes-amz",
        "capabilitiesUrl": "",
        "stackOrder": 2,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:28:28",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f5df1a21020001cd6639",
        "name": "accumulated_deforestation_1988_2012",
        "title": "Deforestation Mask",
        "description": "Deforestation Mask",
        "attribution": "",
        "workspace": "prodes-amz",
        "capabilitiesUrl": "",
        "stackOrder": 3,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:29:03",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f61b1a21020001cd663a",
        "name": "hydrography",
        "title": "Hydrography",
        "description": "Hydrography",
        "attribution": "",
        "workspace": "prodes-amz",
        "capabilitiesUrl": "",
        "stackOrder": 4,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:30:03",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f6761a21020001cd663b",
        "name": "no_forest",
        "title": "No Forest",
        "description": "No Forest",
        "attribution": "",
        "workspace": "prodes-amz",
        "capabilitiesUrl": "",
        "stackOrder": 5,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:31:34",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f6a51a21020001cd663c",
        "name": "cloud",
        "title": "Cloud",
        "description": "Cloud",
        "attribution": "",
        "workspace": "prodes-amz",
        "capabilitiesUrl": "",
        "stackOrder": 6,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:32:21",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f6ba1a21020001cd663d",
        "name": "brazilian_legal_amazon",
        "title": "Legal Amazon",
        "description": "Legal Amazon",
        "attribution": "",
        "workspace": "prodes-amz",
        "capabilitiesUrl": "",
        "stackOrder": 7,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:32:42",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f7391a21020001cd663e",
        "name": "estados",
        "title": "States",
        "description": "States",
        "attribution": "",
        "workspace": "prodes-cerrado",
        "capabilitiesUrl": "",
        "stackOrder": 8,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:34:49",
        "datasource": {
            "id": "5c409e1e0e9b2a0b8424ef1a",
            "name": "Deter/Prodes Cerrado",
            "description": "Deter/Prodes Cerrado",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:24:14",
            "downloads": [
                null
            ],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f7581a21020001cd663f",
        "name": "prodes_cerrado_2000_2017_uf_mun",
        "title": "Cerrado Yearly Deforestation",
        "description": "Cerrado Yearly Deforestation",
        "attribution": "",
        "workspace": "prodes-cerrado",
        "capabilitiesUrl": "",
        "stackOrder": 9,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:35:20",
        "datasource": {
            "id": "5c409e1e0e9b2a0b8424ef1a",
            "name": "Deter/Prodes Cerrado",
            "description": "Deter/Prodes Cerrado",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:24:14",
            "downloads": [
                null
            ],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f7791a21020001cd6640",
        "name": "limite_cerrado",
        "title": "Biome Border",
        "description": "Biome Border",
        "attribution": "",
        "workspace": "prodes-cerrado",
        "capabilitiesUrl": "",
        "stackOrder": 10,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:35:53",
        "datasource": {
            "id": "5c409e1e0e9b2a0b8424ef1a",
            "name": "Deter/Prodes Cerrado",
            "description": "Deter/Prodes Cerrado",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:24:14",
            "downloads": [
                null
            ],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f78e1a21020001cd6641",
        "name": "municipios_2017",
        "title": "Counties",
        "description": "Counties",
        "attribution": "",
        "workspace": "prodes-cerrado",
        "capabilitiesUrl": "",
        "stackOrder": 11,
        "opacity": 1,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-24 17:36:14",
        "datasource": {
            "id": "5c409e1e0e9b2a0b8424ef1a",
            "name": "Deter/Prodes Cerrado",
            "description": "Deter/Prodes Cerrado",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:24:14",
            "downloads": [
                null
            ],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f7b41a21020001cd6642",
        "name": "cerrado_mosaics",
        "title": "Cerrado Mosaics",
        "description": "Cerrado Mosaics",
        "attribution": "",
        "workspace": "prodes-cerrado",
        "capabilitiesUrl": "",
        "stackOrder": 12,
        "opacity": 1,
        "baselayer": false,
        "active": false,
        "enabled": true,
        "created": "2019-01-24 17:36:52",
        "datasource": {
            "id": "5c409e1e0e9b2a0b8424ef1a",
            "name": "Deter/Prodes Cerrado",
            "description": "Deter/Prodes Cerrado",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:24:14",
            "downloads": [
                null
            ],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c49f8161a21020001cd6643",
        "name": "mapeamento",
        "title": "Mapeamento Miranda",
        "description": "Mapeamento Miranda",
        "attribution": "",
        "workspace": "miranda",
        "capabilitiesUrl": "",
        "stackOrder": 14,
        "opacity": 1,
        "baselayer": false,
        "active": false,
        "enabled": true,
        "created": "2019-01-24 17:38:30",
        "datasource": {
            "id": "5c409e1e0e9b2a0b8424ef1a",
            "name": "Deter/Prodes Cerrado",
            "description": "Deter/Prodes Cerrado",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 15:24:14",
            "downloads": [
                null
            ],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    }
]
```

```json
[
    {
        "id": "5c40b6930e9b2a0e7553750d",
        "name": "layer",
        "title": "Layer 1",
        "description": "description",
        "attribution": "attribution",
        "workspace": "workspace",
        "capabilitiesUrl": "capabilitiesUrl",
        "stackOrder": 0,
        "opacity": 0.9,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-17 15:08:35",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 13:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [],
        "subdomains": []
    },
    {
        "id": "5c40b7510e9b2a0e7553750e",
        "name": "layer_2",
        "title": "Layer 2",
        "description": "description",
        "attribution": "attribution",
        "workspace": "workspace",
        "capabilitiesUrl": "capabilitiesUrl",
        "stackOrder": 0,
        "opacity": 0.9,
        "baselayer": false,
        "active": true,
        "enabled": true,
        "created": "2019-01-17 15:11:44",
        "datasource": {
            "id": "5c409e920e9b2a0b8424ef1b",
            "name": "Prodes Amazonia",
            "description": "Prodes Amazonia",
            "host": "http://terrabrasilis.dpi.inpe.br/geoserver/ows",
            "metadata": "",
            "enabled": true,
            "created": "2019-01-17 13:26:10",
            "downloads": [],
            "tools": []
        },
        "tools": [
            {
                "id": "5c40b5a70e9b2a0e7553750b",
                "name": "Fullscreen",
                "description": "This tool, after used, make the screen fullscreen",
                "tag": "html to the tool",
                "target": "where this tag will be built",
                "enabled": true,
                "created": "2019-01-17 15:04:39"
            },
            {
                "id": "5c40b5d40e9b2a0e7553750c",
                "name": "Transparency",
                "description": "This tool allow increase and decrease the layer transparency",
                "tag": "html to the tool",
                "target": "where this tag will be built",
                "enabled": true,
                "created": "2019-01-17 15:05:24"
            }
        ],
        "subdomains": [
            {
                "id": "5c40b5760e9b2a0e7553750a",
                "name": "subdominio1",
                "description": "subdominio1",
                "domain": "domain",
                "enabled": true,
                "created": "2019-01-17 15:03:50"
            }
        ]
    }
]
```

`PUT - update layer - example`

```json
{
	"id": "5c65c03fc4267d0001284325",
	"metadata": "http://terrabrasilis.dpi.inpe.br/geonetwork/srv/por/catalog.search#/metadata/6b621182-93d6-4a83-b5db-ae53a621276d",
	"dashboard": "http://terrabrasilis.dpi.inpe.br/dashboard/deforestation/biomes/cerrado/increments",
	"tools": [
		{
			"id": "5c5d6293c4267d0001284321"
		}	
	]
}
```

# Business API via Postman

The communications with API via [Postman](https://www.getpostman.com/) can be found on a JSON collection file at ./postman/ in this repository.