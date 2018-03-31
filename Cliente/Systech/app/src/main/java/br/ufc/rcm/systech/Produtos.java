package br.ufc.rcm.systech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Produtos extends AppCompatActivity {
    List<String> lista = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listaProdutos);
        setSupportActionBar(toolbar);
        lista.add("Bebidas");
        lista.add("Refeição");
        lista.add("Petiscos");
        lista.add("Salgados");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(chamaProduto());
    }
    public AdapterView.OnItemClickListener chamaProduto(){
        return (new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                Bundle params = new Bundle();
                switch (position) {
                    case 0:

                        intent = new Intent(getBaseContext(), ListaProdutos.class);
                        params.putString("operacao", "Bebidas");
                        params.putString("flag", "listaBebidas");
                        intent.putExtras(params);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getBaseContext(), ListaProdutos.class);
                        params.putString("operacao", "Refeição");
                        params.putString("flag", "listaRefeicao");
                        intent.putExtras(params);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getBaseContext(), ListaProdutos.class);
                        params.putString("operacao", "Petiscos");
                        params.putString("flag", "listaPetiscos");
                        intent.putExtras(params);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getBaseContext(), ListaProdutos.class);
                        params.putString("operacao", "Salgados");
                        params.putString("flag", "listaSalgados");
                        intent.putExtras(params);
                        startActivity(intent);
                        break;
                }
            }
        });

        }
    }



