package com.example.tenchrio.flashcardz.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.example.tenchrio.flashcardz.R;
import com.example.tenchrio.flashcardz.animation.AnimationFactory;
import com.example.tenchrio.flashcardz.database.FlashCardDAO;
import com.example.tenchrio.flashcardz.domain.FlashCard;

import java.util.ArrayList;

public class CardActivity extends FragmentActivity {
    private long chapterId;
    private FlashCardDAO myFlashCardDAO;
    private ArrayList<FlashCard> myCards;
    private ArrayList<ViewFlipper> myFlippers; // :D
    private ArrayList<ViewAnimator> myAnimators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent intent = getIntent();
        chapterId = intent.getLongExtra(getString(R.string.extra_chapter_id),-1);

        HorizontalScrollView horiview = this.findViewById(R.id.cardsHoriView);

        myFlashCardDAO = new FlashCardDAO(this);
        myCards = (ArrayList<FlashCard>) myFlashCardDAO.getAllFlashCardsForChapter(chapterId);
        myFlippers = new ArrayList<ViewFlipper>();
        myAnimators = new ArrayList<ViewAnimator>();

        FragmentManager myFragmentManager = getSupportFragmentManager();
        FragmentTransaction myFragmentTransaction;

        LinearLayout lay = horiview.findViewById(R.id.horiLayout);

        for (int i= 0; i < myCards.size(); i++){
            myFlippers.add(new ViewFlipper(this));
            myFlippers.get(i).setId(View.generateViewId());
            myFlippers.get(i).setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            myFlippers.get(i).setBackgroundColor(Color.parseColor("#444444"));
            myFlippers.get(i).isClickable();
            myFlippers.get(i).setPadding(90,90,90,90);
            lay.addView(myFlippers.get(i));


            FrontCardFragment frontCard = FrontCardFragment.newInstance(myCards.get(i).getQuestion());
            BackCardFragment backCard = BackCardFragment.newInstance(myCards.get(i).getAnswer());
            myFragmentTransaction = myFragmentManager.beginTransaction();
            myFragmentTransaction.add(myFlippers.get(i).getId(),frontCard,"FrontFragment");
            myFragmentTransaction.add(myFlippers.get(i).getId(),backCard,"BackFragment");
            myFragmentTransaction.commit();


            myAnimators.add(myFlippers.get(i));
            final ViewAnimator myVA = myAnimators.get(i);
            myAnimators.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AnimationFactory.flipTransition(myVA, AnimationFactory.FlipDirection.LEFT_RIGHT);
                }
            });


        }


    }
}
