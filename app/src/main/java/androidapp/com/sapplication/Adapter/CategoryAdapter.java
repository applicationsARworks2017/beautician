package androidapp.com.sapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidapp.com.sapplication.Activity.Categories;
import androidapp.com.sapplication.Pojo.CategoryList;
import androidapp.com.sapplication.R;

/**
 * Created by Amaresh on 11/3/17.
 */

public class CategoryAdapter extends BaseAdapter {
    Context _context;
    ArrayList<CategoryList> categoryLists;
    Holder holder;
    public CategoryAdapter(Categories categories, ArrayList<CategoryList> cList) {
        this._context=categories;
        this.categoryLists=cList;
    }

    @Override
    public int getCount() {
        return categoryLists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Holder{
        TextView cat_name;
        CheckBox chkbox;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CategoryList _pos=categoryLists.get(position);
        holder=new Holder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) _context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.cat_list, parent, false);
            holder.cat_name=(TextView)convertView.findViewById(R.id.c_name);
            holder.chkbox=(CheckBox) convertView.findViewById(R.id.flatChk);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }
        holder.cat_name.setTag(position);
        holder.chkbox.setTag(position);
        holder.cat_name.setText(_pos.getCategory());
        holder.chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();
                categoryLists.get(getPosition).setSelected(buttonView.isChecked());
              /* if(context instanceof SelectPreferedLocationReg) {
                   ((SelectPreferedLocationReg) context).onItemClickOfListView(getPosition, buttonView.isChecked());
               }*/
            }
        });

        holder.chkbox.setChecked(categoryLists.get(position).isSelected());

        return convertView;
    }
}
