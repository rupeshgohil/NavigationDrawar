package aru.navigationdrawar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CameraActivity extends BaseActivity {
    public String str="Camera";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setTitle(str);
    }
}
