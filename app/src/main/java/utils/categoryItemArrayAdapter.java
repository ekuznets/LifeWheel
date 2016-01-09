package utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.whell.evgeny.lifewhell2.R;

import java.util.List;

/**
 * Created by Jenya on 21.11.2015.
 */
public class categoryItemArrayAdapter extends ArrayAdapter<categoryItem> {
    private Context context;
    private List<categoryItem> objects;

    // custom array adapter for displaying upcoming views
    public categoryItemArrayAdapter(Context context, int resource, List<categoryItem> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        categoryItem item = objects.get(position);
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.categoryitem_unit, null);

        TextView tvName = (TextView) view.findViewById(R.id.categoryName);
        tvName.setText("Name: " + item.getCategoryName());

        TextView tvValue = (TextView) view.findViewById(R.id.categoryValue);
        tvValue.setText("Percentage: " +  (String.valueOf(item.getValue()) ) + " %");

        TextView tvDescription = (TextView) view.findViewById(R.id.categoryDescription);
        tvDescription.setText("Description: " + item.getDescription());

        TextView tvColor = (TextView) view.findViewById(R.id.categoryFavColor);
        tvColor.setText("Color: " + item.getFavColor());

        //if needed you can add parameters in here for more descriptive views

        return view;
    }

}