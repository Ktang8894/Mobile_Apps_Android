package ktang20.project3a3;


import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class BaseballWebFragment extends Fragment {

    private static WebView webView;
    private static String url;
    private Bundle webViewBundle;

    public void setWebView(int position) {
        url = "http://m.cubs.mlb.com/";
        if (position == 0) {
            url = "http://m.cubs.mlb.com/";
        }
        else if (position == 1) {
            url = "http://m.dodgers.mlb.com/";
        }
        else if (position == 2) {
            url = "http://m.yankees.mlb.com/";
        }
        else if (position == 3) {
            url = "http://m.mets.mlb.com/";
        }
        else if (position == 4) {
            url = "http://m.phillies.mlb.com/";
        }
        else if (position == 5) {
            url = "http://m.giants.mlb.com/";
        }
       // webView.setWebViewClient(new WebViewClient());
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl(url);
    }


    public BaseballWebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ViewGroup.LayoutParams params = webView.getLayoutParams();
            params.width = 1080;
            webView.setLayoutParams(params);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            ViewGroup.LayoutParams params = webView.getLayoutParams();
            params.width = 1080;
            webView.setLayoutParams(params);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baseball_web, container, false);
        webView = (WebView) view.findViewById(R.id.webView);
        //webView.loadUrl("http://sanfrancisco.giants.mlb.com/index.jsp?c_id=sf");
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        if (webViewBundle != null)
        {
            webView.restoreState(webViewBundle);
        }
        else
        {
            webView.loadUrl(url);;
        }

        return view;
    }

    public void onPause() {
        super.onPause();
        webViewBundle = new Bundle();
        webView.saveState(webViewBundle);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

}
