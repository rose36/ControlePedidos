package br.ufc.rcm.systech;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    EditText etNome, etSenha;
    Button btLogar;
    TextView msg,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etNome = (EditText) findViewById(R.id.etNome);
        etSenha = (EditText) findViewById(R.id.etSenha);
        msg = (TextView) findViewById(R.id.msg);
        status = (TextView) findViewById(R.id.status);
    }
    public void logar(View view) throws JSONException {
        String nome = etNome.getText().toString();
        String senha = etSenha.getText().toString();

        msg.setText("Esperando resposta do servidor");
        Json json = new Json();
        String stringJson = json.criaJson(nome, senha, "login");
        Recebe recebe = new Recebe(stringJson);
        recebe.start();
    }

    public class Recebe extends Thread{
        ServerSocket serverSocket;
        Socket socket;
        DataInputStream input;
        DataOutputStream output;
        String resposta;
        String paraEnviar;
        Json json;

        public Recebe(String json){
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
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resposta.equals("1")) {
                            msg.setText("Logado com Sucesso");
                            Intent intent = new Intent(getBaseContext(), EnviarPedido.class);
                            startActivity(intent);
                        } else if (resposta.equals("0")) {
                            msg.setText("Usuarios ou senha inválido");
                            etNome.setText("");
                            etSenha.setText("");
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

}
