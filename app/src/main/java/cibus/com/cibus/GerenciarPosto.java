package cibus.com.cibus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GerenciarPosto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_posto);

        findViewById(R.id.bt_InserirPosto).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(
                                GerenciarPosto.this, InserirPostos.class), 0);
                    }
                });

        findViewById(R.id.bt_ExcluirPostos).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(
                                GerenciarPosto.this, InserirPostos.class), 0);
                    }
                });

    }
}
