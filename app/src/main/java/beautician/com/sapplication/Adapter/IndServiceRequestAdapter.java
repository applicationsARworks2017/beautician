package beautician.com.sapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import beautician.com.sapplication.Activity.CheckIndividualPost;
import beautician.com.sapplication.Pojo.IndServiceRequest;
import beautician.com.sapplication.Pojo.ServiceRequest;
import beautician.com.sapplication.R;
import beautician.com.sapplication.Utils.Constants;

/**
 * Created by Amaresh on 11/26/17.
 */

public class IndServiceRequestAdapter extends BaseAdapter {
    Context _context;
    ArrayList<IndServiceRequest> new_list;
    Holder holder;
    String user_id;
    public IndServiceRequestAdapter(CheckIndividualPost checkIndividualPost, ArrayList<IndServiceRequest> isrList) {
        this._context=checkIndividualPost;
        this.new_list=isrList;
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
        TextView Name_service,remarks;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final IndServiceRequest _pos=new_list.get(position);
        holder=new Holder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) _context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.serviceind_list, parent, false);
            user_id = _context.getSharedPreferences(Constants.SHAREDPREFERENCE_KEY, 0).getString(Constants.USER_ID, null);
            holder.Name_service=(TextView)convertView.findViewById(R.id.name_service);
            holder.remarks=(TextView)convertView.findViewById(R.id.servicedetails);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }
        holder.Name_service.setTag(position);
        holder.remarks.setTag(position);
        holder.Name_service.setText(_pos.getPersonName()+" has requseted you for the service");
        holder.remarks.setText(_pos.getRemarks());
        return convertView;
    }
}
