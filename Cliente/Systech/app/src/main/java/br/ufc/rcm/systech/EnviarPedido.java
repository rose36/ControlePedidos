package br.ufc.rcm.systech;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnviarPedido extends AppCompatActivity {
    Button btFazerPedido;
    EditText nomeUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enviar_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nomeUsuario = (EditText) findViewById(R.id.nomeUsuario);
        btFazerPedido = (Button) findViewById(R.id.btFazerPedido);
        toolbar.setTitle("Tela de Pedidos");

    }
    public void fazerPedido(View view){

        Bundle bundle = new Bundle();
        bundle.putString("nome", nomeUsuario.getText().toString());
        bundle.putString("flag", "itemComanda");
        Intent intent = new Intent(getBaseContext(), ListaProdutos.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
