package androidapp.com.sapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidapp.com.sapplication.Activity.Subcategories;
import androidapp.com.sapplication.Pojo.SubCategoryList;
import androidapp.com.sapplication.R;
import androidapp.com.sapplication.Utils.Constants;

/**
 * Created by Amaresh on 11/11/17.
 */

public class SubcategoriesAdapter extends BaseAdapter {
    Context _context;
    ArrayList<SubCategoryList> new_list;
    Holder holder,holder1;
    public SubcategoriesAdapter(Subcategories subcategories, ArrayList<SubCategoryList> scList) {
        this._context=subcategories;
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
        TextView tv_category,tv_subcategory,tv_price;
        ImageView im_edit;
        Button bt_add;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SubCategoryList _pos=new_list.get(position);
        holder=new Holder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) _context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.sub_cat_list, parent, false);
            holder.tv_category=(TextView)convertView.findViewById(R.id.tv_catname);
            holder.tv_subcategory=(TextView)convertView.findViewById(R.id.tv_subcatname);
            holder.tv_price=(TextView)convertView.findViewById(R.id.tv_price);
            holder.im_edit=(ImageView) convertView.findViewById(R.id.iv_edit);
            holder.bt_add=(Button) convertView.findViewById(R.id.bt_add);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }
        holder.tv_category.setTag(position);
        holder.tv_subcategory.setTag(position);
        holder.tv_price.setTag(position);
        holder.im_edit.setTag(holder);
        holder.bt_add.setTag(holder);

        holder.tv_subcategory.setText(_pos.getSubcategory());
        holder.tv_price.setText("$ "+_pos.getPrice());
        holder.bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder1= (Holder) v.getTag();
                if(holder.tv_price.getText().toString().trim().contentEquals("$ _ _")){
                    Constants.noInternetDialouge(_context,"You have not set the price !");
                }
                else{

                }
            }
        });

        return convertView;
    }
}
