package com.example.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    String main="";
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear= (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(main != " ")
                {
                    int length = main.length();
                    main = main.substring(0, length - 1);
                    display(main);
                    displayanswer(" ");
                }
            }
        });


        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                main="0";
                display(main);
                main="";
                displayanswer(" ");
                return true;
            }
        });
    }

    public void zero(View View)
    {
        main=main+"0";
        display(main);
    }
    public void one(View View)
    {
        main=main+"1";
        display(main);
    }
    public void two(View View)
    {
        main=main+"2";
        display(main);
    }
    public void three(View View)
    {
        main=main+"3";
        display(main);
    }
    public void four(View View)
    {
        main=main+"4";
        display(main);
    }
    public void five(View View)
    {
        main=main+"5";
        display(main);
    }
    public void six(View View)
    {
        main=main+"6";
        display(main);
    }
    public void seven(View View)
    {
        main=main+"7";
        display(main);
    }
    public void eight(View View)
    {
        main=main+"8";
        display(main);
    }
    public void nine(View View)
    {
        main=main+"9";
        display(main);
    }
    public void divide(View View)
    {
        main=main+"/";
        display(main);
    }
    public void multiply(View View)
    {
        main=main+"*";
        display(main);
    }
    public void add(View View)
    {
        main=main+"+";
        display(main);
    }
    public void subtract(View View)
    {
        main=main+"-";
        display(main);
    }
    public void dot(View View)
    {
        main = main + ".";
        display(main);
    }
    private int priority(char a)
    {
        if(a=='*' || a=='/')
        {
            return 3;
        }
        else if((a=='+') || (a=='-'))
        {
            return 2;
        }
        else {
            return 1;
        }

    }


    public void equalTo(View View)
    {
        main="(" + main + ")";
        int mainLength=main.length();
        String[] arr;     //  To store the postfix expression
        arr = new String[39];  // Its size is 39 , so that the user can evaluate maximum of 20 operands
        int a=0;    // a is the current position in the array , in which the postfix expression is being stored
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<mainLength;i++)
        {
            if((int) main.charAt(i)>=48 && (int) main.charAt(i)<=57){
                 while(((int) main.charAt(i)>=48) && ((int) main.charAt(i)<=57) && i<mainLength || main.charAt(i)=='.')
                 {
                     arr[a] += main.charAt(i);
                     i++;
                 }
                 arr[a] = arr[a].replace("null","");
                 a++;
                 i--;

            }
            else if(main.charAt(i) == '(')
            {
                stack.push('(');
            }
            else if(main.charAt(i)==')')
            {
                while(stack.peek()!='(')
                {
                    arr[a]= Character.toString(stack.pop());
                    a++;
                }

            }
            else{                               // If an operator is found
                if(priority(main.charAt(i))<=priority(stack.peek()))    // priority is a function , which returns the operator precedence.
                {
                    while(priority(main.charAt(i))<=priority(stack.peek())) // The operator will pop till it has less than or equal precedence than the previous operator.
                    {
                        arr[a] = Character.toString(stack.pop());
                        a++;
                    }
                    stack.push(main.charAt(i));
                }
                else{
                    stack.push(main.charAt(i));
                }


            }
        }

        Stack<String> stack_result = new Stack<>();
        for(int i=0;i<a;i++) {
            if ((int) arr[i].charAt(0) >= 48 && (int) arr[i].charAt(0) <= 57)
            {
                stack_result.push(arr[i]);
            }
            else{
                float y= Float.valueOf(stack_result.pop());
                float z= Float.valueOf(stack_result.pop());
                if(arr[i].equals("/"))
                {
                    stack_result.push(Float.toString(z/y));
                }
                else if(arr[i].equals("*"))
                {
                    stack_result.push(Float.toString(z*y));
                }
                else if(arr[i].equals("+"))
                {
                    stack_result.push(Float.toString(z+y));
                }
                else if(arr[i].equals("-"))
                {
                    stack_result.push(Float.toString(z-y));
                }

            }
        }
        displayanswer(stack_result.pop());

    }

    private void display(String message) {
        TextView displaytext = (TextView) findViewById(R.id.display);
        displaytext.setText(message);
    }
    private void displayanswer(String message) {
        TextView displaytext = (TextView) findViewById(R.id.displayanswer);
        displaytext.setText(message);
    }
}

