package com.metagain.frontend.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.metagain.frontend.R;
import com.metagain.frontend.controll.MeetingController;
import com.metagain.frontend.controll.implementations.MeetingControllerImpl;
import com.metagain.frontend.exceptions.CoordinatesFormatException;
import com.metagain.frontend.exceptions.NetworkErrorException;
import com.metagain.frontend.exceptions.handler.ActivityExceptionHandler;
import com.metagain.frontend.model.Friends;
import com.metagain.frontend.model.Meeting;
import com.metagain.frontend.model.storage.ProfileDataStorage;

import org.osmdroid.config.Configuration;
// import org.osmdroid.library.BuildConfig;
import com.metagain.frontend.BuildConfig;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity  {

    private MapView mapView;

    private double minZoom = 5;

    private double maxZoom = 19;

    private double[] markedMeetingPoint;

    private MeetingController meetingController = new MeetingControllerImpl();

    private Button confirmMeetingPoint;

    private ItemizedIconOverlay<OverlayItem> markerOverlay;

    ActivityExceptionHandler activityExceptionHandler = new ActivityExceptionHandler(this);

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Meeting meeting = (Meeting) intent.getSerializableExtra("meeting");

        setContentView(R.layout.activity_map);
        confirmMeetingPoint = findViewById(R.id.buttonConfirmMeetingPoint);
        confirmMeetingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    meeting.setMeetingPoint(markedMeetingPoint);
                    meetingController.updateMeetingPoint(meeting);
                    backToMeetings();
                } catch (CoordinatesFormatException e) {
                    activityExceptionHandler.handleCoordinatesFormatException();
                } catch (NetworkErrorException e) {
                    activityExceptionHandler.handleNetworkErrorException();
                }

            }
        });


        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);


        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mapView.setMultiTouchControls(true);
        mapView.setMinZoomLevel(minZoom);
        mapView.setMaxZoomLevel(maxZoom);
        mapView.setScrollableAreaLimitLatitude(MapView.getTileSystem().getMaxLatitude(), MapView.getTileSystem().getMinLatitude(), 0);

        GeoPoint currentPosition = new GeoPoint(ProfileDataStorage.getLatitude(), ProfileDataStorage.getLongitude());

        ArrayList<OverlayItem> items = new ArrayList<>();
        markerOverlay = new ItemizedIconOverlay<>(items,
                getDrawable(org.osmdroid.library.R.drawable.marker_default), null, this);

        mapView.getOverlays().add(markerOverlay);

        Marker currentPositionMarker = new Marker(mapView);
        currentPositionMarker.setPosition(currentPosition);
        Drawable myLocationIcon = getDrawable(org.osmdroid.library.R.drawable.osm_ic_center_map);
        currentPositionMarker.setIcon(myLocationIcon);

        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                GeoPoint clickedPoint = (GeoPoint) mapView.getProjection().fromPixels(
                        (int) event.getX(),
                        (int) event.getY());



                OverlayItem markerItem = new OverlayItem("Meetingpoint Marker", "Click", clickedPoint);
                markerOverlay.removeAllItems();
                markerOverlay.addItem(markerItem);
                mapView.invalidate();


                confirmMeetingPoint.setVisibility(View.VISIBLE);


                markedMeetingPoint = new double[]{clickedPoint.getLatitude(), clickedPoint.getLongitude()};


                return true;
            }
        });

        mapView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return  gestureDetector.onTouchEvent(event);

            }
        });

        mapView.getController().setZoom(17.0);

        mapView.getOverlayManager().add(currentPositionMarker);
        System.out.println(meeting.getMeetingPoint());
        if (meeting.getMeetingPoint() != null) {
            GeoPoint meetingPoint = new GeoPoint(meeting.getMeetingPoint()[0], meeting.getMeetingPoint()[1]);
            OverlayItem markerItem = new OverlayItem("Meetingpoint Marker", "Click", meetingPoint);
            markerOverlay.addItem(markerItem);
            mapView.invalidate();
            mapView.setExpectedCenter(meetingPoint);
        } else {
            mapView.setExpectedCenter(currentPosition);
        }


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

    public void backToMeetings() {
        Intent intent = new Intent(this, MeetingsView.class);
        startActivity(intent);
    }

}
