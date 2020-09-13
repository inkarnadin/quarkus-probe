package ru.alarh.db.dao;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import lombok.Data;
import ru.alarh.db.domain.Camera;

@Data
public class CameraDao {

    private static Camera from(Row row) {
        return new Camera(row.getLong("id"), row.getString("address"), row.getString("guid"));
    }

    public static Multi<Camera> findAll(MySQLPool client, Long limit) {
        String query = String.format("select id, address, guid from camera limit %1s", limit);
        return client.preparedQuery(query).execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(CameraDao::from);
    }

    public static Uni<Camera> findById(MySQLPool client, Long id) {
        return client.preparedQuery("select id, address, guid from camera where id = ?").execute(Tuple.of(id))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

}
