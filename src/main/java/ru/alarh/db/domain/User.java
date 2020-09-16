package ru.alarh.db.domain;

import io.vertx.codegen.annotations.DataObject;
import lombok.Data;

@Data
@DataObject
public class User {

    private Long id;
    private String username;
    private String guid;

}
