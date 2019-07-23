package com.example.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.watermark.androidwm.WatermarkBuilder;
import com.watermark.androidwm.bean.WatermarkImage;
import com.watermark.androidwm.bean.WatermarkText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class cameraFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera,null);
    }
    Fragment fragment =this;
    int REQUEST_IMAGE_CAPTURE=1234;
    ImageView cameraImage;
    private String pictureFilePath;
    private String deviceIdentifier;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode== REQUEST_IMAGE_CAPTURE){
                WatermarkText watermarkText = new WatermarkText("CSIS '19")
                        .setPositionX(0.5)
                        .setPositionY(0.5)
                        .setTextAlpha(100)
                        .setTextColor(Color.WHITE)
                        .setTextFont(R.font.raleway_light)
                        .setTextShadow(0.1f, 5, 5, Color.BLUE);

//                Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
//                cameraImage.setImageBitmap(capturedImage);
                File imgFile = new  File(pictureFilePath);
                if(imgFile.exists())            {
                    cameraImage.setImageURI(Uri.fromFile(imgFile));
                    WatermarkBuilder.create(getContext(),cameraImage)
                            .loadWatermarkText(watermarkText)
                            .getWatermark()
                            .setToImageView(cameraImage);

                }
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cameraImage = view.findViewById(R.id.cameraImage);
        Button cameraButton = view.findViewById(R.id.cameraButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePicture.putExtra( MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
                if (takePicture.resolveActivity(getActivity().getPackageManager()) != null) {
                    fragment.startActivityForResult(takePicture,REQUEST_IMAGE_CAPTURE);
                    File pictureFile = null;
                    try {
                        pictureFile = getPictureFile();
                    } catch (IOException ex) {
                        Toast.makeText(getActivity(),
                                "Photo file can't be created, please try again",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (pictureFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getActivity(),
                                "com.zoftino.android.fileprovider",
                                pictureFile);
                        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        fragment.startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);

                    }
                    Intent galleryIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File f = new File(pictureFilePath);
                    Uri picUri = Uri.fromFile(f);
                    galleryIntent.setData(picUri);
                    getActivity().sendBroadcast(galleryIntent);
                    }
            }
        });


        getInstallationIdentifier();
    }
    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "ZOFTINO_" + timeStamp;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile,  ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }

    protected synchronized String getInstallationIdentifier() {
        if (deviceIdentifier == null) {
            SharedPreferences sharedPrefs = getActivity().getSharedPreferences(
                    "DEVICE_ID", Context.MODE_PRIVATE);
            deviceIdentifier = sharedPrefs.getString("DEVICE_ID", null);
            if (deviceIdentifier == null) {
                deviceIdentifier = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString("DEVICE_ID", deviceIdentifier);
                editor.commit();
            }
        }
        return deviceIdentifier;
    }



}
