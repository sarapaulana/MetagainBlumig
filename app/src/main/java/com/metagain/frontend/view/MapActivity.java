package com.metagain.frontend.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.metagain.frontend.R;
import com.metagain.frontend.model.storage.ProfileDataStorage;

import org.osmdroid.config.Configuration;
// import org.osmdroid.library.BuildConfig;
import com.metagain.frontend.BuildConfig;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    private int maxZoom = 18;

    private int minZoom = 10;

    private static int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);


        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setMultiTouchControls(true);

        GeoPoint currentPosition = new GeoPoint(ProfileDataStorage.getLatitude(), ProfileDataStorage.getLongitude());

        Marker currentPositionMarker = new Marker(mapView);
        currentPositionMarker.setPosition(currentPosition);
        Drawable myLocationIcon = getDrawable(org.osmdroid.library.R.drawable.osm_ic_center_map);
        currentPositionMarker.setIcon(myLocationIcon);


        mapView.getController().setZoom(18.0);

        mapView.setExpectedCenter(currentPosition);

        mapView.getOverlayManager().add(currentPositionMarker);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDetach();
    }

}
