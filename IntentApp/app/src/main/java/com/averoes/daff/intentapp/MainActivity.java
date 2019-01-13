package com.averoes.daff.intentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button move;
    Button move2;
    Button callMe;
    Button home;
    Button choose;
    TextView result;


    private int REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        move = findViewById(R.id.move);
        move.setOnClickListener(this);

        move2 = findViewById(R.id.move2);
        move2.setOnClickListener(this);

        callMe = findViewById(R.id.callMe);
        callMe.setOnClickListener(this);

        home =findViewById(R.id.home);
        home.setOnClickListener(this);

        choose = findViewById(R.id.choose);
        choose.setOnClickListener(this);

        result = findViewById(R.id.result);


    }

    public void onClick(View v){

        switch (v.getId()){

            case R.id.move:

                Intent moveIntentData = new Intent(MainActivity.this, Main1Activity.class);
                moveIntentData.putExtra(Main1Activity.EXTRA_NAME, "Daff");//Key yang dikirimkan melalui putExtra() harus sama dengan key sewaktu mengambil nilai dari data yang dikirimkan melalui getStringExtra().
                moveIntentData.putExtra(Main1Activity.EXTRA_AGE, 17);
                startActivity(moveIntentData);
                break;

            case R.id.move2:

                Person person = new Person();
                person.setName("Daff");
                person.setAge(17);
                person.setEmail("averoesdaff@gmail.com");
                person.setCity("Bekasi");
                Intent moveWithObjectIntent = new Intent(MainActivity.this, Main2Activity.class);
                moveWithObjectIntent.putExtra(Main2Activity.EXTRA_PERSON, person);
                startActivity(moveWithObjectIntent);
                break;


            case R.id.callMe:
                String phoneNumber = "089606242545";
                Intent callPhoneNumber = new Intent(Intent.ACTION_DIAL, Uri.parse("tel : " + phoneNumber));
                startActivity(callPhoneNumber);
                break;

                /* menentukan intent filter dari aplikasi-aplikasi yang bisa menangani action tersebut. Di sini aplikasi yang memiliki kemampuan untuk komunikasi akan muncul pada opsi pilihan*/
                //Uri adalah sebuah untaian karakter yang digunakan untuk mengidentifikasi nama, sumber, atau layanan di internet
                /*
                “tel” adalah sebuah skema yang disepakati untuk sumber daya telepon dan phoneNumber adalah variabel string yang bernilai “081210841382”.
                Skema lain dari Uri seperti “geo” untuk peta, “http” untuk browser
                 */
            case R.id.home:
                String city = "Bekasi";
                Intent myhome = new Intent(Intent.ACTION_TIMEZONE_CHANGED,Uri.parse("time:" + city));
                startActivity(myhome);
                break;

            case R.id.choose:

                Intent foodMenu = new Intent(MainActivity.this, Main3Activity.class);
                startActivityForResult(foodMenu, REQUEST_CODE);
                break;
                }
        }

        public void onActivityResult(int requestcode, int resultcode, Intent data){

        super.onActivityResult(requestcode,resultcode,data);

        if (requestcode == REQUEST_CODE){

            if (resultcode == Main3Activity.RESULT_CODE){

                int selectedvalue = data.getIntExtra(Main3Activity.EXTRA_SELECTED_VALUE, 0);
                result.setText(String.format("Your Order: %s",selectedvalue));
            }
        }
        }
    }

//Kita memanfaatkan metode putExtra() untuk mengirimkan data bersamaan dengan obyek Intent. Sedangkan metode putExtra() itu sendiri merupakan metode yang menampung pasangan key-value
