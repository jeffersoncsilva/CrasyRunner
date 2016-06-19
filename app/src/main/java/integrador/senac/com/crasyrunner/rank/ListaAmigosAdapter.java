package integrador.senac.com.crasyrunner.rank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import integrador.senac.com.crasyrunner.R;

/**
 * Created by Jefferson on 08/05/2016.
 */
public class ListaAmigosAdapter extends BaseAdapter {
    private ArrayList<Amigo> amigos;
    private Activity act;

    private int linha;

    public ListaAmigosAdapter(ArrayList<Amigo> amigos, Activity rankJogo) {
        this.amigos = amigos;
        this.act = rankJogo;
        this.linha = 1;
    }

    @Override
    public int getCount() {
        return amigos.size();
    }

    @Override
    public Object getItem(int position) {
        return amigos.get(position);
    }

    @Override
    public long getItemId(int position) {
        Amigo amigo = amigos.get(position);
        return amigo.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linha;
        Amigo amigo = amigos.get(position);

        LayoutInflater inflater = act.getLayoutInflater();
        if(this.linha == 1) {
            linha = inflater.inflate(R.layout.linha_rank_1, null);
            this.linha = 2;
        }
        else
        {
            linha = inflater.inflate(R.layout.linha_rank_2, null);
            this.linha = 1;
        }
        TextView nome = (TextView) linha.findViewById(R.id.nome);
        nome.setText(amigo.getNome());

        TextView pt = (TextView) linha.findViewById(R.id.pontos);
        pt.setText(String.valueOf(amigo.getPontos()));

        ImageView img = (ImageView) linha.findViewById(R.id.foto);
        img.setImageBitmap(amigo.getFoto());

        return linha;
    }
}
