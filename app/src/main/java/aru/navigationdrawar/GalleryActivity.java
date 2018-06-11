package aru.navigationdrawar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GalleryActivity extends BaseActivity {
    public String str = "Gallery";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        setTitle(str);
    }
}
