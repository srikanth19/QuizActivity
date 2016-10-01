package com.example.user.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_TRUE ="com.example.user.quizactivity.answer_true";
    private TextView mResultTextView;
    private int mCrctsCol[]=new int[]{0,0,0};

    public static Intent resultintent(Context mpackage,int crcts[]){
        Intent in=new Intent(mpackage,ResultActivity.class);
        in.putExtra(EXTRA_ANSWER_TRUE,crcts);
     return in;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mCrctsCol=getIntent().getIntArrayExtra(EXTRA_ANSWER_TRUE);
        mResultTextView=(TextView) findViewById(R.id.result_text);
        mResultTextView.setText(""+mCrctsCol[1]);
    }
}
