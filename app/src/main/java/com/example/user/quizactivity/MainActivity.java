package com.example.user.quizactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX="index";
    private static final String KEY_ARRAY="index_array";
    private static final String TAG="QuizActivity";
    private static final String BOOLEAN_CHEATRESULT="cheatresult";
    private static final int REQUEST_CODE_CHEAT=0;
    private int mCrctAns[]=new int[]{0,0,0};
    private Button mtruebutton;
    private Button mfalsebutton;
    private TextView qustnview;
    private Button nxt;
    private Button mCheatButton;
    private boolean[] mIsCheater=new boolean[]{false,false,false,false,false};
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;
    private void updatequstn()
    {
        final int question=mQuestionBank[mCurrentIndex].getTextResId();
        qustnview.setText(question);
    }
    private void checkanser(boolean userpressed)
    {
        boolean answeristrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int msgresid;
        if(mIsCheater[mCurrentIndex])
        {
         msgresid=R.string.judgment_toast;
            mCrctAns[0]++;
        }else {
            if (userpressed == answeristrue) {
                msgresid = R.string.crrct_txt;
                mCrctAns[1]++;
            } else {
                msgresid = R.string.wrng_txt;
                mCrctAns[2]++;
            }
        }
        Toast.makeText(this,msgresid,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qustnview=(TextView)findViewById(R.id.txt);

        mtruebutton=(Button) findViewById(R.id.trbttn);
        mtruebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                checkanser(true);

            }
        });
        mfalsebutton=(Button) findViewById(R.id.flsbttn);
        mfalsebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                checkanser(false);
            }
        });
        nxt=(Button) findViewById(R.id.nxt);
        nxt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex<4) {
                    mCurrentIndex = (mCurrentIndex + 1);
                    updatequstn();
                }
                else{
                    Intent inm=ResultActivity.resultintent(MainActivity.this,mCrctAns);
                    startActivity(inm);
                }
            }
        });
        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i=CheatActivity.newIntent(MainActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });

        if (savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            mIsCheater[mCurrentIndex]=savedInstanceState.getBoolean(BOOLEAN_CHEATRESULT,false);
            mCrctAns=savedInstanceState.getIntArray(KEY_ARRAY);
        }
        updatequstn();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstancestate");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        savedInstanceState.putBoolean(BOOLEAN_CHEATRESULT,mIsCheater[mCurrentIndex]);
        savedInstanceState.putIntArray(KEY_ARRAY,mCrctAns);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==REQUEST_CODE_CHEAT){
            if(data==null){
                return;
            }
            mIsCheater[mCurrentIndex]=CheatActivity.wasAnswerShown(data);
        }
    }
}
