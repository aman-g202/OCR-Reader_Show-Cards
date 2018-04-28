package corporation.darkshadow.ocr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by darkshadow on 28/4/18.
 */

public class FormActivity extends AppCompatActivity{

    EditText metatext,name,email,mobile;
    Button clickimage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);

        metatext = (EditText)findViewById(R.id.imagetext);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        mobile = (EditText)findViewById(R.id.mobile);

        clickimage = (Button)findViewById(R.id.clickimage);
    }
}
