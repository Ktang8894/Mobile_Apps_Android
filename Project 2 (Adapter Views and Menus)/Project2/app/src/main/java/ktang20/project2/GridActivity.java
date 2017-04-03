/*
REFERENCES
https://developer.android.com/guide/topics/ui/layout/gridview.html
https://developer.android.com/guide/topics/ui/menus.html#FloatingContextMenu
 */

package ktang20.project2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    public ArrayList<Integer> songList = new ArrayList<Integer>();

    public void getSongs() {
        //StringBuffer response = new StringBuffer();
        Intent grid = getIntent();
        int numSongs = Integer.parseInt(grid.getStringExtra("NumSongs"));
        int []songs = new int[numSongs];
        for(int i=0; i<numSongs; i++) {
            if ((grid.getStringArrayExtra("Songs")[i]).equals("Childish Gambino - Heartbeat")) {
                //songs[i] = R.drawable.childish_gambino;
                songList.add(R.drawable.childish_gambino);
            }
            else if ((grid.getStringArrayExtra("Songs")[i]).equals("DJ Snake - Let Me Love You")) {
               // songs[i] = R.drawable.dick_dale;
                songList.add(R.drawable.dj_snake);
            }
            else if ((grid.getStringArrayExtra("Songs")[i]).equals("Dick Dale - Misirlou")) {
                //songs[i] = R.drawable.dj_snake;
                songList.add(R.drawable.dick_dale);
            }
            else if ((grid.getStringArrayExtra("Songs")[i]).equals("Isles and Glaciers - Clush")) {
                //songs[i] = R.drawable.isles_and_glaciers;
                songList.add(R.drawable.isles_and_glaciers);
            }
            else if ((grid.getStringArrayExtra("Songs")[i]).equals("ODESZA - Say My Name")) {
                //songs[i] = R.drawable.odesza;
                songList.add(R.drawable.odesza);
            }
            else if ((grid.getStringArrayExtra("Songs")[i]).equals("Tristam & Braken - Frame of Mind")) {
                //songs[i] = R.drawable.tristam_and_braken;
                songList.add(R.drawable.tristam_and_braken);
            }
            //songs[i] = grid.getStringArrayExtra("Songs")[i];
            //   response.append(songs[i] + "\n");
        }
        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(); //Debugging
        //Toast.makeText(getApplicationContext(), Integer.toString(numSongs), Toast.LENGTH_SHORT).show(); //Debugging

    }

    //Helper function to start a browser using the selected URL
    public void startBrowser(String url) {
        Intent browser = new Intent();
        browser.setAction(Intent.ACTION_VIEW);
        browser.setData(Uri.parse(url));
        if (browser.resolveActivity(getPackageManager()) != null) {
            startActivity(browser);
        }
    }

    //Helper function to play selected song on youtube
    public void playSong (int position) {
        if (songList.get(position).equals(R.drawable.childish_gambino)) {
            startBrowser("https://www.youtube.com/watch?v=uGGIrvWIFKw");
        }
        else if (songList.get(position).equals(R.drawable.dj_snake)) {
            startBrowser("https://www.youtube.com/watch?v=euCqAq6BRa4");
        }
        else if (songList.get(position).equals(R.drawable.dick_dale)) {
            startBrowser("https://www.youtube.com/watch?v=lRH_70_Foow");
        }
        else if (songList.get(position).equals(R.drawable.isles_and_glaciers)) {
            startBrowser("https://www.youtube.com/watch?v=llxWoDjANDA");
        }
        else if (songList.get(position).equals(R.drawable.odesza)) {
            startBrowser("https://www.youtube.com/watch?v=HdzI-191xhU");
        }
        else if (songList.get(position).equals(R.drawable.tristam_and_braken)) {
            startBrowser("https://www.youtube.com/watch?v=SCD2tB1qILc");
        }
    }

    //Helper function to open the song (or album) wikipedia page
    public void songWiki (int position) {
        if (songList.get(position).equals(R.drawable.childish_gambino)) {
            startBrowser("https://en.wikipedia.org/wiki/Heartbeat_(Childish_Gambino_song)");
        }
        else if (songList.get(position).equals(R.drawable.dj_snake)) {
            startBrowser("https://en.wikipedia.org/wiki/Let_Me_Love_You_(DJ_Snake_song)");
        }
        else if (songList.get(position).equals(R.drawable.dick_dale)) {
            startBrowser("https://en.wikipedia.org/wiki/Misirlou");
        }
        else if (songList.get(position).equals(R.drawable.isles_and_glaciers)) {
            startBrowser("https://en.wikipedia.org/wiki/The_Hearts_of_Lonely_People");
        }
        else if (songList.get(position).equals(R.drawable.odesza)) {
            startBrowser("https://en.wikipedia.org/wiki/Odesza#Singles");
        }
        else if (songList.get(position).equals(R.drawable.tristam_and_braken)) {
            startBrowser("https://en.wikipedia.org/wiki/Monstercat#Compilation_Albums");
        }
    }

    //Helper function to open the artist wiki page
    public void artistWiki (int position) {
        if (songList.get(position).equals(R.drawable.childish_gambino)) {
            startBrowser("https://en.wikipedia.org/wiki/Donald_Glover");
        }
        else if (songList.get(position).equals(R.drawable.dj_snake)) {
            startBrowser("https://en.wikipedia.org/wiki/DJ_Snake");
        }
        else if (songList.get(position).equals(R.drawable.dick_dale)) {
            startBrowser("https://en.wikipedia.org/wiki/Dick_Dale");
        }
        else if (songList.get(position).equals(R.drawable.isles_and_glaciers)) {
            startBrowser("https://en.wikipedia.org/wiki/Isles_%26_Glaciers");
        }
        else if (songList.get(position).equals(R.drawable.odesza)) {
            startBrowser("https://en.wikipedia.org/wiki/Odesza");
        }
        else if (songList.get(position).equals(R.drawable.tristam_and_braken)) {
            startBrowser("https://en.wikipedia.org/wiki/Monstercat");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);
        final GridView gridview = (GridView) findViewById(R.id.gridview);

        getSongs();

        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                playSong(position);
                //Toast.makeText(GridActivity.this, "Test", Toast.LENGTH_SHORT).show(); //Debugging
            }
        });

        //Wait for longclick to open context menu
        registerForContextMenu(gridview);

    }

    //Position selected from longclick in gridview
    int selected;
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.play:
                playSong(selected);
                return true;
            case R.id.songWiki:
                songWiki(selected);
                return true;
            case R.id.artistWiki:
                artistWiki(selected);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //Context Menu Init
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        int position = ((AdapterView.AdapterContextMenuInfo)menuInfo).position;
        selected = position;
    }

    //Image Adapter Class
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
            //return mThumbIds[position];
        }

        public long getItemId(int position) {
            return 0;
        }

        public void songsToArray() {
            int n = songList.size();
            StringBuffer response = new StringBuffer();
            for (int i=0; i<n; i++) {
                mThumbIds[i] = songList.get(i);
                //response.append(Integer.toString(mThumbIds[i]) + "\n");
            }
            // Toast.makeText(getApplicationContext(), Integer.toString(songList.size()), Toast.LENGTH_SHORT).show(); //Debugging
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            songsToArray();
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = new Integer[songList.size()];

    }

}


