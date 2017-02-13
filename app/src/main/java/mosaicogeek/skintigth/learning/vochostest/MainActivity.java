package mosaicogeek.skintigth.learning.vochostest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    public static String historialPrueba="";
    public static Context contexto;

    public int contAz, contRo, contNe, contBl;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contexto=getApplicationContext();

        try{
            contAz = Integer.parseInt(cargarDatos("Azul"));
        }catch (NumberFormatException e){
        }
        try {
            contNe = Integer.parseInt(cargarDatos("Negro"));
        }catch (NumberFormatException e){
        }
        try{
            contRo = Integer.parseInt(cargarDatos("Rosa"));
        }catch (NumberFormatException e){
        }
        try{
            contBl = Integer.parseInt(cargarDatos("Blanco"));
        }catch (NumberFormatException e){
        }
        MainActivity.historialPrueba = cargarHistorial();

        List vochosList = new ArrayList<>();

        vochosList.add(new vochoObj(R.drawable.azul_back_b, contAz, "Azul"));
        vochosList.add(new vochoObj(R.drawable.negro_back_b, contNe, "Negro"));
        vochosList.add(new vochoObj(R.drawable.rosa_back_b, contRo, "Rosa"));
        vochosList.add(new vochoObj(R.drawable.blanco_back_b, contBl, "Blanco"));

        recyclerView = (RecyclerView)findViewById(R.id.reyclerView);
        recyclerView.setHasFixedSize(true);

        manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

        adapter = new adapterVocho(vochosList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.borrar:
                final AlertDialog.Builder alertaBorrar = new AlertDialog.Builder(this);
                alertaBorrar.setIcon(R.drawable.ic_error_128);
                alertaBorrar.setTitle("Resetear el conteo de Vochos");
                alertaBorrar.setMessage("¿Seguro que deseas regresar a 0 todos los Vochos?\nEl historial tambien se eliminará\n\nEsta Accion no se puede deshacer");
                alertaBorrar.setCancelable(true);
                alertaBorrar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast aceptar = Toast.makeText(getApplicationContext(), "Vochos reseteados", Toast.LENGTH_LONG);
                        aceptar.show();
                        borrarVochos();
                    }
                });
                alertaBorrar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alerta = alertaBorrar.create();
                alerta.show();
                historialPrueba="";
                guardarHistorial();
                guardarDatos("Azul", "0");
                guardarDatos("Negro", "0");
                guardarDatos("Rosa", "0");
                guardarDatos("Blanco", "0");
                return true;

            case R.id.info:
                final AlertDialog.Builder historial = new AlertDialog.Builder(this);
                historial.setIcon(R.drawable.history2);
                historial.setTitle("Historial de Vochos");
                historial.setMessage(historialPrueba);
                historial.setCancelable(true);
                historial.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog hist = historial.create();
                hist.show();
                return true;

            case R.id.guardar:
                Toast aceptar = Toast.makeText(getApplicationContext(), "Subiendo a la nube\n(aun no lo hace)", Toast.LENGTH_LONG);
                aceptar.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void borrarVochos(){

        List vochosListMod = new ArrayList<>();

        vochosListMod.add(new vochoObj(R.drawable.azul_back_b, 0, "Azul"));
        vochosListMod.add(new vochoObj(R.drawable.negro_back_b, 0, "Negro"));
        vochosListMod.add(new vochoObj(R.drawable.rosa_back_b, 0, "Rosa"));
        vochosListMod.add(new vochoObj(R.drawable.blanco_back_b, 0, "Blanco"));

        guardarDatos("Azul", "0");
        guardarDatos("Negro", "0");
        guardarDatos("Rosa", "0");
        guardarDatos("Blanco", "0");

        adapter=new adapterVocho(vochosListMod);
        recyclerView.setAdapter(adapter);
    }

    public static String guardarDatos(String color, String contador){
        SharedPreferences preferences = MainActivity.contexto.getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(color, contador);
        editor.apply();
        return contador;
    }

    public static String cargarDatos(String color){
        String contador;
        SharedPreferences preferences = MainActivity.contexto.getSharedPreferences("datos", Context.MODE_PRIVATE);
        contador=preferences.getString(color, "");
        return contador;
    }

    public static void guardarHistorial(){
        SharedPreferences preferences = MainActivity.contexto.getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("historial",MainActivity.historialPrueba);
        editor.apply();
    }

    public static String cargarHistorial(){
        String historial="";
        SharedPreferences preferences = MainActivity.contexto.getSharedPreferences("datos", Context.MODE_PRIVATE);
        historial = preferences.getString("historial", "");
        return historial;
    }
}