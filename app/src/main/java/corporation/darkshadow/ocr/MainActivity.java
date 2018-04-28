package corporation.darkshadow.ocr;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.guna.ocrlibrary.OCRCapture;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import corporation.darkshadow.ocr.RecyclerDivider.MyDividerItemDecoration;

import static com.guna.ocrlibrary.OcrCaptureActivity.TextBlockObject;

public class MainActivity extends AppCompatActivity {

    public String imagetext = "";
    public SparseArray<TextBlock> textBlock = null;
    public Button clickimage;
    public EditText editText;
    private final int CAMERA_SCAN_TEXT = 0;
    private final int LOAD_IMAGE_RESULTS = 1;
    private final int CAMERA = 2;
    private AlertDialog.Builder builder;
    public ImageView imageView;
    public EditText name,mobile,email;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = (EditText) findViewById(R.id.imagetext);
        imageView = (ImageView)findViewById(R.id.captureimage);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        mobile = (EditText)findViewById(R.id.mobile);
        clickimage = (Button)findViewById(R.id.clickimage);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_card);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(MainActivity.this,LinearLayoutManager.VERTICAL,16));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }

//        Code to use ocr using google dependency library

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.example_01);
//        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
//
//        Frame imageframe = new Frame.Builder().setBitmap(bitmap).build();
//        textBlock  = textRecognizer.detect(imageframe);
//
//        for (int i = 0; i < textBlock.size(); i++) {
//            // return string
//            Log.d("textImage",i+"");
//            TextBlock text = textBlock.get(textBlock.keyAt(i));
//            imagetext += text.getValue()+" ";
//        }
//
//        Log.d("textImage",imagetext);


//        Code for OCR image to text detection
//        camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.setTitle("Flash Permission")
//                        .setMessage("Do you want to use the flash?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                OCRCapture.Builder(MainActivity.this)
//                                        .setUseFlash(true)
//                                        .setAutoFocus(true)
//                                        .buildWithRequestCode(CAMERA_SCAN_TEXT);
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                OCRCapture.Builder(MainActivity.this)
//                                        .setUseFlash(false)
//                                        .setAutoFocus(true)
//                                        .buildWithRequestCode(CAMERA_SCAN_TEXT);
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
//            }
//        });

//        gallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (hasPermission()) {
//                    pickImage();
//                } else {
//                    getPermission();
//                }
//            }
//        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setTitle("Flash Permission")
                        .setMessage("Do you want to use the flash?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                OCRCapture.Builder(MainActivity.this)
                                        .setUseFlash(true)
                                        .setAutoFocus(true)
                                        .buildWithRequestCode(CAMERA_SCAN_TEXT);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                OCRCapture.Builder(MainActivity.this)
                                        .setUseFlash(false)
                                        .setAutoFocus(true)
                                        .buildWithRequestCode(CAMERA_SCAN_TEXT);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        clickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, CAMERA);
            }
        });

    }

    private void getPermission() {
// Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //TODO:
        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    pickImage();

                } else {

                    Toast.makeText(MainActivity.this,"Kindly allow the Read External Storage Permission",Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
        }
    }

    private void pickImage() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, LOAD_IMAGE_RESULTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == CAMERA_SCAN_TEXT) {
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    String text = data.getStringExtra(TextBlockObject);

                    String mob = "";
                    String mainmob = "";
                    String mail = "";
                    String tempmail = "";
                    String naam = "";
                    String tempnaam = "";
                    String temp = "";
                    String metatext = "";
                    int count;

                    String[] lines = text.split("\n");
                    for (int i=0; i<lines.length;i++){
                        temp = lines[i];
                        count = 0;
                        for (int j=0;j<temp.length();j++){
                            if (Character.isDigit(temp.charAt(j))){
                                mob += temp.charAt(j);
                                count++;
                            }
                        }
                        if (count >= 9){
                            lines[i] = "";
                        }
                        if (temp.matches(".*\\d+.*")){
                            mob += "\n";
                        }
                        if(temp.contains("@")){
                            if (temp.contains("E-mail")){
                                tempmail = temp;
                                tempmail = tempmail.replace("E-mail","");
                                mail += tempmail;
                            }
                            else {
                                mail += temp;
                            }
                            lines[i] = "";
                            mail += "\n";
                        }
                        if (temp.contains("Name")){
                            tempnaam = temp;
                            naam += tempnaam.replace("Name","");
                        }
                    }
                    String[] mobileno = mob.split("\n");
                    for (int i=0;i<mobileno.length;i++){
                        if (mobileno[i].length() >=9){
                            mainmob += mobileno[i];
                            mainmob += "\n";
                        }
                    }

                    for ( int i=0; i<lines.length;i++){
                        if (lines[i] == ""){
                            continue;
                        }
                        metatext += lines[i];
                        metatext += "\n";
                    }
                    name.setText(naam);
                    mobile.setText(mainmob);
                    email.setText(mail);
                    editText.setText(metatext);

                }
            } else if (requestCode == LOAD_IMAGE_RESULTS) {
                Uri pickedImage = data.getData();
                String text = OCRCapture.Builder(this).getTextFromUri(pickedImage);
                editText.setText(text);
            }
            else if (requestCode == CAMERA && resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    }


    private boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
