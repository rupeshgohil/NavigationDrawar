package aru.navigationdrawar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SlideshowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);
        setTitle("SlideShow");
    }
}
