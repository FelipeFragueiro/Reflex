package a0817moact03c_2.a0817moact03c_02;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by ma on 27/10/17.
 */

public class FotoCompletaPeliculaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foto_completa);

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();
        int fotoCompleta = unBundle.getInt("foto_completa");

        ImageView imageView = (ImageView)findViewById(R.id.imageViewFotoCompleta);
        imageView.setImageResource(fotoCompleta);
    }
}
