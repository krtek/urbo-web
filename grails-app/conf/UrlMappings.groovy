class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/api/v1/case"(controller: "apiFeedback") {
            action = [GET: "findAll", POST: "save"]
        }

        "/api/v1/uploadPhoto"(controller: "apiFeedback", action: "uploadPhoto")

        "/api/v1/case/$id"(controller: "apiFeedback", action: "findById")

        "/api/v1/"(controller: "apiHelp")

        "/api/v1/photo/$id"(controller: "apiFeedback", action: "getPhoto")

        "/api/v1/thumbnail/$id/$width/$height"(controller: "apiFeedback", action: "getPhotoThumbnail")

        "/"(controller: "map")
        "/admin" (controller: "admin")

		"500"(view:'/error')
	}
}
