package com.firebase.androidchat;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.firebase.androidchat.WiFiDirectActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.IOException;

/**
 * Created by Maria Alejandra B on 23/04/2016.
 */
public class MyService extends Service {

    public static WiFiDirectActivity actividad;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        System.out.println("el servicio esta iniciado y sigue");
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Log.i(getClass().getSimpleName(), "mensae");
          actividad = new WiFiDirectActivity();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.e(WiFiDirectActivity.TAG,"Service running 1");
                //Your logic that service will perform will be placed here
                //In this example we are just looping and waits for 1000 milliseconds in each loop.
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }


                       // Log.e(WiFiDirectActivity.TAG,"Service running");

                }

                //Stop service once it finishes its task
               // stopSelf();
            }
        }).start();


        //escucha siempre del servidor

        WiFiDirectActivity.basededatos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String recorre = new String();
                for (DataSnapshot object : dataSnapshot.getChildren()) {
                    Chat chat = object.getValue(Chat.class);

                    recorre = chat.getText();
                    WiFiDirectActivity.estado = chat.getText();


                    System.out.println("esto es lo que hay del servidor" + WiFiDirectActivity.estado + "es lo del recorre" + recorre);


                    //alarmas

                    WiFiDirectActivity.alert.setTitle("NOTIFICACION");


                    if (WiFiDirectActivity.estado.equals("rojo") || WiFiDirectActivity.estado.equals("Rojo") || WiFiDirectActivity.estado.equals("ROJO"))
                    {
                        WiFiDirectActivity.alert.setMessage("ALERTA ROJA: EL VOLCAN SE ENCUENTRA EN ALERTA ROJA DIRIJASE AL REFUGIO MAS CERCANO");
                        WiFiDirectActivity.alert.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                WiFiDirectActivity.vibration.cancel();
                                Intent i = new Intent(getBaseContext(), MapsActivity.class);
                                System.out.println("esto es lo que se va a guardar" + WiFiDirectActivity.lng + WiFiDirectActivity.lat);
                                i.putExtra("UNICOLON", String.valueOf(WiFiDirectActivity.lng));
                                i.putExtra("UNICOLAT", String.valueOf(WiFiDirectActivity.lat));
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                            }
                        });
                        WiFiDirectActivity.alert.show();

                        WiFiDirectActivity.myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        int usuario = WiFiDirectActivity.myAudioManager.getStreamVolume(AudioManager.STREAM_ALARM); //recuerda cual era antes de cambiarlo

                        WiFiDirectActivity.mediaPlayer = new MediaPlayer();

                        Uri alarmsound = null;
                        Uri ringtoneuri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


                        try {
                            if (WiFiDirectActivity.mediaPlayer != null){
                                WiFiDirectActivity.mediaPlayer.start();
                                if (!WiFiDirectActivity.mediaPlayer.isPlaying()) {
                                    WiFiDirectActivity.mediaPlayer.setDataSource(ringtoneuri.toString());
                                    WiFiDirectActivity. mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                                    WiFiDirectActivity.mediaPlayer.setLooping(true);
                                    WiFiDirectActivity.mediaPlayer.prepare();
                                    WiFiDirectActivity.mediaPlayer.start();
                                }
                            }WiFiDirectActivity.mediaPlayer.stop();

                        } catch (IOException e) {
                           // Toast.makeText(this, "Your alarm sound was unavailable.", Toast.LENGTH_LONG).show();

                        }

                        WiFiDirectActivity.myAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, WiFiDirectActivity.myAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), AudioManager.FLAG_PLAY_SOUND);
                        //vibration.vibrate(300000);



                    }

                    if (WiFiDirectActivity.estado.equals("naranja") || WiFiDirectActivity.estado.equals("Raranja")||WiFiDirectActivity.estado.equals("NARANJA"))
                    {
                        WiFiDirectActivity.alert.setMessage("ALERTA NARANJA: Se le recomienda dirigirse al refugio mas cercano");
                        WiFiDirectActivity.alert.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                WiFiDirectActivity.vibration.cancel();
                            }
                        });
                        WiFiDirectActivity.alert.show();



                        WiFiDirectActivity.myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        int usuario = WiFiDirectActivity.myAudioManager.getStreamVolume(AudioManager.STREAM_ALARM); //recuerda cual era antes de cambiarlo

                        WiFiDirectActivity. mediaPlayer = new MediaPlayer();

                        Uri alarmsound = null;
                        Uri ringtoneuri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


                        try {
                            if (WiFiDirectActivity.mediaPlayer != null){
                                WiFiDirectActivity.mediaPlayer.start();
                                if (!WiFiDirectActivity.mediaPlayer.isPlaying()) {
                                    WiFiDirectActivity. mediaPlayer.setDataSource(ringtoneuri.toString());
                                    WiFiDirectActivity. mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                                    WiFiDirectActivity. mediaPlayer.setLooping(true);
                                    WiFiDirectActivity. mediaPlayer.prepare();
                                    WiFiDirectActivity. mediaPlayer.start();
                                }
                            }WiFiDirectActivity.mediaPlayer.stop();

                        } catch (IOException e) {
                         //   Toast.makeText(this, "Your alarm sound was unavailable.", Toast.LENGTH_LONG).show();

                        }

                        WiFiDirectActivity.myAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, WiFiDirectActivity.myAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), AudioManager.FLAG_PLAY_SOUND);
                        WiFiDirectActivity.vibration.vibrate(30);


                    }

                    if (WiFiDirectActivity.estado.equals("amarillo")||WiFiDirectActivity.estado.equals("Amarillo")||WiFiDirectActivity.estado.equals("AMARILLO"))
                    {
                        WiFiDirectActivity.alert.setMessage("ALERTA AMARILLA: Se recomienda tomar precauciones");
                        WiFiDirectActivity.alert.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo1, int id) {
                                WiFiDirectActivity.vibration.cancel();
                            }
                        });
                        WiFiDirectActivity.alert.show();



                        WiFiDirectActivity.myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        int usuario = WiFiDirectActivity.myAudioManager.getStreamVolume(AudioManager.STREAM_ALARM); //recuerda cual era antes de cambiarlo

                        WiFiDirectActivity.mediaPlayer = new MediaPlayer();

                        Uri alarmsound = null;
                        Uri ringtoneuri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


                        try {
                            if (WiFiDirectActivity.mediaPlayer != null){
                                WiFiDirectActivity.mediaPlayer.start();
                                if (!WiFiDirectActivity.mediaPlayer.isPlaying()) {
                                    WiFiDirectActivity.mediaPlayer.setDataSource(ringtoneuri.toString());
                                    WiFiDirectActivity.mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                                    WiFiDirectActivity.mediaPlayer.setLooping(true);
                                    WiFiDirectActivity.mediaPlayer.prepare();
                                    WiFiDirectActivity.mediaPlayer.start();
                                }
                            }WiFiDirectActivity.mediaPlayer.stop();

                        } catch (IOException e) {
                            //Toast.makeText(this, "Your alarm sound was unavailable.", Toast.LENGTH_LONG).show();

                        }

                        WiFiDirectActivity.myAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, WiFiDirectActivity.myAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), AudioManager.FLAG_PLAY_SOUND);
                        WiFiDirectActivity. vibration.vibrate(300000);
                    }

                    if (WiFiDirectActivity.estado.equals("verde"))
                    {


                    }




/*
                    if (WiFiDirectActivity.estado.equals("rojo"))
                    {
                        System.out.println("solo quiero que lo haga");
                       Intent intent1 = new Intent(getBaseContext(),MapsActivity.class);

                        if (intent1 !=null)
                        {
                            System.out.println("El intent quedo nulo");
                            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //startActivity(intent1);
                        }


                    }*/


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return START_STICKY;


        }//fin del servicio






            @Override
            public void onDestroy() {
                super.onDestroy();
                // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
            }
    }
