package com.cibertec.appdocente.appdocentecibertec.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cibertec.appdocente.appdocentecibertec.Model.Horario;
import com.cibertec.appdocente.appdocentecibertec.R;

import java.util.List;

public class AdaptadorHorario extends ArrayAdapter<Horario> {

    Context micontexto;
    int miLayout;
    List<Horario> milista;

    public AdaptadorHorario(@NonNull Context context, int resource, @NonNull List<Horario> objects) {
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

        TextView tvdia_clase,tvcurso_clase,tvhora_clase,tvaula_clase,tvsede_clase,tvfecha_clase;
        tvdia_clase=(TextView)mivista.findViewById(R.id.TVDIA_CLASE);
        tvhora_clase=(TextView)mivista.findViewById(R.id.TVHORA_CLASE);
        tvcurso_clase=(TextView)mivista.findViewById(R.id.TVCURSO_CLASE);
        tvaula_clase=(TextView)mivista.findViewById(R.id.TVAULA_CLASE);
        tvsede_clase=(TextView)mivista.findViewById(R.id.TVSEDE_CLASE);
        tvfecha_clase=(TextView)mivista.findViewById(R.id.TVFECHA_CLASE);

        Horario obj=milista.get(position);
        tvdia_clase.setText(obj.getNombredia());
        tvcurso_clase.setText(obj.getDesccurso());
        tvhora_clase.setText(obj.getHorainicio()+"-"+obj.getHorafin());
        tvaula_clase.setText(obj.getCodaula());
        tvsede_clase.setText(obj.getNomsede());
        tvfecha_clase.setText(obj.getFechaclase());

        return mivista;
    }
}
