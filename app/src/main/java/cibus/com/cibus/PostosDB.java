package cibus.com.cibus;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Postos.class},version = 1, exportSchema = false)

public abstract class PostosDB extends RoomDatabase {


    public abstract PostosDAO PostosDB();

    private static PostosDB instancia;

    public static PostosDB obterInstanciaUnica(
            Context context) {
        synchronized (PostosDB.class) {
            if (instancia == null)
                instancia = Room.databaseBuilder(
                        context.getApplicationContext(),
                        PostosDB.class,
                        "banco").build();

            return instancia;
        }
    }

}
