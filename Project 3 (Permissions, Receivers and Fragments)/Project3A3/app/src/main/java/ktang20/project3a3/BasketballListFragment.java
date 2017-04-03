package ktang20.project3a3;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasketballListFragment extends Fragment {


    //Communicate with Activity
    BasketballListFragment.BasketballListListener activityCommander;
    public interface BasketballListListener {
        public void updateWebView(int position);
    }

    //OnAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //create interface instance
            activityCommander = (BasketballListFragment.BasketballListListener) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }


    public BasketballListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Create View
        View view = inflater.inflate(R.layout.fragment_basketball_list, container, false);

        //Populate listview
        String[] teams = {"Golden State Warriors", "San Antonio Spurs", "Chicago Bulls", "Cleveland Cavaliers",
                "Los Angeles Lakers", "Houston Rockets"};
        ListAdapter teamAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, teams);
        ListView teamListView = (ListView) view.findViewById(R.id.listView);
        teamListView.setAdapter(teamAdapter);

        //OnClickListener
        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                activityCommander.updateWebView(position);
            }
        });

        return view;
    }

}
