package com.example.user.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE ="com.example.user.quizactivity.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN ="com.example.user.quizactivity.answer_shown";
    private static final String KEY_BOOLEAN="index_boolean";
    private static final String KEY_FLAG="index_flag";
    private static final String TAG_C="CheatActivity";
    private  boolean mAnswerIsTrue;
    private  boolean mAnswerIsTrueIns;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private int flag;
    public static Intent newIntent(Context packageContext,boolean answerIsTrue)
    {
        Intent i=new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return i;
    }
    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView=(TextView) findViewById(R.id.answer_text_view);
        mShowAnswer=(Button)findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               answerShower();
                setAnswerShownResult(true);
                flag=1;
            }
        });
        if(savedInstanceState!=null)
        {
            mAnswerIsTrueIns=savedInstanceState.getBoolean(KEY_BOOLEAN);
            flag=savedInstanceState.getInt(KEY_FLAG);
        }
        if(flag==1){
           answerShower();
            intentcreater();
        }
    }
    private void setAnswerShownResult(boolean isAnswerShow){
        mAnswerIsTrueIns=isAnswerShow;
        intentcreater();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG_C, "onSaveInstancestate");
        savedInstanceState.putInt(KEY_FLAG,flag);
        savedInstanceState.putBoolean(KEY_BOOLEAN, mAnswerIsTrueIns);
    }
    private void answerShower(){
        if(mAnswerIsTrue){
            mAnswerTextView.setText(R.string.truebutton);
        }
        else{
            mAnswerTextView.setText(R.string.falsebutton);
        }
    }
    private void intentcreater(){
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,mAnswerIsTrueIns);
        setResult(RESULT_OK, data);
    }
}
