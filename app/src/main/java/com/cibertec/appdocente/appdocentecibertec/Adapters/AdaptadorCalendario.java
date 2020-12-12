package com.cibertec.appdocente.appdocentecibertec.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cibertec.appdocente.appdocentecibertec.Model.Calendario;
import com.cibertec.appdocente.appdocentecibertec.R;

import java.util.List;

public class AdaptadorCalendario extends ArrayAdapter<Calendario> {
    Context micontexto;
    int milayout;
    List<Calendario> milista;
    public AdaptadorCalendario(@NonNull Context context, int resource, @NonNull List<Calendario> objects) {
        super(context, resource, objects);
        micontexto=context;
        milayout=resource;
        milista=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mivista=convertView;
        LayoutInflater inflater=LayoutInflater.from(micontexto);
        mivista=inflater.inflate(milayout,null);
        TextView tvfechaevento,tvtituloevento;
        tvfechaevento=(TextView)mivista.findViewById(R.id.TVFECHA_EVENTO);
        tvtituloevento=(TextView)mivista.findViewById(R.id.TVTIT_EVENTO);
        Calendario obj =milista.get(position);
        tvfechaevento.setText(obj.getNombredia()+" "+obj.getDia()+" de "+obj.getNombremes());

        return mivista;
    }
}
