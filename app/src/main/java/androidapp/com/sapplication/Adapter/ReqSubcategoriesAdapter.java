package androidapp.com.sapplication.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidapp.com.sapplication.Activity.RequestSubcategories;
import androidapp.com.sapplication.Pojo.CategoryList;
import androidapp.com.sapplication.Pojo.SubCategoryList;
import androidapp.com.sapplication.R;

/**
 * Created by Amaresh on 11/19/17.
 */

public class ReqSubcategoriesAdapter extends BaseAdapter {
    Context _context;
    ArrayList<SubCategoryList> new_list;
    Holder holder,holder1;
    Dialog dialog;
    String user_id;
    public ReqSubcategoriesAdapter(RequestSubcategories requestSubcategories, ArrayList<SubCategoryList> scList) {
        this._context=requestSubcategories;
        this.new_list=scList;
    }

    @Override
    public int getCount() {
        return new_list.size();
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

        final SubCategoryList _pos=new_list.get(position);
        holder=new Holder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) _context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.catsub_list, parent, false);
            holder.cat_name=(TextView)convertView.findViewById(R.id.c_name);
            holder.chkbox=(CheckBox) convertView.findViewById(R.id.flatChk);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder)convertView.getTag();
        }
        holder.cat_name.setTag(position);
        holder.chkbox.setTag(position);
        holder.cat_name.setText(_pos.getSubcategory());
        holder.chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();
                new_list.get(getPosition).setSelected(buttonView.isChecked());
              /* if(context instanceof SelectPreferedLocationReg) {
                   ((SelectPreferedLocationReg) context).onItemClickOfListView(getPosition, buttonView.isChecked());
               }*/
            }
        });

        holder.chkbox.setChecked(new_list.get(position).isSelected());

        return convertView;
    }
}
