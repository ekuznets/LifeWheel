package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.whell.evgeny.lifewhell2.R;

import java.util.ArrayList;

import utils.MyDBHandler;
import utils.categoryItem;
import utils.categoryItemList;

//import com.github.mikephil.charting.utils.Highlight;
//import com.github.mikephil.charting.utils.PercentFormatter;

/**
 * Created by Jenya on 20.11.2015.
 */
public class WhellFragment extends Fragment 
{
        // for fragment
        private View rootView;
        private MyDBHandler db;
        // for chart
        private com.github.mikephil.charting.charts.PieChart mainLayout;
        public PieChart mChart;
        // we're going to display pie chart for smartphones martket shares
        private float[] yData = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        private String[] xData = { "0","1","2", "3", "4", "5", "6","7","8","9"};

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            rootView = inflater.inflate(R.layout.whellfragment, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated (View view, Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            // code to test database
            db = new MyDBHandler(this.getContext(), null, null, 1);

            categoryItemList list = new categoryItemList();
            list=db.findAllItems();
            int counter = 0;

            for(categoryItem item : list.getList()) // go through data list form db and assign value for graph
            {
                yData[counter]=item.getValue();
                xData[counter]=item.getCategoryName();
                counter++;
            }

            // here to manipulate graph

            mainLayout = (com.github.mikephil.charting.charts.PieChart) rootView.findViewById(R.id.chart);
            mChart = new PieChart(this.getContext());
            // add pie chart to main layout
            mainLayout.addView(mChart);
            mainLayout.setBackgroundColor(Color.parseColor("#55656C"));
            // configure pie chart
            mChart.setUsePercentValues(true);
            mChart.setDescription("Chart represents how do you spend your life");
            // enable hole and configure
            mChart.setDrawHoleEnabled(true); // enable hole
            mChart.setHoleColorTransparent(true); // hole transparant
            mChart.setHoleRadius(5);
            mChart.setTransparentCircleRadius(5); // transparant radius
            // enable rotation of the chart by touch
            mChart.setRotationAngle(0);
            mChart.setRotationEnabled(true);
            // add data
            addData();
            // customize legends
            Legend l = mChart.getLegend();
            l.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
            l.setXEntrySpace(7);
            l.setYEntrySpace(5);
        }

        private void addData() {
            ArrayList<Entry> yVals1 = new ArrayList<Entry>();

            for (int i = 0; i < yData.length; i++) {
                if (yData[i] != 0) {
                    yVals1.add(new Entry(yData[i], i));
                }
            }
            ArrayList<String> xVals = new ArrayList<String>();

            for (int i = 0; i < xData.length; i++) {
                if (yData[i] != 0) {
                    xVals.add(xData[i]);
                }
            }
            // create pie data set
            PieDataSet dataSet = new PieDataSet(yVals1, "Your Life Preferences");
            dataSet.setSliceSpace(3);
            dataSet.setSelectionShift(5);

            // add many colors
            ArrayList<Integer> colors = new ArrayList<Integer>();

            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);

            colors.add(ColorTemplate.getHoloBlue());
            dataSet.setColors(colors);

            // instantiate pie data object now
            PieData data = new PieData(xVals, dataSet);
            // data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(14f);
            data.setValueTextColor(Color.GRAY);

            mChart.setData(data);

            // undo all highlights
            mChart.highlightValues(null);

            // update pie chart
            mChart.invalidate();
        }

}

