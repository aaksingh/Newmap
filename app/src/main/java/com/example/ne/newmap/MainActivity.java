package com.example.ne.newmap;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import static com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition;

public class MainActivity extends Activity implements OnMapReadyCallback{
    MarkerOptions ggsipu;
    GoogleMap m_map;
    boolean mapReady=false;
    static final CameraPosition bp = CameraPosition.builder()
                                        .target(new LatLng(28.5946,77.0184))
            .zoom(21)
            .bearing(0)
            .tilt(45)
            .build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap=(Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        Button btnSatellite=(Button)findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        }); Button btnHybrid=(Button)findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
        Button btnbp=(Button)findViewById(R.id.btnBP);
        btnbp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(mapReady){
                    flyTo(bp);
                }
            }
        });
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

             ggsipu=new MarkerOptions().position(new LatLng(28.5946, 77.0184)).title("GGSIPU");
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady = true;
        m_map = map;
        LatLng delhi = new LatLng(28.7041, 77.1025);
        CameraPosition target = CameraPosition.builder().target(delhi).zoom(14).build();
        m_map.moveCamera(newCameraPosition(target));
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
    }

    private void flyTo(CameraPosition target){
        m_map.addMarker(ggsipu);
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target),3000,null);
    }
}
