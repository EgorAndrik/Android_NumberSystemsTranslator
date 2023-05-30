package com.example.android_numbersystemstranslator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // поля ввода систем и числа
    EditText geterNum, geterSystemTranslate, getSystemNum;
    // поле результата
    TextView seterResult;
    //кнопки получения результата и очистки
    Button buttonResult, cleanerEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // подключаем кнопки и поля текстов через id
        geterNum = (EditText) findViewById(R.id.geterNum);
        geterSystemTranslate = (EditText) findViewById(R.id.geterSystemTranslate);
        getSystemNum = (EditText) findViewById(R.id.geterSystemNum);
        seterResult = (TextView) findViewById(R.id.seterResult);
        buttonResult = (Button) findViewById(R.id.buttonResult);
        cleanerEditText = (Button) findViewById(R.id.Cleaner);
        // прослушка для кнопки перевода числа
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TranslatedSystem();
            }
        });
        // прослушка для кнопки очисткм
        cleanerEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cleanPoli();
            }
        });
    }
    // функция перевода числа с проследовательной проверкой коректности ввода
    public void TranslatedSystem(){
        // первая проверка на корректность ввода и заполнения всех полей
        if (Examination.filter_Sys(geterSystemTranslate.getText() + "") &&
                Examination.filter_Sys(getSystemNum.getText() + "") &&
                Examination.filter_Num(geterNum.getText() + "") &&
                Examination.cleanPoli(geterSystemTranslate.getText() + "") &&
                Examination.cleanPoli(getSystemNum.getText() + "") &&
                Examination.cleanPoli(geterNum.getText() + "")) {
            String simvols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //алфавит перевода
            String num = new StringBuffer(geterNum.getText()
                    + "").reverse().toString().toUpperCase();//берём число предварительно перевернув
            int first_sys = Integer.valueOf(getSystemNum.getText() + "");//первое основание
            int second_sys = Integer.valueOf(geterSystemTranslate.getText() + "");//второе основание
            String resultNum = "";//результат
            int n = 0;//переведённое число в десятичную систему
            // проверка систем счисления
            if (second_sys < 37 && second_sys > 1 && first_sys < 37 && first_sys > 1 &&
                    Examination.filter_Sys_stack(geterNum.getText() + "",
                            getSystemNum.getText() + "")) {
                // алгоритм перевода в десятичную систему
                for (int i = 0; i < num.length(); i++)
                    n += simvols.indexOf(num.charAt(i)) * (int) Math.pow(first_sys, i);
                // перевод в нужную нам систему
                while (n != 0) {
                    resultNum = simvols.charAt(n % second_sys) + resultNum;
                    n /= second_sys;
                }
                seterResult.setText(resultNum); // выводим результат на экран
            }
            // классификация ошибки
            else if (first_sys > 37 || first_sys < 1)
                seterResult.setText("Некорректная первая система");
            else if (second_sys > 37 || second_sys < 1)
                seterResult.setText("Некорректная вторая система");
            else if (!Examination.filter_Sys_stack(geterNum.getText() + "",
                    getSystemNum.getText() + ""))
                seterResult.setText("Число и система не совпадают");
            else seterResult.setText("ERROR");
        }
        // классификация ошибки
        else if (!Examination.cleanPoli(geterNum.getText() + ""))
            seterResult.setText("Введите число");
        else if (!Examination.filter_Num(geterNum.getText() + ""))
            seterResult.setText("Некорректно введено число");
        else if (!Examination.cleanPoli(getSystemNum.getText() + ""))
            seterResult.setText("Введите первую систему");
        else if (!Examination.filter_Sys(getSystemNum.getText() + ""))
            seterResult.setText("Некорректно введена первая система");
        else if (!Examination.cleanPoli(geterSystemTranslate.getText() + ""))
            seterResult.setText("Введите вторую систему");
        else if (!Examination.filter_Sys(geterSystemTranslate.getText() + ""))
            seterResult.setText("Некорректно введена вторая система");
        else seterResult.setText("ERROR");
    }
    // функция очистки полей
    public void cleanPoli(){
        geterNum.setText("");
        getSystemNum.setText("");
        geterSystemTranslate.setText("");
        seterResult.setText("");
    }
}