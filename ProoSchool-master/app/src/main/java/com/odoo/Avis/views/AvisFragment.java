package com.odoo.Avis.views;

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
import android.widget.ListView;
import android.widget.Toast;

import com.odoo.Avis.model.ParentAvis;
import com.odoo.Etudiant.Model.Classes;
import com.odoo.R;
import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.support.OUser;
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
 * Created by Aymen on 10/04/2018.
 */

public class AvisFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> , SwipeRefreshLayout.OnRefreshListener,ISyncStatusObserverListener, OCursorListAdapter.OnViewBindListener ,AdapterView.OnItemClickListener  {
    public static final String TAG = AvisFragment.class.getSimpleName();
    private View mView;
    private ListView listView;
    private boolean syncRequested = false;
    private OCursorListAdapter mAdapter = null;
    int pos;
    Context context;
    ArrayList<String> _list;
    ImageView img_new;


    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> menu = new ArrayList<>();
        menu.add(new ODrawerItem(TAG).setTitle("Avis au parent ")
                .setInstance(new AvisFragment())
                .setIcon(R.drawable.ic_avis));

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

        listView = (ListView) mView.findViewById(R.id.listview);
        mAdapter = new OCursorListAdapter(getActivity(), null, R.layout.avis_row_item);
        listView.setAdapter(mAdapter);
        mAdapter.setOnViewBindListener(this);
        listView.setOnItemClickListener(this);

        setHasSyncStatusObserver(TAG, this, db());
        getLoaderManager().initLoader(0, null, this);
        listView.setFastScrollAlwaysVisible(true);




    }

    @Override
    public Class<ParentAvis> database() {
        return ParentAvis.class;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

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
            OControls.setText(mView, R.id.title, "No Avis  found");
            OControls.setText(mView, R.id.subTitle, "Swipe to check new Avis");
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
            parent().sync().requestSync(ParentAvis.AUTHORITY);
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

        //  OControls.setText(view, R.id._numberAvis,  );

        //TODO block Try catch !


        try {
            ResPartner partner1 = new ResPartner(context, null);
            ODataRow rows1 = partner1.select(
                    new String[]{"company_name"}
            ).get(row.getInt("student_id") - 1);
            String RefParent_after_Cut = (rows1.getString("company_name").toString().split("\\]")[0].split("\\[")[1]);


            // Toast.makeText(view.getContext(), "2:"+RefParent_after_Cut.toString(), Toast.LENGTH_SHORT).show();


            OUser user = OUser.current(context);
            String[] NameUserAfterCut = null;
            NameUserAfterCut = user.toString().split("\\[");

            // Toast.makeText(view.getContext(), "2:"+nameStudent_after_Cut, Toast.LENGTH_SHORT).show();

            ParentAvis parentAvis = new ParentAvis(context, null);

            List<ODataRow> rowListAvis = parentAvis.select();


          try {
              ResPartner partner = new ResPartner(context, null);
              ODataRow rowsg = partner.select(
                      new String[]{"name"} // to get name of student
              ).get(row.getInt("student_id") - 1);


              OControls.setText(view, R.id._StudentAvis, rowsg.getString("name"));

          }
          catch (Exception e)
          {
              e.printStackTrace();
          }


        }
        // Toast.makeText(view.getContext(), "NameUserAfterCut"+NameUserAfterCut[0], Toast.LENGTH_SHORT).show();
        catch (Exception e) {
            e.printStackTrace();
        }


        OControls.setText(view, R.id._titleAvis, row.getString("name"));
        OControls.setText(view, R.id._dateAvis, row.getString("date"));

      /*  Classes classes = new Classes(context, null);

        List<ODataRow> rows_class = classes.select();
        for(ODataRow row_class: rows_class) {
          Classes classes1 = new Classes(context, null);
            ODataRow rows_c = classes1.select(

                    new String[]{"code"}
            ).get(row.getInt("class_id")-1);
            OControls.setText(view, R.id._classAvis, rows_c.getString("code"));

         //   Toast.makeText(view.getContext(), "id"+row.getString("date"), Toast.LENGTH_SHORT).show();
        }
    }




*/
    }

    private void loadActivity(ODataRow row) {
        Bundle data = new Bundle();
        if (row != null) {
            data = row.getPrimaryBundleData();
        }

        ResPartner partner = new ResPartner(context, null);
        ODataRow rows = partner.select(
                new String[]{"name"}
        ).get(row.getInt("student_id")-1);

        data.putString("student_name",rows.getString("name"));

        Classes classes = new Classes(context, null);
        List<ODataRow> rows_class = classes.select();
        for (ODataRow row_class : rows_class) {
            Classes classes1 = new Classes(context, null);
            ODataRow rows_c = classes1.select(

                    new String[]{"code"}
            ).get(row.getInt("class_id")-1);
            data.putString("ClassStudent",rows_c.getString("code"));

        }


        data.putString("message",row.getString("message"));
        data.putString("Title",row.getString("name"));
        data.putString("date",row.getString("date"));
        //data.putString("student_name",rows.getString("name"));





        IntentUtils.startActivity(getActivity(), DetailsAvisAuParent.class, data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ODataRow row = OCursorUtils.toDatarow((Cursor) mAdapter.getItem(position));

        loadActivity(row);
      //  Toast.makeText(view.getContext(), "id"+position, Toast.LENGTH_SHORT).show();

    }
}
