package cibus.com.cibus;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class InserirPostos extends AppCompatActivity {

    private Handler handlerThreadPrincipal;
    private Executor executorThreadDoBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_postos);

        findViewById(R.id.gravarPostos).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        final EditText nome = findViewById(R.id.nomePostos);
                        final EditText x = findViewById(R.id.x);
                        final EditText y = findViewById(R.id.y);
                        final EditText bandeira = findViewById(R.id.bandeira);
                        final EditText precoGas = findViewById(R.id.precoGas);
                        final Postos postos = new Postos(nome.getText().toString(),x.getText().toString(),y.getText().toString(),
                                bandeira.getText().toString(),precoGas.getText().toString());


                        rodarNaThreadDoBanco(new Runnable() {
                            @Override
                            public void run() {
                                PostosDB banco = PostosDB
                                        .obterInstanciaUnica(InserirPostos.this);
                                PostosDAO movimentacoes = banco.PostosDB();
                                movimentacoes.inserir(postos);

                            }
                        } );



                        Toast.makeText(InserirPostos.this, "Inserido com sucesso !", Toast.LENGTH_SHORT).show();


                    }
        });

        handlerThreadPrincipal = new Handler(Looper.getMainLooper());
        executorThreadDoBanco = Executors.newSingleThreadExecutor();
    }



    void rodarNaThreadPrincipal(Runnable acao) {
        handlerThreadPrincipal.post(acao);
    }

    void rodarNaThreadDoBanco(Runnable acao) {
        executorThreadDoBanco.execute(acao);
    }
}
