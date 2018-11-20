package cibus.com.cibus;

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

       // final ListView listaPosto = findViewById(R.id.listaPostos);

       /* listaPosto.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(consultaPostos.this,
                        "Posição Selecionada:" + (position + 1), Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
*/
/*
        listaPosto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapter, final View view,
                                    final int posicao, long id) {


                rodarNaThreadDoBanco(new Runnable() {
                        @Override
                        public void run() {
                            PostosDB banco = PostosDB
                                    .obterInstanciaUnica(consultaPostos.this);
                            PostosDAO postosDAO = banco.PostosDB();
                            postosDAO.limpar((posicao + 1));
                            atualiza();
                        }
                    });

                Toast.makeText(consultaPostos.this,
                        "Posição Selecionada:" + ((posicao + 1) + " id:  " + (id +1)), Toast.LENGTH_LONG)
                        .show();
            }
        });
*/


       /* listaPosto.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            public void onCreateContextMenu(ContextMenu contextMenu, View view,
                                            ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 1, Menu.NONE, "Deletar");


            }

            public boolean onContextItemSelected(MenuItem item) {
                AdapterView.AdapterContextMenuInfo menuInfo =
                        (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                switch (item.getItemId()) {
                    case 1:
                        deletar();
                        Toast.makeText(consultaPostos.this,
                                "teste:" + (item.getItemId()), Toast.LENGTH_LONG)
                                .show();
                }
                return consultaPostos.super.onContextItemSelected(item);
            }

            private void deletar() {
                rodarNaThreadDoBanco(new Runnable() {
                    @Override
                    public void run() {
                        PostosDB banco = PostosDB
                                .obterInstanciaUnica(consultaPostos.this);
                        PostosDAO postosDAO = banco.PostosDB();
                        postosDAO.limpar();
                        atualiza();

                    }
                });
            }
        });
        */
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