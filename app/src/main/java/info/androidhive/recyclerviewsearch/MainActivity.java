package info.androidhive.recyclerviewsearch;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.ContactsAdapterListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    // url to fetch contacts json
    private static final String URL = "https://api.androidhive.info/json/contacts.json";
    private RecyclerView recyclerView;
    private List<Contact> contactList;
    private ContactsAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        recyclerView = findViewById(R.id.recycler_view);
        contactList = new ArrayList<>();

        /*for (int i = 97; i <= 122; i++) {
            contactList.add(new Contact(Character.toString((char) i), "", "12345", false));
        }*/

        contactList.add(new Contact("apple", "", "12345", false));
        contactList.add(new Contact("ball", "", "12345", false));
        contactList.add(new Contact("cat", "", "12345", false));
        contactList.add(new Contact("dog", "", "12345", false));
        contactList.add(new Contact("egg", "", "12345", false));
        contactList.add(new Contact("fish", "", "12345", false));
        contactList.add(new Contact("goat", "", "12345", false));
        contactList.add(new Contact("hat", "", "12345", false));
        contactList.add(new Contact("ice", "", "12345", false));
        contactList.add(new Contact("joker", "", "12345", false));
        contactList.add(new Contact("kite", "", "12345", false));
        contactList.add(new Contact("lion", "", "12345", false));
        contactList.add(new Contact("mango", "", "12345", false));
        contactList.add(new Contact("nest", "", "12345", false));
        contactList.add(new Contact("owl", "", "12345", false));
        contactList.add(new Contact("pencil", "", "12345", false));
        contactList.add(new Contact("queen", "", "12345", false));
        contactList.add(new Contact("rat", "", "12345", false));
        contactList.add(new Contact("ship", "", "12345", false));
        contactList.add(new Contact("toy", "", "12345", false));
        contactList.add(new Contact("umbrella", "", "12345", false));
        contactList.add(new Contact("van", "", "12345", false));
        contactList.add(new Contact("watch", "", "12345", false));
        contactList.add(new Contact("xerox", "", "12345", false));
        contactList.add(new Contact("yard", "", "12345", false));
        contactList.add(new Contact("zebra", "", "12345", false));

        mAdapter = new ContactsAdapter(this, contactList, this);

        // white background notification bar
        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(Contact contact) {
        Toast.makeText(getApplicationContext(), "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show();
    }
}
