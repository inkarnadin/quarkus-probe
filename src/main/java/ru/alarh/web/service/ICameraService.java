package ru.alarh.web.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import ru.alarh.web.domain.Camera;

public interface ICameraService {

    Multi<Camera> getCameras(Long limit);
    Uni<Camera> getCamera(Long id);

}
