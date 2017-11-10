package a0817moact03c_2.a0817moact03c_02;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListaGenerosFragment.EscuchadorDeGeneros {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListaGenerosFragment listaGenerosFragment = new ListaGenerosFragment();
        fragmentTransaction.replace(R.id.contenedorFragmentListaGeneros, listaGenerosFragment);
        fragmentTransaction.commit();

        
    }


    @Override
    public void seleccionaronGenero(Genero unGenero) {
        Intent unIntent = new Intent(this, DetalleGenerosDePeliculaActivity.class);
        Bundle unBundle = new  Bundle();
        unBundle.putString("nombre_genero",unGenero.getNombreGenero());
        unIntent.putExtras(unBundle);
        startActivity(unIntent);

    }
}
