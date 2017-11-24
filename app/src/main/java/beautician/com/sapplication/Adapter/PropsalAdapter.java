package beautician.com.sapplication.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import beautician.com.sapplication.Activity.PropsalView;
import beautician.com.sapplication.Activity.SpProposal;
import beautician.com.sapplication.Pojo.CategoryList;
import beautician.com.sapplication.Pojo.Proposals;
import beautician.com.sapplication.R;
import beautician.com.sapplication.Utils.CheckInternet;
import beautician.com.sapplication.Utils.Constants;

/**
 * Created by Amaresh on 11/23/17.
 */

public class PropsalAdapter extends BaseAdapter {
    private Context _context;
    Holder holder,holder1;
    Dialog dialog;
    String from_page;
    private ArrayList<Proposals> new_list;
    public PropsalAdapter(SpProposal spProposal, ArrayList<Proposals> pList,String page) {
        this._context=spProposal;
        this.new_list=pList;
        this.from_page=page;
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
        ImageView im_reply,im_agree;
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
            holder.im_agree=(ImageView)convertView.findViewById(R.id.im_agree);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }
        holder.propsal_hd.setTag(position);
        holder.vew_details.setTag(position);
        holder.im_reply.setTag(position);
        holder.im_agree.setTag(holder);
        String status=_pos.getStatus();

        if(from_page.contentEquals("user_side")){
            holder.im_agree.setVisibility(View.VISIBLE);
            Resources ress = _context.getResources();
            Drawable drawable1 = ress.getDrawable(R.mipmap.ic_done_white_24dp);
            drawable1 = DrawableCompat.wrap(drawable1);
            DrawableCompat.setTint(drawable1, _context.getResources().getColor(R.color.black));
            holder.im_agree.setImageDrawable(drawable1);

            holder.im_reply.setVisibility(View.GONE);
        }
        else{
            holder.im_agree.setVisibility(View.GONE);
            holder.im_reply.setVisibility(View.VISIBLE);
        }
        if(status.contentEquals("1")){
            Resources ress = _context.getResources();
            Drawable drawable1 = ress.getDrawable(R.mipmap.ic_error_black_24dp);
            drawable1 = DrawableCompat.wrap(drawable1);
            DrawableCompat.setTint(drawable1, _context.getResources().getColor(R.color.deep_background));
            holder.im_reply.setImageDrawable(drawable1);
        }
        holder.propsal_hd.setText(_pos.getRemarks());

        holder.vew_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(_context,PropsalView.class);
                intent.putExtra("SID",_pos.getService_request_id());
                intent.putExtra("PID",_pos.getId());
                intent.putExtra("PSTATUS",_pos.getStatus());
                intent.putExtra("PROPSAL",_pos.getRemarks());
                _context.startActivity(intent);

            }
        });
        holder.im_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder1=(Holder) v.getTag();
                AlertDialog.Builder builder = new AlertDialog.Builder(_context);
                builder.setTitle("Are you Comfortable with this proposal");
                builder.setMessage("Do you want to go ahead ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO
                        dialog.dismiss();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TODO
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return convertView;
    }
}
