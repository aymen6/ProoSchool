package com.odoo.Etudiant.Views;

/**
 * Created by Aymen on 10/04/2018.
 */
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.app.NotificationCompat;

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



public class SanctionFragment extends BaseFragment implements
        LoaderManager.LoaderCallbacks<Cursor> ,
        SwipeRefreshLayout.OnRefreshListener,
        ISyncStatusObserverListener,
        OCursorListAdapter.OnViewBindListener
        ,AdapterView.OnItemClickListener ,
        IOnSearchViewChangeListener
{
    public static final String TAG = SanctionFragment.class.getSimpleName();
    private static String X = "";
    private View mView;
    private ListView listView;
    private boolean syncRequested = false;
    private OCursorListAdapter mAdapter = null;
    Context context;
    Runnable timedTask;
    ArrayList<String> friends = new ArrayList<String>();
     private String mCurFilter = null;

    NotificationManager manager;
    static int idk=1;
    ArrayAdapter<String> arrayAdapter;
    TextView title1;


    public static final String KEY = Sanction.class.getSimpleName();


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
        title1=(TextView)mView.findViewById(R.id._TypeSanction);

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
    public Class<Sanction> database() {
        return Sanction.class;
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
            OControls.setText(mView, R.id.subTitle, "Swipe to check new Sanction Student");
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
            parent().sync().requestSync(Sanction.AUTHORITY);
            setSwipeRefreshing(true);
            parent().refreshDrawer();
           int a=5;
           int b=2;
            ODataRow row = new ODataRow();
            //String r=row.getString("state");
                Notifcation();





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

    public void Notifcation()
    {
        Sanction sanction= new Sanction(context, null);
        int total = sanction.count("state = ?", new String[]{"sent"});
        String textNotifcation="aaaa";

        NotificationCompat.Builder nbulid = new NotificationCompat.Builder(context)
                .setContentTitle("Sanction et descpline ")
                .setContentText("message Non lu" + total)
                .setSmallIcon(R.drawable.ic_app);

        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, nbulid.build());
        idk++;

    }


    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {




String ok=user().getUsername();
        typee type = new typee(context, null);
        ODataRow rows_type = type.select(
                new String[]{"name"}
        ).get(row.getInt("type_id") - 1);

             //title1.setBackgroundColor(Color.GREEN); //this is green color
            OControls.setText(view, R.id._TypeSanction, rows_type.getString("name"));




                OControls.setText(view, R.id.title, row.getString("name"));

        try {
            // Retrive Code  class*/
            ResPartner partner = new ResPartner(context, null);
            ODataRow rows = partner.select(
                    new String[]{"name"}
            ).get(row.getInt("student_id") - 1);

                OControls.setText(view, R.id._StudentSanction, rows.getString("name"));

        } catch (Exception e) {
            e.printStackTrace();
        }

          // Toast.makeText(view.getContext(), "id" + ok, Toast.LENGTH_SHORT).show();

        //   Toast.makeText(view.getContext(), "id" + rows.getString("name").toString(), Toast.LENGTH_SHORT).show();
        try {
            Classes classes = new Classes(context, null);
            List<ODataRow> rows_class = classes.select();
            for (ODataRow row_class : rows_class) {
                Classes classes1 = new Classes(context, null);
                ODataRow rows_c = classes1.select(
                        new String[]{"code"}
                ).get(row.getInt("class_id") - 1);



            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
    private void loadActivity(ODataRow row) {
        Bundle data = new Bundle();
        if (row != null) {
            data = row.getPrimaryBundleData();
        }
        //// TODO: 29/03/2018 read from data row


            typee type = new typee(context, null);
            ODataRow rows_type = type.select(
                    new String[]{"name"}
            ).get(row.getInt("type_id") - 1);
            data.putString("type", rows_type.getString("name"));


            ResPartner partner = new ResPartner(context, null);
            ODataRow rows = partner.select(
                    new String[]{"name"}
            ).get(row.getInt("student_id") - 1);
            data.putString("student_name", rows.getString("name"));
            data.putString("message", row.getString("message"));
            data.putString("date", row.getString("date"));
            data.putString("Title", row.getString("name"));


            IntentUtils.startActivity(getActivity(), DetailsSanction.class, data);
        }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
        Sanction sanction= new Sanction(context, null);
        String sqlInsert = "INSERT INTO proschool_sanction (name)" +"VALUES ('name')";


                ODataRow row = OCursorUtils.toDatarow((Cursor) mAdapter.getItem(position));

            loadActivity(row);


        }
    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
//to show numbr of message


        Sanction sanction= new Sanction(context, null);
            int total = sanction.count("state = ?", new String[]{"sent"});

               List<ODrawerItem> menu = new ArrayList<>();
         menu.add(new ODrawerItem(TAG).setTitle("Sanction et discipline")
                .setInstance(new SanctionFragment())
                .setCounter(total)
                .setIcon(R.drawable.ic_sanction_menu));


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
