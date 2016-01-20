package com.example.android.sunshine.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private static final String LOG_CLASS = DetailActivityFragment.class
            .getSimpleName();
    private static final String SUNSHINE_HASHNAME = "#SunshineApp";

    private ShareActionProvider mShareActionProvider;
    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        String msg = intent.getStringExtra(Intent.EXTRA_TEXT);
        Log.v(LOG_CLASS, msg);
        TextView textView = (TextView) rootView.findViewById(R.id.detail_text);
        textView.setText(msg);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.detail, menu);
        MenuItem share = menu.findItem(R.id.action_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(share);
        Log.v(LOG_CLASS, "shareActionProvider is initialized: " + mShareActionProvider);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.v(LOG_CLASS, "Item is clicked." + id);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_share) {
            Log.v(LOG_CLASS, "share button clicked.");
            share();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void share() {
        String msg = getActivity().getIntent().getStringExtra(Intent.EXTRA_TEXT);
        Log.v(LOG_CLASS, msg + SUNSHINE_HASHNAME);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, msg + SUNSHINE_HASHNAME);
        mShareActionProvider.setShareIntent(intent);
    }
}
