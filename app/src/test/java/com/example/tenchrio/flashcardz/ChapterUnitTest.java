package com.example.tenchrio.flashcardz;


import com.example.tenchrio.flashcardz.domain.Chapter;
import com.example.tenchrio.flashcardz.domain.FlashCard;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChapterUnitTest {

    @Test
    public void addingFlashCards(){
        Chapter testing = new Chapter();
        FlashCard testCard = new FlashCard("Who?","The Who");
        FlashCard emptyCard = null;

        assertTrue(testing.addFlashCard(testCard));
        assertFalse(testing.addFlashCard(emptyCard));
    }

}
