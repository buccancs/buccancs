package com.shimmerresearch.tools;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/* loaded from: classes2.dex */
public class FileUtils {
    public static String FALLBACK_COPY_FOLDER = "upload_part";
    private static String TAG = "FileUtils";
    private static Uri contentUri;
    Context context;

    public FileUtils(Context context) {
        this.context = context;
    }

    private static boolean fileExists(String str) {
        return new File(str).exists();
    }

    private static String getPathFromExtSD(String[] strArr) {
        String str = strArr[0];
        String str2 = File.separator + strArr[1];
        Log.d(TAG, "MEDIA EXTSD TYPE: " + str);
        Log.d(TAG, "Relative path: " + str2);
        if ("primary".equalsIgnoreCase(str)) {
            String str3 = Environment.getExternalStorageDirectory() + str2;
            if (fileExists(str3)) {
                return str3;
            }
        }
        if ("home".equalsIgnoreCase(str)) {
            String str4 = "/storage/emulated/0/Documents" + str2;
            if (fileExists(str4)) {
                return str4;
            }
        }
        String str5 = System.getenv("SECONDARY_STORAGE") + str2;
        if (fileExists(str5)) {
            return str5;
        }
        String str6 = System.getenv("EXTERNAL_STORAGE") + str2;
        if (fileExists(str6)) {
            return str6;
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public String getPath(Uri uri, UriType uriType) throws Throwable {
        String documentId;
        Uri contentUri2 = null;
        Cursor cursor = null;
        if (isExternalStorageDocument(uri)) {
            if (uriType.equals(UriType.FOLDER)) {
                documentId = DocumentsContract.getTreeDocumentId(uri);
            } else {
                documentId = uriType.equals(UriType.FILE) ? DocumentsContract.getDocumentId(uri) : "";
            }
            String[] strArrSplit = documentId.split(":");
            String str = strArrSplit[0];
            String pathFromExtSD = getPathFromExtSD(strArrSplit);
            if (pathFromExtSD == null || !fileExists(pathFromExtSD)) {
                Log.d(TAG, "Copy files as a fallback");
                pathFromExtSD = copyFileToInternalStorage(uri, FALLBACK_COPY_FOLDER);
            }
            if (pathFromExtSD != "") {
                return pathFromExtSD;
            }
            return null;
        }
        if (isDownloadsDocument(uri)) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Cursor cursorQuery = this.context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                String str2 = Environment.getExternalStorageDirectory().toString() + "/Download/" + cursorQuery.getString(0);
                                if (!TextUtils.isEmpty(str2)) {
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    return str2;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    String documentId2 = DocumentsContract.getDocumentId(uri);
                    if (!TextUtils.isEmpty(documentId2)) {
                        if (documentId2.startsWith("raw:")) {
                            return documentId2.replaceFirst("raw:", "");
                        }
                        try {
                            return getDataColumn(this.context, ContentUris.withAppendedId(Uri.parse(new String[]{"content://downloads/public_downloads", "content://downloads/my_downloads"}[0]), Long.valueOf(documentId2).longValue()), null, null);
                        } catch (NumberFormatException unused) {
                            return uri.getPath().replaceFirst("^/document/raw:", "").replaceFirst("^raw:", "");
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                String documentId3 = DocumentsContract.getDocumentId(uri);
                if (documentId3.startsWith("raw:")) {
                    return documentId3.replaceFirst("raw:", "");
                }
                try {
                    contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId3).longValue());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                Uri uri2 = contentUri;
                if (uri2 != null) {
                    return getDataColumn(this.context, uri2, null, null);
                }
            }
        }
        if (isMediaDocument(uri)) {
            String[] strArrSplit2 = DocumentsContract.getDocumentId(uri).split(":");
            String str3 = strArrSplit2[0];
            Log.d(TAG, "MEDIA DOCUMENT TYPE: " + str3);
            if ("image".equals(str3)) {
                contentUri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(str3)) {
                contentUri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(str3)) {
                contentUri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            } else if ("document".equals(str3)) {
                contentUri2 = MediaStore.Files.getContentUri(MediaStore.getVolumeName(uri));
            }
            return getDataColumn(this.context, contentUri2, "_id=?", new String[]{strArrSplit2[1]});
        }
        if (isGoogleDriveUri(uri)) {
            return getDriveFilePath(uri);
        }
        if (isWhatsAppFile(uri)) {
            return getFilePathForWhatsApp(uri);
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            if (isGoogleDriveUri(uri)) {
                return getDriveFilePath(uri);
            }
            if (Build.VERSION.SDK_INT >= 29) {
                return copyFileToInternalStorage(uri, FALLBACK_COPY_FOLDER);
            }
            return getDataColumn(this.context, uri, null, null);
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return copyFileToInternalStorage(uri, FALLBACK_COPY_FOLDER);
    }

    private String getDriveFilePath(Uri uri) throws IOException {
        Cursor cursorQuery = this.context.getContentResolver().query(uri, null, null, null, null);
        int columnIndex = cursorQuery.getColumnIndex("_display_name");
        int columnIndex2 = cursorQuery.getColumnIndex("_size");
        cursorQuery.moveToFirst();
        String string = cursorQuery.getString(columnIndex);
        Long.toString(cursorQuery.getLong(columnIndex2));
        File file = new File(this.context.getCacheDir(), string);
        try {
            InputStream inputStreamOpenInputStream = this.context.getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[Math.min(inputStreamOpenInputStream.available(), 1048576)];
            while (true) {
                int i = inputStreamOpenInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            }
            Log.e(TAG, "Size " + file.length());
            inputStreamOpenInputStream.close();
            fileOutputStream.close();
            Log.e(TAG, "Path " + file.getPath());
            Log.e(TAG, "Size " + file.length());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return file.getPath();
    }

    private String copyFileToInternalStorage(Uri uri, String str) throws IOException {
        File file;
        Cursor cursorQuery = this.context.getContentResolver().query(uri, new String[]{"_display_name", "_size"}, null, null, null);
        int columnIndex = cursorQuery.getColumnIndex("_display_name");
        int columnIndex2 = cursorQuery.getColumnIndex("_size");
        cursorQuery.moveToFirst();
        String string = cursorQuery.getString(columnIndex);
        Long.toString(cursorQuery.getLong(columnIndex2));
        if (!str.equals("")) {
            String string2 = UUID.randomUUID().toString();
            File file2 = new File(this.context.getFilesDir() + File.separator + str + File.separator + string2);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            file = new File(this.context.getFilesDir() + File.separator + str + File.separator + string2 + File.separator + string);
        } else {
            file = new File(this.context.getFilesDir() + File.separator + string);
        }
        try {
            InputStream inputStreamOpenInputStream = this.context.getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpenInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            }
            inputStreamOpenInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return file.getPath();
    }

    private String getFilePathForWhatsApp(Uri uri) {
        return copyFileToInternalStorage(uri, "whatsapp");
    }

    private String getDataColumn(Context context, Uri uri, String str, String[] strArr) throws Throwable {
        Cursor cursor = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public boolean isWhatsAppFile(Uri uri) {
        return "com.whatsapp.provider.media".equals(uri.getAuthority());
    }

    private boolean isGoogleDriveUri(Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority()) || "com.google.android.apps.docs.storage.legacy".equals(uri.getAuthority());
    }

    public enum UriType {
        FILE,
        FOLDER
    }
}
