package com.odoo.Calendrier.views;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.odoo.Calendrier.model.Calend;
import com.odoo.Etudiant.Model.Classes;
import com.odoo.Etudiant.Model.Classroom;
import com.odoo.Etudiant.Model.matiere;
import com.odoo.Etudiant.Model.typee;
import com.odoo.HistoryPay.model.HistoryPayModel;
import com.odoo.HistoryPay.views.HistoryPayActivity;
import com.odoo.HistoryPay.views.HistoryPayFragment;
import com.odoo.R;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.base.addons.res.ResUsers;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.support.addons.fragment.BaseFragment;
import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
import com.odoo.core.utils.IntentUtils;
import com.odoo.core.utils.OControls;
import com.odoo.core.utils.OCursorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aymen on 30/05/2018.
 */

public class CalendFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> , SwipeRefreshLayout.OnRefreshListener,ISyncStatusObserverListener, OCursorListAdapter.OnViewBindListener ,AdapterView.OnItemClickListener  {
    public static final String TAG = CalendFragment.class.getSimpleName();
    private View mView;
    private ListView listView;
    private boolean syncRequested = false;
    private OCursorListAdapter mAdapter = null;
    Context context;
    ArrayList<String> _list;
    ImageView img_new;
    LinearLayout linearLayout,linearLayout1;

    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> menu = new ArrayList<>();
        menu.add(new ODrawerItem(TAG).setTitle("Emploi du temps")
                .setInstance(new CalendFragment())
                .setIcon(R.drawable.ic_time));

        return menu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_listview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        context= getContext();
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        //   img_new.setVisibility(View.INVISIBLE); TODO null object here

        listView = (ListView) mView.findViewById(R.id.listview);
        mAdapter = new OCursorListAdapter(getActivity(), null, R.layout.row_emploi);
        listView.setAdapter(mAdapter);
        mAdapter.setOnViewBindListener(this);
        listView.setOnItemClickListener(this);
        setHasSyncStatusObserver(TAG, this, db());
        getLoaderManager().initLoader(0, null, this);
        listView.setFastScrollAlwaysVisible(true);

    }

    @Override
    public Class<Calend> database() {
        return Calend.class;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
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
            OControls.setText(mView, R.id.title, "No UnPaid  found");
            OControls.setText(mView, R.id.subTitle, "Swipe ");
        }
        if (db().isEmptyTable() && !syncRequested) {
            syncRequested = true;
            onRefresh();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);

    }

    @Override
    public void onRefresh() {
        if (inNetwork()) {
            parent().sync().requestSync(Calend.AUTHORITY);
            setSwipeRefreshing(true);
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



        try{

            matiere mat = new matiere(context, null);
            ODataRow rows_mat = mat.select(
                    new String[]{"name"}
            ).get(row.getInt("subject_id")-1);

            OControls.setText(view, R.id.matiere, rows_mat.getString("name"));

            Classroom ma = new Classroom(context, null);
            ODataRow rows_classroom = ma.select(
                    new String[]{"name"}
            ).get(row.getInt("classroom_id")-1);
            OControls.setText(view, R.id.salle, rows_classroom.getString("name"));




        }
        catch (Exception e)

        {
            e.printStackTrace();
        }














        try{

            ResUsers resUsers = new ResUsers(context, null);
            ODataRow rows = resUsers.select(
                    new String[]{"name"}
            ).get(row.getInt("teacher_id")-1);

            OControls.setText(view, R.id.prof, rows.getString("name"));

            Classroom ma = new Classroom(context, null);
                ODataRow rows_classroom = ma.select(
                        new String[]{"name"}
                ).get(row.getInt("classroom_id")-1);
                OControls.setText(view, R.id.salle, rows_classroom.getString("name"));




        }
        catch (Exception e)

        {
            e.printStackTrace();
        }





         try {
            Classes classes = new Classes(context, null);
            List<ODataRow> rows_class = classes.select();
            for (ODataRow row_class : rows_class) {
                Classes classes1 = new Classes(context, null);
                ODataRow rows_c = classes1.select(
                        new String[]{"code"}
                ).get(row.getInt("class_id") - 1);
                      OControls.setText(view, R.id.ClassC, rows_c.getString("code"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        OControls.setText(view,R.id.start, row.getString("start_time"));
                OControls.setText(view,R.id.end, row.getString("end_time"));



    }

    private void loadActivity(ODataRow row) {
        Bundle data = new Bundle();
        if (row != null) {
            data = row.getPrimaryBundleData();
        }





        IntentUtils.startActivity(getActivity(), HistoryPayActivity.class, data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
