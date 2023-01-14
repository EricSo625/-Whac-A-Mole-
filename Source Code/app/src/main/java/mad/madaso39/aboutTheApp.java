package mad.madaso39;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class aboutTheApp extends AppCompatActivity {
    VideoView vView; // to refer the videoview, for showing our story video
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_the_app);
        vView = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vView);
        vView.setMediaController(mediaController);
        vView.setKeepScreenOn(true);
        vView.setVideoPath("android.resource://"
                + getPackageName() + "/" + R.raw.promotional_video);
        vView.start();
    }
}