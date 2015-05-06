package app.com.example.android.sunshine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view, it's blank fragment
     * The fragment was created as an inner class, but it doesn't need to be an inner class
     */
    public static class PlaceholderFragment extends Fragment {

        /*these are the steps for creating a scrollable list (populate a ListView):

        * 1. We create the Activity or fragmnet XML to display the ListView control

        * 2. Declare an ArrayAdapter outside the onCreateView class

        * 3. Within the onCreateView class, declare and assign a List<String> to hold the data
        * (List<> better than ArrayList<> ,
        * in case we change from ArrayList to LinkedList or others without needing to refactor)

        * 4. The adpter we have created will need to know the following
        * (Context, the ListViewLayout XML ID , the list item textView ID, the data)

        * 5. We reference the ListView where the list items will appear to create a Java object

        * 6. We attach the adapter to this newly created ListView object using the setAdapter() method
        * */

        //STEP 2 of 6 : Declaring the ArrayAdpter (we split the declarion and assignment before the onCreateView class
        // because we will not be able to declare mForecaastAdapter as private down in the code)
        private ArrayAdapter<String> mForecastAdapter;

        public PlaceholderFragment() {
        }



        @Override
        public View onCreateView(LayoutInflater inflater,
                                 //giving the fragment a view within onCreateView :
                                 //
                                 // To turn an xml layout into java view objects,
                                 // we need to inflate the layout. After the layout is inflated,
                                 // we need to associate it with an Activity or Fragment.
                                 // This process of inflating and
                                 // associating is a little different depending on whether
                                 // it’s a layout for an Activity or Fragment.
                                 ViewGroup container,
                                 Bundle savedInstanceState) {
            //associating the the xml layout with a fragment
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //Create an array (bogus data in an array to pull from, you could be pulling data from the web
            String[] forecastArray = {
                    "Today - Sunny - 88/63",
                    "Tomorrow - Foggy - 70/40",
                    "Weds - Cloudy - 72/63",
                    "Thurs - Asteroids - 75/65",
                    "Fri - Heavy Rain - 65/56",
                    "Sat - HELP TRAPPED IN WEATHERSTATION - 60/51",
                    "Sun - Sunny - 80/68"
            };

            //STEP 3 of 6 : Create an List<String> to hold the data

            List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

            /*
            Above we've used List
            List is more liberal than ArrayList, since List can be any kind of List implementation e.g. an ArrayList,
             a LinkedList or FrancosSpecialList.
            Hence it is a good idea to be liberal and accept any kind of list
            since you may want to change the implementation later.
            */

            //STEP 4 of 6 : passing on the parameters to the adapter (mForecastAdaper was declared above,
            // now being assigned)
            mForecastAdapter =
                    new ArrayAdapter<String>(
                            //the context (the parent activity)
                            getActivity(),
                            //ID of list item layout (the xml file that determines how the list will look like)
                            //here we will use a custom XML file that we have created, we can also use a generic layout
                            //provided by android, then we don't need to create our own XML file
                            R.layout.list_item_forecast,
                            //ID for textView to populate (the view where each list item will go in the layout)
                            R.id.list_item_forecast_textview,
                            //finally where the data will be coming from to fill the item list
                            weekForecast
                    );

            /*The ListView takes the ListAdapter instance through its setAdapter method, as shown below
            providing the ListAdapter to the ListView */

                            //STEP 5 of 6 :  Get a reference to the ListView object
                            ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
                            //The ListView takes the ListAdapter instance through its setAdapter method,
                            //STEP 6 of 6 : This is how we finally attach the adapter to the ListView :
                            listView.setAdapter(mForecastAdapter);

            //finally it returns a view to the fragment?
            return rootView;
        }
    }
}
