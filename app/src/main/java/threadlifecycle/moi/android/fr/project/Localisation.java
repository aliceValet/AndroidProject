package threadlifecycle.moi.android.fr.project;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


import static java.util.Objects.requireNonNull;

public class Localisation extends Fragment implements LocationListener, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener{
    EditText GameAddress;
    Button Add;
    LocationManager locationManager;
    String provider;
    String direccion;
    Location location;
    MapView test;

    Marker userLocation;

    Geocoder address;

    GoogleMap gogole;

    static final int REQUEST_ACCESS_FINE_LOCATION = 0;
    static final int REQUEST_ACCESS_COARSE_LOCATION = 1;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameAddress = (EditText)getActivity().findViewById(R.id.NewGame);
        Add = (Button)getActivity().findViewById(R.id.AddButton);


        // Get the location manager 
        locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider -> use  default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //TODO: Consider calling 
            // ActivityCompat#requestPermissions 
            // here to request the missing permissions, and then overriding 
            // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) 
            // to handle the case where the user grants the permission. See the documentation 
            // for ActivityCompat#requestPermissions for more details. 
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION);
        }
        location = locationManager.getLastKnownLocation(provider);

        //Je récupère le fragment contenant ma map
        //SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmaps);
        //SupportMapFragment mapFragment2 = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmaps);
        //on s'assure que la map est non null pour pouvoir la récupérer. la map est une tache asynchrone pour pouvoir l'actualiser sans freeze l'activite.
        //requireNonNull(mapFragment).getMapAsync((OnMapReadyCallback) getActivity());

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_localisation, container, false);

    }



    //Necessaire pour pouvoir implementer OnMapRedayCallback lorsque le fragment contenant la map a bien ete cree.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        //Map = fragment dans fragment donc maps est le fragment enfant du fragment localisation
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmaps);
        //SupportMapFragment mapFragment2 = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmaps);
        //on s'assure que la map est non null pour pouvoir la récupérer. la map est une tache asynchrone pour pouvoir l'actualiser sans freeze l'activite.
        requireNonNull(mapFragment).getMapAsync(this);

        GameAddress = (EditText)Objects.requireNonNull(getActivity()).findViewById(R.id.NewGame);
        Add = (Button)getActivity().findViewById(R.id.AddButton);

        //clicklistener sur le bouton
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccion = GameAddress.getText().toString();
                ConvertAdresstoLatLng(direccion);
            }
        });
    }



    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }
    public void ConvertLatLngtoAddress(LatLng latlng){
        address=new Geocoder(getActivity(),Locale.getDefault());
        List<Address> places;
        double lat = (double) (latlng.latitude);
        double log = (double) (latlng.longitude);
        try {
            places =address.getFromLocation(lat,log,1);
            String localAddress = places.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = places.get(0).getLocality();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void ConvertAdresstoLatLng(String stg){

    try {
            address = new Geocoder(getActivity(),Locale.getDefault());
            List<Address> places;
            places = address.getFromLocationName(String.valueOf(stg), 10);


            if (places == null)
            {
                //on lance une exception
                //à faire car erreur pour l'instant

            }
            else {
                /**
                 * Get latitude and longitude with address and add marker
                 */
                Address location = places.get(0);
                gogole.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(),location.getLongitude()))
                        .title("New saved game"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gogole = googleMap;
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
        }

        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACCESS_FINE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ACCESS_COARSE_LOCATION);
        }
        gogole.setMyLocationEnabled(true);
        gogole.setOnMyLocationButtonClickListener(this);
        gogole.setOnMyLocationClickListener(this);

        /*gogole.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                gogole.clear();
                gogole.addMarker(new MarkerOptions().position(latLng));
            }
        });*/

    }

}
