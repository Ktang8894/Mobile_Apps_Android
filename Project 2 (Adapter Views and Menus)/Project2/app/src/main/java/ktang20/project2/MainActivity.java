/*
Kevin Tang [ktang20]
CS 478 - Project 2
University of Illinois at Chicago

REFERENCES
http://www.mysamplecode.com/2012/07/android-listview-checkbox-example.html
https://developer.android.com/guide/topics/ui/menus.html
https://developer.android.com/training/appbar/actions.html
*/

package ktang20.project2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import android.util.Log;

import static ktang20.project2.R.id.button;
import static ktang20.project2.R.id.checkBox1;
import static ktang20.project2.R.id.mainList;


public class MainActivity extends AppCompatActivity {

    customAdapter itemsAdapter = null;

    //Create listview of songs
    public void listViewDisplay() {
        //Populate Listview
        ArrayList<Song> songList = new ArrayList<Song>();
        Song song = new Song("Childish Gambino - Heartbeat", 0, false);
        songList.add(song);
        song = new Song("DJ Snake - Let Me Love You", 1, false);
        songList.add(song);
        song = new Song("Dick Dale - Misirlou", 2, false);
        songList.add(song);
        song = new Song("Isles and Glaciers - Clush", 3, false);
        songList.add(song);
        song = new Song("ODESZA - Say My Name", 4, false);
        songList.add(song);
        song = new Song("Tristam & Braken - Frame of Mind", 5, false);
        songList.add(song);
        //Create adapter for listview
        itemsAdapter =
                new customAdapter(this, R.layout.row, songList);
        ListView listView = (ListView) findViewById(mainList);
        listView.setAdapter(itemsAdapter);

        //Listener for listview rows
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Gets the index
                Song song = (Song) parent.getItemAtPosition(position);
                //Also check the checkboxes if the row is clicked
                View child = parent.getChildAt(position);
                CheckBox box = (CheckBox) child.findViewById(R.id.checkBox1);
                box.toggle();
                //Toggle the song selection
                if (song.isSelected() == true) {
                    song.setSelected(false);
                }
                else {
                    song.setSelected(true);
                }
            }
        });

    }

    //Custom Adapter
    public class customAdapter extends ArrayAdapter<Song> {

        public ArrayList<Song> songList;


        public customAdapter(Context context, int textViewResourceID, ArrayList<Song> songList) {
            super(context, textViewResourceID, songList);
            this.songList = new ArrayList<Song>();
            this.songList.addAll(songList);
        }

        private class ViewHolder {
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.row, null);

                holder = new ViewHolder();
                holder.name = (CheckBox) convertView.findViewById(checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Song song = (Song) cb.getTag();
                        song.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Song song = songList.get(position);
            holder.name.setText(song.getName());
            holder.name.setChecked(song.isSelected());
            holder.name.setTag(song);

            return convertView;

        }

    }


    //Called by createPlaylist button in onOptionsItemSelected
    public void createPlaylist() {
        Intent grid = new Intent(MainActivity.this, GridActivity.class);
        //StringBuffer response = new StringBuffer();
        //response.append("Selected\n");
        ArrayList<Song> songList = itemsAdapter.songList;
        ArrayList<Song> temp = new ArrayList<Song>();
        int selectCount = 0;
        for (int i=0; i < songList.size(); i++){
            Song song = songList.get(i);
            if (song.isSelected()){
                //response.append("\n" + song.getName());
                selectCount++;
                temp.add(song);
           //     grid.putExtra("Song"+Integer.toString(i), song.getCode());
            }
            grid.putExtra("NumSongs", Integer.toString(selectCount));
        }

        if (selectCount == 0){
            Toast.makeText(getApplicationContext(), "At least 1 song required",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String []toGrid = new String[selectCount];
        for (int i=0; i<selectCount; i++) {
            Song song = temp.get(i);
            toGrid[i] = song.getName();
        }
        //Toast.makeText(getApplicationContext(), toGrid[1], Toast.LENGTH_SHORT).show(); //Debugging
        grid.putExtra("Songs", toGrid);


        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(); //Debugging
        //Intent grid = new Intent(MainActivity.this, GridActivity.class);

        startActivity(grid);
    }

    //Clear all checked boxes and set all selections to false
    public void clearSelections() {
        ArrayList<Song> songList = itemsAdapter.songList;
        ListView listView = (ListView) findViewById(mainList);
        for (int i=0; i < listView.getCount(); i++){
            View child = listView.getChildAt(i);
            CheckBox box = (CheckBox) child.findViewById(R.id.checkBox1);
            if (box.isChecked() == true) {
                box.toggle();
            }
            Song song = songList.get(i);
            song.setSelected(false);
        }
        return;
    }

    //Invert all selected boxes
    public void invertSelections() {
        ArrayList<Song> songList = itemsAdapter.songList;
        ListView listView = (ListView) findViewById(mainList);
        for (int i=0; i < listView.getCount(); i++){
            View child = listView.getChildAt(i);
            CheckBox box = (CheckBox) child.findViewById(R.id.checkBox1);
            box.toggle();
            Song song = songList.get(i);

            if (song.isSelected() == true) {
                song.setSelected(false);
            }
            else {
                song.setSelected(true);
            }
        }
        return;
    }

    //Check all boxes
    public void checkAll() {

        ArrayList<Song> songList = itemsAdapter.songList;
        ListView listView = (ListView) findViewById(mainList);
        for (int i=0; i < listView.getCount(); i++){
            View child = listView.getChildAt(i);
            CheckBox box = (CheckBox) child.findViewById(R.id.checkBox1);
            if (box.isChecked() == false){
                box.toggle();
            }
            Song song = songList.get(i);
            song.setSelected(true);
        }
        return;
    }

    //Options Menu Buttons
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     // Handle item selection
        switch (item.getItemId()) {
            case R.id.createPlaylist:
                createPlaylist();
                return true;
            case R.id.clear:
                clearSelections();
                return true;
            case R.id.invert:
                invertSelections();
                return true;
            case R.id.checkAll:
               checkAll();
                return true;
          default:
             return super.onOptionsItemSelected(item);
       }
    }

    public Button but;
    public void checkButtonClick() {
        but = (Button)findViewById(button); //Assign button1
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPlaylist();
            }
        });
    }

    //Initiates Option Menu Buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewDisplay();
        checkButtonClick();
    }
}
