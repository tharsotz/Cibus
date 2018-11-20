package cibus.com.cibus;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PostosDAO {

    @Insert
    void inserir(Postos postos);

    @Query("SELECT * from Postos")
    List<Postos> listar();

    @Delete
    void remover(Postos id);

    @Query("DELETE from Postos where id = :id2")
    void limpar(int id2);

}
