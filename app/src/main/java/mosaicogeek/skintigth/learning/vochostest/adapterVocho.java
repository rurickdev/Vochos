package mosaicogeek.skintigth.learning.vochostest;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adapterVocho extends RecyclerView.Adapter<adapterVocho.VochoViewHolder>{
    public List<vochoObj> vochoObjList;

    public static class VochoViewHolder extends RecyclerView.ViewHolder{
        CardView CarView;
        TextView Contador;
        ImageButton plusOne;
        ImageButton minOne;
        ImageView ColorImg;
        TextView Color;

        public VochoViewHolder(View v){
            super(v);

            CarView = (CardView)v.findViewById(R.id.CardView);
            Contador = (TextView)v.findViewById(R.id.contador);
            plusOne = (ImageButton)v.findViewById(R.id.plusOne);
            minOne = (ImageButton)v.findViewById(R.id.minOne);
            ColorImg = (ImageView)v.findViewById(R.id.ColorImg);
            Color = (TextView)v.findViewById(R.id.Color);

            plusOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence cadeContar = Contador.getText();
                    CharSequence colorOp = Color.getText();
                    char op = colorOp.charAt(0);
                    int contar = Integer.parseInt(String.valueOf(cadeContar));
                    contar = contar + 1;

                    switch (op) {
                        case 'A':
                            MainActivity.historialPrueba = MainActivity.historialPrueba + "Se sumó un Vocho Azul\n";
                            MainActivity.guardarDatos("Azul", Integer.toString(contar));
                            break;
                        case 'N':
                            MainActivity.historialPrueba = MainActivity.historialPrueba + "Se sumó un Vocho Negro\n";
                            MainActivity.guardarDatos("Negro", Integer.toString(contar));
                            break;
                        case 'R':
                            MainActivity.historialPrueba = MainActivity.historialPrueba + "Se sumó un Vocho Rosa\n";
                            MainActivity.guardarDatos("Rosa", Integer.toString(contar));
                            break;
                        case 'B':
                            MainActivity.historialPrueba = MainActivity.historialPrueba + "Se sumó un Vocho Blanco\n";
                            MainActivity.guardarDatos("Blanco", Integer.toString(contar));
                            break;
                        default:
                            break;
                    }
                    MainActivity.guardarHistorial();
                    Contador.setText(Integer.toString(contar));
                }
            });

            minOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence colorOp = Color.getText();
                    char op = colorOp.charAt(0);
                    CharSequence cadeContar2 = Contador.getText();
                    int contar2 = Integer.parseInt(String.valueOf(cadeContar2));
                    if (contar2 == 0) {
                    } else {
                        contar2 = contar2 - 1;
                        switch (op) {
                            case 'A':
                                MainActivity.historialPrueba = MainActivity.historialPrueba + "Se restó un Vocho Azul\n";
                                MainActivity.guardarDatos("Azul", Integer.toString(contar2));
                                break;
                            case 'N':
                                MainActivity.historialPrueba = MainActivity.historialPrueba + "Se restó un Vocho Negro\n";
                                MainActivity.guardarDatos("Negro", Integer.toString(contar2));
                                break;
                            case 'R':
                                MainActivity.historialPrueba = MainActivity.historialPrueba + "Se restó un Vocho Rosa\n";
                                MainActivity.guardarDatos("Rosa", Integer.toString(contar2));
                                break;
                            case 'B':
                                MainActivity.historialPrueba = MainActivity.historialPrueba + "Se restó un Vocho Blanco\n";
                                MainActivity.guardarDatos("Blanco", Integer.toString(contar2));
                                break;
                            default:
                                break;
                        }
                        MainActivity.guardarHistorial();
                        Contador.setText(Integer.toString(contar2));
                    }
                }
            });
        }
    }

    public adapterVocho(List<vochoObj> vochoObjList){
        this.vochoObjList = vochoObjList;
    }

    @Override
    public int getItemCount(){
        return vochoObjList.size();
    }

    @Override
    public VochoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view, viewGroup, false);
        return new VochoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VochoViewHolder vochoViewHolder, int i){
        vochoViewHolder.Contador.setText(String.valueOf(vochoObjList.get(i).getContador()));
        vochoViewHolder.ColorImg.setImageResource(vochoObjList.get(i).getColorImge());
        vochoViewHolder.Color.setText(vochoObjList.get(i).getColString());
    }
}