package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jefferson on 08/05/2016.
 */
public class ListaAmigosAdapter extends BaseAdapter {
    private ArrayList<Amigo> amigos;
    private Activity act;

    public ListaAmigosAdapter(ArrayList<Amigo> amigos, Activity rankJogo) {
        this.amigos = amigos;
        this.act = rankJogo;
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
        Amigo amigo = amigos.get(position);
        LayoutInflater inflater = act.getLayoutInflater();

        View linha = inflater.inflate(R.layout.linha_rank, null);

        TextView nome = (TextView) linha.findViewById(R.id.nome);
        nome.setText(amigo.getNome());

        TextView pt = (TextView) linha.findViewById(R.id.pontos);
        pt.setText(String.valueOf(amigo.getPontos()));

        ImageView img = (ImageView) linha.findViewById(R.id.foto);
        img.setImageBitmap(amigo.getFoto());

        return linha;
    }
}
