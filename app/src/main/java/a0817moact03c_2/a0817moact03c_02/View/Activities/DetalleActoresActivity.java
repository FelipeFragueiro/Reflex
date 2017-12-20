package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetalleActorFragment;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetallePeliculaFragment;

public class DetalleActoresActivity extends AppCompatActivity implements DetallePeliculaFragment.CallBackDetallePeliculaFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actores);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();




        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetalleActorFragment detalleActorFragment = new DetalleActorFragment();
        detalleActorFragment.setArguments(unBundle);
        fragmentTransaction.replace(R.id.contenedorDeFragmentsDeActores, detalleActorFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void seleccionaronPelicula(Pelicula unaPelicula) {

    }

    @Override
    public void seleccionaronActor(Actores unActor) {

        Intent unIntent = new Intent(DetalleActoresActivity.this, DetalleActoresActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString("nombre_actor", unActor.getName());
        unBundle.putString("also_known_as_actor",unActor.getAlso_known_as());
        unBundle.putString("character_actor",unActor.getCharacter());
        unBundle.putString("profile_path_actor",unActor.getProfile_path());
        unBundle.putString("birthday_actor",unActor.getBirthday());
        unBundle.putString("biografia_actor",unActor.getBiography());

        unIntent.putExtras(unBundle);
        startActivity(unIntent);
    }
}
