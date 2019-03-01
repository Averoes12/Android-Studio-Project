package com.averoes.daff.listmahasiswa.services;

/**
 * Created by daff on 21/02/19 at 20:56.
 */

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.averoes.daff.listmahasiswa.R;
import com.averoes.daff.listmahasiswa.adapter.MahasiswaAdapter;
import com.averoes.daff.listmahasiswa.database.MahasiswaHelper;
import com.averoes.daff.listmahasiswa.model.MahasiswaModel;
import com.averoes.daff.listmahasiswa.preference.AppReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Digunakan untuk mengelola data mahasiswa kedalam database
 */
public class LoadData extends AsyncTask<Void, Integer, Boolean> {
    private static String TAG = LoadData.class.getSimpleName();

    private MahasiswaHelper helper;
    private AppReference appReference;
    private WeakReference<LoadCallback> weakCallback;
    private WeakReference<Resources> resources;

    double progress;
    double maxProses = 100;


    public LoadData(MahasiswaHelper helper, AppReference appReference, LoadCallback callback, Resources resources) {

        this.helper = helper;
        this.appReference = appReference;
        this.weakCallback = new WeakReference<>(callback);
        this.resources = new WeakReference<>(resources);
    }

    public ArrayList<MahasiswaModel> preLoadRaw() {
        ArrayList<MahasiswaModel> mahasiswaModels = new ArrayList<>();
        String line;
        BufferedReader reader;

        try {

            Resources res = resources.get();
            InputStream raw_dict = res.openRawResource(R.raw.data_mahasiswa);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            do {

                line = reader.readLine();

                String[] split_string = line.split("\t");

                MahasiswaModel mahasiswaModel = new MahasiswaModel(split_string[0], split_string[1]);
                mahasiswaModels.add(mahasiswaModel);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mahasiswaModels;
    }

    /**
     * Persiapan sebelum proses dimulai
     * Berjalan di Main Thread
     */

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "on PreExecute");
        weakCallback.get().onPreLoad();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        weakCallback.get().onProgressUpdate(values[0]);
    }

    /*
Proses background terjadi di method doInBackground
 */

    @Override
    protected Boolean doInBackground(Void... voids) {
// Panggil preference first run

        Boolean firstRun = appReference.getFirstRun();
        /**
         * Jika first run true maka melakukan proses pre load,
         * Jika first run false maka akan langsung menuju home
         */

        if (firstRun) {
             /*
            Load raw data dari file txt ke dalam array model mahasiswa
            */
            ArrayList<MahasiswaModel> list_mahasiswa = preLoadRaw();

            helper.open();

            progress = 30;
            publishProgress((int) progress);
            Double progresMaxInsert = 80.0;
            Double progresDiff = (progresMaxInsert - progress) / list_mahasiswa.size();

            boolean isInsertSucces;

            /*
             * Gunakan kode ini untuk query insert yang transactional
             * Begin Transaction
             */

            try {
                helper.beginTransaction();

                for (MahasiswaModel model : list_mahasiswa) {
//Jika service atau activity dalam keadaan destroy maka akan menghentikan perulangan

                    if (isCancelled()) {
                        break;
                    } else {
                        helper.insertTransaction(model);
                        progress += progresDiff;
                        publishProgress((int) progress);
                    }
                }
//Jika service atau activity dalam keadaan destroy maka data insert tidak di essekusi

                if (isCancelled()) {
                    isInsertSucces = false;
                    appReference.setFirstRun(true);
                    weakCallback.get().onLoadCancel();
                }else {
// Jika semua proses telah di set success maka akan di commit ke database

                    helper.setTransactionSucces();
                    isInsertSucces = true;
 /* Set preference first run ke false
    Agar proses preload tidak dijalankan untuk kedua kalinya
 */
                    appReference.setFirstRun(false);
                }

            } catch (Exception e) {
                Log.e(TAG, "doInBackground: Exception");
                isInsertSucces = false;

            } finally {
                helper.endTransaction();
            }
//tutup helper ketika query sudah selesai
            helper.close();

            publishProgress((int) maxProses);

            return isInsertSucces;
        } else {
            try {
                synchronized (this) {
                    this.wait(2000);

                    publishProgress(30);

                    this.wait(1000);
                    publishProgress(50);

                    this.wait(1000);
                    publishProgress(75);

                    this.wait(1000);
                    publishProgress(98);

                    this.wait(3000);
                    publishProgress((int) maxProses);

                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            weakCallback.get().onLoadSucces();
        } else {
            weakCallback.get().onLoadFailed();
        }
    }
}
