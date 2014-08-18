package convert.oxbao.ru.cenverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpListAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<String>> mGroups;
    private Context mContext;
    private String[] arrGroup;

    public ExpListAdapter(ArrayList<ArrayList<String>> mGroups, Context mContext,String[] arrGroup) {
        this.mGroups = mGroups;
        this.mContext = mContext;
        this.arrGroup = arrGroup;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    /** @param i group position  */
    public int getChildrenCount(int i) {
        return mGroups.get(i).size();
    }

    @Override
    /** @param i group position  */
    public Object getGroup(int i) {
        return mGroups.get(i);
    }

    @Override
    /** @param i group position
     * @param i2 child position*/
    public Object getChild(int i, int i2) {
        return mGroups.get(i).get(i2);
    }

    @Override
    /** @param i group position  */
    public long getGroupId(int i) {
        return i;
    }
    /** @param i group position
     * @param i2 child position*/
    @Override
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_view, null);
        }
        if (isExpanded){
            // change something if current group is opened
        } else {
            //change something if current group is closed
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);

        textGroup.setText(arrGroup[groupPosition]);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_view, null);
        }

        TextView textChild = (TextView) convertView.findViewById(R.id.textChild);
        textChild.setText(mGroups.get(groupPosition).get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
