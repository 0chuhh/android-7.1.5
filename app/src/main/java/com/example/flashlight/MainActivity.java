package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Camera myCamera;
    private Camera.Parameters myParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if(hasFlash){
            myCamera = Camera.open();
            myParam = myCamera.getParameters();
        }
        else{
            Toast myToast = Toast.makeText(getApplicationContext(),
                    "у камеры нет вспышки", Toast.LENGTH_SHORT);
            myToast.show();
        }
    }

    public void flashlight(View view) {
        myParam = myCamera.getParameters();
        myParam.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        myCamera.setParameters(myParam);
        myCamera.startPreview();
    }
}