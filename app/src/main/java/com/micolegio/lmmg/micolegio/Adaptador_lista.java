package com.micolegio.lmmg.micolegio;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;

public abstract class Adaptador_lista extends BaseAdapter {

    private List<Usuario> listaUsuarios = null;
    private ArrayList<Usuario> entradas;
    private int R_layout_IdView;
    private Context contexto;

    public Adaptador_lista(Context contexto, int R_layout_IdView, ArrayList<Usuario> entradas) {
        super();
        this.contexto = contexto;
        this.listaUsuarios = entradas;
        this.entradas = new ArrayList<Usuario>();
        this.entradas.addAll(listaUsuarios);
        this.R_layout_IdView = R_layout_IdView;
    }

    public void filtrar (String charText) {
        charText = charText.toLowerCase();
        listaUsuarios.clear();
        if (charText.length() == 0) {
            listaUsuarios.addAll(entradas);
        }
        else
        {
            for (Usuario usuario : entradas)
            {
                if (usuario.getNombre().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    listaUsuarios.add(usuario);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }

        onEntrada (listaUsuarios.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return listaUsuarios.size();
    }

    @Override
    public Object getItem(int posicion) {
        return listaUsuarios.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    public void removeItem(int posicion) { listaUsuarios.remove(posicion); entradas.remove(posicion); }

    public void cambiaLista (ArrayList<Usuario> entradas) {
        this.listaUsuarios = entradas;
        this.entradas = new ArrayList<Usuario>();
        this.entradas.addAll(listaUsuarios);
    }

    public abstract void onEntrada (Usuario entrada, View view);

}