package br.ufc.rcm.systech;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaProdutos extends AppCompatActivity {

    List<Produto> lista = new ArrayList<Produto>();
    ListView listaBebidas;
    String nomeUsuario = "", flag = "";
    TextView tipoLista;
    Adaptador adaptadorProdutos;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bebidas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaBebidas = (ListView) findViewById(R.id.listbebidas);
        adaptadorProdutos = new Adaptador(this,  lista);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if(params!=null)
        {
            nomeUsuario = params.getString("nome");
            flag = params.getString("flag");
        }

        Json json = new Json();
        try {
            String mensagem = json.criaJson("", "", "listaProdutos");
            atualizaLista at = new atualizaLista(mensagem);
            at.start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listaBebidas.setOnItemLongClickListener((new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> av, View view, final int position, long id) {
                final Produto produto= (Produto) av.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), ""+produto.getId(), Toast.LENGTH_SHORT).show();
                Log.i("Dados do Produto", produto.getDescricao() + "," + produto.getId() + "," + produto.getGrupo() + "," + produto.getPreco());

                final AlertDialog alertDialog = new AlertDialog.Builder(ListaProdutos.this).create();
                alertDialog.setTitle(produto.getDescricao());
                alertDialog.setMessage("Digite a quantidade desejada");
                final EditText input = new EditText(ListaProdutos.this);
                alertDialog.setView(input);
                alertDialog.setButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    int quantidade = Integer.parseInt(input.getText().toString());
                    Json json = new Json();
                        try {
                            Log.i("Data", getDate());
                            String produtoComanda = json.criaJsonComanda(nomeUsuario, input.getText().toString(), ""+produto.getId(), getDate(),flag);
                            enviaComanda enviaComanda = new enviaComanda(produtoComanda);
                            enviaComanda.start();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alertDialog.show();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);


                return true;
            }

        }));
    }

    private String getDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        return dateFormat.format(date);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //MENU COM SUAS DEVIDAS FUNÇÕES

        //noinspection SimplifiableIfStatement
        if (id == R.id.atualiza) {
                Json json = new Json();
            try {
                String mensagem = json.criaJson("", "", "listaProdutos");
                atualizaLista at = new atualizaLista(mensagem);
                at.start();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public class atualizaLista extends Thread{

        Socket socket;
        DataInputStream input;
        DataOutputStream output;
        String resposta;
        String paraEnviar;
        Json json;
        JSONArray jsonArray;
        Produto produto;

        public atualizaLista(String json){
            this.paraEnviar = json;
        }


        public void run() {
            try {
                json = new Json();
                produto = new Produto();
                socket = new Socket("10.0.1.12", 9000);
                output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF(paraEnviar);
                output.flush();
                input = new DataInputStream(socket.getInputStream());
                resposta = input.readUTF();

                Log.i("RECEBIDO: ", resposta);
                ListaProdutos.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                                JSONArray arrayJson = new JSONArray(resposta);
                                JSONObject object = null;
                                for(int n = 0; n < arrayJson.length(); n++)
                                {
                                    object = arrayJson.getJSONObject(n);
                                    String descricao = object.getString("descricao");
                                    Double preco = object.getDouble("preco");
                                    String grupo = object.getString("grupo");
                                    int id  = object.getInt("id");
                                    Produto produto = new Produto(descricao,grupo,id, preco);
                                    lista.add(produto);
                                }

                            listaBebidas.setAdapter(adaptadorProdutos);
                            adaptadorProdutos.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    public class enviaComanda extends Thread{

        Socket socket;
        DataInputStream input;
        DataOutputStream output;
        String resposta;
        String paraEnviar;
        Json json;
        JSONArray jsonArray;
        Produto produto;

        public enviaComanda(String json){
            this.paraEnviar = json;
        }


        public void run() {
            try {
                socket = new Socket("10.0.1.12", 9000);
                output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF(paraEnviar);
                output.flush();
                input = new DataInputStream(socket.getInputStream());
                resposta = input.readUTF();

                Log.i("RECEBIDO: ", resposta);
                ListaProdutos.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Enviado", Toast.LENGTH_SHORT);
                    }
            });

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}