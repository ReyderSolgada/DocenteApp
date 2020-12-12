package com.cibertec.appdocente.appdocentecibertec.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cibertec.appdocente.appdocentecibertec.Model.TemasCurso;
import com.cibertec.appdocente.appdocentecibertec.R;

import java.util.List;

public class AdaptadorTemasCurso extends ArrayAdapter<TemasCurso> {
    Context micontexto;
    int milayout;
    List<TemasCurso> milista;

    public AdaptadorTemasCurso(@NonNull Context context, int resource, @NonNull List<TemasCurso> objects) {
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
        TextView tvnrotema,tvdesctema,tvdetexamen;
        tvnrotema=(TextView)mivista.findViewById(R.id.TVNRO_TEMA);
        tvdesctema=(TextView)mivista.findViewById(R.id.TVDESC_TEMA);
        tvdetexamen=(TextView)mivista.findViewById(R.id.TVDET_EXAMEN);
        TemasCurso obj= milista.get(position);
        tvnrotema.setText(obj.getNrotema()+"");
        tvdesctema.setText(obj.getDestema());
        tvdetexamen.setText(obj.getDetsemana());
        return mivista;
    }
}
