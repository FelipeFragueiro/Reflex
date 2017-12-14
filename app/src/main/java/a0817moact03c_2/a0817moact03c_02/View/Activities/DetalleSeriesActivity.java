package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetalleSerieFragment;

public class DetalleSeriesActivity extends AppCompatActivity implements DetalleSerieFragment.CallBackDetalleSerieFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_series);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();

        Toast.makeText(this, "hola serie", Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetalleSerieFragment detalleSerieFragment = new DetalleSerieFragment();
        detalleSerieFragment.setArguments(unBundle);
        fragmentTransaction.replace(R.id.contenedorFragmentDetalleSerie, detalleSerieFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void seleccionaronPelicula(Serie unaSerie) {
        Intent unIntent = new Intent(this, DetalleSeriesActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_serie",unaSerie.getName());
        //unBundle.putString("genero_serie",unaSerie.getGenre_ids());
        unBundle.putString("id",unaSerie.getId());
        unBundle.putString("descripcion_serie",unaSerie.getOverview());
        unBundle.putString("imagen_serie",unaSerie.getPoster_path());
        unBundle.putInt("posicion_serie",unaSerie.getPosicion());
        unBundle.putString("idDeSerie", unaSerie.getId());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
