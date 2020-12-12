package com.cibertec.appdocente.appdocentecibertec.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cibertec.appdocente.appdocentecibertec.Model.Alumno;
import com.cibertec.appdocente.appdocentecibertec.R;

import java.util.List;

public class AdaptadorAlumnos extends ArrayAdapter<Alumno> {
    Context micontexto;
    int milayout;
    List<Alumno> milista;
    public AdaptadorAlumnos(@NonNull Context context, int resource, @NonNull List<Alumno> objects) {
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
        TextView tvnomalumno,tvcorreoalumno;
        tvnomalumno=(TextView)mivista.findViewById(R.id.TVNOM_ALUMNO);
        tvcorreoalumno=(TextView)mivista.findViewById(R.id.TVCORREO_ALUMNO);
        Alumno obj= milista.get(position);
        tvnomalumno.setText(obj.getNomalum()+" "+obj.getApealum());
        tvcorreoalumno.setText(obj.getCorreoalum());
        return mivista;
    }
}
