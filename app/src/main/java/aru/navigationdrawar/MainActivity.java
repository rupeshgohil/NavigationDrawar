package aru.navigationdrawar;
import android.os.Bundle;
public class MainActivity extends BaseActivity {
    String str="Home";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(str);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
