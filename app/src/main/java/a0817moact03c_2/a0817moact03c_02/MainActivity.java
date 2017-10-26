package a0817moact03c_2.a0817moact03c_02;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
}
