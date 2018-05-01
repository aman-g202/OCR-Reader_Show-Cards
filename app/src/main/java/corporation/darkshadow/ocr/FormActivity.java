package corporation.darkshadow.ocr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import corporation.darkshadow.ocr.Utils.SharedPrefUtil;

/**
 * Created by darkshadow on 28/4/18.
 */

public class FormActivity extends AppCompatActivity{

    EditText metatext,name,email,mobile;
    Button clickimage, savebutton;
    private String naam,mail,meta,mainmob;
    public Set<String> details;
    int newsize;

    public SharedPrefUtil prefUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);

        metatext = (EditText)findViewById(R.id.imagetext);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        mobile = (EditText)findViewById(R.id.mobile);

        clickimage = (Button)findViewById(R.id.clickimage);
        savebutton = (Button)findViewById(R.id.savebutton);

        details = new HashSet<>();

        prefUtil = new SharedPrefUtil(FormActivity.this);

        Intent intent = getIntent();

        metatext.setText(intent.getStringExtra("metatext"));
        name.setText(intent.getStringExtra("name"));
        email.setText(intent.getStringExtra("email"));
        mobile.setText(intent.getStringExtra("mobile"));

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = mobile.getText().toString();
                mainmob = "";

                if (temp.contains("\n")){
                    String[] number = temp.split("\n");
                    for (int i=0; i < number.length; i++){
                        mainmob += number[i];
                        if (i != number.length -1){
                            mainmob += ", ";
                        }
                    }
                }
                else{
                    mainmob = temp;
                }
//                String ext = name.getText().toString()+" - "+email.getText().toString()+" - "+mainmob+"----"+prefUtil.getSize("size",0);
//                Toast.makeText(FormActivity.this,ext,Toast.LENGTH_SHORT).show();
                details.add(name.getText().toString());
                details.add(email.getText().toString());
                details.add(mainmob);

//                Set<String> abc = new HashSet<String>();

                if (prefUtil.getSize("size",0) > 0){
                    newsize = prefUtil.getSize("size",0);
                    newsize += 1;
                    prefUtil.createCard("card"+newsize, details);
//                    abc = prefUtil.retrieveCard("card"+newsize,null);
//                    Iterator<String> it = abc.iterator();
//                    Toast.makeText(FormActivity.this,it.next()+"-"+it.next()+"-"+it.next(),Toast.LENGTH_SHORT).show();
                    prefUtil.createSize("size",newsize);
                }
                else {
                    prefUtil.createSize("size",1);
                    prefUtil.createCard("card"+prefUtil.getSize("size",0),details);
                }

                Toast.makeText(FormActivity.this,"Your Card is Saved Successfully",Toast.LENGTH_SHORT).show();
//
                Intent intent = new Intent(FormActivity.this,MainActivity.class);
                // Closing all the Activities
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}
