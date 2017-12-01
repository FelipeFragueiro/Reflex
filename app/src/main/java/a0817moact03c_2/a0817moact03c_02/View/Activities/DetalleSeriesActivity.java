package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetalleSerieFragment;

public class DetalleSeriesActivity extends AppCompatActivity {

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
}
