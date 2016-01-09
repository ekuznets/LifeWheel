package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenya on 21.11.2015.
 */
/**
 * Decorates the List class to support
 * specialized CJSFProgram methods.
 */

public class categoryItemList
{

    private List<categoryItem> itemList;

    public categoryItemList() {
        this.itemList = new ArrayList<categoryItem>();
    }

    public void add (categoryItem p)
    {
        itemList.add(p);
    }

    public categoryItem getObjectAtIndex (int i)	{
        return itemList.get(i);
    }

    public List<categoryItem> getList ()
    {
        return itemList;
    }

    public int indexOf(categoryItem program) {
        for(categoryItem item : itemList)
        {
            if(item.getCategoryName().equals(program.getCategoryName())) {
                return itemList.indexOf(item);
            }
        }
        return -1;
    }

    public Object get(int index) {
        if (!itemList.isEmpty()) {
            return itemList.get(index);
        } else {
            return null;
        }
    }

    public int size() {
        return itemList.size();
    }
}
