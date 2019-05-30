package com.yujongu.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button unitBtn, backspaceBtn, clearBtn, caretBtn,
            percentBtn, divideBtn, multiplyBtn, subtractBtn,
            addBtn, equalsBtn, pointBtn, invertBtn;

    Button zeroBtn, oneBtn, twoBtn, threeBtn,
            fourBtn, fiveBtn, sixBtn, sevenBtn,
            eightBtn, nineBtn;

    TextView TvEquation, TvAnswer;

    private boolean hasDecimal = false;
    final String[] operands = {"+", "-", "*", "/", "^", "%"};


    public boolean endsWithOperand(String str){
        if (str.endsWith("+") || str.endsWith("-")
                || str.endsWith("*") || str.endsWith("/")
                || str.endsWith("^") || str.endsWith("%")){
            return true;
        }
        return false;
    }

    public boolean endsWithOperZero(String str){
        if (str.endsWith("+0") || str.endsWith("-0")
                || str.endsWith("*0") || str.endsWith("/0")
                || str.endsWith("^0") || str.endsWith("%0")){
            return true;
        }
        return false;
    }

    public int getMaxOperInd(String str){
        int max = 0;
        for (int i = 0; i < operands.length; i++){
            if (str.lastIndexOf(operands[i]) > max){
                max = str.lastIndexOf(operands[i]);
            }
        }
        return max;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitBtn = findViewById(R.id.unitConvBtn);

        unitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UnitActivity.class);
                startActivity(intent);
            }
        });

        backspaceBtn = findViewById(R.id.backSpace);
        clearBtn = findViewById(R.id.clear);
        caretBtn = findViewById(R.id.caret);
        percentBtn = findViewById(R.id.percent);
        divideBtn = findViewById(R.id.divide);
        multiplyBtn = findViewById(R.id.multiply);
        subtractBtn = findViewById(R.id.subtract);
        addBtn = findViewById(R.id.add);
        equalsBtn = findViewById(R.id.equals);
        pointBtn = findViewById(R.id.point);
        invertBtn = findViewById(R.id.invert);

        zeroBtn = findViewById(R.id.zero);
        oneBtn = findViewById(R.id.one);
        twoBtn = findViewById(R.id.two);
        threeBtn = findViewById(R.id.three);
        fourBtn = findViewById(R.id.four);
        fiveBtn = findViewById(R.id.five);
        sixBtn = findViewById(R.id.six);
        sevenBtn = findViewById(R.id.seven);
        eightBtn = findViewById(R.id.eight);
        nineBtn = findViewById(R.id.nine);

        TvEquation = findViewById(R.id.equationTv);
        TvAnswer = findViewById(R.id.answerTv);


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TvEquation.setText("");
                hasDecimal = false;
            }
        });
        backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.length() != 0){
                    if (eq.substring(eq.length() - 1).equals(".")){
                        hasDecimal = false;
                    } else if (endsWithOperand(eq) && eq.lastIndexOf(".") > getMaxOperInd(eq.substring(0, eq.length() - 1))){
                        hasDecimal = true;
                    }
                    TvEquation.setText(eq.substring(0, eq.length() - 1));
                }
            }
        });

        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("")){
                    TvEquation.setText("0");
                } else if (eq.equals("0")){
                    //do Nothing
                } else if (endsWithOperZero(eq)){
                    //do Nothing
                } else {
                    TvEquation.setText(eq + "0");
                }
            }
        });

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("1");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "1");
                } else {
                    TvEquation.setText(eq + "1");
                }
            }
        });
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("2");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "2");
                } else {
                    TvEquation.setText(eq + "2");
                }
            }
        });
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("3");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "3");
                } else {
                    TvEquation.setText(eq + "3");
                }
            }
        });
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("4");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "4");
                } else {
                    TvEquation.setText(eq + "4");
                }
            }
        });
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("5");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "5");
                } else {
                    TvEquation.setText(eq + "5");
                }
            }
        });
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("6");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "6");
                } else {
                    TvEquation.setText(eq + "6");
                }
            }
        });
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("7");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "7");
                } else {
                    TvEquation.setText(eq + "7");
                }
            }
        });
        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("8");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "8");
                } else {
                    TvEquation.setText(eq + "8");
                }
            }
        });
        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("0")){
                    TvEquation.setText("9");
                } else if (endsWithOperZero(eq)){
                    TvEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "9");
                } else {
                    TvEquation.setText(eq + "9");
                }
            }
        });


        pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (eq.equals("") || endsWithOperand(eq)){
                    TvEquation.setText(eq + "0.");
                    hasDecimal = true;
                } else if (hasDecimal == false){
                    TvEquation.setText(eq + ".");
                    hasDecimal = true;
                }
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    TvEquation.setText(eq + "+");
                    hasDecimal = false;
                }
            }
        });
        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    TvEquation.setText(eq + "-");
                    hasDecimal = false;
                }
            }
        });
        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    TvEquation.setText(eq + "*");
                    hasDecimal = false;
                }
            }
        });
        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    TvEquation.setText(eq + "/");
                    hasDecimal = false;
                }
            }
        });
        caretBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    TvEquation.setText(eq + "^");
                    hasDecimal = false;
                }
            }
        });
        percentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = TvEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    TvEquation.setText(eq + "%");
                    hasDecimal = false;
                }
            }
        });

        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stack<Integer> operand = new Stack();
                Stack<Integer> operator = new Stack();

                String eq = TvEquation.getText().toString();

            }
        });


    }
}
