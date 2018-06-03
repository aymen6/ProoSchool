package com.odoo.Notes.Views;

import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.odoo.Calendrier.views.CalendActivity;
import com.odoo.Etudiant.Model.Classes;
import com.odoo.Etudiant.Model.Sanction;
import com.odoo.Etudiant.Model.typee;
import com.odoo.Notes.Model.note;
import com.odoo.R;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.addons.fragment.IOnSearchViewChangeListener;
import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.IntentUtils;
import com.odoo.core.utils.OControls;
import com.odoo.core.utils.OCursorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aymen on 31/05/2018.
 */

public class NotesFragment extends BaseFragment implements
        LoaderManager.LoaderCallbacks<Cursor> ,
        SwipeRefreshLayout.OnRefreshListener,
        ISyncStatusObserverListener,
        OCursorListAdapter.OnViewBindListener
        ,AdapterView.OnItemClickListener ,
        IOnSearchViewChangeListener
{
    public static final String TAG = com.odoo.Notes.Views.NotesFragment.class.getSimpleName();
     private View mView;
    private ListView listView;
    private boolean syncRequested = false;
    private OCursorListAdapter mAdapter = null;
    Context context;
      private String mCurFilter = null;

    NotificationManager manager;
     TextView title1;


    public static final String KEY = ResPartner.class.getSimpleName();
    private String NoteStudent;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setHasSyncStatusObserver(KEY, this, db());
        View view= inflater.inflate(R.layout.common_listview, container, false);

        return  view;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        context= getContext();
        super.onViewCreated(view, savedInstanceState);

        mView = view;
        parent().setHasActionBarSpinner(true);

        parent().refreshDrawer();
        //   img_new.setVisibility(View.INVISIBLE); TODO null object here
        listView = (ListView) mView.findViewById(R.id.listview);
        //title1=(TextView)mView.findViewById(R.id._TypeSanction);

        mAdapter = new OCursorListAdapter(getActivity(), null, R.layout.sanction_row_item);
        listView.setAdapter(mAdapter);
        mAdapter.setOnViewBindListener(this);
        listView.setOnItemClickListener(this);
        mAdapter.setHasSectionIndexers(true, "name");
        listView.setAdapter(mAdapter);
        listView.setFastScrollAlwaysVisible(true);
        listView.setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);


    }

    @Override
    public Class<ResPartner> database() {
        return ResPartner.class;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle data) {
      /*   List<String> args = new ArrayList<>();
         args.add("true");
        if (mCurFilter != null) {

            args.add(mCurFilter + "%");
        }
        String[] selectionArgs = (args.size() > 0) ? args.toArray(new String[args.size()]) : null;
        return new CursorLoader(getActivity(), db().uri(),
                null, null, selectionArgs, "name");*/
        return new CursorLoader(getActivity(), db().uri(), null, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mAdapter.changeCursor(data);
        if (data.getCount() > 0) {
            OControls.setGone(mView, R.id.loadingProgress);
            OControls.setVisible(mView, R.id.swipe_container);
            OControls.setGone(mView, R.id.data_list_no_item);
            setHasSwipeRefreshView(mView, R.id.swipe_container, this);

        } else {
            OControls.setGone(mView, R.id.loadingProgress);
            OControls.setGone(mView, R.id.swipe_container);
            OControls.setVisible(mView, R.id.data_list_no_item);
            OControls.setText(mView, R.id.title, "No Sanction  found");
            OControls.setText(mView, R.id.subTitle, "Swipe to check new Note");
        }
        if (db().isEmptyTable() && !syncRequested) {
            syncRequested = true;
            if(inNetwork()) {
                parent().refreshDrawer();

                onRefresh();
            }
            else
            {
                Toast.makeText(context, "please check your connection and try again", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);

    }

    @Override
    public void onRefresh() {
        if (inNetwork()) {
            parent().sync().requestSync(ResPartner.AUTHORITY);
            setSwipeRefreshing(true);
            parent().refreshDrawer();







        } else {
            hideRefreshingProgress();
            Toast.makeText(getActivity(), _s(R.string.toast_network_required), Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onStatusChange(Boolean changed) {
        if(changed){
            getLoaderManager().restartLoader(0, null, this);


        }
    }



    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {
        try {

            ResPartner partner = new ResPartner(context, null);

            List<ODataRow> ints = row.getO2MRecord("student_gradebook_ids").browseEach();
            for(ODataRow r : ints){
                r = partner.select(

                        new String[]{"name"}
                ).get(r.getInt("student_gradebook_ids"));
                OControls.setText(view, R.id.title, row.getString("name"));

            }



            if (ints.size() > 0) {
                List<String> rec_ids = new ArrayList<>();


                for (ODataRow o2mR : ints) {



                    if (o2mR.getString("average") != null)
                        rec_ids.add(o2mR.getString("average"));
                }


                ints = partner.select(
                        new String[]{"name", "parent_id", "city"},
                        "city = ?",
                        new String[]{"Gandhinagar"}
                );


            }
        }

 catch(Exception e3)
            {
                e3.printStackTrace();
            }


        try {
            ResPartner notes = new ResPartner(context, null);
            List<ODataRow> rows_class = notes.select();
            for (ODataRow row_class : rows_class) {
                ResPartner classes1 = new ResPartner(context, null);
                 ODataRow rows_c = classes1.select(

                        new String[]{"student_gradebook_ids"}
                ).get(row.getInt("parent_id")-1);

            }}
        catch(Exception e)
        {
            e.printStackTrace();
        }










        // Toast.makeText(view.getContext(), "id" + ok, Toast.LENGTH_SHORT).show();

        //   Toast.makeText(view.getContext(), "id" + rows.getString("name").toString(), Toast.LENGTH_SHORT).show();











    }
    private void loadActivity(ODataRow row) {
        Bundle data = new Bundle();


        IntentUtils.startActivity(getActivity(), CalendActivity.class, data);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {


        ODataRow row = OCursorUtils.toDatarow((Cursor) mAdapter.getItem(position));

        loadActivity(row);


    }
    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
//to show numbr of message




        List<ODrawerItem> menu = new ArrayList<>();
        menu.add(new ODrawerItem(TAG).setTitle("Note")
                .setInstance(new com.odoo.Notes.Views.NotesFragment())
                .setCounter(23)
                .setIcon(R.drawable.ic_note));


        return menu;
    }


    @Override
    public boolean onSearchViewTextChange(String newFilter) {
        mCurFilter = newFilter;
        //  getLoaderManager().restartLoader(0, null, this);
        return true;
    }

    @Override
    public void onSearchViewClose() {
        // nothing to do here

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_partners, menu);
        setHasSearchView(this, menu, R.id.menu_partner_search);
    }
}
