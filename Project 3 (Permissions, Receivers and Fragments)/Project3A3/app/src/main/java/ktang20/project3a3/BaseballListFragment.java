package ktang20.project3a3;


import android.app.Activity;
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
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseballListFragment extends Fragment {

    //Communicate with Activity
    BaseballListListener activityCommander;
    public interface BaseballListListener {
        public void updateWebView(int position);
    }

    //OnAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //create interface instance
            activityCommander = (BaseballListListener) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }


    public BaseballListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Create View
        View view = inflater.inflate(R.layout.fragment_baseball_list, container, false);

        //Populate listview
        String[] teams = {"Chicago Cubs", "Los Angeles Dodgers", "New York Yankees",
                "New York Mets", "Philadelphia Philles", "San Francisco Giants"};
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
