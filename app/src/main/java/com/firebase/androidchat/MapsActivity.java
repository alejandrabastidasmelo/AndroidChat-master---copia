package com.firebase.androidchat;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public  final static String longi = "longitud";
    public  static String lati = "latitud";

    private Button panico;

    public static final LatLng distrital = new LatLng (4.627871, -74.066065); // este es de prueba
  public static final LatLng albergueVERGEL  = new LatLng(1.1707982109, -77.1947753304);
    public static final LatLng alberguePOTREROS = new LatLng(1.1607121034, -77.1947188047);
    public static final LatLng albergueFONTIBON = new LatLng(1.1356323443, -77.1836205636);
    public static final LatLng alberguePOSTOBON = new LatLng(1.1421415257, -77.1813468883);
    public static final LatLng albergueELROSAL = new LatLng(1.1542745679, -77.1918975830);
    public static final LatLng albergueLALOMITA = new LatLng(1.1827379325, -77.2103491082);
    public static final LatLng albergueLAPALMA = new LatLng(1.1829722648, -77.2418337670);
    public static final LatLng albergueBELLAVISTA = new LatLng(1.1817731076, -77.2516732348);
    public static final LatLng alberguePLAZUELAS = new LatLng(1.1854029726, -77.2311056125);



    //public static  LatLng lollega = null;


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentomapa);
        mapFragment.getMapAsync(this);
        panico = (Button)findViewById(R.id.panico);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
*/
        //se obtiene la instancia del intent
        Intent i = getIntent();

        //se extrae el extra

        Bundle bun = getIntent().getExtras();
        LatLng lollega;
        double longi = 0;
        double lati = 0;
        String longitude = new String();
        longitude = bun.getString("UNICOLON");
        //longitude = getIntent().getStringExtra("UNICOLON");
        String latitude =  new String ();
                latitude = bun.getString("UNICOLAT");
         //latitude = getIntent().getStringExtra("UNICOLAT");

        System.out.println(longitude + latitude + "esto es lo que hay en longitud");
      longi = Double.parseDouble(longitude);
       lati = Double.parseDouble(latitude);

        //se le añade esos valores a la variable lollega
        lollega = new LatLng(lati,longi);


        //se mira que albergue esta mas cerca de donde esta el usuario

        if (esCerca(lollega, alberguePOSTOBON, 2.1) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(alberguePOSTOBON).title("Albergue Postobon"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(alberguePOSTOBON, lollega)
                    .width(5)
                    .color(Color.RED));
        }


        if (esCerca(lollega, albergueFONTIBON, 5.3) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(alberguePOSTOBON).title("Albergue Fontibon"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(albergueFONTIBON, lollega)
                    .width(5)
                    .color(Color.RED));
        }

        if (esCerca(lollega, albergueELROSAL, 2.2) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(albergueELROSAL).title("Albergue El Rosal"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(albergueELROSAL, lollega)
                    .width(5)
                    .color(Color.RED));
        }

        if (esCerca(lollega, alberguePOTREROS, 5.9) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(alberguePOTREROS).title("Albergue Potreros"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(alberguePOTREROS, lollega)
                    .width(5)
                    .color(Color.RED));
        }

        if (esCerca(lollega, albergueVERGEL, 5) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(albergueVERGEL).title("Albergue Vergel"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(albergueVERGEL, lollega)
                    .width(5)
                    .color(Color.RED));
        }


        if (esCerca(lollega, albergueLALOMITA, 9) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(albergueLALOMITA).title("Albergue La Lomita"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(albergueLALOMITA, lollega)
                    .width(5)
                    .color(Color.RED));
        }


        if (esCerca(lollega, alberguePLAZUELAS, 9.5) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(alberguePLAZUELAS).title("Albergue Plazuelas"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(alberguePLAZUELAS, lollega)
                    .width(5)
                    .color(Color.RED));
        }

        if (esCerca(lollega, albergueLAPALMA, 2) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(albergueLAPALMA).title("Albergue La Palma"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(albergueLAPALMA, lollega)
                    .width(5)
                    .color(Color.RED));
        }

        if (esCerca(lollega, albergueBELLAVISTA, 2.1) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(albergueBELLAVISTA).title("Albergue Bellavista"));

            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(albergueBELLAVISTA, lollega)
                    .width(5)
                    .color(Color.RED));
        }

        if (esCerca(lollega,distrital, 4) == true)
        {
            mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
            mMap.addMarker(new MarkerOptions().position(distrital).title("Esto es solo una prueba"));


            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(distrital, lollega)
                    .width(5)
                    .color(Color.RED));
            System.out.println("entro en la distrital");
            mMap.animateCamera(CameraUpdateFactory.zoomTo(50));
        }



        //se pone la marca en el lugar que se recibe, o sea la posicion actual
      //  mMap.addMarker(new MarkerOptions().position(lollega).title("Usted esta aqui"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(lollega));
       // mMap.addMarker(new MarkerOptions().position(). title("Albergue"));



       /* Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(distrital, lollega)
                .width(5)
                .color(Color.RED));*/
        //  System.out.println(longi + "esto es lo que hay en longitud");


    }//fin de mapready


    //funcion para saber si esta cerca del albergue, recibe la coordenada del albergue, la actual y la distancia a la que debe estar
    public boolean esCerca (LatLng cordenada1, LatLng cordenada2, double distancia)
    {
        boolean esta = false;

        double radio = 6372.795477598;

        double resultado = 0;



        resultado = radio * Math.acos(Math.sin(cordenada1.latitude) * Math.sin(cordenada2.latitude) + Math.cos(cordenada1.latitude) * Math.cos(cordenada2.latitude) * Math.cos((cordenada1.longitude)-(cordenada2.longitude)));
      // resultado = resultado ;
        resultado = resultado* Math.PI;
        resultado = resultado / 180;
        System.out.println("esto hay en resultado " + resultado);
        if (resultado <= distancia) {
            esta = true;
        }

        return esta;
    }


}
