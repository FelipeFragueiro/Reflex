package a0817moact03c_2.a0817moact03c_02.View.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.DetallePeliculaFragment;

/**
 * Created by ma on 03/11/17.
 */

public class AdaptadorDeDetallePeliculaFragment extends FragmentStatePagerAdapter {
    private List<Fragment> listaDeDetallePelicula;

    public AdaptadorDeDetallePeliculaFragment(FragmentManager fm) {
        super(fm);

        DetallePeliculaFragment unFragmentoPeliculaElTransportador = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("El transportador", R.drawable.el_transportador, "Accion", "Un pelado trabado, que anda siempre arriba de un Audi enfierrado.", 1));
        DetallePeliculaFragment unFragmentoPeliculaIndianaJones = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("Indiana jones", R.drawable.indiana_jones, "Aventura", "Un exploraGay que siempre lleva su latigo encima.", 2));
        DetallePeliculaFragment unFragmentoPeliculaMartes13 = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("Martes 13", R.drawable.martes_13, "Terror", "Un feo con una mascara que murio ahogado en un lago , resucita todos los martes 13 para matar a todos.", 3));
        DetallePeliculaFragment unFragmentoPeliculaMasacreDeTexas = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("Masacre de texas", R.drawable.masacre_de_texas, "Terror", "Un carnisero loco con muy pocas pulgas mata a todo habitante nuevo en Texas", 4));
        DetallePeliculaFragment unFragmentoPeliculaSaw = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("SAW", R.drawable.saw, "Terror", "Un viejo loco asesina a toda persona culpable de pecados", 5));
        DetallePeliculaFragment unFragmentoPeliculaFreddyCrugger = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("Freddy Cruger", R.drawable.freddy_cruger, "Terror", "Una persona acusada de algo que no hizo fue quemada viva por sus vecinos, vuelve en tus sueños en busca de venganza ", 6));
        DetallePeliculaFragment unFragmentoPeliculaIt = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("IT", R.drawable.it,"Terror","Un payaso loco en busca de niños para alimentarse vuelve cada 27 años a su suidad donde murio", 7));
        DetallePeliculaFragment unFragmentoPeliculaAnabelle = DetallePeliculaFragment.dameUnDetallePeliculaFragment(new Pelicula("Anabelle",R.drawable.anabelle,"Terror","Una muñeca con un espiritu maligno se adueña de una familia, sus intenciones no son buenas", 8));


        listaDeDetallePelicula = new ArrayList<>();
        listaDeDetallePelicula.add(unFragmentoPeliculaElTransportador);
        listaDeDetallePelicula.add(unFragmentoPeliculaIndianaJones);
        listaDeDetallePelicula.add(unFragmentoPeliculaMartes13);
        listaDeDetallePelicula.add(unFragmentoPeliculaMasacreDeTexas);
        listaDeDetallePelicula.add(unFragmentoPeliculaAnabelle);
        listaDeDetallePelicula.add(unFragmentoPeliculaFreddyCrugger);
        listaDeDetallePelicula.add(unFragmentoPeliculaIt);
        listaDeDetallePelicula.add(unFragmentoPeliculaSaw);





    }

    @Override
    public Fragment getItem(int position) {
        return listaDeDetallePelicula.get(position);
    }

    @Override
    public int getCount() {
        return listaDeDetallePelicula.size();
    }


}
