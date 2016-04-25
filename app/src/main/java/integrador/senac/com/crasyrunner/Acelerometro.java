package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.util.FloatMath;
import android.util.Log;

import java.util.List;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class Acelerometro implements SensorEventListener {

    private Context context;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    private int acX = 0;

    public Acelerometro(Context context){
        this.context = context;
        iniciaSensor();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.acX = (int)event.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void iniciaSensor(){
        sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ALL);
        sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public int getAcelerationX() { return acX; }
}
