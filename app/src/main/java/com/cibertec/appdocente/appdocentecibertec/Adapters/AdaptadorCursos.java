package com.cibertec.appdocente.appdocentecibertec.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cibertec.appdocente.appdocentecibertec.Model.Cursos;
import com.cibertec.appdocente.appdocentecibertec.R;

import java.util.List;

public class AdaptadorCursos extends ArrayAdapter<Cursos> {
    Context micontexto;
    int miLayout;
    List<Cursos> milista;
    public AdaptadorCursos(@NonNull Context context, int resource, @NonNull List<Cursos> objects) {
        super(context, resource, objects);
        micontexto=context;
        miLayout=resource;
        milista=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View mivista=convertView;

        LayoutInflater inflat= LayoutInflater.from(micontexto);

        mivista=inflat.inflate(miLayout,null);

        TextView tvnom_curso,tvciclomatricula,tvseccion;
        tvnom_curso=(TextView)mivista.findViewById(R.id.TVNOMBRE_CURSO);
        tvciclomatricula=(TextView)mivista.findViewById(R.id.TVCICLO_MATRICULA);
        tvseccion=(TextView)mivista.findViewById(R.id.TVSECCION);

        Cursos obj=milista.get(position);
        tvnom_curso.setText(obj.getDesccurso());
        tvciclomatricula.setText(obj.getPeriodoMatricula());
        tvseccion.setText(obj.getSeccion());

        return mivista;
    }
}
