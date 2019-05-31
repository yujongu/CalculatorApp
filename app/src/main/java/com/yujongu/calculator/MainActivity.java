package com.yujongu.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button unitBtn, backspaceBtn, clearBtn, caretBtn,
            divideBtn, multiplyBtn, subtractBtn,
            addBtn, equalsBtn, pointBtn;

    Button zeroBtn, oneBtn, twoBtn, threeBtn,
            fourBtn, fiveBtn, sixBtn, sevenBtn,
            eightBtn, nineBtn;

    TextView EtEquation;
    TextView TvAnswer;

    private boolean hasDecimal = false;
    final String[] operands = {"+", "-", "*", "/", "^", "%"};

    public int checkPriority(String operand){
        if (operand.equals(".")){
            return 3;
        } else if (operand.equals("*") || operand.equals("/")
                || operand.equals("^") || operand.equals("%")){
            return 2;
        } else if (operand.equals("+") || operand.equals("-")){
            return 1;
        }
        return 0;
    }

    public boolean isNumber(String str){
        for (int i = 0; i < 9; i++){
            if (str.equals(Integer.toString(i))){
                return true;
            }
        }
        return false;
    }

    public BigDecimal compute(BigDecimal a, BigDecimal b, String oper){
        BigDecimal ans = new BigDecimal(0);
        if (oper.equals("+")){
            ans = a.add(b);
        } else if (oper.equals("-")){
            ans = a.subtract(b);
        } else if (oper.equals("*")){
            ans = a.multiply(b);
        } else if (oper.equals("/")){
            ans = a.divide(b, MathContext.DECIMAL64);
        } else if (oper.equals("^")){
            ans = BigDecimal.valueOf(1);
            for (int i = 0; i < Integer.valueOf(String.valueOf(b)); i++){
                ans = ans.multiply(a);
            }
        }
        return ans;
    }


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
        divideBtn = findViewById(R.id.divide);
        multiplyBtn = findViewById(R.id.multiply);
        subtractBtn = findViewById(R.id.subtract);
        addBtn = findViewById(R.id.add);
        equalsBtn = findViewById(R.id.equals);
        pointBtn = findViewById(R.id.point);

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

        EtEquation = findViewById(R.id.equationEt);
        TvAnswer = findViewById(R.id.answerTv);


        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EtEquation.setText("");
                hasDecimal = false;
                TvAnswer.setText("");
            }
        });
        backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.length() != 0){
                    if (eq.substring(eq.length() - 1).equals(".")){
                        hasDecimal = false;
                    } else if (endsWithOperand(eq) && eq.lastIndexOf(".") > getMaxOperInd(eq.substring(0, eq.length() - 1))){
                        hasDecimal = true;
                    }
                    EtEquation.setText(eq.substring(0, eq.length() - 1));
//                    EtEquation.setSelection(EtEquation.getText().length());
                }
            }
        });

        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("")){
                    EtEquation.append("0");
                } else if (eq.equals("0")){
                    //do Nothing
                } else if (endsWithOperZero(eq)){
                    //do Nothing
                } else {
                    EtEquation.append("0");
                }
            }
        });

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("1");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "1");
                } else {
                    EtEquation.append("1");
                }
            }
        });
        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("2");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "2");
                } else {
                    EtEquation.append("2");
                }
            }
        });
        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("3");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "3");
                } else {
                    EtEquation.append("3");
                }
            }
        });
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("4");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "4");
                } else {
                    EtEquation.append("4");
                }
            }
        });
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("5");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "5");
                } else {
                    EtEquation.append("5");
                }
            }
        });
        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("6");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "6");
                } else {
                    EtEquation.append("6");
                }
            }
        });
        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("7");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "7");
                } else {
                    EtEquation.append("7");
                }
            }
        });
        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("8");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "8");
                } else {
                    EtEquation.append("8");
                }
            }
        });
        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("0")){
                    EtEquation.setText("");
                    EtEquation.append("9");
                } else if (endsWithOperZero(eq)){
                    EtEquation.setText(eq.substring(0, eq.lastIndexOf("0")) + "9");
                } else {
                    EtEquation.append("9");
                }
            }
        });


        pointBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (eq.equals("") || endsWithOperand(eq)){
                    EtEquation.append("0.");
                    hasDecimal = true;
                } else if (hasDecimal == false){
                    EtEquation.append(".");
                    hasDecimal = true;
                }
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    EtEquation.append("+");
                    hasDecimal = false;
                }
            }
        });
        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    EtEquation.append("-");
                    hasDecimal = false;
                }
            }
        });
        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    EtEquation.append("*");
                    hasDecimal = false;
                }
            }
        });
        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    EtEquation.append("/");
                    hasDecimal = false;
                }
            }
        });
        caretBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();
                if (!eq.equals("") && !endsWithOperand(eq)){
                    EtEquation.append("^");
                    hasDecimal = false;
                }
            }
        });

        equalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eq = EtEquation.getText().toString();

                if (endsWithOperand(eq)) {
                    Toast.makeText(MainActivity.this, "The equation is incomplete", Toast.LENGTH_SHORT).show();
                } else {
                    Stack<String> operand = new Stack();
                    Stack<BigDecimal> operator = new Stack();

                    String[] arr = new String[eq.length()];
                    arr = eq.split("");

                    System.out.println("ArrayLength: " + arr.length);

                    if (eq.length() != 0){
                        for (int i = 1; i < arr.length; i++){

                            if (isNumber(arr[i])){
                                BigDecimal num = new BigDecimal(0);
                                while (i < arr.length && isNumber(arr[i])){
                                    num = (num.multiply(BigDecimal.valueOf(10)));
                                    num = num.add(BigDecimal.valueOf(Integer.valueOf(arr[i])));
                                    i++;
                                }
                                i--;
                                operator.push(num);
                                System.out.println("num: " + num);
                            } else if (arr[i].equals(".")){
                                BigDecimal num = operator.peek();
                                int decimalCount = 0;
                                operator.pop();
                                i++;
                                while (i < arr.length && isNumber(arr[i])){
                                    num = num.multiply(BigDecimal.valueOf(10));
                                    num = num.add(BigDecimal.valueOf(Integer.valueOf(arr[i])));
                                    i++;
                                    decimalCount++;
                                }
                                i--;
                                System.out.println("i: " + i);
                                System.out.println("num1: " + num);
                                num = num.divide(BigDecimal.valueOf(Math.pow(10, decimalCount)));
                                operator.push(num);
                                System.out.println("peek: " + operator.peek());
                            }
                            else {
                                while (!operand.isEmpty() && checkPriority(operand.peek()) >= checkPriority(arr[i])){
                                    BigDecimal val2 = operator.peek();
                                    operator.pop();

                                    BigDecimal val1 = operator.peek();
                                    operator.pop();

                                    String oper = operand.peek();
                                    operand.pop();
                                    operator.push(compute(val1, val2, oper));
                                }
                                operand.push(arr[i]);
                            }
                        }
                    }

                    while (!operand.isEmpty()){
                        BigDecimal val2 = operator.peek();
                        operator.pop();

                        BigDecimal val1 = operator.peek();
                        operator.pop();

                        String oper = operand.peek();
                        operand.pop();
                        System.out.println("Computed: " + compute(val1, val2, oper));

                        operator.push(compute(val1, val2, oper));
                    }
                    if (!operator.isEmpty()){
                        TvAnswer.setText(operator.peek().toString());
                    }
                }
            }
        });
    }
}
