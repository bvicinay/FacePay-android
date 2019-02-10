package com.example.facepay_android;
import java.io.*;
import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.graphics.*;
import android.widget.*;
import android.provider.*;

import com.microsoft.projectoxford.face.*;
import com.microsoft.projectoxford.face.contract.*;

public class FaceHandler {

    private String TAG = "FaceHandler";

    // Replace `<API endpoint>` with the Azure region associated with
    // your subscription key. For example,
    // apiEndpoint = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0"
    private final String apiEndpoint = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0";

    // Replace `<Subscription Key>` with your subscription key.
    // For example, subscriptionKey = "0123456789abcdef0123456789ABCDEF"
    private final String subscriptionKey = "683055d82fdf44b493df7dceda9f317e";

    private final FaceServiceClient faceServiceClient =
            new FaceServiceRestClient(apiEndpoint, subscriptionKey);

    private final int PICK_IMAGE = 1;


    public FaceHandler() {

        setup();

    }

    public void setup() {






    }

    // Detect faces by uploading a face image.
// Frame faces after detection.

    public void detectAndFrame(final Bitmap imageBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        AsyncTask<InputStream, String, Face[]> detectTask =
                new AsyncTask<InputStream, String, Face[]>() {
                    String exceptionMessage = "";

                    @Override
                    protected Face[] doInBackground(InputStream... params) {
                        try {
                            //publishProgress("Detecting...");
                            Face[] result = faceServiceClient.detect(
                                    params[0],
                                    true,         // returnFaceId
                                    false,
                                    new FaceServiceClient.FaceAttributeType[] {
                                            FaceServiceClient.FaceAttributeType.Age,
                                            FaceServiceClient.FaceAttributeType.Gender }// returnFaceLandmarks
                                              // returnFaceAttributes:
                                /* new FaceServiceClient.FaceAttributeType[] {
                                    FaceServiceClient.FaceAttributeType.Age,
                                    FaceServiceClient.FaceAttributeType.Gender }
                                */
                            );
                            if (result == null){
                                //publishProgress(
                                //        "Detection Finished. Nothing detected");
                                return null;
                            }
                            //publishProgress(String.format(
                            //        "Detection Finished. %d face(s) detected",
                            //        result.length));
                            return result;
                        } catch (Exception e) {
                            exceptionMessage = String.format(
                                    "Detection failed: %s", e.getMessage());
                            return null;
                        }
                    }

                    @Override
                    protected void onPreExecute() {
                        //TODO: show progress dialog
                        //detectionProgressDialog.show();
                    }
                    @Override
                    protected void onProgressUpdate(String... progress) {
                        //TODO: update progress
                        //detectionProgressDialog.setMessage(progress[0]);
                    }
                    @Override
                    protected void onPostExecute(Face[] result) {
                        //TODO: update face frames
                        //detectionProgressDialog.dismiss();

                        if (result != null) {
                            for (Face face : result) {

                                String gender = face.faceAttributes.gender;
                                FaceRectangle faceRectangle = face.faceRectangle;
                                Log.i(TAG, "user id is :" + face.faceId);
                                Log.i(TAG, "user gender :" + face.faceAttributes.gender);
                                Log.i(TAG, "user age is :" + face.faceAttributes.age);



                            }
                        }



                        if(!exceptionMessage.equals("")){
                            showError(exceptionMessage);
                        }
                        if (result == null) return;

                        //ImageView imageView = findViewById(R.id.imageView1);
                        //imageView.setImageBitmap(
                        //        drawFaceRectanglesOnBitmap(imageBitmap, result));
                        imageBitmap.recycle();
                    }
                };

        detectTask.execute(inputStream);
    }

    private void showError(String message) {
        Log.d(TAG, message);
    }
}
