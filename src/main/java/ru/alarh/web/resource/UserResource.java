package ru.alarh.web.resource;

import ru.alarh.web.service.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject
    IUserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("limit") Long limit) {
        userService.get();
        return Response.ok().build();
    }

}