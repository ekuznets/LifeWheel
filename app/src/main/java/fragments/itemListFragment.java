package fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whell.evgeny.lifewhell2.R;

import java.util.List;

import utils.MyDBHandler;
import utils.categoryItem;
import utils.categoryItemArrayAdapter;
import utils.categoryItemList;

/**
 * Created by Jenya on 20.11.2015.
 */
public class itemListFragment extends ListFragment {

    private categoryItemArrayAdapter categoryItemArrayAdapter;
    private MyDBHandler db;

    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.itemlistfragment, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        db = new MyDBHandler(this.getContext(), null, null, 1);
        categoryItemList ArrayItemList = new categoryItemList();
        ArrayItemList=db.findAllItems();

        List<categoryItem> ArrayListCategoryItem = ArrayItemList.getList();
        categoryItemArrayAdapter = new categoryItemArrayAdapter(getActivity(), R.layout.categoryitem_unit, ArrayListCategoryItem);
        setListAdapter(categoryItemArrayAdapter);

        ListView lv = getListView();
        lv.setAdapter(categoryItemArrayAdapter);

    }
}