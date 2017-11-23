package beautician.com.sapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import beautician.com.sapplication.Activity.SpProposal;
import beautician.com.sapplication.Pojo.CategoryList;
import beautician.com.sapplication.Pojo.Proposals;
import beautician.com.sapplication.R;

/**
 * Created by Amaresh on 11/23/17.
 */

public class PropsalAdapter extends BaseAdapter {
    private Context _context;
    Holder holder;
    private ArrayList<Proposals> new_list;
    public PropsalAdapter(SpProposal spProposal, ArrayList<Proposals> pList) {
        this._context=spProposal;
        this.new_list=pList;
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
    private class Holder{
        TextView propsal_hd,vew_details;
        ImageView im_reply;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Proposals _pos=new_list.get(position);
        holder=new Holder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) _context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.propsal_list, parent, false);
            holder.propsal_hd=(TextView)convertView.findViewById(R.id.propsal_hd);
            holder.vew_details=(TextView)convertView.findViewById(R.id.view_details);
            holder.im_reply=(ImageView)convertView.findViewById(R.id.im_reply);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }
        holder.propsal_hd.setTag(position);
        holder.vew_details.setTag(position);
        holder.im_reply.setTag(position);
        String status=_pos.getStatus();
        if(status.contentEquals("1")){
            Resources ress = _context.getResources();
            Drawable drawable1 = ress.getDrawable(R.mipmap.ic_error_black_24dp);
            drawable1 = DrawableCompat.wrap(drawable1);
            DrawableCompat.setTint(drawable1, _context.getResources().getColor(R.color.deep_background));
            holder.im_reply.setImageDrawable(drawable1);

        }
        holder.propsal_hd.setText(_pos.getRemarks());

        return convertView;
    }
}
