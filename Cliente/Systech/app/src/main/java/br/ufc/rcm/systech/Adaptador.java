package br.ufc.rcm.systech;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Garen on 05/12/2015.
 */
public class Adaptador extends BaseAdapter {

    private Context context;
    private List<Produto> lista = null;

    public Adaptador(){

    }
    public Adaptador(Context context,  List<Produto> usuario) {
        this.lista = usuario;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = lista.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.conversas, null);
        TextView descricao = (TextView) layout.findViewById(R.id.descricao);
        TextView preco= (TextView) layout.findViewById(R.id.preco);
        TextView tipo= (TextView) layout.findViewById(R.id.tipoProduto);

        descricao.setText(produto.getDescricao());
        preco.setText("Preço: R$ "+produto.getPreco().toString());
        tipo.setText("Categoria: "+produto.getGrupo());

        return layout;
    }
    
}
