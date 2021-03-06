package com.droidplanner.checklist.row;

import com.droidplanner.R;
import com.droidplanner.checklist.CheckListItem;
import com.droidplanner.drone.Drone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;

public class ListRow_Switch extends ListRow implements OnCheckedChangeListener {

	public ListRow_Switch(Drone drone, LayoutInflater inflater,
			CheckListItem checkListItem) {
		super(drone,inflater, checkListItem);
	}

	public View getView(View convertView) {
		View view;
		if (convertView == null) {
			ViewGroup viewGroup = (ViewGroup) inflater.inflate(
					R.layout.list_switch_item, null);
			holder = new ViewHolder(viewGroup, checkListItem);

			viewGroup.setTag(holder);
			view = viewGroup;
		} else {
			view = convertView;
			holder = (ViewHolder) convertView.getTag();
		}

		// TODO - Add spinner items
		updateDisplay(view, (ViewHolder)holder, checkListItem);
		return view;
	}

	private void updateDisplay(View view, ViewHolder holder,
			CheckListItem mListItem) {
		boolean failMandatory = false;
		
		getDroneVariable(this.drone, mListItem);
		failMandatory = !checkListItem.isSys_activated();

		holder.switchView.setOnCheckedChangeListener(this);
		holder.switchView.setClickable(checkListItem.isEditable());
		holder.switchView.setChecked(mListItem.isSys_activated());
		
		updateCheckBox(checkListItem.isMandatory() && !failMandatory);
	}

	public int getViewType() {
		return ListRow_Type.SWITCH_ROW.ordinal();
	}

	private static class ViewHolder extends BaseViewHolder {
		private Switch switchView;

		private ViewHolder(ViewGroup viewGroup, CheckListItem checkListItem) {
			super(viewGroup, checkListItem);
		}

		@Override
		protected void setupViewItems(ViewGroup viewGroup,
				CheckListItem checkListItem) {
			this.layoutView = (LinearLayout) viewGroup
					.findViewById(R.id.lst_layout);
			this.switchView = (Switch) viewGroup.findViewById(R.id.lst_switch);
			this.checkBox = (CheckBox) viewGroup.findViewById(R.id.lst_check);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		this.checkListItem.setSys_activated(arg1);
		updateRowChanged((View)arg0,this.checkListItem);

	}
}
