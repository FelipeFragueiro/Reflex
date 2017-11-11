package a0817moact03c_2.a0817moact03c_02.View.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.View.Fragments.ListaGenerosDePeliculaFragment;
import a0817moact03c_2.a0817moact03c_02.View.Fragments.SeriesFragment;

/**
 * Created by ma on 10/11/17.
 */

public class AdaptadorViewPagerMenuPrincipal extends FragmentStatePagerAdapter {
    private List<Fragment>listaDeFragments;

    public AdaptadorViewPagerMenuPrincipal(FragmentManager fm, List<Fragment> listaDeFragments) {
        super(fm);
        this.listaDeFragments = listaDeFragments;
    }

    public AdaptadorViewPagerMenuPrincipal(FragmentManager fm) {
        super(fm);
        SeriesFragment seriesFragment = new SeriesFragment();
        ListaGenerosDePeliculaFragment listaGenerosDePeliculaFragment = new ListaGenerosDePeliculaFragment();

        listaDeFragments = new ArrayList<>();
        listaDeFragments.add(listaGenerosDePeliculaFragment);
        listaDeFragments.add(seriesFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }
}
