package ru.alarh.web.resource;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import ru.alarh.db.domain.Camera;
import ru.alarh.web.service.CameraService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/camera")
public class CameraResource {

    @Inject
    CameraService cameraService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Camera> list(@QueryParam("limit") Long limit) {
        return cameraService.getCameras(limit);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Uni<Camera> get(@PathParam("id") Long id) {
        return cameraService.getCamera(id);
    }

}