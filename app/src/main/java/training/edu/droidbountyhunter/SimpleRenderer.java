package training.edu.droidbountyhunter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import training.edu.utils.PictureTools;

public class SimpleRenderer implements GLSurfaceView.Renderer {
    Context context;
    FloatBuffer vertexBuffer;
    FloatBuffer texturaBuffer;
    ShortBuffer indexBuffer;
    int carasLength;

    public SimpleRenderer(Context context){
        this.context = context;
    }

    public void cargarTextura(GL10 gl){
        Bitmap bitmap;
        if(ActivityOpenGLFugitivos.fotoDefault.equalsIgnoreCase("0")){
            bitmap = PictureTools.decodeSampledBitmapFromUri(ActivityOpenGLFugitivos.foto, 200,200);
        }else{
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }

        int textureIds[] = new int[1];
        gl.glGenTextures(1, textureIds, 0);
        int textureId= textureIds[0];

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        Log.d("AR", "superficie modificada: " + i + "x" + i1);

        float positivo = ActivityOpenGLFugitivos.distorcion;
        float negativo = ActivityOpenGLFugitivos.distorcion * (-1.0f);

        float vertices[] = {
            negativo, 1f, 0f,
                -1f, -1f, 0f,
                0f, -1f, 0f,
                1f, -1f, 0f,
                positivo, 1f, 0f
        };

        short caras[] = {
                0,1,2,
                0,2,4,
                2,3,4
        };

        carasLength = caras.length;

        float textura[] = {
                0f, 0f,
                0f, 1f,
                0.5f, 1f,
                1f,1f,
                1f,0f
        };

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(caras.length*2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(caras);
        indexBuffer.position(0);

        ByteBuffer tbb = ByteBuffer.allocateDirect(textura.length*4);
        tbb.order(ByteOrder.nativeOrder());
        texturaBuffer = tbb.asFloatBuffer();
        texturaBuffer.put(textura);
        texturaBuffer.position(0);

        gl10.glViewport(0,0,i, i1);
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        GLU.gluPerspective(gl10, 45.0f, (float)i /(float)i1, 0.1f, 100.0f);

        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
    }

    public void draw(GL10 gl){
        cargarTextura(gl);

        gl.glFrontFace(GL10.GL_CCW);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texturaBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, carasLength, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();

        gl.glTranslatef(0,0, -7);

        draw(gl);
    }
}
