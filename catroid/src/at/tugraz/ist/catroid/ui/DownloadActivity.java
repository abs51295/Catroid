/**
 *  Catroid: An on-device graphical programming language for Android devices
 *  Copyright (C) 2010  Catroid development team 
 *  (<http://code.google.com/p/catroid/wiki/Credits>)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package at.tugraz.ist.catroid.ui;

import java.net.URLDecoder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import at.tugraz.ist.catroid.R;
import at.tugraz.ist.catroid.common.Consts;
import at.tugraz.ist.catroid.transfers.ProjectDownloadTask;

public class DownloadActivity extends Activity {

	private static final String TAG = "DownloadActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_download);

		String zipUrl = getIntent().getDataString();

		Log.v(TAG, "data: " + zipUrl);
		if (zipUrl == null || zipUrl.length() <= 0) {
			return;
		}

		String projectName = getProjectName(zipUrl);

		new ProjectDownloadTask(this, zipUrl, projectName, Consts.TMP_PATH + "/down" + Consts.CATROID_EXTENTION)
				.execute();

	}

	private String getProjectName(String zipUrl) {
		int projectNameIndex = zipUrl.lastIndexOf(Consts.PROJECTNAME_TAG) + Consts.PROJECTNAME_TAG.length();
		String projectName = zipUrl.substring(projectNameIndex);
		projectName = URLDecoder.decode(projectName);

		return projectName;
	}

}