package cibus.com.cibus;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class consultaPostos extends AppCompatActivity {

    private Handler handlerThreadPrincipal;
    private Executor executorThreadDoBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_postos);

        handlerThreadPrincipal = new Handler(Looper.getMainLooper());
        executorThreadDoBanco = Executors.newSingleThreadExecutor();
        atualiza();

        final ListView listaPosto = findViewById(R.id.listaPostos);

        listaPosto.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                rodarNaThreadDoBanco(new Runnable() {
                    @Override
                    public void run() {
                        PostosDB banco = PostosDB
                                .obterInstanciaUnica(consultaPostos.this);
                        PostosDAO movimentacoes = banco.PostosDB();
                        movimentacoes.limpar();
                        atualiza();

                    }
                });
                return true;
            }
        });
}



    void atualiza () {
        rodarNaThreadDoBanco(new Runnable() {
            @Override
            public void run() {
                PostosDB banco = PostosDB
                        .obterInstanciaUnica(consultaPostos.this);
                PostosDAO postosDAO = banco.PostosDB();

                final List<Postos> lista = postosDAO.listar();

                rodarNaThreadPrincipal(new Runnable() {
                                           @Override
                                           public void run() {

                                               ListView listaMarmita = findViewById(R.id.listaPostos);
                                               ArrayAdapter<Postos> adaptador = new ArrayAdapter<>(
                                                       consultaPostos.this,
                                                       android.R.layout.simple_list_item_1,
                                                       lista);
                                               listaMarmita.setAdapter(adaptador);


                                           }
                                       }
                );

            }
        });
    }

    void rodarNaThreadPrincipal (Runnable acao){
        handlerThreadPrincipal.post(acao);
    }

    void rodarNaThreadDoBanco (Runnable acao){
        executorThreadDoBanco.execute(acao);
    }
}