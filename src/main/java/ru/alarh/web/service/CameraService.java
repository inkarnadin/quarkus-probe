package ru.alarh.web.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import ru.alarh.web.dao.CameraDao;
import ru.alarh.web.domain.Camera;

@ApplicationScoped
public class CameraService implements ICameraService {

    @Inject
    MySQLPool client;

    @Override
    public Multi<Camera> getCameras(Long limit) {
        return CameraDao.findAll(client, limit);
    }

    @Override
    public Uni<Camera> getCamera(Long id) {
        return CameraDao.findById(client, id);
    }

}
