package com.example.user.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_TRUE ="com.example.user.quizactivity.answer_true";
    private static final String EXTRA_RESULT ="com.example.user.quizactivity.result";
    private TextView mResultTextView;
    private int mCrctsCol[]=new int[]{0,0,0};
    private String mResultCol[]=new String[]{};
    private TextView mResultshower;
    private String str="";

    public static Intent resultintent(Context mpackage,int crcts[],String results[]){
        Intent in=new Intent(mpackage,ResultActivity.class);
        in.putExtra(EXTRA_ANSWER_TRUE,crcts);
        in.putExtra(EXTRA_RESULT,results);
     return in;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mCrctsCol=getIntent().getIntArrayExtra(EXTRA_ANSWER_TRUE);
        mResultCol=getIntent().getStringArrayExtra(EXTRA_RESULT);
        mResultTextView=(TextView) findViewById(R.id.result_text);
        mResultTextView.setText(""+mCrctsCol[1]);
        mResultshower=(TextView)findViewById(R.id.reslut_shower);
        for(int i=0;i<5;i++){
            int j=i+1;
            str=str+j+'.'+mResultCol[i]+'\n';
        }
        mResultshower.setText(str);
    }
}
