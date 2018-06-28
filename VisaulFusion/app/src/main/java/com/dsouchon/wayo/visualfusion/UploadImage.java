package com.dsouchon.wayo.visualfusion;
//DO NOT USE THIS - USE UPLOAD PHOTO CLASS
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import com.dsouchon.wayo.visualfusion.R;
public class UploadImage extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;
private static final String SERVER_ADDRESS = "https://dardic-knot.000webhostapp.com/";

    ImageView imageToUpload1, downloadedImage;
    Button bUploadImage, bDownloadImage;
    EditText uploadImageName, downloadImageName;

    @Override
    public void onBackPressed() {     }      @Override  protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);  //dbManager = new DBManager(this);        dbManager.open();
        setContentView(R.layout.activity_upload_image);

        imageToUpload1 = (ImageView) findViewById(R.id.imageToUpload);
        downloadedImage = (ImageView) findViewById(R.id.downloadedImage);
        bUploadImage = (Button)findViewById(R.id.bUploadImage);
        bDownloadImage = (Button)findViewById(R.id.bDownloadImage);

        uploadImageName = (EditText)findViewById(R.id.etUploadName);
        downloadImageName = (EditText)findViewById(R.id.etDownloadName);

        imageToUpload1.setOnClickListener(this);
        bUploadImage.setOnClickListener(this);
        bDownloadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
switch(v.getId()){

    case R.id.imageToUpload :
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        break;
    case R.id.bUploadImage:
        Bitmap image  = ((BitmapDrawable) imageToUpload1.getDrawable()).getBitmap();
        break;
    case R.id.bDownloadImage :
        break;

}
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            imageToUpload1.setImageURI(selectedImage);

        }
    }

    private class UploadImage1 extends AsyncTask<Void, Void, Void>{
       Bitmap image;
        String name;

        public UploadImage1(Bitmap image, String name) {
            this.image = image;
            this.name = name;
        }
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image", encodedImage));
            dataToSend.add(new BasicNameValuePair("name", name));

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "SavePicture.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch(Exception e){
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }

    }

    private HttpParams getHttpRequestParams(){
    HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams, 1000*30);
        return httpRequestParams;
        
    }
}
