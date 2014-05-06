package run_fitplay_app;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

import ws.remote.RemoteDBAdapter;
import ws.remote.contracts.RemoteDBAdapterDelegate;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fitplay_app.R;

import entities.User;

public class Register extends ActionBarActivity implements RemoteDBAdapterDelegate{

	
	private static final int SELECT_PHOTO = 995;
	private static final int TAKE_PHOTO = 994;
	public static final int MEDIA_TYPE_IMAGE = 1;
	public Uri fileUri;
	public ImageView iv;
	public EditText et1,et2,et3;
	Bitmap yourSelectedImage;
	boolean flag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		iv = (ImageView) findViewById(R.id.showimg);
		et1 = (EditText) findViewById(R.id.regusername);
		et2 = (EditText) findViewById(R.id.regpassword);
		et3 = (EditText) findViewById(R.id.regemail);
		
	}
	
	public void UploadIMG(View v)
	{
		
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, SELECT_PHOTO);
	}
	
	public void TakeSelfie(View v)
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	 
	    // start the image capture Intent
	    startActivityForResult(intent, TAKE_PHOTO);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case SELECT_PHOTO:
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = imageReturnedIntent.getData();
	            flag = true;
				try {
					
					yourSelectedImage = decodeUri(selectedImage);
					iv.setVisibility(ImageView.VISIBLE);
					iv.setImageBitmap(yourSelectedImage);
	
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        break;
	        
	    case TAKE_PHOTO:
	    	  if(resultCode == RESULT_OK){  
	    		 
	    		  flag = false;
				try {
					yourSelectedImage = decodeUri(fileUri);  // should be fileUri
					iv.setVisibility(ImageView.VISIBLE);
					iv.setImageBitmap(yourSelectedImage);
				
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	        
	    	  }
	    	  break;
	    }
	 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 200;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
               || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }
	
	public Uri getOutputMediaFileUri(int type) {
	    return Uri.fromFile(getOutputMediaFile(type));
	}
	
	private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_DCIM), "FitPlay");
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("FitPlay", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
	public void RegisterUser(View v)  // called when user presses Register
	{
		//"name", et1.getText().toString());
		//"password", et2.getText().toString());
		// "email", et3.getText().toString());
		// add these entries into the database
		
		if( et1.getText().toString().trim().equals(""))
		 {    
			et1.setError( "name is required!" );
			et1.setHint("please enter name");
		 }
		else if( et2.getText().toString().trim().equals(""))
		 {    
			et2.setError( "password is required!" );
			et2.setHint("please enter password");
		 }
		else if( et3.getText().toString().trim().equals(""))
		 {    
			et3.setError( "email is required!" );
			et3.setHint("please enter email");
		 }

		else{
		
		User user = new User(et1.getText().toString(),et3.getText().toString(), et2.getText().toString());
		RemoteDBAdapter rdb = new RemoteDBAdapter("http://ec2-54-86-107-60.compute-1.amazonaws.com", this);
	    try {
	    	rdb.PostObjectsOfTypeWithParams("User", "user/index.php", user);
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 // upload the image from here only. No need to pass it back to login screen.
	 		try {
	 			executeMultipartPost(yourSelectedImage, et3.getText().toString());
	 		} catch (Exception e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}
	
        finish();
		}
	}

	@Override
	public void didReceiveResponseObjects(ArrayList<?> obj, int id) {
		// TODO Auto-generated method stub
		ArrayList<User> users = (ArrayList<User>) obj;
		int i=1;
		i=i+1;
	}
	
	
	public void executeMultipartPost(Bitmap bm, String username) throws Exception {
        try {
        	StrictMode.ThreadPolicy policy = new StrictMode.
        	          ThreadPolicy.Builder().permitAll().build();
        	        StrictMode.setThreadPolicy(policy); 
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
           	bm.compress(CompressFormat.JPEG, 100, bos);
            byte[] data = bos.toByteArray();
            HttpClient httpClient = new DefaultHttpClient();
            String postreq = "http://ec2-54-86-107-60.compute-1.amazonaws.com/imageUpload/?username="+username;
            System.out.println(postreq);
            HttpPost postRequest = new HttpPost(postreq);
            		
            String imagename = username + ".jpg";
            ByteArrayBody bab = new ByteArrayBody(data, imagename);
            
            MultipartEntity reqEntity = new MultipartEntity(
                    HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("image", bab);
            postRequest.setEntity(reqEntity);
            
            HttpResponse response = httpClient.execute(postRequest);
          //            
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), "UTF-8"));
            String sResponse="not";
            
            while ((sResponse = reader.readLine()) != null) {
            	 System.out.println("RESPONSE: " + sResponse);
            }
           
            
            
        } catch (Exception e) {
            // handle exception here
           e.printStackTrace();
        }
    }

}
