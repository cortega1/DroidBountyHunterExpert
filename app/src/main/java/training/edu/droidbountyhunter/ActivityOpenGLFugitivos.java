package training.edu.droidbountyhunter;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityOpenGLFugitivos extends AppCompatActivity {

    GLSurfaceView glView;
    public static String foto;
    public static float distorcion;
    public static String fotoDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle oExt = this.getIntent().getExtras();
        foto = oExt.getString("foto");
        distorcion = Float.parseFloat(oExt.getString("distorcion"));
        fotoDefault = oExt.getString("default");
        glView = new GLSurfaceView(this);
        glView.setRenderer(new SimpleRenderer(this));
        setContentView(glView);
    }

    @Override
    public void onResume(){
        super.onResume();
        glView.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        glView.onPause();
    }
}
