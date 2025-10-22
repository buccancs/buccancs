package com.shimmerresearch.android.guiUtilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.shimmerresearch.androidinstrumentdriver.R;

import java.io.File;
import java.util.ArrayList;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public class FileListActivity extends Activity {
    public static final String INTENT_EXTRA_DIR_PATH = "DirectoryPath";
    public static final String INTENT_EXTRA_PROVIDER_AUTHORITY = "FileProviderAuthority";
    protected static final String FILE_TYPE = "text/";
    protected Button cancelButton;
    protected String[] fileNamesArray;
    protected File[] filesArray;
    protected ListView filesListView;
    protected ArrayAdapter<String> mFilesArrayAdapter;
    protected String FILE_PROVIDER_AUTHORITY = "com.shimmerresearch.shimmer.fileprovider";
    protected String DIRECTORY_PATH = Environment.getExternalStorageDirectory().getPath() + "/Shimmer/";
    protected AdapterView.OnItemClickListener mFileClickListener = new AdapterView.OnItemClickListener() { // from class: com.shimmerresearch.android.guiUtilities.FileListActivity.2
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String strSubstringAfterLast = StringUtils.substringAfterLast(FileListActivity.this.fileNamesArray[i], ".");
            Intent intent = new Intent("android.intent.action.VIEW");
            FileListActivity fileListActivity = FileListActivity.this;
            intent.setDataAndType(FileProvider.getUriForFile(fileListActivity, fileListActivity.FILE_PROVIDER_AUTHORITY, FileListActivity.this.filesArray[i]), FileListActivity.FILE_TYPE + strSubstringAfterLast);
            intent.setFlags(1);
            FileListActivity.this.startActivity(Intent.createChooser(intent, "Open File"));
        }
    };

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setupFilesList();
    }

    protected void setupFilesList() {
        retrieveAnyStringExtras();
        setContentView(R.layout.file_list);
        setResult(0);
        this.cancelButton = (Button) findViewById(R.id.buttonCancel);
        File file = new File(this.DIRECTORY_PATH);
        if (!file.exists()) {
            Toast.makeText(this, "Error! Directory does not exist.", 1).show();
            finish();
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        this.filesArray = fileArrListFiles;
        if (fileArrListFiles != null) {
            ArrayList arrayList = new ArrayList();
            for (File file2 : this.filesArray) {
                arrayList.add(file2.getName());
            }
            String[] strArr = new String[arrayList.size()];
            this.fileNamesArray = strArr;
            this.fileNamesArray = (String[]) arrayList.toArray(strArr);
            this.mFilesArrayAdapter = new ArrayAdapter<>(this, R.layout.device_name, this.fileNamesArray);
            ListView listView = (ListView) findViewById(R.id.listViewFiles);
            this.filesListView = listView;
            listView.setAdapter((ListAdapter) this.mFilesArrayAdapter);
            this.filesListView.setOnItemClickListener(this.mFileClickListener);
            this.cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.shimmerresearch.android.guiUtilities.FileListActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FileListActivity.this.finish();
                }
            });
            return;
        }
        Toast.makeText(this, "Error! Could not retrieve files from directory", 1).show();
        finish();
    }

    private void retrieveAnyStringExtras() {
        String stringExtra = getIntent().getStringExtra(INTENT_EXTRA_DIR_PATH);
        String stringExtra2 = getIntent().getStringExtra(INTENT_EXTRA_PROVIDER_AUTHORITY);
        if (stringExtra != null && !stringExtra.equals("")) {
            this.DIRECTORY_PATH = stringExtra;
        }
        if (stringExtra2 == null || stringExtra2.equals("")) {
            return;
        }
        this.FILE_PROVIDER_AUTHORITY = stringExtra2;
    }
}
